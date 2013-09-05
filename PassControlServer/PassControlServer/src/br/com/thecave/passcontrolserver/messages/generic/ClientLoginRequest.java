/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.generic;

/**
 *
 * @author guilherme
 */
public class ClientLoginRequest extends PassControlMessage
{
    String user;
    String password;
    
    
    public ClientLoginRequest(MessageActors from, String user, String password) {
        super(from, MessageActors.ServerActor);
        this.user = user;
        this.password = password;
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
}
