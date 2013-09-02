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
public class AdministratorRemoveUser extends PassControlMessage
{
    /**
     * ID do usuário a ser removido
     */
    int id;

    public AdministratorRemoveUser(int id) {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
        this.id = id;
    }

    public AdministratorRemoveUser(int id, String comment) {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor, comment);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
