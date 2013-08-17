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
public class PassControlMessage implements Serializable{
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

    /**
     * 
     * Construtor
     * 
     * @param from Remetente
     * @param to Destinatário
     * @param type Sublclasse que ela instancia
     */
    public PassControlMessage(MessageActors from, MessageActors to, String type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    /**
     * Getter do remetente
     * @return remetente
     */
    public MessageActors getFrom() {
        return from;
    }

    /**
     * Getter do destinatário
     * @return destinatário
     */
    public MessageActors getTo() {
        return to;
    }

    /**
     * Getter do tipo.
     * Uma String cujo valor é o mesmo do nome da subclasse a qual ela pertence
     * @return tipo
     */
    public String getType() {
        return type;
    }
        
    
}
