/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.queuepusher;

import br.com.thecave.passcontrolserver.db.bean.ClientBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author guilherme
 */
public class QueuePusherAddClient extends PassControlMessage
{
    ClientBean clientBean;

    public QueuePusherAddClient(ClientBean clientBean) {
        super(MessageActors.QueuePushActor, MessageActors.ServerActor);
        this.clientBean = clientBean;
    }    
    
    public ClientBean getClientBean() {
        return clientBean;
    }

    public void setClientBean(ClientBean clientBean) {
        this.clientBean = clientBean;
    }
    
    
}
