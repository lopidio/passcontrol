package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.admin.AdminScreen;
import br.com.thecave.passcontrol.screens.admin.BalconyCrud;
import br.com.thecave.passcontrol.screens.admin.ServiceCrud;
import br.com.thecave.passcontrol.screens.admin.UserCrud;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorSetMainImage;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFileChooser;
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
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if ( returnVal == JFileChooser.APPROVE_OPTION )
        {
            Image img = Toolkit.getDefaultToolkit().getImage(chooser.getSelectedFile().getName());
            AdministratorSetMainImage setMainImage = new AdministratorSetMainImage(img, chooser.getSelectedFile().getName());
            ConfirmationResponse response = Main.getInstance().getCommunicationThread().
                    sendMessageToServerAndWaitForResponseOrTimeout(setMainImage, ConfirmationResponse.class, 2000);
            return response.getStatusOperation();
        }
        return false;
    }
}
