/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.balcony;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author guilherme
 */
public class BalconyInitRequest extends PassControlMessage
{

    public BalconyInitRequest() {
        super(MessageActors.BalconyActor, MessageActors.ServerActor);
    }

}
