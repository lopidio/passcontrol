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
public class BalconyShowClientMessage extends PassControlMessage
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
     * O nome do balcão
     */
    String balconyNumber;
    
    /**
     * 
     * @param clientName
     * @param serviceType
     * @param passNumber 
     */
    public BalconyShowClientMessage(String clientName, String serviceType, String balconyNumber, QueuesManagerBean queuesManagerBean, MessageActors from, MessageActors to) 
    {
        super(from, to);
        this.clientName = clientName;
        this.serviceType = serviceType;
        this.queuesManagerBean = queuesManagerBean;
        this.balconyNumber = balconyNumber;
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

    public String getBalconyNumber() {
        return balconyNumber;
    }

    public void setBalconyNumber(String balconyNumber) {
        this.balconyNumber = balconyNumber;
    }


    
}
