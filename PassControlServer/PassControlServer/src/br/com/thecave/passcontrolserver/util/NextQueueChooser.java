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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author guilherme
 */
public class NextQueueChooser implements Runnable
{
    /**
     * Indica se a escolha é automática (true) ou manual (false)
     */
    private boolean on;
    
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
    private static NextQueueChooser instance = null;
    public synchronized static NextQueueChooser getInstance()
    {
        if (instance == null)
            instance = new NextQueueChooser();
        return instance;
    }

    /**
     * Constructor
     */
    private NextQueueChooser() 
    {
        //Começa como falso.. Só pra ver qual é.
        this.on = false;
        this.nextPriority = 4; //Alta
        waitingBalconys = new ConcurrentHashMap<>(); //
    }
    
    /**
     * Percorre a lista de serviços e retorna o índice do primeiro serviço com aquela prioridade
     * Retorna -1 caso não encontre
     */
    private static int getFirstServiceBeanFromPriority(ArrayList<ServiceBean> serviceBeans, int priority)
    {
        for (int i = 0; i < serviceBeans.size(); i++) 
        {
            if (serviceBeans.get(i).getPriority() == priority)
                return i;
        }
        return -1;
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
    
    private QueuesManagerBean prepareSelectedQueuesManageElement(QueuesManagerBean selectedManagerBean, ServiceBean serviceBean)
    {
        //Seto a hora da escolha
        selectedManagerBean.setCheckout(new Date());
        
        //Atualizo a próxima prioridade que eu devo escolher
        updateNextPriority(ServiceDAO.selectFromId(selectedManagerBean.getIdService()).getPriority());
        
        //Atualiza no banco
        QueuesManagerDAO.update(selectedManagerBean);
        
        //Retorna o bean atualizado
        return selectedManagerBean;
    }
    
    private QueuesManagerBean automaticChoose(BalconyBean balconyBean, ArrayList<QueuesManagerBean> avaliableClients)
    {
        //Pego os serviço de todos os clientes que se adequam
        ArrayList<ServiceBean> serviceBeans = new ArrayList<>(avaliableClients.size());
        for (QueuesManagerBean queuesManagerBean : avaliableClients) 
        {
            serviceBeans.add(ServiceDAO.selectFromId(queuesManagerBean.getIdService()));
        }

        int selectedIndex = getFirstServiceBeanFromPriority(serviceBeans, 5); //Prioridade máxima
        //Se existir alguém com prioridade máxima
        if (selectedIndex != -1)
        {
            return prepareSelectedQueuesManageElement(avaliableClients.get(selectedIndex), serviceBeans.get(selectedIndex));
        }
        //Tento para as prioridades Alta, média e baixa (A partir da última escolha...)
        for (int i = 0; i < 3; ++i)
        {
            selectedIndex = getFirstServiceBeanFromPriority(serviceBeans, nextPriority); //Próxima prioridade
            //Se eu achar alguém dessa prioridade
            if (selectedIndex != -1)
                return prepareSelectedQueuesManageElement(avaliableClients.get(selectedIndex), serviceBeans.get(selectedIndex));
            else //Altero a prioridade que eu quero
                updateNextPriority(nextPriority);
        }
        selectedIndex = getFirstServiceBeanFromPriority(serviceBeans, 1); //Prioridade mínima
        if (selectedIndex != -1)
            return prepareSelectedQueuesManageElement(avaliableClients.get(selectedIndex), serviceBeans.get(selectedIndex));
        return null;
    }
    
    /**
     * Trava enquanto um popper não escolher um cliente
     * @param balconyBean
     * @param avaliableClients
     * @return 
     */
    private QueuesManagerBean manualChoose(BalconyBean balconyBean, ArrayList<QueuesManagerBean> avaliableClients)
    {
        QueuePopperChooseNextElement chooseNextElement = new QueuePopperChooseNextElement(balconyBean, avaliableClients);

        //Mando para todos os poppers
        HashMap<Socket, BalconyShowClientMessage> clientsResponse = PassControlServer.getInstance().getServer().sendMessageToClientsAndWaitForResponseOrTimeout(chooseNextElement, BalconyShowClientMessage.class, 1000);

        //A resposta de algum popper
        BalconyShowClientMessage balconyShowClientMessage = null;
        while (on)
        {
            //Analiso a resposta de cada um (e respondo as que existirem)
            for (Map.Entry<Socket, BalconyShowClientMessage> entry : clientsResponse.entrySet()) 
            {
                Socket socket = entry.getKey();
                BalconyShowClientMessage message = entry.getValue();

                //Se existir a resposta
                if (message != null)
                {
                    //Informo ao popper que sua escolha foi bem sucedida
                    ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, balconyShowClientMessage, MessageActors.QueuePopActor);
                    if (balconyShowClientMessage != null)
                    {
                        confirmationResponse.setStatusOperation(false);
                        confirmationResponse.setComment("O cliente já foi escolhido através de outro removedor.");
                    }
                    PassControlServer.getInstance().getServer().addResponseToSend(socket, confirmationResponse);

                    //Informo que uma escolha já aconteceu
                    balconyShowClientMessage = message;
                }

            }
            
            //Se algum deles enviou resposta
            if (balconyShowClientMessage != null)
            {
                //Preparo a escolha para ser envia
                return prepareSelectedQueuesManageElement(balconyShowClientMessage.getQueuesManagerBean(),
                                                    ServiceDAO.selectFromId(balconyShowClientMessage.getQueuesManagerBean().getIdService()));
            }
        }
        //O tipo de escolha mudou (automática para manual)
        return null;
    }    
    
