/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.viewer;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author guilherme
 */
public class ViewerQueueRequest extends PassControlMessage{


    public ViewerQueueRequest() 
    {
        super(MessageActors.ViewerActor, MessageActors.ServerActor);
    }

}
