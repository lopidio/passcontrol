/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.viewer;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.queuepopper.QueuePopperNewClientAdded;
import java.util.ArrayList;

/**
 *
 * @author lopidio
 */
public class QueuePopperRefreshResponse extends PassControlMessage
{
    ArrayList<QueuePopperNewClientAdded> queuePopperNewClientAdded;

    public QueuePopperRefreshResponse(ArrayList<QueuePopperNewClientAdded> queuePopperNewClientAdded)
    {
        super(MessageActors.ServerActor, MessageActors.QueuePopActor);
        this.queuePopperNewClientAdded = queuePopperNewClientAdded;
    }
    
    public ArrayList<QueuePopperNewClientAdded> getQueuePopperNewClientAdded() {
        return queuePopperNewClientAdded;
    }

    public void setQueuePopperNewClientAdded(ArrayList<QueuePopperNewClientAdded> queuePopperNewClientAdded) {
        this.queuePopperNewClientAdded = queuePopperNewClientAdded;
    }
    
}
