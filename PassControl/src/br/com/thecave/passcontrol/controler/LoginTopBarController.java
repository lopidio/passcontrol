/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.messages.PassControlMessage;
import java.net.Socket;

/**
 *
 * @author Arleudo
 */
public class LoginTopBarController extends PassControlController
{

    public void performLogin() 
    {
        //TODO: implement
        //Main.getInstance().getMainScreen().activatePassControl(new ChooseModulesScreen());
        //Main.getInstance().getMainScreen().setTopBar(new MainTopBar());
    }

    public void resetPassword() 
    {
        //Main.getInstance().getMainScreen().setTopBar(new ResetTopBar());
        //TODO: implements
    }

    @Override
    public void addMessageListeners() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
