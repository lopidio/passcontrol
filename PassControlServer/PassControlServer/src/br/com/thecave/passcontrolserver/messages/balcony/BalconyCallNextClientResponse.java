/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.balcony;

import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author lopidio
 */
public class BalconyCallNextClientResponse extends PassControlMessage
{
    /**
     * Nome do cliente
     */
    String clientName;
    
    /**
     * Tipo do serviço
     */
    String serviceType;
    
    /**
     * Representa o próprio elemento
     */
    QueuesManagerBean queuesManagerBean;
    
    /**
     * 
     * @param clientName
     * @param serviceType
     * @param passNumber 
     */
    public BalconyCallNextClientResponse(String clientName, String serviceType, QueuesManagerBean queuesManagerBean) 
    {
        super(MessageActors.ServerActor, MessageActors.BalconyActor);
        this.clientName = clientName;
        this.serviceType = serviceType;
        this.queuesManagerBean = queuesManagerBean;
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

    public QueuesManagerBean getQueuesManagerBean() {
        return queuesManagerBean;
    }

    public void setQueuesManagerBean(QueuesManagerBean queuesManagerBean) {
        this.queuesManagerBean = queuesManagerBean;
    }


    
}
