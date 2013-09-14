/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controller.PassControlController;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JPanel;

/**
 *
 * @author guilherme
 */
public abstract class PassControlPanel extends JPanel
{   
    protected PassControlController panelController;
    private String passControlPanelTitle;

    public PassControlPanel(String passControlPanelTitle, PassControlController panelController) {
        this.panelController = panelController;
        this.passControlPanelTitle = passControlPanelTitle;
        
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
     * Adiciona seus campos ao menu principal (vazio, por default)
     * @return 
     */
    public ArrayList<JMenu> createMenuItems()
    {
        ArrayList<JMenu> retorno = new ArrayList<>();
        return retorno;
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
    
    public void blockPassControlPanel()
    {
        //hook
    }

    public void unblockPassControlPanel()
    {
        //hook
    }

}
