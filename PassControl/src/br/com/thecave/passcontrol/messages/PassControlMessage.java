/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.messages;

import java.io.Serializable;
import java.net.Socket;

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
     * Socket que representa a conexão entre o remetente e o destinatário
     */
    private Socket socket;

    /**
     * 
     * Construtor de mensagens do tipo broadcast
     * 
     * @param from Remetente
     * @param to Destinatário
     * @param type Sublclasse que ela instancia
     */
    public PassControlMessage(MessageActors from, MessageActors to) {
        this.from = from;
        this.to = to;
        this.type = this.getClass().getSimpleName();
        this.socket = null;
    }

    /**
     * Construtor de mensagens de resposta
     */
    public PassControlMessage(MessageActors from, MessageActors to, Socket socket) {
        this.from = from;
        this.to = to;
        this.socket = socket;
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
     * Getter do destinatário
     * @return destinatário
     */
    public MessageActors getTo() {
        return to;
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

    public Socket getSocket() 
    {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
        
    
    
}
