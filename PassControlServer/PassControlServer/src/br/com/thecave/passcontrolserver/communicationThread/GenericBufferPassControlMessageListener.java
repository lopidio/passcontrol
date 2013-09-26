/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.communicationThread;

import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author lopidio
 */
public class GenericBufferPassControlMessageListener implements PassControlMessageListener
{

    HashMap<Socket, PassControlMessage> messagesReceived = null;
    
    int messagesToWaitCount;

    GenericBufferPassControlMessageListener(int messagesToWaitCount) 
    {
        this.messagesToWaitCount = messagesToWaitCount;
        messagesReceived = new HashMap<>(messagesToWaitCount);
    }

    public boolean hasReceivedAllMessages()
    {
        return messagesReceived.size() == messagesToWaitCount;
    }

    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket) 
    {
        messagesReceived.put(socket, message);
    }

    public HashMap<Socket, PassControlMessage> getMessagesReceived() {
        return messagesReceived;
    }
    
}
