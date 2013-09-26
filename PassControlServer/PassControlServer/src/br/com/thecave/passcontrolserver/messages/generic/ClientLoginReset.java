package br.com.thecave.passcontrolserver.messages.generic;

/**
 *
 * @author Arleudo
 */
public class ClientLoginReset extends PassControlMessage
{
    String user;
    
    public ClientLoginReset(MessageActors from, String user) {
        super(from, MessageActors.ServerActor);
        this.user = user;
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
