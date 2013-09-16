/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.util;

import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.db.dao.QueuesManagerDAO;
import br.com.thecave.passcontrolserver.db.dao.ServiceDAO;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientBalconyListeners;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
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
        this.on = false;
        this.nextPriority = 4; //Alta
        waitingBalconys = new ConcurrentHashMap<>(); //
    }
    
    /**
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
    
    private QueuesManagerBean chooseNextElement(BalconyBean balconyBean)
    {        
        ArrayList<QueuesManagerBean> managerBeans = QueuesManagerDAO.selectAvaliableTuplesFromBalcony(balconyBean);
        /**Se a fila estiver vazia...
         * Adiciono esse balcony (SINCRONIZADA) em uma lista e o informo assim que um novo cliente chegar
         */
        
        ArrayList<ServiceBean> serviceBeans = new ArrayList<>(managerBeans.size());
        for (QueuesManagerBean queuesManagerBean : managerBeans) 
        {
            serviceBeans.add(ServiceDAO.selectFromId(queuesManagerBean.getIdService()));
        }
        
        if (on)
        {
            while (on)
            {
                //TODO
                //Boto um timeout só a massa
                //Informo ao QueuePoper a mensagem: BalconyBean, managerBeans e seus serviços, e o managerBeanRecomendado
                //Ele me retorna um QueueManagerBean...
                //return prepareSelectedQueuesManageElement(resposta.getSelected());                
                
                //Só pra não dar erro
            }
            return new QueuesManagerBean();
        }
        else
        {
            int selectedIndex = getFirstServiceBeanFromPriority(serviceBeans, 5); //Prioridade máxima
            //Se existir alguém com prioridade máxima
            if (selectedIndex != -1)
            {
                return prepareSelectedQueuesManageElement(managerBeans.get(selectedIndex), serviceBeans.get(selectedIndex));
            }
            //Tento para as prioridades Alta, média e baixa (A partir da última escolha...)
            for (int i = 0; i < 3; ++i)
            {
                selectedIndex = getFirstServiceBeanFromPriority(serviceBeans, nextPriority); //Próxima prioridade
                //Se eu achar alguém dessa prioridade
                if (selectedIndex != -1)
                    return prepareSelectedQueuesManageElement(managerBeans.get(selectedIndex), serviceBeans.get(selectedIndex));
                else //Altero a prioridade que eu quero
                    updateNextPriority(nextPriority);
            }
            selectedIndex = getFirstServiceBeanFromPriority(serviceBeans, 1); //Prioridade mínima
            if (selectedIndex != -1)
                return prepareSelectedQueuesManageElement(managerBeans.get(selectedIndex), serviceBeans.get(selectedIndex));
            return null;
        }
        
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
                        //Informo ao guichê
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
