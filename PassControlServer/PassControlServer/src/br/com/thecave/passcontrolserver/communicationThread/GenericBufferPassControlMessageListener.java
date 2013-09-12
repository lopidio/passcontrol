/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.communicationThread;

import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author lopidio
 */
public class GenericBufferPassControlMessageListener implements PassControlMessageListener
{

    ArrayList<PassControlMessage> messagesReceived = null;
    
    int messagesToWait;

    public GenericBufferPassControlMessageListener(int messagesToWait) {
        messagesReceived = new ArrayList<>(messagesToWait);
        this.messagesToWait = messagesToWait;
    }
    
    public ArrayList<PassControlMessage> getReceivedMessage()
    {
        return messagesReceived;
    }
    
    public boolean hasReceivedAllMessages()
    {
        return messagesReceived.size() == messagesToWait;
    }

    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket) {
        messagesReceived.add(message);
    }

    public ArrayList<PassControlMessage> getMessagesReceived() {
        return messagesReceived;
    }
    
}
