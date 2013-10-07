/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.util;

import br.com.thecave.passcontrolserver.PassControlServer;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.db.dao.QueuesManagerDAO;
import br.com.thecave.passcontrolserver.db.dao.ServiceDAO;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientBalconyListeners;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.generic.ChangeActorMessage;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import br.com.thecave.passcontrolserver.messages.queuepopper.QueuePopperChooseNextElement;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherAddQueueElement;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author guilherme
 */
public class QueueElementHandler implements Runnable
{
    public static final int AUTOMATIC_QUEUE_CHOOSER_USERCHECKOUT_ID = -1;
    public static final int QUEUE_ELEMENT_SKIPPED_BALCONY_ID = -1;
    
    /**
     * Indica se a escolha é automática (true) ou manual (false)
     */
    private boolean automaticChooseOn;
    
    /**
     * Indica qual prioridade deve ser atendida na poróxima chamada
     */
    private int nextPriority;
    
    /**
     * Mapa de balcão e os seu respectivos sockets que estão aguardando por clientes
     */
    private final ConcurrentHashMap<BalconyBean, Socket> waitingBalconys;
    
    /**
     * Cliente escolhido através do queue pop
     */
    private BalconyShowClientMessage chosenClient;    
    
    /**
     * Mensagem enviada para os poppers
     */
    QueuePopperChooseNextElement chooseNextElementMessage;
    
    /**
     * Informa se os queue poppers já estão cientes de que devem escolher algum
     */
    boolean wasMessageSent;

    /**
     * Singleton properties
     */
    private static QueueElementHandler instance = null;
    public synchronized static QueueElementHandler getInstance()
    {
        if (instance == null)
            instance = new QueueElementHandler();
        return instance;
    }

    /**
     * Constructor
     */
    private QueueElementHandler() 
    {
        //Carrega do arquivo de configuração
        this.automaticChooseOn = PassControlConfigurationSynchronizer.getInstance().getConfigurationFile().isGerenciamentoAutomatico();
        this.nextPriority = 3; //Alta
        waitingBalconys = new ConcurrentHashMap<>(); //
    }
    
    public void initialize()
    {
        PassControlServer.getInstance().getServer().addMessageListener(new ChangeActorMessageQueuePopListener(), ChangeActorMessage.class);
        PassControlServer.getInstance().getServer().addMessageListener(new QueuePopChosenListener(), BalconyShowClientMessage.class);
        PassControlServer.getInstance().getServer().addMessageListener(new QueuePusherAddQueueElementListener(), QueuePusherAddQueueElement.class);        
    }
    
    private void updateNextPriority(int currentPriority)
    {
        switch (currentPriority)
        {
            case 3: //Se a prioridade atual for Alta
                nextPriority = 2; //A próxima prioridade é Média
                break;
            case 2: //Se a prioridade atual for Média
                nextPriority = 1; //A próxima prioridade é Baixa
                break;
            default: //Se for qualquer outro valor
                nextPriority = 3; //A próxima prioridade é Alta
                break;
        }
    }
    
    private QueuesManagerBean prepareSelectedQueuesManageElement(BalconyBean balconyBean, QueuesManagerBean selectedManagerBean)
    {
        //Seto o Id do guichê
        selectedManagerBean.setIdBalcony(balconyBean.getId());
        //salvo as alterações no banco.
        QueuesManagerDAO.update(selectedManagerBean);
        
        //Atualizo a próxima prioridade que eu devo escolher
        updateNextPriority(ServiceDAO.selectFromId(selectedManagerBean.getIdService()).getPriority());
        
        //Retorna o bean atualizado
        return selectedManagerBean;
    }
    
    private QueuesManagerBean automaticChoose(HashMap<Integer, ArrayList<QueuesManagerBean>> avaliableClients, BalconyBean balconyBean)
    {
        QueuesManagerBean automaticChosenClient = null;
        //Se existir alguém com prioridade máxima
        if (!avaliableClients.get(4).isEmpty())
        {
            //Pego o primeiro
            automaticChosenClient = avaliableClients.get(4).get(0);
        }
        else
        {
            //Tento para as prioridades Alta, média e baixa (A partir da última escolha...)
            for (int i = 0; i < 3; ++i)
            {
                //Se existir algum com essa prioridade
                if (!avaliableClients.get(nextPriority).isEmpty())
                {
                    automaticChosenClient = avaliableClients.get(nextPriority).get(0);
                    break;
                }
                else //Altero a prioridade que eu quero
                    updateNextPriority(nextPriority);
            }
            //Se existe alguém com a prioridade mínima
            if (automaticChosenClient != null && !avaliableClients.get(0).isEmpty())
            {
                automaticChosenClient = avaliableClients.get(0).get(0);
            }
        }
        
        if (automaticChosenClient == null)
            return null;
        
        automaticChosenClient.setIdUserCheckout(AUTOMATIC_QUEUE_CHOOSER_USERCHECKOUT_ID);
        return prepareSelectedQueuesManageElement(balconyBean, automaticChosenClient);
    }
    
