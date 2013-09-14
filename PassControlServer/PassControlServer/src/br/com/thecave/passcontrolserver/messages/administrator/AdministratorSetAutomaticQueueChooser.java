/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author lopidio
 */
public class AdministratorSetAutomaticQueueChooser extends PassControlMessage
{
    boolean isOn;

    public AdministratorSetAutomaticQueueChooser(boolean isOn) {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
        this.isOn = isOn;
    }

    public boolean isIsOn() {
        return isOn;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }
    
    
}
