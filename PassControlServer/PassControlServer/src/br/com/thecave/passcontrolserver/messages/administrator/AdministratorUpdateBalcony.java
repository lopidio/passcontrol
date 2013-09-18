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
public class AdministratorUpdateBalcony extends PassControlMessage
{

    BalconyBean balconyBean;
    ArrayList<ServiceBean> services;

    public AdministratorUpdateBalcony( BalconyBean balconyBean, ArrayList<ServiceBean> services )
    {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
        this.balconyBean = balconyBean;
        this.services = services;
    }

    public BalconyBean getBalconyBean()
    {
        return balconyBean;
    }

    public void setBalconyBean( BalconyBean balconyBean )
    {
        this.balconyBean = balconyBean;
    }

    public ArrayList<ServiceBean> getServices()
    {
        return services;
    }

    public void setServices( ArrayList<ServiceBean> services )
    {
        this.services = services;
    }

    public void addService( ServiceBean bean )
    {
        this.services.add(bean);
    }

    public boolean removeService( ServiceBean bean )
    {
        if ( this.services.contains(bean) )
        {
            this.services.remove(bean);
            return true;
        }
        else
        {
            return false;
        }
    }
}
