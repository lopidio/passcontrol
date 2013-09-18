package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.QueuePopScreen;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class QueuePopController extends PassControlController
{

    QueuePopScreen screen;

    @Override
    public void setPassControlPanel( JPanel passControlPanel )
    {
        this.screen = (QueuePopScreen) passControlPanel;
    }
}
