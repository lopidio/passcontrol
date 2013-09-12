/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.communicationThread;

import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author lopidio
 */
public class GenericBufferPassControlMessageListener implements PassControlMessageListener
{

    HashMap<PassControlMessage, Socket> messagesReceived = null;
    
    int messagesToWait;

    public GenericBufferPassControlMessageListener(int messagesToWait) {
        messagesReceived = new HashMap<>(messagesToWait);
        this.messagesToWait = messagesToWait;
    }

    public boolean hasReceivedAllMessages()
    {
        return messagesReceived.size() == messagesToWait;
    }

    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket) {
        messagesReceived.put(message, socket);
    }

    public HashMap<PassControlMessage, Socket> getMessagesReceived() {
        return messagesReceived;
    }
    
}
