package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.MultiScreen;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class MultiScreenController extends PassControlController
{

    MultiScreen multiScreen;

    @Override
    public void setPassControlPanel( JPanel passControlPanel )
    {
        this.multiScreen = (MultiScreen) passControlPanel;
    }

}