/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.viewer;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author lopidio
 */
public class QueuePopperRefreshAllRequest extends PassControlMessage
{

    public QueuePopperRefreshAllRequest() {
        super(MessageActors.QueuePopActor, MessageActors.ServerActor);
    }
    
}
