/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.queuepusher;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author lopidio
 */
public class QueuePusherLoadClientFromRegistration extends PassControlMessage
{
    String register;

    public QueuePusherLoadClientFromRegistration(String register) 
    {
        super(MessageActors.QueuePushActor, MessageActors.ServerActor);
        this.register = register;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }
    
    
}
