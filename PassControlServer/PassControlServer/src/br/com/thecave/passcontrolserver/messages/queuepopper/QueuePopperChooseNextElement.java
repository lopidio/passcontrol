/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.queuepopper;

import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author guilherme
 */
public class QueuePopperChooseNextElement extends PassControlMessage
{
    /**
     * Guichê requisitante
     */
    BalconyBean balconyBean;
    
    /**
     * Possíveis tuplas
     */
    HashMap<Integer, ArrayList<QueuesManagerBean>> managerBeans;

    public QueuePopperChooseNextElement(BalconyBean balconyBean, HashMap<Integer, ArrayList<QueuesManagerBean>> managerBeans)
    {
        super(MessageActors.ServerActor, MessageActors.QueuePopActor);
        this.balconyBean = balconyBean;
        this.managerBeans = managerBeans;
    }

    
    
    public BalconyBean getBalconyBean() {
        return balconyBean;
    }

    public void setBalconyBean(BalconyBean balconyBean) {
        this.balconyBean = balconyBean;
    }

    public HashMap<Integer, ArrayList<QueuesManagerBean>> getManagerBeans() {
        return managerBeans;
    }

    public void setManagerBeans(HashMap<Integer, ArrayList<QueuesManagerBean>> managerBeans) {
        this.managerBeans = managerBeans;
    }

    
}
