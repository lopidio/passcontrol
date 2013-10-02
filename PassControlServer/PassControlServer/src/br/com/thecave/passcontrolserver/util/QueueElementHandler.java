/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.util;

import br.com.thecave.passcontrolserver.PassControlServer;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.db.dao.QueuesManagerDAO;
import br.com.thecave.passcontrolserver.db.dao.ServiceDAO;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientBalconyListeners;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.queuepopper.QueuePopperChooseNextElement;
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
    public static final int AUTOMATIC_QUEUE_CHOOSER_USERCHECKOUT_ID = 0;
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
        //Começa como falso.. Só pra ver qual é.
        this.automaticChooseOn = false;
        this.nextPriority = 4; //Alta
        waitingBalconys = new ConcurrentHashMap<>(); //
    }
    
    private void updateNextPriority(int currentPriority)
    {
        switch (currentPriority)
        {
            case 4: //Se a prioridade atual for Alta
                nextPriority = 3; //A próxima prioridade é Média
                break;
            case 3: //Se a prioridade atual for Média
                nextPriority = 3; //A próxima prioridade é Baixa
                break;
            default: //Se for qualquer outro valor
                nextPriority = 4; //A próxima prioridade é Alta
                break;
        }
    }
    
    private QueuesManagerBean prepareSelectedQueuesManageElement(BalconyBean balconyBean, QueuesManagerBean selectedManagerBean)
    {
        //Seto a hora da escolha
        //A hora de escolha vai ser setada ao guichê pressionar "Iniciar atendimento"
//        selectedManagerBean.setCheckout(new Date());
        //Seto o Id do guichê
        selectedManagerBean.setIdBalcony(balconyBean.getId());
        //salvo as alterações no banco.
        QueuesManagerDAO.update(selectedManagerBean);
        
        //Atualizo a próxima prioridade que eu devo escolher
        updateNextPriority(ServiceDAO.selectFromId(selectedManagerBean.getIdService()).getPriority());
        
        //Retorna o bean atualizado
        return selectedManagerBean;
    }
    
    private QueuesManagerBean automaticChoose(BalconyBean balconyBean, HashMap<Integer, ArrayList<QueuesManagerBean>> avaliableClients)
    {
        //Pego os serviço de todos os clientes que se adequam
        ArrayList<ServiceBean> serviceBeans = new ArrayList<>(avaliableClients.size());
        
        for (Map.Entry<Integer, ArrayList<QueuesManagerBean>> entry : avaliableClients.entrySet()) 
        {
            Integer priority = entry.getKey();
            for (QueuesManagerBean queuesManagerBean : entry.getValue()) 
            {
                serviceBeans.add(ServiceDAO.selectFromId(queuesManagerBean.getIdService()));                    
            }

        }

        //Se existir alguém com prioridade máxima
        if (!avaliableClients.get(5).isEmpty())
        {
            //Pego o primeiro
            return avaliableClients.get(5).get(0);
        }
        else
        {
            //Tento para as prioridades Alta, média e baixa (A partir da última escolha...)
            for (int i = 0; i < 3; ++i)
            {
                //Se existir algum com essa prioridade
                if (!avaliableClients.get(nextPriority).isEmpty())
                {
                    return avaliableClients.get(nextPriority).get(0);
                }
                else //Altero a prioridade que eu quero
                    updateNextPriority(nextPriority);
            }
            //Se existe alguém com a prioridade mínima
            if (!avaliableClients.get(0).isEmpty())
                return avaliableClients.get(0).get(0);
        }

        return null;
    }
    
    /**
     * Retorna null se nenhum popper escolher
     * @param balconyBean
     * @param avaliableClients
     * @return 
     */
    private QueuesManagerBean manualChoose(BalconyBean balconyBean, HashMap<Integer, ArrayList<QueuesManagerBean>> avaliableClients)
    {
        QueuePopperChooseNextElement chooseNextElement = new QueuePopperChooseNextElement(balconyBean, avaliableClients);

        //Mando para todos os poppers
        HashMap<Socket, BalconyShowClientMessage> clientsResponse = PassControlServer.getInstance().getServer().sendMessageToClientsAndWaitForResponseOrTimeout(chooseNextElement, BalconyShowClientMessage.class, 1000);

        //A resposta de algum popper
        BalconyShowClientMessage balconyShowClientMessage = null;
        //Analiso a resposta de cada um (e respondo as que existirem)
        for (Map.Entry<Socket, BalconyShowClientMessage> entry : clientsResponse.entrySet()) 
        {
            Socket socket = entry.getKey();
            BalconyShowClientMessage clientRespose = entry.getValue();

            //Se existir a resposta
            if (clientRespose != null)
            {
                //Informo ao popper que sua escolha foi bem sucedida
                ConfirmationResponse confirmationResponse = new ConfirmationResponse(false, balconyShowClientMessage, MessageActors.QueuePopActor);
                
                QueuesManagerBean queuesManagerBean = QueuesManagerDAO.selectFromId(clientRespose.getQueuesManagerBean().getId());
                if (queuesManagerBean.getIdBalcony() == 0 ) //Não foi escolhido ainda
                {
                    confirmationResponse.setStatusOperation(true);
                }
                else
                {
                    confirmationResponse.setComment("O cliente já foi escolhido através de outro removedor.");                    
                }
                
                PassControlServer.getInstance().getServer().addResponseToSend(socket, confirmationResponse);

                //Informo que uma escolha já aconteceu
                balconyShowClientMessage = clientRespose;
            }

        }
        //Se não tiver nenhuma escolha ainda
        if (balconyShowClientMessage == null)
            return null;
        return balconyShowClientMessage.getQueuesManagerBean();
    }    
    
    private QueuesManagerBean chooseNextElement(BalconyBean balconyBean)
    {   
        //Seleciona todos os clientes atendíveis para aquele guichê
        HashMap<Integer, ArrayList<QueuesManagerBean>> managerBeans = QueuesManagerDAO.selectAvaliableTuplesFromBalcony(balconyBean);
        
        //Não existe nenhum cliente que se adeque
        if (managerBeans.isEmpty())
        {
            return null;
        }
        
        QueuesManagerBean chosenClient = null;
        //Se a escolhar for automática
        if (automaticChooseOn)
        {
            chosenClient = automaticChoose(balconyBean, managerBeans);
            //É zero quando a escolha foi automática
            if (chosenClient != null)
                chosenClient.setIdUserCheckout(AUTOMATIC_QUEUE_CHOOSER_USERCHECKOUT_ID);
        }
        else
        {
            //Retorna nulo caso a tenha mudado a forma de escolha (automática para manual)
            chosenClient = manualChoose(balconyBean, managerBeans);
        }
        if (chosenClient == null)
            return null;
        return prepareSelectedQueuesManageElement(balconyBean, chosenClient);
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
                    
                    
                    //Seleciono o melhor elemento para aquele guichê
                    QueuesManagerBean managerBean = chooseNextElement(balconyBean);
                    Socket socket = entry.getValue();                

                    //Se existir um cliente que se adeque ao guichê
                    if (managerBean != null)
                    {
                        //Informo ao guichê
                        ClientBalconyListeners.sendBackElementQueueToBalcony(socket, managerBean);
                        System.out.println("QueueHandler::Cliente redirecionado para guichê: [" + balconyBean.getNumber()+ "]");                        
                        waitingBalconys.remove(balconyBean, socket);                        
                    }
                    
                    //Só funciona para o primeiro guichê!!!
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

}
