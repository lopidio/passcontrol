package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.screens.admin.ServiceCrud;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListService;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListServiceResponse;
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
        AdministratorListService listService = new AdministratorListService();
        AdministratorListServiceResponse response = Main.getInstance().
                                                        getCommunicationThread().
                                                    sendMessageAndWaitForResponseOrTimeout(listService, AdministratorListServiceResponse.class, 2000);
        
        servicos = response.getListService();
    }

    public ArrayList<ServiceBean> getServices()
    {
        return servicos;
    }
}
