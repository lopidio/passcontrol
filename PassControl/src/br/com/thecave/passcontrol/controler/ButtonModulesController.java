/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.screens.ButtonsModulesScreen;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class ButtonModulesController extends PassControlController
{
    ButtonsModulesScreen modulesScreen;
    
    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.modulesScreen = (ButtonsModulesScreen)passControlPanel;
    }    
}
