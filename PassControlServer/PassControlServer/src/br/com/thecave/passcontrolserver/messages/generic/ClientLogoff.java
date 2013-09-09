/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.generic;

/**
 *
 * @author guilherme
 */
public class ClientLogoff extends PassControlMessage
{
    
    public ClientLogoff() 
    {
        super(MessageActors.NotIdentified, MessageActors.ServerActor);
    }

    public ClientLogoff( String comment) 
    {
        super(MessageActors.NotIdentified, MessageActors.ServerActor, comment);
    }

}
