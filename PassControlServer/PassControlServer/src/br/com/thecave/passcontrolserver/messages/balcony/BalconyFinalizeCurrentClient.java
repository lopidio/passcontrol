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
public class BalconyFinalizeCurrentClient extends PassControlMessage
{

    public BalconyFinalizeCurrentClient() 
    {
        super(MessageActors.BalconyActor, MessageActors.ServerActor);
    }
    
}

