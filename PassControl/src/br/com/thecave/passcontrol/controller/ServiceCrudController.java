package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.admin.ServiceCrud;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorAddService;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorRemoveService;
import br.com.thecave.passcontrolserver.messages.generic.ClientListService;
import br.com.thecave.passcontrolserver.messages.generic.ClientListServiceResponse;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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

    public void defineCBNames( JComboBox cbName )
    {
        loadServices();
        ArrayList<ServiceBean> services = getServices();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        for(ServiceBean bean : services)
        {
            model.addElement(bean.getName());
        }
        cbName.setModel(model);
    }

    public ServiceBean extractBeanFromName( String name )
    {
        loadServices();
        for(ServiceBean bean : getServices())
        {
            if(bean.getName().equals(name))
                return bean;
        }
        return null;
    }

    public void saveService(String name, int priority)
    {
        // criando um bean com os dados da tela
        ServiceBean bean = new ServiceBean();
        bean.setName(name);
        bean.setPriority(priority);
        // enviando o bean ao servidor
        AdministratorAddService addService = new AdministratorAddService(bean);
        ConfirmationResponse response = Main.getInstance().getCommunicationThread().
                            sendMessageToServerAndWaitForResponseOrTimeout(addService, 
                                                                           ConfirmationResponse.class, 
                                                                           2000);
        if(response.getStatusOperation())
            JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
        else
            JOptionPane.showMessageDialog(null, "Erro ao salvar registro!");
    }

    public void deleteService( ServiceBean bean )
    {
        bean = extractBeanFromName(bean.getName());
        AdministratorRemoveService removeService = new AdministratorRemoveService(bean.getId());
        
        ConfirmationResponse response = Main.getInstance().getCommunicationThread().
                            sendMessageToServerAndWaitForResponseOrTimeout(removeService, 
                                                                           ConfirmationResponse.class, 
                                                                           2000);
        if(response.getStatusOperation())
            JOptionPane.showMessageDialog(null, "Registro deletado com sucesso!");
        else
            JOptionPane.showMessageDialog(null, "Erro ao deletar registro!");
    }
}
