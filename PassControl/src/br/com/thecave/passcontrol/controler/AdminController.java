package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.screens.ButtonsModulesScreen;
import br.com.thecave.passcontrol.screens.DefaultScreen;
import br.com.thecave.passcontrol.screens.admin.AdminScreen;
import br.com.thecave.passcontrol.topbar.LoginTopBar;
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

    public void performBack() 
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new ButtonsModulesScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }

    public void performLogout() 
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new DefaultScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new LoginTopBar()); 
    }
}
