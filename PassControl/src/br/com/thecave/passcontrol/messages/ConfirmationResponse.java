/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.messages;

import java.net.Socket;

/**
 *
 * @author guilherme
 */
public class ConfirmationResponse extends PassControlConnectionPacket
{
    boolean statusOperation;

    String messageRequest;

    public ConfirmationResponse(boolean statusOperation, String messageRequest, PassControlMessage packet, Socket socket) {
        super(packet, socket);
        this.statusOperation = statusOperation;
        this.messageRequest = messageRequest;
    }

    public boolean isStatusOperation() {
        return statusOperation;
    }

    public void setStatusOperation(boolean statusOperation) {
        this.statusOperation = statusOperation;
    }

    public String getMessageRequest() {
        return messageRequest;
    }

    public void setMessageRequest(String messageRequest) {
        this.messageRequest = messageRequest;
    }
    
    
    
}
