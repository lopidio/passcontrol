/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author lopidio
 */
public class AdministratorUpdateBalcony extends PassControlMessage
{
    BalconyBean balconyBean;

    public AdministratorUpdateBalcony(BalconyBean balconyBean) {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
        this.balconyBean = balconyBean;
    }

    public BalconyBean getBalconyBean() {
        return balconyBean;
    }

    public void setBalconyBean(BalconyBean balconyBean) {
        this.balconyBean = balconyBean;
    }
    
    
}
