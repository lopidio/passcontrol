/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.component.util.QueueElementInfo;
import br.com.thecave.passcontrol.topbar.ViewTopBar;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.viewer.ViewerQueueRequest;
import br.com.thecave.passcontrolserver.messages.viewer.ViewerQueueResponse;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author lopidio
 */
public class ViewTopBarController extends PassControlController
{
    ViewTopBar viewTopBar;
    ArrayList<QueueElementInfo> queueElementInfos;

    public ViewTopBarController() 
    {
        queueElementInfos = new ArrayList<>();
    }

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        viewTopBar = (ViewTopBar)passControlPanel;
        
    }

    @Override
    public void addMessageListeners() 
    {
        Main.getInstance().getCommunicationThread().addMessageListener(this, BalconyShowClientMessage.class);
    }

    @Override
    public void removeMessageListeners() {
        Main.getInstance().getCommunicationThread().removeListener(this, BalconyShowClientMessage.class);
    }

    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket) 
    {
        BalconyShowClientMessage showClientMessage = (BalconyShowClientMessage)message;
        //Identifica o que é da mesma fila
        boolean flagFound = false;
        for (QueueElementInfo queueElementInfo : queueElementInfos) 
        {
            if (queueElementInfo.getQueueName().equals(showClientMessage.getServiceType()))
            {
                queueElementInfo.setBalconyName(showClientMessage.getBalconyNumber());
                queueElementInfo.setClientName(showClientMessage.getClientName());
                queueElementInfo.setUserPass(showClientMessage.getQueuesManagerBean().getPassNumber());
                flagFound = true;
                break;
            }
        }
        //Vou ter que criar um novo elemento
        if (!flagFound)
        {
                QueueElementInfo queueElementInfo = new QueueElementInfo(showClientMessage.getClientName(),
                                                                        showClientMessage.getServiceType(),
                                                                        showClientMessage.getQueuesManagerBean().getPassNumber(), 
                                                                        showClientMessage.getBalconyNumber());
                queueElementInfos.add(queueElementInfo);
        }
        //Insere no exibidor
        synchronizeQueuesListToPanel();
    }

    @Override
    public void initialize() 
    {
        //Informo ao server que quero todos os últimos clientes de hoje de cada serviço
        ViewerQueueRequest viewerQueueRequest = new ViewerQueueRequest();
        ViewerQueueResponse viewerQueueResponse = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(viewerQueueRequest, ViewerQueueResponse.class, 3000);
        if (viewerQueueResponse != null)
        {
            for (BalconyShowClientMessage balconyShowClientMessage : viewerQueueResponse.getLastCalledClients()) 
            {
                QueueElementInfo queueElementInfo = new QueueElementInfo(balconyShowClientMessage.getClientName(),
                                                                        balconyShowClientMessage.getServiceType(),
                                                                        balconyShowClientMessage.getQueuesManagerBean().getPassNumber(), 
                                                                        balconyShowClientMessage.getBalconyNumber());
                queueElementInfos.add(queueElementInfo);
            }
            //Insere no exibidor
            synchronizeQueuesListToPanel();
        }
    }
    
    private void synchronizeQueuesListToPanel()
    {
        JPanel scrollablePanel = viewTopBar.getScrollableQueueInfoPanel();
        scrollablePanel.removeAll();
        for (QueueElementInfo queueElementInfo : queueElementInfos) 
        {
            scrollablePanel.add(queueElementInfo);
        }
    }
    
    
    
}
