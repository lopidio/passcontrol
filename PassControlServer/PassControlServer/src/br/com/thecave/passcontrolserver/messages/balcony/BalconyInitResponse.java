/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.balcony;

import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public class BalconyInitResponse extends PassControlMessage
{
    /**
     * Tipos disponíveis do balcão
     */
    ArrayList<BalconyBean> avaliableBalconys;
    
    public BalconyInitResponse(ArrayList<BalconyBean> avaliableBalconys) {
        super(MessageActors.ServerActor, MessageActors.BalconyActor);
        this.avaliableBalconys = avaliableBalconys;
    }

    public ArrayList<BalconyBean> getAvaliableBalconys() {
        return avaliableBalconys;
    }

    public void setAvaliableBalconys(ArrayList<BalconyBean> avaliableBalconys) {
        this.avaliableBalconys = avaliableBalconys;
    }

    
    

    
}
