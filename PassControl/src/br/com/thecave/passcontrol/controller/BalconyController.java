package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.BalconyScreen;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyCallNextClientRequest;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyFinalizeCurrentClient;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.balcony.BalconySkipCurrentClient;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class BalconyController extends PassControlController
{
    
    /**
     * FLUXO:
     * 
     * Para chamar um cliente:
     *  Enviar: BalconyCallNextClientRequest
     *  Retorna um: ConfirmationResponse
     * 
     * Fazer o controller escutar: BalconyShowClientMessage
     *  E mostrar na tela toda vez que escutar um...
     * 
     * Para rechamar, é só reenviar um BalconyShowClientMessage
     */    
        

    private BalconyScreen screen;
    private BalconyShowClientMessage lastCalledClient = null;
    private BalconyBean balconyBean = null;
    private QueuesManagerBean queuesManagerBean = null;

    @Override
    public void setPassControlPanel( JPanel passControlPanel )
    {
        this.screen = (BalconyScreen) passControlPanel;
    }

    public void recallNextClient()
    {
        Main.getInstance().getCommunicationThread().addBroadcastToSend(lastCalledClient);
        showBalconyClient(lastCalledClient);
    }

    public boolean callNextClient()
    {
        queuesManagerBean = null;
        BalconyCallNextClientRequest balconyCallNextClientRequest = new BalconyCallNextClientRequest(balconyBean);
        ConfirmationResponse response = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(balconyCallNextClientRequest, ConfirmationResponse.class, 3000);
        if ( response != null )
        {
            JOptionPane.showMessageDialog(null, response.getComment(), "Por favor, aguarde;", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    }

    public BalconyBean getBalconyBean()
    {
        return balconyBean;
    }

    public void setBalconyBean( BalconyBean balconyBean )
    {
        this.balconyBean = balconyBean;
    }
    
    @Override
    public void onMessageReceive( PassControlMessage message, Socket socket )
    {
        //Recebo um cliente para atender...
        //Mostra o cliente
        BalconyShowClientMessage received = (BalconyShowClientMessage) message;
        queuesManagerBean = received.getQueuesManagerBean();
        showBalconyClient(received);
    }

    @Override
    public void addMessageListeners()
    {
        Main.getInstance().getCommunicationThread().addMessageListener(this, BalconyShowClientMessage.class);
    }

    @Override
    public void removeMessageListeners()
    {
        Main.getInstance().getCommunicationThread().removeListener(this, BalconyShowClientMessage.class);
    }

    private void showBalconyClient( BalconyShowClientMessage showClientMessage )
    {
        lastCalledClient = showClientMessage;
        lastCalledClient.setFrom(MessageActors.BalconyActor);
        lastCalledClient.setTo(MessageActors.ServerActor);
        screen.showPanelQueueInfo(showClientMessage);
    }    

    public boolean finalizeServiceClient()
    {
        BalconyFinalizeCurrentClient finalizeCurrentClient = new BalconyFinalizeCurrentClient(queuesManagerBean);
        ConfirmationResponse response = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(finalizeCurrentClient, ConfirmationResponse.class, 3000);
    
        if(response == null)
        {
            JOptionPane.showMessageDialog(null, "Conexão com o servidor comprometida!");
        }
        else
        {
            if(response.getStatusOperation())
            {
                JOptionPane.showMessageDialog(null, "Atendimento encerrado!");
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, response.getComment());
            }
        }
        return false;        
    }

    public boolean putClientOnWaitting()
    {
        BalconySkipCurrentClient skipCurrentClient = new BalconySkipCurrentClient(queuesManagerBean);
        ConfirmationResponse response = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(skipCurrentClient, ConfirmationResponse.class, 3000);
    
        if(response == null)
        {
            JOptionPane.showMessageDialog(null, "Conexão com o servidor comprometida!");
        }
        else
        {
            if(response.getStatusOperation())
            {
                JOptionPane.showMessageDialog(null, "Cliente colocado na espera!");
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, response.getComment());
            }
        }
        return false;
    }
}
