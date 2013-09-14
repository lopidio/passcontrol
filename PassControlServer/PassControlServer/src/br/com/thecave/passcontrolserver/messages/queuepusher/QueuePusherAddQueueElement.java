/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.queuepusher;

import br.com.thecave.passcontrolserver.db.bean.ClientBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.db.bean.UserBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author lopidio
 */
public class QueuePusherAddQueueElement extends PassControlMessage
{
    ClientBean clientBean;
    
    UserBean userBean;
    
    ServiceBean serviceBean;

    public QueuePusherAddQueueElement(ClientBean clientBean, UserBean userBean, ServiceBean serviceBean) {
        super(MessageActors.QueuePushActor, MessageActors.ServerActor);
        this.clientBean = clientBean;
        this.userBean = userBean;
        this.serviceBean = serviceBean;
    }

    public ClientBean getClientBean() {
        return clientBean;
    }

    public void setClientBean(ClientBean clientBean) {
        this.clientBean = clientBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public ServiceBean getServiceBean() {
        return serviceBean;
    }

    public void setServiceBean(ServiceBean serviceBean) {
        this.serviceBean = serviceBean;
    }

    
}
