package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.screens.ButtonsModulesScreen;
import br.com.thecave.passcontrol.screens.admin.AdminScreen;
import br.com.thecave.passcontrol.screens.admin.BalconyCrud;
import br.com.thecave.passcontrol.screens.admin.BalconyTypeCrud;
import br.com.thecave.passcontrol.screens.admin.ServiceCrud;
import br.com.thecave.passcontrol.screens.admin.UserCrud;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class AdminController extends PassControlController
{
    AdminScreen adminScreen;
    
    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.adminScreen = (AdminScreen) passControlPanel;
    }    

    @Override
    public void performBack() 
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new ButtonsModulesScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }

    public void openBalconyCrud() 
    {
         Main.getInstance().getMainFrame().activatePassControlPanel(new BalconyCrud());
         Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }

    public void openBalconyTypeCrud() 
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new BalconyTypeCrud());
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
}
