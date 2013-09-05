/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controler.PassControlController;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author guilherme
 */
public abstract class PassControlPanel extends JPanel
{   
    private PassControlController panelController;
    private String passControlPanelTitle;
    private JMenuBar menu;

    public PassControlPanel(String passControlPanelTitle, PassControlController panelController) {
        this.panelController = panelController;
        this.passControlPanelTitle = passControlPanelTitle;
        this.menu = null;
        
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);        
    }

    public String getPassControlPanelTitle() {
        return passControlPanelTitle;
    }

    public void setPassControlPanelTitle(String passControlPanelTitle) {
        this.passControlPanelTitle = passControlPanelTitle;
    }
    
    /**
     * Adiciona seus campos ao menu principal
     * @return 
     */
    public abstract ArrayList<JMenu> createMenuItems();

    public PassControlController getPanelController() {
        return panelController;
    }

    public void setPanelController(PassControlController panelController) {
        this.panelController = panelController;
    }
    
}
