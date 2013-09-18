package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.ViewerScreen;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class ViewerController extends PassControlController
{
    ViewerScreen screen;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.screen = (ViewerScreen) passControlPanel;
    }    

}
