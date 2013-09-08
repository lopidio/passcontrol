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
public class BalconyRecallLastClient extends PassControlMessage
{
    String balconyID;

    public BalconyRecallLastClient(String balconyID) 
    {
        super(MessageActors.BalconyActor, MessageActors.ServerActor);
        this.balconyID = balconyID;
    }

    public BalconyRecallLastClient(String balconyID, String comment) 
    {
        super(MessageActors.BalconyActor, MessageActors.ServerActor, comment);
        this.balconyID = balconyID;
    }

    public String getBalconyID() {
        return balconyID;
    }

    public void setBalconyID(String balconyID) {
        this.balconyID = balconyID;
    }
    
    
}
