/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.generic;

/**
 * Simbolisa a mudança de ator de um usuário
 * @author guilherme
 */
public class ChangeActorMessage extends PassControlMessage
{

    public ChangeActorMessage(MessageActors from) {
        super(from, MessageActors.ServerActor);
    }

    public ChangeActorMessage(MessageActors from, String comment) {
        super(from, MessageActors.ServerActor, comment);
    }
    
}
