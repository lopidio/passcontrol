package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.BalconyScreen;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyCallNextClientRequest;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientInit;
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
     * Para rechamar, é só reenviar um BalconyShowClientMessage não tem resposta!!
     */    
        

    private BalconyScreen screen;
    private BalconyShowClientMessage lastCalledClient = null;
    private BalconyBean balconyBean = null;

    @Override
    public void setPassControlPanel( JPanel passControlPanel )
    {
        this.screen = (BalconyScreen) passControlPanel;
    }

    public void recallNextClient()
    {

        ConfirmationResponse response = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(lastCalledClient, ConfirmationResponse.class, 1000);
        if ( response != null )
        {
            if ( response.getStatusOperation() )
            {
                showBalconyClient(lastCalledClient);
            }
        }
    }

    public void callNextClient()
    {
        BalconyCallNextClientRequest balconyCallNextClientRequest = new BalconyCallNextClientRequest(balconyBean);
        ConfirmationResponse response = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(balconyCallNextClientRequest, ConfirmationResponse.class, 1000);
        if ( response != null )
        {
            if ( response.getStatusOperation() )
            {
                JOptionPane.showMessageDialog(null, "Aguardando próximo cliente", "Por favor, aguarde;", JOptionPane.INFORMATION_MESSAGE);
            }
        }
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
}
