/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.balcony;

import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author guilherme
 */
public class BalconyShowClientInit extends PassControlMessage
{
    QueuesManagerBean queuesManagerBean;

    public BalconyShowClientInit(QueuesManagerBean queuesManagerBean) {
        super(MessageActors.BalconyActor, MessageActors.ServerActor);
        this.queuesManagerBean = queuesManagerBean;
    }

    
    
    public QueuesManagerBean getQueuesManagerBean() {
        return queuesManagerBean;
    }

    public void setQueuesManagerBean(QueuesManagerBean queuesManagerBean) {
        this.queuesManagerBean = queuesManagerBean;
    }
    
    
    
}
