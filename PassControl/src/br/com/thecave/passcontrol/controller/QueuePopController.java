package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.component.util.QueueElementInfo;
import br.com.thecave.passcontrol.screens.QueuePopScreen;
import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.queuepopper.QueuePopperChooseNextElement;
import br.com.thecave.passcontrolserver.messages.queuepopper.QueuePopperNewClientAdded;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class QueuePopController extends PassControlController implements QueueElementInfo.QueueElementInfoMouseClickedListener
{
    QueuePopScreen queuePopScreen;

    @Override
    public void setPassControlPanel( JPanel passControlPanel )
    {
        this.queuePopScreen = (QueuePopScreen) passControlPanel;
    }

    @Override
    public void addMessageListeners() 
    {
        Main.getInstance().getCommunicationThread().addMessageListener(this, QueuePopperChooseNextElement.class);
        Main.getInstance().getCommunicationThread().addMessageListener(this, QueuePopperNewClientAdded.class);
    }

    @Override
    public void removeMessageListeners() 
    {
        Main.getInstance().getCommunicationThread().addMessageListener(this, QueuePopperChooseNextElement.class);
        Main.getInstance().getCommunicationThread().addMessageListener(this, QueuePopperNewClientAdded.class);
    }

    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket) 
    {
        //O servidor está avisando que chegou mais um cliente, simples assim
        if (message instanceof QueuePopperNewClientAdded)
        {
            QueuePopperNewClientAdded queuePopperNewClientAdded = (QueuePopperNewClientAdded)message;
            addBalconyShowClient(queuePopperNewClientAdded);
        }
        else if (message instanceof QueuePopperChooseNextElement)
        {
            QueuePopperChooseNextElement addQueueElement = (QueuePopperChooseNextElement)message;
            
            //Informo quais o usuário pode escolher
            enablePossibleChoices(addQueueElement.getManagerBeans());
        }
    }

    @Override
    public void initialize() {
        super.initialize(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addBalconyShowClient(QueuePopperNewClientAdded queuePopperNewClientAdded)
    {
        //Crio um QueueInfoPanel        
        QueueElementInfo queueElementInfo = new QueueElementInfo(queuePopperNewClientAdded.getClientName(), 
                                                                queuePopperNewClientAdded.getServiceType(), 
                                                                queuePopperNewClientAdded.getQueuesManagerBean().getPassNumber(),
                                                                queuePopperNewClientAdded.getBalconyNumber());
        
        //Aramazeno na fila correta
        Box queueAreaToAdd = queuePopScreen.getQueuesArea().get(queuePopperNewClientAdded.getPriority());
        queueAreaToAdd.add(queueElementInfo);
        queueAreaToAdd.revalidate();
        queueAreaToAdd.repaint();
        //Crio o evento de clique
        queueElementInfo.setMouseClickedListener(this);
    }

    @Override
    public void mouseClicked(QueueElementInfo queueElementInfo) 
    {
        sendChosenElementToServer(queueElementInfo);
    }
        
    private void enablePossibleChoices(HashMap<Integer, ArrayList<QueuesManagerBean>> avaliableChoices)
    {
        //desseleciono todos os bichos
        queuePopScreen.disableAllQueueElementInfo();
        
        //Seleciono só os passíveis de escolha
        for (Map.Entry<Integer, ArrayList<QueuesManagerBean>> entry : avaliableChoices.entrySet())
        {
            Integer priority = entry.getKey();
            for (QueuesManagerBean queuesManagerBean : entry.getValue()) 
            {
                QueueElementInfo queueElementInfo = queuePopScreen.findQueueElementInfoFromPassNumber(queuesManagerBean.getPassNumber());
                if (queueElementInfo != null)
                {
                    queueElementInfo.setEnabled(true);
                    queueElementInfo.setQueuesManagerBean(queuesManagerBean);
                }
            }            
        }

    }
    
    private void sendChosenElementToServer(QueueElementInfo queueElementInfo)
    {
        //removo esse da fila
        Box parent = (Box) queueElementInfo.getParent();
        //repaint e revalidate
        parent.revalidate();
        parent.repaint();
        
        //Informo ao servidor qual foi o meu escolhido
        BalconyShowClientMessage balconyShowClientMessage = new BalconyShowClientMessage(queueElementInfo.getClientName(),
                queueElementInfo.getQueueName(), queueElementInfo.getBalconyName(), queueElementInfo.getQueuesManagerBean(), MessageActors.QueuePopActor, MessageActors.ServerActor);

        //E espera 3 segundos
        ConfirmationResponse confirmationResponse = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(balconyShowClientMessage, ConfirmationResponse.class, 3000);
        
        if (confirmationResponse != null)
        {
            if (confirmationResponse.getStatusOperation())
            {
                JOptionPane.showConfirmDialog(null, "Operação bem sucedida");
            }
            else
            {
                JOptionPane.showMessageDialog(null, confirmationResponse.getComment());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Comunicação com o servidor expirada");
        }
    }
    
}
