package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.screens.BalconyScreen;
import br.com.thecave.passcontrol.screens.ButtonsModulesScreen;
import br.com.thecave.passcontrol.screens.DefaultScreen;
import br.com.thecave.passcontrol.topbar.LoginTopBar;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class ViewerController extends PassControlController
{
    BalconyScreen screen;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.screen = (BalconyScreen) passControlPanel;
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

    public void performExit() 
    {
        System.exit(0);
    }
}