    /**
     * Retorna null se nenhum popper escolher
     * @param balconyBean
     * @param avaliableClients
     * @return 
     */
    private QueuesManagerBean manualChoose(BalconyBean balconyBean, HashMap<Integer, ArrayList<QueuesManagerBean>> avaliableClients)
    {
        //Só precisa enviar caso não tenha sido enviada ainda
        if (!wasMessageSent)
        {
            //Zero o cliente anterior
            chosenClient = null;
            chooseNextElementMessage = new QueuePopperChooseNextElement(balconyBean, avaliableClients);

            PassControlServer.getInstance().getServer().addBroadcastToSend(chooseNextElementMessage);
        }
        
        wasMessageSent = true;
        if (chosenClient != null)
            return prepareSelectedQueuesManageElement(balconyBean, chosenClient.getQueuesManagerBean());
        return null;
    }    
    
    public boolean isAutomaticChooseOn() 
    {
        return automaticChooseOn;
    }

    public void setAutomaticChooseOn(boolean automaticChooseOn) 
    {
        this.automaticChooseOn = automaticChooseOn;
    }

    @Override
    public void run() 
    {
        while (true)
        {
            synchronized (waitingBalconys)  
            {
                //Percorro só o primeiro guichê que está esperando
                for (Map.Entry<BalconyBean, Socket> entry : waitingBalconys.entrySet()) 
                {
                    BalconyBean balconyBean = entry.getKey();
                    //Verifica se o guichê ainda está em uso
                    if (ClientBalconyListeners.getLoggedBalconysID().get(balconyBean.getId()) == null)
                    {
                        System.out.println("QueueHandler::Guichê: [" + balconyBean.getNumber()+ "] não está mais esperando (desapareceu?)");                        
                        waitingBalconys.remove(balconyBean);                         
                        break;
                    }
                
                    //Seleciona todos os clientes atendíveis para aquele guichê
                    HashMap<Integer, ArrayList<QueuesManagerBean>> managerBeans = QueuesManagerDAO.selectAvaliableClientsOfBalcony(balconyBean);

                    Socket balconySocket = entry.getValue();                

                    //Se a escolha estiver automática
                    QueuesManagerBean managerBean;
                    if (this.automaticChooseOn)
                    {
                        //Seleciono o melhor elemento para aquele guichê
                        managerBean = automaticChoose(managerBeans, balconyBean);
                    }
                    else
                    {
                        managerBean = manualChoose(balconyBean, managerBeans);
                    }
                        
                    //Se existir um cliente que se adeque ao guichê
                    if (managerBean != null)
                    {
                        //Informo ao guichê
                        ClientBalconyListeners.sendBackElementQueueToBalcony(balconySocket, managerBean);
                        System.out.println("QueueHandler::Cliente [automático] redirecionado para guichê: [" + balconyBean.getNumber()+ "]");                        
                        waitingBalconys.remove(balconyBean, balconySocket);                        
                    }   
                    
                    //O laço só é para primeiro guichê!!!
                    //Foi a melhor maneira (não consegui achar o método do hashMap que retorna o primeiro elemento. :D)
                    break;
                }
            }
        }
    }

    /**
     * Indica que existe um guichê esperando pelo próximo cliente
     */
    public void balconyIsWaiting(BalconyBean balconyBean, Socket socket) 
    {
        //Informa que existe um balcão esperando algum cliente
        synchronized (waitingBalconys)
        {
            System.out.println("QueueHandler::Novo guichê: [" + balconyBean.getNumber()+ "] está esperando por cliente");
            
            waitingBalconys.put(balconyBean, socket);
        }
    }

    private static class ChangeActorMessageQueuePopListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            ChangeActorMessage changeActorMessage = (ChangeActorMessage)message;
            //Insere
            if (changeActorMessage.getFrom() == MessageActors.QueuePopActor)
            {
               //Informo que a mensagem deve ser reenviada
               getInstance().wasMessageSent = false;
            }
        }
    }      
    
    private static class QueuePopChosenListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            BalconyShowClientMessage clientMessage = (BalconyShowClientMessage)message;
            
            //Informo ao popper que sua escolha foi bem sucedida
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(false, clientMessage, MessageActors.QueuePopActor);

            QueuesManagerBean queuesManagerBean = QueuesManagerDAO.selectFromId(clientMessage.getQueuesManagerBean().getId());
            if (queuesManagerBean.getIdBalcony() == 0 ) //Não foi escolhido ainda
            {
                confirmationResponse.setStatusOperation(true);
                getInstance().chosenClient = (BalconyShowClientMessage)message;
            }
            else
            {
                confirmationResponse.setComment("O cliente já não está mais apto a escolha");
            }

            PassControlServer.getInstance().getServer().addResponseToSend(socket, confirmationResponse);
        }
    }    

    private static class QueuePusherAddQueueElementListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            //Tenho que atualizar os clientes passíveis de escolha de todos os poppers
            getInstance().wasMessageSent = false;            
        }
    }
    
}
