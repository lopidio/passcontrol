package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.topbar.LoginTopBar;
import br.com.thecave.passcontrol.topbar.ResetTopBar;
import br.com.thecave.passcontrolserver.messages.generic.ClientLoginReset;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
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

    public void performReset(String username)
    {
        ClientLoginReset recoverClientMessage = new ClientLoginReset(username);
        ConfirmationResponse confirmationResponse = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(recoverClientMessage, ConfirmationResponse.class, 10000);
        if (confirmationResponse != null)
        {
            if (confirmationResponse.getStatusOperation())
            {
                JOptionPane.showMessageDialog(null, confirmationResponse.getComment(), "Senha reenviada", JOptionPane.OK_OPTION);
                Main.getInstance().getMainFrame().activatePassControlTopBar(new LoginTopBar());                
            }
            else
            {
                JOptionPane.showMessageDialog(null, confirmationResponse.getComment(), "Erro", JOptionPane.ERROR_MESSAGE);                
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "conexão com o servidor comprometida", "Erro", JOptionPane.ERROR_MESSAGE);                            
        }
    }

    public void backToLoginTopbar()
    {
        Main.getInstance().getMainFrame().activatePassControlTopBar(new LoginTopBar());
    }
}