    private QueuesManagerBean chooseNextElement(BalconyBean balconyBean)
    {   
        ArrayList<QueuesManagerBean> managerBeans = QueuesManagerDAO.selectAvaliableTuplesFromBalcony(balconyBean);
        
        //Não existe nenhum cliente que se adeque
        if (managerBeans.isEmpty())
        {
            return null;
        }
        
        QueuesManagerBean chosenClient = null;
        //Se a escolhar for manual
        if (on)
        {
            //Retorna nulo caso a tenha mudado a forma de escolha (autmática para manual)
            chosenClient = manualChoose(balconyBean, managerBeans);
        }
        
        //Se a escolha for automática
        if (chosenClient == null)
        {
            chosenClient = automaticChoose(balconyBean, managerBeans);
        }
        
        return chosenClient;
        
        //Fluxo:
        /**
         * 
         * Se estiver desligado:
         *      repassa o serviço para o QueuePoper
         * 
         * 
         * Se estiver ligado:
         * Verifica quais serviços esse guichê atende.
         * 
         * Verifica se tem alguém com prioridade máxima.
         *      Se tiver:
         *          retorna o próximo de prioridade máxima;
         *      Se não:
         *          Verifica se tem algum elemento com prioridade alta, média ou baixa
         *              Se tiver:
         *                  Verifica qual, dessas 3 prioridades, foi a última a ser chamada;
         *                  Incrementa em um esse valor;
         *                  Retorna o próximo dessa prioridade incrementada;
         *              Se não:
         *              Verifica se tem algum da prioridade mínima
         *                  Se tiver:
         *                      retorna o próximo de prioridade mínima;
         *                  Se não:
         *                      retorna nulo;
         */
    }
    
    public boolean isOn() 
    {
        return on;
    }

    public void setOn(boolean on) 
    {
        this.on = on;
    }

    @Override
    public void run() 
    {
        while (true)
        {
            synchronized (waitingBalconys)  
            {
                //Percorro todos os guichês que estão esperando
                for (Map.Entry<BalconyBean, Socket> entry : waitingBalconys.entrySet()) 
                {
                    BalconyBean balconyBean = entry.getKey();
                    //Seleciono o melhor elemento para aquele guichê
                    QueuesManagerBean managerBean = chooseNextElement(balconyBean);
                    Socket socket = entry.getValue();                

                    //Se existir um cliente que se adeque ao guichê
                    if (managerBean != null)
                    {
                        //Informo aos guichê
                        ClientBalconyListeners.sendBackElementQueueToBalcony(socket, managerBean);
                        waitingBalconys.remove(balconyBean, socket);
                        
                        //Recomeço do ZERO
                        break;
                    }
                }
            }
        }
    }

    /**
     * Indica que existe um guichê esperando pelo próximo cliente
     */
    public void balconyAvaliable(BalconyBean balconyBean, Socket socket) 
    {
        //Informa que existe um balcão esperando algum cliente
        synchronized (waitingBalconys)
        {
            System.out.println("Guichê " + balconyBean.getNumber()+ " chamando o próximo");
            
            waitingBalconys.put(balconyBean, socket);
        }
    }

}
