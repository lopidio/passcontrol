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
public class MainTopBarController extends PassControlController implements StatusConnectionListener
{
    MainTopBar mainTopBar = null;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) {
        mainTopBar = (MainTopBar)passControlPanel;
    }
    
    @Override
    public void addMessageListeners() {
        Main.getInstance().getCommunicationThread().addStatusConnectionListeners(this);
    }

    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket) {
        Main.getInstance().getCommunicationThread().removeStatusConnectionListener(this);
    }    
    
    @Override
    public void onChangeConnection(boolean connectionStatus) 
    {
        mainTopBar.setConnectionIcon(connectionStatus);
        if (connectionStatus)
        {
            Main.getInstance().getMainFrame().enableControlPanel();
        }
        else
        {
            Main.getInstance().getMainFrame().disableControlPanel();            
        }
    }
    
    public void performlogout() 
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new DefaultScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new LoginTopBar());
    }
    
}
