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
public class BalconyCallNextClientRequest extends PassControlMessage
{
    /**
     * balcão
     */
    BalconyBean balconyBean;

    public BalconyCallNextClientRequest(BalconyBean balconyBean) 
    {
        super(MessageActors.BalconyActor, MessageActors.ServerActor);
        this.balconyBean = balconyBean;
    }

    public BalconyBean getBalconyBean() {
        return balconyBean;
    }

    public void setBalconyBean(BalconyBean balconyBean) {
        this.balconyBean = balconyBean;
    }


    
}
