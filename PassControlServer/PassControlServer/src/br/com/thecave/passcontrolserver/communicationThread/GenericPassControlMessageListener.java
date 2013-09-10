/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.communicationThread;

import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import java.net.Socket;

/**
 *
 * @author lopidio
 */
public class GenericPassControlMessageListener implements PassControlMessageListener{

    PassControlMessage messageReceived = null;
    
    Socket socket;

    public GenericPassControlMessageListener() {
        this.socket = null;
    }
    
    public PassControlMessage getReceivedMessage()
    {
        return messageReceived;
    }
    
    public boolean hasReceivedMessage()
    {
        return messageReceived != null;
    }

    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket) {
//        System.out.println("Retorno chegou no escutador genérico");
        messageReceived = message;
        this.socket = socket;
    }
}
