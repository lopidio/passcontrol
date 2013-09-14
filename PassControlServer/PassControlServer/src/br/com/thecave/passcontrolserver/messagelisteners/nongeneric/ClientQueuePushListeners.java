/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.nongeneric;

import br.com.thecave.passcontrolserver.PassControlServer;
import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.messagelisteners.generic.ClientListeners;

/**
 *
 * @author guilherme
 */
public class ClientQueuePushListeners implements ClientListeners
{
    @Override
    public void addListenersCallback()
    {
        ServerCommunicationThread server = PassControlServer.getInstance().getServer();
//        server.addMessageListener(new QueuePusherAddClientListeners.BalconyInitListener(), QueuePusherAddClient.class);
    }    
}
