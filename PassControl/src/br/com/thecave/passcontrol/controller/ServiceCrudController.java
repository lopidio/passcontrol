package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.admin.ServiceCrud;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.messages.generic.ClientListService;
import br.com.thecave.passcontrolserver.messages.generic.ClientListServiceResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class ServiceCrudController extends PassControlController
{
    ServiceCrud screen;
    ArrayList<ServiceBean> servicos;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.screen = (ServiceCrud) passControlPanel;
        servicos = new ArrayList<>();
    }   
    
    public void loadServices()
    {
        ClientListService listService = new ClientListService(MessageActors.AdministratorActor);
        ClientListServiceResponse response = Main.getInstance().
                                                        getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(listService, ClientListServiceResponse.class, 2000);
        
        servicos = response.getListService();
    }

    public ArrayList<ServiceBean> getServices()
    {
        return servicos;
    }
}
