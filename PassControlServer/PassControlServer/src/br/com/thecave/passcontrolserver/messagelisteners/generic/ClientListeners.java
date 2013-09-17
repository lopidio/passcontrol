/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.generic;

import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;

/**
 *
 * @author guilherme
 */
public interface ClientListeners 
{
    public abstract void addListenersCallback(ServerCommunicationThread server);
}
