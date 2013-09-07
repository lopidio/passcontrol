package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.screens.ButtonsModulesScreen;
import br.com.thecave.passcontrol.screens.QueuePushScreen;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class QueuePushController extends PassControlController
{
    QueuePushScreen screen;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.screen = (QueuePushScreen) passControlPanel;
    }

    @Override
    public void performBack() 
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new ButtonsModulesScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }    

    public void insert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
