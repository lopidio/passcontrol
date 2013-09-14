package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.topbar.MainTopBar;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class MainTopBarController extends PassControlController
{
    MainTopBar mainTopBar = null;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        mainTopBar = (MainTopBar)passControlPanel;
    }  
 
}
