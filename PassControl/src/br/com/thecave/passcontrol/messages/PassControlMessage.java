/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.messages;

import java.io.Serializable;

/**
 *
 * @author guilherme
 */
public abstract class PassControlMessage implements Serializable
{
    
    /**
     * Indica o remetente da mensagem
     */
    private MessageActors from;

    /**
     * Indica o destinatário da mensagem
     */
    private MessageActors to;      
    
    /**
     * Indica o tipo da mensagem. (de qual subclasse ela é uma instância)
     */
    private String type; //Ou pode-se criar uma enumeração para isso.

    public PassControlMessage(MessageActors from, MessageActors to) {
        this.from = from;
        this.to = to;
        this.type = this.getClass().getSimpleName();
    }
    

    /**
     * Getter do remetente
     * @return remetente
     */
    public MessageActors getFrom() {
        return from;
    }

  
    /**
     * Getter do tipo.
     * Valor é o mesmo do nome da subclasse a qual ela pertence
     * @return tipo
     */
    public String getType() 
    {
        return type;
    }

    public MessageActors getTo() {
        return to;
    }

    public void setTo(MessageActors to) {
        this.to = to;
    }

    
    
}
