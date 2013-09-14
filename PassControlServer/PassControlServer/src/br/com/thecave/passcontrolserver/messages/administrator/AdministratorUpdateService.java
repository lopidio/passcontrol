/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author lopidio
 */
public class AdministratorUpdateService extends PassControlMessage
{
    ServiceBean serviceBean;

    public AdministratorUpdateService(ServiceBean serviceBean) {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
        this.serviceBean = serviceBean;
    }
    
    public ServiceBean getServiceBean() {
        return serviceBean;
    }

    public void setServiceBean(ServiceBean serviceBean) {
        this.serviceBean = serviceBean;
    }
            
}
