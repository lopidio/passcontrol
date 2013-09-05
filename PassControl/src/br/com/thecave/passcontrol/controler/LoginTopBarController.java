/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.messages.ClientLoginRequest;
import br.com.thecave.passcontrol.messages.ClientLoginResponse;
import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.screens.ChooseModulesScreen;
import br.com.thecave.passcontrol.screens.MainFrame;
import br.com.thecave.passcontrol.topbar.LoginTopBar;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import br.com.thecave.passcontrol.topbar.ResetTopBar;

/**
 *
 * @author Arleudo
 */
public class LoginTopBarController extends PassControlController 
{
    
    
    public void performLogin(LoginTopBar loginTopBar) 
    {
//        Main main2 = Main.getInstance();
//                MainFrame mainFrame2 = main2.getMainFrame();
//                mainFrame2.activatePassControlPanel(new ChooseModulesScreen());
//                mainFrame2.activatePassControlTopBar(new MainTopBar());

        Main main = Main.getInstance();
        ClientLoginRequest initRequest = new 
                ClientLoginRequest(MessageActors.NotIdentified, loginTopBar.getUserName(), loginTopBar.getUserPassword());
        ClientLoginResponse clientLoginResponse = (ClientLoginResponse)main.getCommunicationThread().sendMessageAndWaitForResponseOrTimeout(initRequest, "ClientLoginResponse", 1000);
        if (clientLoginResponse != null)
        {
            if (clientLoginResponse.getUser() != null)
            {
                MainFrame mainFrame = main.getMainFrame();
                mainFrame.activatePassControlPanel(new ChooseModulesScreen());
                mainFrame.activatePassControlTopBar(new MainTopBar());
                
            }
            else
            {
                loginTopBar.incorrectUser();
            }
        }
        else
        {

            //TODO criar popup aqui
            System.out.println("TIME OUT!");
        }
        
    }

    public void resetPassword() 
    {
        //Simples assim
        Main.getInstance().getMainFrame().activatePassControlTopBar(new ResetTopBar());
    }
    
}
