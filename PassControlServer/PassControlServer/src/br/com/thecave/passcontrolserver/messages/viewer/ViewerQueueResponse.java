/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.viewer;

import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import java.util.ArrayList;

/**
 *
 * @author lopidio
 */
public class ViewerQueueResponse extends PassControlMessage
{
    ArrayList<BalconyShowClientMessage> lastCalledClients;

    public ViewerQueueResponse(ArrayList<BalconyShowClientMessage> lastCalledClients) 
    {
        super(MessageActors.ServerActor, MessageActors.ViewerActor);
        this.lastCalledClients = lastCalledClients;
    }

    public ArrayList<BalconyShowClientMessage> getLastCalledClients() {
        return lastCalledClients;
    }

    public void setLastCalledClients(ArrayList<BalconyShowClientMessage> lastCalledClients) {
        this.lastCalledClients = lastCalledClients;
    }
    
}
