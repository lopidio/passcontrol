package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.BalconyScreen;
import br.com.thecave.passcontrol.screens.ButtonsModulesScreen;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyCallNextClientRequest;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyCallNextClientResponse;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyLogin;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class BalconyController extends PassControlController
{
    BalconyScreen screen;
    
    BalconyCallNextClientResponse lastCalledClient = null;
    
    /**
     * ID do guichê
     */
    private String balconyID = null;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.screen = (BalconyScreen) passControlPanel;
    }    

    public void recallNextClient() 
    {
        BalconyCallNextClientRequest balconyCallNextClientRequest = new BalconyCallNextClientRequest(balconyID);
        ConfirmationResponse response = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(balconyCallNextClientRequest, ConfirmationResponse.class, 1000);        
        if (response != null)
        {
            screen.showPanelQueueInfo(lastCalledClient);
        }           
    }

    public void callNextClient() 
    {
        BalconyCallNextClientRequest balconyCallNextClientRequest = new BalconyCallNextClientRequest(balconyID);
        BalconyCallNextClientResponse response = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(balconyCallNextClientRequest, BalconyCallNextClientResponse.class, 1000);
        
        if (response != null)
        {
            lastCalledClient = response;
            screen.showPanelQueueInfo(response);
        }        
    }

    public void setBalconyID(String balconyID) {
        this.balconyID = balconyID;
    }
}
