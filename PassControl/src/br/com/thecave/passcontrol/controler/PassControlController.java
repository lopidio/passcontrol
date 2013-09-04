/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.messages.PassControlMessage;
import br.com.thecave.passcontrol.messages.PassControlMessageListener;
import java.net.Socket;

/**
 *
 * @author guilherme
 */
public abstract class PassControlController implements PassControlMessageListener
{
   
    public void addMessageListeners()
    {
        //hook
    }

    public void removeMessageListeners()
    {
        //hook
    }

    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket) {
        //hook
    }
    
}
