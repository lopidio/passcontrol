/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author lopidio
 */
public class AdministratorListBalcony extends PassControlMessage
{
     public AdministratorListBalcony() {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
    }   
}
