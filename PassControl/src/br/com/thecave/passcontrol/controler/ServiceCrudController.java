package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.screens.admin.AdminScreen;
import br.com.thecave.passcontrol.screens.admin.ServiceCrud;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class ServiceCrudController extends PassControlController
{
    ServiceCrud screen;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.screen = (ServiceCrud) passControlPanel;
    }    

    @Override
    public void performBack() 
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new AdminScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }
}
