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
        super(MessageActors.ServerActor, MessageActors.AdministratorActor);
        this.listService = listUser;
    }

    public ArrayList<ServiceBean> getListService() {
        return listService;
    }

    public void setListService(ArrayList<ServiceBean> listService) {
        this.listService = listService;
    }
    
}
