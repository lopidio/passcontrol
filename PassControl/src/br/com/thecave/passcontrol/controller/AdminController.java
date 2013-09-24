package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.admin.AdminScreen;
import br.com.thecave.passcontrol.screens.admin.BalconyCrud;
import br.com.thecave.passcontrol.screens.admin.ServiceCrud;
import br.com.thecave.passcontrol.screens.admin.UserCrud;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorSetAutomaticQueueChooser;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MainImageSetter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Arleudo
 */
public class AdminController extends PassControlController
{

    AdminScreen adminScreen;

    @Override
    public void setPassControlPanel( JPanel passControlPanel )
    {
        this.adminScreen = (AdminScreen) passControlPanel;
    }

    public void openBalconyCrud()
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new BalconyCrud());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }

    public void openUserCrud()
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new UserCrud());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }

    public void openServiceCrud()
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new ServiceCrud());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }

    public boolean alterImage()
    {
        // altera a imagem da tela
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if ( returnVal == JFileChooser.APPROVE_OPTION )
        {
            ImageIcon imageIcon = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
            MainImageSetter mainImageSetter = new MainImageSetter(imageIcon);

            ConfirmationResponse response = Main.getInstance().getCommunicationThread().
                    sendMessageToServerAndWaitForResponseOrTimeout(mainImageSetter, ConfirmationResponse.class, 2000);
            if (response != null)
            {
                return response.getStatusOperation();
            }
        }
         
        return false;
    }

    public boolean setQueueAutomaticChooser(boolean selected) 
    {
        AdministratorSetAutomaticQueueChooser automaticQueueChooser = new AdministratorSetAutomaticQueueChooser(selected);
        ConfirmationResponse confirmationResponse = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(automaticQueueChooser, ConfirmationResponse.class, 1000);
        if(confirmationResponse != null && confirmationResponse.getStatusOperation())
        {
            return true;
        }
        return false;
    }

}
