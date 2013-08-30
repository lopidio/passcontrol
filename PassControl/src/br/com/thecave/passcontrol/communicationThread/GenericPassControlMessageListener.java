/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

import br.com.thecave.passcontrol.messages.PassControlConnectionPacket;
import br.com.thecave.passcontrol.messages.PassControlConnectionMessageListener;

/**
 *
 * @author lopidio
 */
public class GenericPassControlMessageListener implements PassControlConnectionMessageListener{

    PassControlConnectionPacket messageReceived = null;
    
    public PassControlConnectionPacket getReceivedMessage()
    {
        return messageReceived;
    }
    
    public boolean hasReceivedMessage()
    {
        return messageReceived != null;
    }

    @Override
    public void onMessageReceive(PassControlConnectionPacket message) {
        messageReceived = message;
    }
}
