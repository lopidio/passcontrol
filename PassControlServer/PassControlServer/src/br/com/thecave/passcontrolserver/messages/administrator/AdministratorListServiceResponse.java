/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public class AdministratorListServiceResponse extends PassControlMessage
{
    ArrayList<ServiceBean> listService;
    
    public AdministratorListServiceResponse(ArrayList<ServiceBean> listUser) {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
        this.listService = listUser;
    }

    public AdministratorListServiceResponse(ArrayList<ServiceBean> listUser, String comment) {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor, comment);
        this.listService = listUser;
    }

    public ArrayList<ServiceBean> getListUser() {
        return listService;
    }

    public void setListUser(ArrayList<ServiceBean> listUser) {
        this.listService = listUser;
    }
    
}
