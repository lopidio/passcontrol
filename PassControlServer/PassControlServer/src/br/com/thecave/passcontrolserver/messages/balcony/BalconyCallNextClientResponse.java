/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.balcony;

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
     * Número da senha
     */
    String passNumber;

    public BalconyCallNextClientResponse(String clientName, String serviceType, String passNumber, String comment) 
    {
        super(MessageActors.ServerActor, MessageActors.BalconyActor, comment);
        this.clientName = clientName;
        this.serviceType = serviceType;
        this.passNumber = passNumber;
    }
    
    public BalconyCallNextClientResponse(String clientName, String serviceType, String passNumber) 
    {
        super(MessageActors.ServerActor, MessageActors.BalconyActor);
        this.clientName = clientName;
        this.serviceType = serviceType;
        this.passNumber = passNumber;
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

    public String getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(String passNumber) {
        this.passNumber = passNumber;
    }
    
    
    
}
