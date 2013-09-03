/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.screens;

import java.awt.LayoutManager;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author guilherme
 */
public abstract class PassControlPanel extends JPanel{
    
    private String passControlPanelTitle;
    private JMenuBar menu;

    public PassControlPanel(String passControlPanelTitle) {
        this.passControlPanelTitle = passControlPanelTitle;
        this.menu = null;
    }

    public String getPassControlPanelTitle() {
        return passControlPanelTitle;
    }

    public void setPassControlPanelTitle(String passControlPanelTitle) {
        this.passControlPanelTitle = passControlPanelTitle;
    }
    
    public abstract JMenuBar createMenu();

    public JMenuBar getMenu()
    {
        return menu;
    }

    public void setMenu(JMenuBar menu) {
        this.menu = menu;
    }
            
    
}
