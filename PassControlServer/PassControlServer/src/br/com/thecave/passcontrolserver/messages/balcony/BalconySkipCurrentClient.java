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
public class BalconySkipCurrentClient extends PassControlMessage
{

    public BalconySkipCurrentClient() 
    {
        super(MessageActors.BalconyActor, MessageActors.ServerActor);
    }
    
}
