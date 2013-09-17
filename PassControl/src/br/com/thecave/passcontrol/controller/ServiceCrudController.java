package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.admin.ServiceCrud;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorAddService;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorRemoveService;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorUpdateService;
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

    public boolean saveService(ServiceBean bean)
    {
        if(!findOldRegister(bean))
        {
            // enviando o bean ao servidor
            AdministratorAddService addService = new AdministratorAddService(bean);
            ConfirmationResponse response = Main.getInstance().getCommunicationThread().
                                sendMessageToServerAndWaitForResponseOrTimeout(addService, 
                                                                               ConfirmationResponse.class, 
                                                                               2000);
            if(response.getStatusOperation())
            {
                JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Erro ao salvar registro!");
                return false;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Registro já existente, insira um outro nome!");
            return false;
        }
    }

    public void deleteService( ServiceBean bean )
    {
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

    public boolean updateService( ServiceBean bean )
    {
        if(!findOldRegister(bean))
        {
            // criando um bean com os dados da tela
            if(bean == null)
            {
                JOptionPane.showMessageDialog(null, "Não existe nenhum registro para ser atualizado");
                return false;
            }
            else
            {
                // enviando o bean ao servidor
                AdministratorUpdateService update = new AdministratorUpdateService(bean);
                ConfirmationResponse response = Main.getInstance().getCommunicationThread().
                                    sendMessageToServerAndWaitForResponseOrTimeout(update, 
                                                                                   ConfirmationResponse.class, 
                                                                                   2000);
                if(response.getStatusOperation())
                {
                    JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso!");
                    return true;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar registro!");
                    return false;
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Já existe um registro com esse nome, escolha um diferente!");
            return false;
        }
    }

    /**
     * Verifica na lista de beans se já existe um outro bean com o mesmo nome
     * @param name Nome a ser verificado
     * @return true se existe, false se não existe
     */
    private boolean findOldRegister( ServiceBean bean )
    {
        loadServices();
        for ( ServiceBean serviceBean : servicos )
        {
            if(serviceBean.equals(bean))
                return true;
        }
        return false;
    }
}
