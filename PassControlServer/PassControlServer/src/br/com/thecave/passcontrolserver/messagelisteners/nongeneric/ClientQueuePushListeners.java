/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.nongeneric;

import br.com.thecave.passcontrolserver.PassControlServer;
import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;

/**
 *
 * @author guilherme
 */
public class ClientQueuePushListeners 
{
    public static void addListenersCallback()
    {
        ServerCommunicationThread server = PassControlServer.getInstance().getServer();
//        server.addMessageListener(new QueuePusherAddClientListeners.BalconyInitListener(), QueuePusherAddClient.class);
    }    
}
