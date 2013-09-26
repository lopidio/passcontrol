package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.topbar.LoginTopBar;
import br.com.thecave.passcontrol.topbar.ResetTopBar;
import br.com.thecave.passcontrolserver.communicationThread.ClientCommunicationThread;
import br.com.thecave.passcontrolserver.messages.generic.ClientLoginReset;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class ResetTopBarControler extends PassControlController
{

    ResetTopBar resetTopBar = null;

    @Override
    public void setPassControlPanel( JPanel passControlPanel )
    {
        resetTopBar = (ResetTopBar) passControlPanel;
    }

    public void performReset(String name)
    {
        ClientLoginReset loginReset = new ClientLoginReset(MessageActors.AllActors, name);
        ClientCommunicationThread thread = Main.getInstance().getCommunicationThread();
        ConfirmationResponse response = thread.sendMessageToServerAndWaitForResponseOrTimeout(loginReset, ConfirmationResponse.class, 2000);
        
        if(response == null)
        {
            JOptionPane.showMessageDialog(null, "Conexão com servidor comprometida!");
        }
        else
        {
            if(response.getStatusOperation())
            {
                JOptionPane.showMessageDialog(null, "Sua senha foi enviada para o email cadastrado");
            }
            else
            {
                JOptionPane.showMessageDialog(null, response.getComment());
            }
        }
        
        Main.getInstance().getMainFrame().activatePassControlTopBar(new LoginTopBar());
    }

    public void backToLoginTopbar()
    {
        Main.getInstance().getMainFrame().activatePassControlTopBar(new LoginTopBar());
    }
}
