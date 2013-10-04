/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.balcony;

import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author lopidio
 */
public class BalconyRecoverClientMessage extends PassControlMessage
{

    private BalconyBean balcony;
    
    public BalconyRecoverClientMessage(BalconyBean balcony) 
    {
        super(MessageActors.BalconyActor, MessageActors.ServerActor);
        this.balcony = balcony;
    }


    public BalconyBean getBalcony() {
        return balcony;
    }

    public void setBalcony(BalconyBean balcony) {
        this.balcony = balcony;
    }
    
}
