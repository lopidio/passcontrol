/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import java.util.ArrayList;

/**
 *
 * @author lopidio
 */
public class AdministratorListBalconyResponse  extends PassControlMessage
{
    ArrayList<BalconyBean> balconyBeans;

    public AdministratorListBalconyResponse(ArrayList<BalconyBean> balconyBeans) {
        super(MessageActors.ServerActor, MessageActors.AdministratorActor);
        this.balconyBeans = balconyBeans;
    }

    public ArrayList<BalconyBean> getBalconyBeans() {
        return balconyBeans;
    }

    public void setBalconyBeans(ArrayList<BalconyBean> balconyBeans) {
        this.balconyBeans = balconyBeans;
    }
    
}
