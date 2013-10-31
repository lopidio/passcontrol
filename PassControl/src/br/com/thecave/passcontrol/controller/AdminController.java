package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.admin.AdminScreen;
import br.com.thecave.passcontrol.screens.admin.BalconyCrud;
import br.com.thecave.passcontrol.screens.admin.ServiceCrud;
import br.com.thecave.passcontrol.screens.admin.UserCrud;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorSetAutomaticQueueChooser;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorSetTimeSlideInterval;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MainImageSetter;
import br.com.thecave.passcontrolserver.util.IValidation;
import br.com.thecave.passcontrolserver.util.ValidationPerform;
import br.com.thecave.passcontrolserver.util.validations.ValidIsDigit;
import java.util.ArrayList;
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

    public void alterTime()
    {
        int newTime;
        String time = JOptionPane.showInputDialog("Insira o tempo de intervalo que deseja para a apresentação");
        ArrayList<IValidation> validations = new ArrayList<>();
        validations.add(new ValidIsDigit());
        while( !ValidationPerform.valid(time, validations ))
        {
            time = JOptionPane.showInputDialog("Insira o tempo de intervalo que deseja para a apresentação");
        }
               
        newTime = Integer.parseInt(time);
        newTime *= 1000;
        
        AdministratorSetTimeSlideInterval timeSlideInterval = new AdministratorSetTimeSlideInterval(newTime);
        ConfirmationResponse confirmationResponse = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(timeSlideInterval, ConfirmationResponse.class, 2000);
        if(confirmationResponse == null)
        {
            JOptionPane.showMessageDialog(null, "Comunicação com servidor comprometida!");
        }
        else
        {
            if(confirmationResponse.getStatusOperation())
            {
                JOptionPane.showMessageDialog(null, "Tempo alterado com sucesso!");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Tempo não foi alterado!");
            }
        }
    }

}
