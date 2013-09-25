/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author lopidio
 */
public class AdministratorListBalconyResponse  extends PassControlMessage
{
    HashMap<BalconyBean, ArrayList<ServiceBean>> balconyServiceBeans;

    public AdministratorListBalconyResponse(HashMap<BalconyBean, ArrayList<ServiceBean>> balconyServiceBeans) {
        super(MessageActors.ServerActor, MessageActors.AdministratorActor);
        this.balconyServiceBeans = balconyServiceBeans;
    }

    public HashMap<BalconyBean, ArrayList<ServiceBean>> getBalconyServiceBeans() {
        return balconyServiceBeans;
    }

    public void setBalconyServiceBeans(HashMap<BalconyBean, ArrayList<ServiceBean>> balconyServiceBeans) {
        this.balconyServiceBeans = balconyServiceBeans;
    }

    
}
