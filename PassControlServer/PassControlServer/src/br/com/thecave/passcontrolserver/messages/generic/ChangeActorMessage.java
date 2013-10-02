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

    MessageActors oldActor;
    
    public ChangeActorMessage(MessageActors oldActor, MessageActors from) 
    {
        super(from, MessageActors.ServerActor);
        this.oldActor = oldActor;
    }

    public MessageActors getOldActor() {
        return oldActor;
    }

    public void setOldActor(MessageActors oldActor) {
        this.oldActor = oldActor;
    }

    
    
}
