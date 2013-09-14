/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.db.bean.UserBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public class AdministratorListUserResponse extends PassControlMessage
{
    /**
     * Usuários cadastrados no banco de dados
     */
    ArrayList<UserBean> users;

    public AdministratorListUserResponse(ArrayList<UserBean> users) {
        super(MessageActors.ServerActor, MessageActors.AdministratorActor);
        this.users = users;
    }

    public ArrayList<UserBean> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserBean> users) {
        this.users = users;
    }
      
    
    
}
