/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.queuepopper;

import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author lopidio
 */
public class QueuePopperNewClientAdded extends PassControlMessage
{
    QueuesManagerBean queuesManagerBean;
    
    ServiceBean serviceBean;

    /**
     * Nome do cliente
     */
    String clientName;
    
    /**
     * O nome do balcão
     */
    String balconyNumber; 

    public QueuePopperNewClientAdded(QueuesManagerBean queuesManagerBean, ServiceBean serviceBean, String clientName, String balconyNumber) 
    {
        super(MessageActors.ServerActor, MessageActors.QueuePopActor);
        this.queuesManagerBean = queuesManagerBean;
        this.serviceBean = serviceBean;
        this.clientName = clientName;
        this.balconyNumber = balconyNumber;
    }

    
    
    public QueuesManagerBean getQueuesManagerBean() {
        return queuesManagerBean;
    }

    public void setQueuesManagerBean(QueuesManagerBean queuesManagerBean) {
        this.queuesManagerBean = queuesManagerBean;
    }

    public ServiceBean getServiceBean() {
        return serviceBean;
    }

    public void setServiceBean(ServiceBean serviceBean) {
        this.serviceBean = serviceBean;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getBalconyNumber() {
        return balconyNumber;
    }

    public void setBalconyNumber(String balconyNumber) {
        this.balconyNumber = balconyNumber;
    }

    
}
