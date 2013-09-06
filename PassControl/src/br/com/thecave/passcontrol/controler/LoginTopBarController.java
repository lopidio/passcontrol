/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrolserver.messages.generic.ClientLoginRequest;
import br.com.thecave.passcontrolserver.messages.generic.ClientLoginResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrol.screens.ButtonsModulesScreen;
import br.com.thecave.passcontrol.screens.MainFrame;
import br.com.thecave.passcontrol.topbar.LoginTopBar;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import br.com.thecave.passcontrol.topbar.ResetTopBar;
import br.com.thecave.passcontrolserver.db.bean.UserBean;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author Arleudo
 */
public class LoginTopBarController extends PassControlController 
{
    LoginTopBar loginTopBar = null;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) {
        loginTopBar = (LoginTopBar)passControlPanel;
    }
    
    
    
    public void performLogin() 
    {
        Main main = Main.getInstance();
        
                MainFrame mainFrame = main.getMainFrame();
                UserBean bean = new UserBean();
                bean.setName("Guigui");
                bean.setType(0);
                main.setCurrentUser(bean);
                mainFrame.activatePassControlPanel(new ButtonsModulesScreen());
                mainFrame.activatePassControlTopBar(new MainTopBar());
                
//        
//        Main main = Main.getInstance();
//        ClientLoginRequest initRequest = new 
//                ClientLoginRequest(MessageActors.NotIdentified, loginTopBar.getUserName(), loginTopBar.getUserPassword());
//        ClientLoginResponse clientLoginResponse = (ClientLoginResponse)main.getCommunicationThread().sendMessageAndWaitForResponseOrTimeout(initRequest, "ClientLoginResponse", 3000);
//        if (clientLoginResponse != null)
//        {
//            if (clientLoginResponse.getUser() != null)
//            {
//                MainFrame mainFrame = main.getMainFrame();
//                main.setCurrentUser(clientLoginResponse.getUser());
//                mainFrame.activatePassControlPanel(new ButtonsModulesScreen());
//                mainFrame.activatePassControlTopBar(new MainTopBar());
//                
//            }
//            else
//            {
//                loginTopBar.incorrectUser();
//            }
//        }
//        else
//        {
//
//            JOptionPane.showMessageDialog(null, "Tempo de conexão expirada!", "Erro", JOptionPane.ERROR_MESSAGE);
//        }
        
    }

    public void resetPassword() 
    {
        //Simples assim
        Main.getInstance().getMainFrame().activatePassControlTopBar(new ResetTopBar());
    }
    
}
