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
    
    private QueuesManagerBean prepareSelectedQueuesManageElement(BalconyBean balconyBean, QueuesManagerBean selectedManagerBean)
    {
        //Seto a hora da escolha
        selectedManagerBean.setCheckout(new Date());
        //Seto o Id do guichê
        selectedManagerBean.setIdBalcony(balconyBean.getId());
        //salvo as alterações no banco.
        QueuesManagerDAO.update(selectedManagerBean);
        
        //Atualizo a próxima prioridade que eu devo escolher
        updateNextPriority(ServiceDAO.selectFromId(selectedManagerBean.getIdService()).getPriority());
        
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

        QueuesManagerBean selectedQueueManagerBean = null;
        int selectedIndex = getFirstServiceBeanFromPriority(serviceBeans, 5); //Prioridade máxima
        //Se existir alguém com prioridade máxima
        if (selectedIndex != -1)
        {
            selectedQueueManagerBean = avaliableClients.get(selectedIndex);
        }
        else
        {
            //Tento para as prioridades Alta, média e baixa (A partir da última escolha...)
            for (int i = 0; i < 3; ++i)
            {
                selectedIndex = getFirstServiceBeanFromPriority(serviceBeans, nextPriority); //Próxima prioridade
                //Se eu achar alguém dessa prioridade
                if (selectedIndex != -1)
                    selectedQueueManagerBean = avaliableClients.get(selectedIndex);
                else //Altero a prioridade que eu quero
                    updateNextPriority(nextPriority);
            }
            if (selectedQueueManagerBean == null)
            {
                selectedIndex = getFirstServiceBeanFromPriority(serviceBeans, 1); //Prioridade mínima
                if (selectedIndex != -1)
                {
                    selectedQueueManagerBean = avaliableClients.get(selectedIndex);
                }
            }
        }

        return selectedQueueManagerBean;
    }
    
    /**
     * Retorna null se nenhum popper escolher
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
        return balconyShowClientMessage.getQueuesManagerBean();
    }    
    
    private QueuesManagerBean chooseNextElement(BalconyBean balconyBean)
    {   
        //Seleciona todos os clientes atendíveis para aquele guichê
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
        else
        {
            chosenClient = automaticChoose(balconyBean, managerBeans);
            //É zero quando a escolha foi automática
            chosenClient.setIdUserCheckout(0);
        }
        
        return prepareSelectedQueuesManageElement(balconyBean, chosenClient);
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
                //Percorro só o primeiro guichê que está esperando
                for (Map.Entry<BalconyBean, Socket> entry : waitingBalconys.entrySet()) 
                {
                    BalconyBean balconyBean = entry.getKey();
                    //Seleciono o melhor elemento para aquele guichê
                    QueuesManagerBean managerBean = chooseNextElement(balconyBean);
                    Socket socket = entry.getValue();                

                    //Se existir um cliente que se adeque ao guichê
                    if (managerBean != null)
                    {
                        //Informo ao guichê
                        ClientBalconyListeners.sendBackElementQueueToBalcony(socket, managerBean);
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
