package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrolserver.communicationThread.StatusConnectionListener;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrol.screens.DefaultScreen;
import br.com.thecave.passcontrol.topbar.LoginTopBar;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import java.net.Socket;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class MainTopBarController extends PassControlController
{
    MainTopBar mainTopBar = null;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) {
        mainTopBar = (MainTopBar)passControlPanel;
    }  
    
    public void performlogout() 
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new DefaultScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new LoginTopBar());
    }
    
}
