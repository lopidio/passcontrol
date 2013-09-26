package br.com.thecave.passcontrolserver.messages.generic;

/**
 *
 * @author Arleudo
 */
public class ClientLoginReset extends PassControlMessage
{
    String user;
    
    public ClientLoginReset(String user) {
        super(MessageActors.NotIdentified, MessageActors.ServerActor);
        this.user = user;
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
