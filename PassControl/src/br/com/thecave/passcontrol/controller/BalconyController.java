package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.BalconyScreen;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyCallNextClientRequest;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyCallNextClientResponse;
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
    private BalconyBean balconyBean = null;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.screen = (BalconyScreen) passControlPanel;
    }    

    public void recallNextClient() 
    {
//        BalconyCallNextClientRequest balconyCallNextClientRequest = new BalconyCallNextClientRequest(balconyBean);
//        ConfirmationResponse response = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(balconyCallNextClientRequest, ConfirmationResponse.class, 1000);        
//        if (response != null)
//        {
//            screen.showPanelQueueInfo(lastCalledClient);
//        }           
    }

    public void callNextClient() 
    {
        BalconyCallNextClientRequest balconyCallNextClientRequest = new BalconyCallNextClientRequest(balconyBean);
        BalconyCallNextClientResponse response = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(balconyCallNextClientRequest, BalconyCallNextClientResponse.class, 1000);
        
        if (response != null)
        {
            lastCalledClient = response;
            screen.showPanelQueueInfo(response);
        }        
    }

    public BalconyBean getBalconyBean() {
        return balconyBean;
    }

    public void setBalconyBean(BalconyBean balconyBean) {
        this.balconyBean = balconyBean;
    }

    
}
