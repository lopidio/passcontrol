/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.component.util.AnimationUtil;
import br.com.thecave.passcontrol.component.util.AnimationUtilObserver;
import br.com.thecave.passcontrol.component.util.QueueElementInfo;
import br.com.thecave.passcontrol.topbar.ViewerTopBar;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.viewer.ViewerQueueRequest;
import br.com.thecave.passcontrolserver.messages.viewer.ViewerQueueResponse;
import java.awt.Rectangle;
import java.net.Socket;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author lopidio
 */
public class ViewerTopBarController extends PassControlController implements AnimationUtilObserver
{
    ViewerTopBar viewerTopBar;
    ArrayList<QueueElementInfo> queueElementInfos;
    AnimationUtil queueElementAnimator = null;

    public ViewerTopBarController() 
    {
        queueElementInfos = new ArrayList<>();
    }

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        viewerTopBar = (ViewerTopBar)passControlPanel;
        
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
                QueueElementInfo newQueueElementInfo = new QueueElementInfo(showClientMessage.getClientName(),
                                                                        showClientMessage.getServiceType(),
                                                                        showClientMessage.getQueuesManagerBean().getPassNumber(), 
                                                                        showClientMessage.getBalconyNumber());
        //Acho o que eu tenho que remover (o último da mesma fila, caso exista)
        for (QueueElementInfo queueElementInfo : queueElementInfos) 
        {
            if (queueElementInfo.getQueueName().equals(showClientMessage.getServiceType()))
            {
                queueElementInfos.remove(queueElementInfo);
                break;
            }
        }
        queueElementInfos.add(newQueueElementInfo);
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
    
    //Chegou um novo cliente, preciso refazer tudo
    private void synchronizeQueuesListToPanel()
    {
        JPanel scrollablePanel = viewerTopBar.getScrollableQueueInfoPanel();
        scrollablePanel.removeAll();
        for (QueueElementInfo queueElementInfo : queueElementInfos) 
        {
            scrollablePanel.add(queueElementInfo);
        }  
        scrollablePanel.revalidate();
        scrollablePanel.repaint();
        
        //Para aev     animação anterior
        if (queueElementAnimator != null)
            queueElementAnimator.stop();
            
        java.util.Timer timer = new java.util.Timer();
        timer.schedule( new TimerTask(){
           @Override
           public void run() { 
                startAnimation();
           }
         }, 5000); //Sempre que um novo elemento é adicionado, passa-se 5 segundos com a animação parada  
    }
    
    private void startAnimation()
    {
        //Utilizado para definir a duração da animação
        final int millisecondsPerExcedentPixel = 10;
        JPanel scrollablePanel = viewerTopBar.getScrollableQueueInfoPanel();        
        int totalLargura = queueElementInfos.size()*QueueElementInfo.SIZE.width;
        System.out.println("Qtde: " + queueElementInfos.size() + " Tamanho: " + totalLargura + " Máximo: " + ViewerTopBar.SCROLL_PANEL_WITDH);
        
        //Só precisa animar caso os elementos não caibam mais no espaço definido na tela
        if (totalLargura > ViewerTopBar.SCROLL_PANEL_WITDH)
        {
            int excesso = totalLargura - ViewerTopBar.SCROLL_PANEL_WITDH;
            int duration = millisecondsPerExcedentPixel*excesso;            
            System.out.println("duration:" + duration);            
            scrollablePanel.setSize(totalLargura, scrollablePanel.getHeight());
            //130, posição x do pai (outter)
            //segundos por elemento
            Rectangle from = new Rectangle(0, 0, scrollablePanel.getWidth(), scrollablePanel.getHeight());
            Rectangle to = new Rectangle(-excesso, 0, scrollablePanel.getWidth(), scrollablePanel.getHeight());            
            //Para a animação anterior
            if (queueElementAnimator != null)
                queueElementAnimator.stop();
            queueElementAnimator = new AnimationUtil(scrollablePanel, from, to);
            queueElementAnimator.setRunTime(duration);
            queueElementAnimator.setObserver(this);
            queueElementAnimator.start();   
        }
        
    }

    @Override
    public void onAnimationEnd() 
    {
        
        //Anima novamente daqui a 3 segundos
        java.util.Timer timer = new java.util.Timer();
        timer.schedule( new TimerTask(){
           @Override
           public void run() { 
                startAnimation();
           }
         }, 3000); //A animação passa 3 segundos parada, antes de reiniciar  
    }
    
}
