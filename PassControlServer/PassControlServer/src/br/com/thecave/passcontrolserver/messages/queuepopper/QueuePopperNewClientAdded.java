/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.queuepopper;

import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author lopidio
 */
public class QueuePopperNewClientAdded extends PassControlMessage
{
    QueuesManagerBean queuesManagerBean;
    
    int priority;

    /**
     * Nome do cliente
     */
    String clientName;
    
    /**
     * Tipo do serviço
     */
    String serviceType;

    /**
     * O nome do balcão
     */
    String balconyNumber; 

    public QueuePopperNewClientAdded(QueuesManagerBean queuesManagerBean, int priority, String clientName, String serviceType, String balconyNumber) 
    {
        super(MessageActors.ServerActor, MessageActors.QueuePopActor);
        this.queuesManagerBean = queuesManagerBean;
        this.priority = priority;
        this.clientName = clientName;
        this.serviceType = serviceType;
        this.balconyNumber = balconyNumber;
    }
    
    

    public QueuesManagerBean getQueuesManagerBean() {
        return queuesManagerBean;
    }

    public void setQueuesManagerBean(QueuesManagerBean queuesManagerBean) {
        this.queuesManagerBean = queuesManagerBean;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getBalconyNumber() {
        return balconyNumber;
    }

    public void setBalconyNumber(String balconyNumber) {
        this.balconyNumber = balconyNumber;
    }

    
    
}
