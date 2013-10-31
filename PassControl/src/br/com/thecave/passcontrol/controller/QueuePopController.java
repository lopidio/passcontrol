package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.component.util.QueueElementInfoSmall;
import br.com.thecave.passcontrol.screens.QueuePopScreen;
import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.queuepopper.QueuePopperChooseNextElement;
import br.com.thecave.passcontrolserver.messages.queuepopper.QueuePopperNewClientAdded;
import br.com.thecave.passcontrolserver.messages.viewer.QueuePopperRefreshResponse;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class QueuePopController extends PassControlController implements QueueElementInfoSmall.QueueElementInfoMouseClickedListener
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
        Main.getInstance().getCommunicationThread().addMessageListener(this, QueuePopperRefreshResponse.class);
    }

    @Override
    public void removeMessageListeners() 
    {
        Main.getInstance().getCommunicationThread().removeListener(this, QueuePopperChooseNextElement.class);
        Main.getInstance().getCommunicationThread().removeListener(this, QueuePopperNewClientAdded.class);
        Main.getInstance().getCommunicationThread().removeListener(this, QueuePopperRefreshResponse.class);
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
            //Existe um balcony livre
            QueuePopperChooseNextElement addQueueElement = (QueuePopperChooseNextElement)message;
            
            //Informo quais o usuário pode escolher
            enablePossibleChoices(addQueueElement.getManagerBeans());
            
            queuePopScreen.setWaitingBalconyName(addQueueElement.getBalconyBean().getNumber());
        }
        else if (message instanceof QueuePopperRefreshResponse)        
        {   
            //Limpa tudo e começa do zero
            queuePopScreen.clearAllQueues();
            QueuePopperRefreshResponse refreshResponse = (QueuePopperRefreshResponse)message;            
            for (QueuePopperNewClientAdded queuePopperNewClientAdded  : refreshResponse.getQueuePopperNewClientAdded()) 
            {
                addBalconyShowClient(queuePopperNewClientAdded);
            }
            
        }
    }
    
    public void addBalconyShowClient(QueuePopperNewClientAdded queuePopperNewClientAdded)
    {
        //Crio um QueueInfoPanel        
        QueueElementInfoSmall queueElementInfo = new QueueElementInfoSmall(queuePopperNewClientAdded.getClientName(), 
                                                                queuePopperNewClientAdded.getServiceBean().getName(),
                                                                queuePopperNewClientAdded.getQueuesManagerBean().getPassNumber(),
                                                                queuePopperNewClientAdded.getBalconyNumber());
        //O elemento já vem com o ID!!
        queueElementInfo.setQueuesManagerBean(queuePopperNewClientAdded.getQueuesManagerBean());
        queueElementInfo.setEnabled(false);

        //Adiciona na lista
        queuePopScreen.getQueueElementsInfo().add(queueElementInfo);
        
        //Aramazeno na fila correta
        JPanel queueAreaToAdd = queuePopScreen.getQueuesArea().get(queuePopperNewClientAdded.getServiceBean().getPriority() - 1);
//        queueAreaToAdd.add(Box.createHorizontalGlue());
        queueAreaToAdd.add(queueElementInfo);
//        queueAreaToAdd.add(Box.createHorizontalGlue());
//        queueAreaToAdd.add(new Label("botão"));
        queueAreaToAdd.revalidate();
        queueAreaToAdd.repaint();
        //Crio o evento de clique
        queueElementInfo.setMouseClickedListener(this);
    }

    @Override
    public void mouseClicked(QueueElementInfoSmall queueElementInfo) 
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
            for (QueuesManagerBean queuesManagerBean : entry.getValue()) 
            {
                QueueElementInfoSmall queueElementInfo = queuePopScreen.findQueueElementInfoFromQueueElementId(queuesManagerBean.getId());
                if (queueElementInfo != null)
                {
                    queueElementInfo.setEnabled(true);
                }
            }            
        }

    }
    
    private void sendChosenElementToServer(QueueElementInfoSmall queueElementInfo)
    {
        //removo esse da fila
        if (queueElementInfo.isEnabled())
        {
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
                //Remove
                queuePopScreen.getQueueElementsInfo().remove(queueElementInfo);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Comunicação com o servidor expirada");
            }
        }
    }
    
}
