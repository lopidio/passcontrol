/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.messages;

/**
 *
 * @author lopidio
 */
public class GenericPassControlMessageListener implements PassControlMessageListener{

    PassControlMessage messageReceived = null;
    
    @Override
    public void onMessageReceive(PassControlMessage message) {
        messageReceived = message;
    }

    public PassControlMessage getReceivedMessage()
    {
        return messageReceived;
    }
    
    public boolean hasReceivedMessage()
    {
        return messageReceived != null;
    }
}
