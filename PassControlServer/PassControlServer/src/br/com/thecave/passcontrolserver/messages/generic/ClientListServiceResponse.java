/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.generic;

import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public class ClientListServiceResponse extends PassControlMessage
{
    ArrayList<ServiceBean> listService;
        
    public ClientListServiceResponse(ArrayList<ServiceBean> listUser, MessageActors to) {
        super(MessageActors.ServerActor, to);
        this.listService = listUser;
    }

    public ArrayList<ServiceBean> getListService() {
        return listService;
    }

    public void setListService(ArrayList<ServiceBean> listService) {
        this.listService = listService;
    }
    
}
