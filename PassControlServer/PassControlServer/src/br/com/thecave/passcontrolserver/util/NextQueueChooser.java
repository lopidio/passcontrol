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
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public class NextQueueChooser 
{
    private boolean on;
    
    private int nextPriority;

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

    public NextQueueChooser() 
    {
        this.on = false;
        this.nextPriority = 4; //Alta
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
        //selectedManagerBean
        updateNextPriority(ServiceDAO.selectFromId(selectedManagerBean.getIdService()).getPriority());
        return selectedManagerBean;
    }
    
    public QueuesManagerBean chooseNextElement(BalconyBean balconyBean)
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
            //Informo ao QueuePoper a mensagem: BalconyBean, managerBeans e seus serviços, e o managerBeanRecomendado
            //Ele me retorna um QueueManagerBean...
            //return prepareSelectedQueuesManageElement(resposta.getSelected());
            return null;
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
            }
            selectedIndex = getFirstServiceBeanFromPriority(serviceBeans, 1); //Prioridade mínima
            return prepareSelectedQueuesManageElement(managerBeans.get(selectedIndex), serviceBeans.get(selectedIndex));
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
    
    
}
