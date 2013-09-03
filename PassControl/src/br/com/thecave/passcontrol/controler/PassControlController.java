/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.messages.PassControlMessageListener;
import br.com.thecave.passcontrol.screens.PassControlPanel;

/**
 *
 * @author guilherme
 */
public abstract class PassControlController implements PassControlMessageListener
{
    /**
     * Panel que representa o view desse controller
     */
    PassControlPanel passControlPanel;

    public PassControlController(PassControlPanel passControlPanel) {
        this.passControlPanel = passControlPanel;
    }
    
}
