/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.topbar;

import br.com.thecave.passcontrol.controler.PassControlController;
import br.com.thecave.passcontrol.screens.PanelConnectionInfo;
import java.awt.Point;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author guilherme
 */
public class PassControlTopBar  extends JPanel
{
    private PassControlController panelController;

    public PassControlTopBar(PassControlController panelController) {
        this.panelController = panelController;
        
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);        
    }


    public PassControlController getPanelController() {
        return panelController;
    }

    public void setPanelController(PassControlController panelController) {
        this.panelController = panelController;
    }
    
    /**
     * Retorna true caso possua um controlador
     */
    public boolean initializeController()
    {
        if (panelController != null)
        {
            panelController.setPassControlPanel(this);
            panelController.initialize();            
            return true;
        }
        return false;
    }    

    
    
    public void blockPassControlTopBar()
    {
        //hook
    }

    public void unblockPassControlTopBar()
    {
        //hook
    }    
    
    /**
     * Atentar para que o layout seja o ABSOLUTO
     */
    protected void addPanelConnectionInfo()
    {
        
        PanelConnectionInfo panelConnectionInfo = new PanelConnectionInfo();
        add(panelConnectionInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(new Point(0,0)));        
    }
}
