/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.screens.DefaultScreen;
import br.com.thecave.passcontrol.topbar.LoginTopBar;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import java.net.Socket;
import javax.swing.JPanel;

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
    
    public void performLogout()
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new DefaultScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new LoginTopBar());
    }
    
    public void performExit()
    {
        System.exit(0);
    }
    
    public abstract void setPassControlPanel(JPanel passControlPanel);
    
    public abstract void performBack();
}
