package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.screens.ButtonsModulesScreen;
import br.com.thecave.passcontrol.screens.QueuePopScreen;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class QueuePopController extends PassControlController
{
    QueuePopScreen screen;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.screen = (QueuePopScreen) passControlPanel;
    }
    
}
