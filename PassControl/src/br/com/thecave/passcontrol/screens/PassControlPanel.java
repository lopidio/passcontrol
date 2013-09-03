/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.screens;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author guilherme
 */
public abstract class PassControlPanel extends JPanel{

    public JMenuBar getMenu()
    {
        return null;
    }
    
    abstract public String getName();
            
}
