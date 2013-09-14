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

/**
 *
 * @author lopidio
 */
public class AdministratorAddBalcony extends PassControlMessage
{
    ArrayList<ServiceBean> serviceBeans;
    BalconyBean balconyBean;

    public AdministratorAddBalcony(BalconyBean balconyBean, ArrayList<ServiceBean> serviceBeans) {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
        this.balconyBean = balconyBean;
        this.serviceBeans = serviceBeans;
    }

    public BalconyBean getBalconyBean() {
        return balconyBean;
    }

    public void setBalconyBean(BalconyBean balconyBean) {
        this.balconyBean = balconyBean;
    }

    public ArrayList<ServiceBean> getServiceBeans() {
        return serviceBeans;
    }

    public void setServiceBeans(ArrayList<ServiceBean> serviceBeans) {
        this.serviceBeans = serviceBeans;
    }
    
}
