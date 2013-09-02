/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.messages.administrator;

import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.messages.PassControlMessage;

/**
 *
 * @author guilherme
 */
public class AdministratorListUser extends PassControlMessage
{
    public AdministratorListUser() {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
    }

    public AdministratorListUser(String comment) {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor, comment);
    }
    
}
