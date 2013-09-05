/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.generic;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author guilherme
 */
public abstract class PassControlMessage implements Serializable
{
    /**
     * Hora em que a mensagem foi CRIADA (e não enviada)
     */
    Date date;
    
    /**
     * Algum comentário adicional
     */
    String comment;
    
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
        this.date = new java.util.Date();
        this.comment = "";
    }
    

    public PassControlMessage(MessageActors from, MessageActors to, String comment) {
        this.from = from;
        this.to = to;
        this.type = this.getClass().getSimpleName();
        this.date = new java.util.Date();
        this.comment = comment;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }   

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }  
  
}
