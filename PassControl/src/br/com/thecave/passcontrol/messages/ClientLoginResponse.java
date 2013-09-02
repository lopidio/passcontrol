/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.messages;

import br.com.thecave.passcontrol.db.bean.UserBean;

/**
 *
 * @author guilherme
 */
public class ClientLoginResponse extends PassControlMessage
{
    UserBean user;

    public ClientLoginResponse(UserBean user,MessageActors to) {
        super(MessageActors.ServerActor, to);
        this.user = user;
    }

    public ClientLoginResponse(UserBean user, MessageActors to, String comment) {
        super(MessageActors.ServerActor, to, comment);
        this.user = user;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

}
