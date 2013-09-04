/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.messages.PassControlMessageListener;
import javax.swing.JPanel;

/**
 *
 * @author guilherme
 */
public abstract class PassControlController implements PassControlMessageListener
{

    JPanel passControlView;
    
    public abstract void addMessageListeners();

    public abstract void removeMessageListeners();

    public JPanel getPassControlView() {
        return passControlView;
    }

    public void setPassControlView(JPanel passControlView) {
        this.passControlView = passControlView;
    }
    
    
    
}
