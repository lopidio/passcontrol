/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.messages.PassControlMessage;
import br.com.thecave.passcontrol.topbar.ResetTopBar;
import java.net.Socket;

/**
 *
 * @author Arleudo
 */
public class ResetTopBarControler extends PassControlController
{

    public void performLogin() 
    {
        //TODO: implements
    }

    @Override
    public void addMessageListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeMessageListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
