package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.screens.admin.AdminScreen;
import br.com.thecave.passcontrol.screens.admin.BalconyTypeCrud;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class BalconyTypeCrudController extends PassControlController
{
    BalconyTypeCrud screen;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.screen = (BalconyTypeCrud) passControlPanel;
    }    

    @Override
    public void performBack() 
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new AdminScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }
}
