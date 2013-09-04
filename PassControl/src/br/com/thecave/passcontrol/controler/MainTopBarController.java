package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.communicationThread.ClientCommunicationThread;
import br.com.thecave.passcontrol.communicationThread.StatusConnectionListener;
import br.com.thecave.passcontrol.messages.PassControlMessage;
import br.com.thecave.passcontrol.screens.DefaultScreen;
import br.com.thecave.passcontrol.topbar.LoginTopBar;
import java.net.Socket;

/**
 *
 * @author Arleudo
 */
public class MainTopBarController extends PassControlController implements StatusConnectionListener
{

    @Override
    public void addMessageListeners() {
        Main.getInstance().getCommunicationThread().addStatusConnectionListeners(this);
    }

    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket) {
        Main.getInstance().getCommunicationThread().removeStatusConnectionListener(this);
    }

    
    
    @Override
    public void onChangeConnection(boolean connectionStatus) {
//        
    }
    
    public void performlogout() 
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new DefaultScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new LoginTopBar());
    }
    
}
