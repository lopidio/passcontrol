package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.ButtonsModulesScreen;
import br.com.thecave.passcontrol.screens.ConfigScreen;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class ConfigController extends PassControlController
{
    ConfigScreen screen;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.screen = (ConfigScreen) passControlPanel;
    }

}
