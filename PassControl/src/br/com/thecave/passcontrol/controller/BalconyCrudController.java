package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.admin.BalconyCrud;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorAddBalcony;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListBalcony;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListBalconyResponse;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorRemoveBalcony;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorUpdateBalcony;
import br.com.thecave.passcontrolserver.messages.generic.ClientListService;
import br.com.thecave.passcontrolserver.messages.generic.ClientListServiceResponse;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public final class BalconyCrudController extends PassControlController
{

    BalconyCrud screen;
    /**
     * Todos os guichês disponívels
     */
    ArrayList<BalconyBean> balconys;
    /**
     * Todos os guichês disponíveis
     */
    ArrayList<ServiceBean> services;
    /**
     * Mapa de serviços para os guichês
     */
    private HashMap<BalconyBean, ArrayList<ServiceBean>> map;

    @Override
    public void setPassControlPanel( JPanel passControlPanel )
    {
        this.screen = (BalconyCrud) passControlPanel;
    }

    public BalconyCrudController() 
    {
        balconys = new ArrayList<>();
        services = new ArrayList<>();
        map = new HashMap<>();
    }
    
    

    public void loadBalconysAndMap()
    {
        AdministratorListBalcony listBalcony = new AdministratorListBalcony();
        AdministratorListBalconyResponse response = Main.getInstance().
                getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(listBalcony, AdministratorListBalconyResponse.class, 2000);

        if (response != null)
        {
            map = response.getBalconyServiceBeans();
            for (Map.Entry<BalconyBean, ArrayList<ServiceBean>> entry : map.entrySet()) 
            {
                BalconyBean balcon = entry.getKey();
                balconys.add(balcon);
            }
        }
    }
    
    public void refreshAttributes()
    {
        loadBalconysAndMap();
        loadServices();
    }
    
    private void loadServices()
    {
        ClientListService listService = new ClientListService(MessageActors.AdministratorActor);
        ClientListServiceResponse response = Main.getInstance().
                getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(listService, ClientListServiceResponse.class, 2000);

        if (response != null)
        {
            services = response.getListService();
        }
    }

    public ArrayList<ServiceBean> getServices()
    {
        return services;
    }

    public boolean saveBalcony( BalconyBean balconyBean, ArrayList<ServiceBean> typesServiceBean )
    {
        AdministratorAddBalcony addBalcony = new AdministratorAddBalcony(balconyBean, typesServiceBean);
        ConfirmationResponse response = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(addBalcony, ConfirmationResponse.class, 2000);

        if ( response.getStatusOperation() )
        {
            JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Erro ao salvar registros!");
            return false;
        }
    }

    public boolean deleteBalcony( BalconyBean balconyBean )
    {
        AdministratorRemoveBalcony remove = new AdministratorRemoveBalcony(balconyBean);
        ConfirmationResponse response = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(remove, ConfirmationResponse.class, 2000);

        if ( response.getStatusOperation() )
        {
            JOptionPane.showMessageDialog(null, "Registro deletado com sucesso!");
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Erro ao deletar registros!");
            return false;
        }
    }

    public ArrayList<BalconyBean> getBalconyBeans()
    {
        return balconys;
    }

    public void defineCBNames( JComboBox cbBalconyName )
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        for ( BalconyBean bean : balconys )
        {
            model.addElement(bean.getNumber());
        }
        cbBalconyName.setModel(model);
    }

    public BalconyBean getBalconyBeanFromName( String name )
    {
        for ( BalconyBean bean : getBalconyBeans() )
        {
            if ( bean.getNumber().equals(name) )
            {
                return bean;
            }
        }
        return null;
    }

    public boolean updateBalcony( BalconyBean bean, ArrayList<ServiceBean> services )
    {
        AdministratorUpdateBalcony update = new AdministratorUpdateBalcony(bean, services);
        ConfirmationResponse response = Main.getInstance().getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(update, ConfirmationResponse.class, 2000);

        if ( response.getStatusOperation() )
        {
            JOptionPane.showMessageDialog(null, "Registro deletado com sucesso!");
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Erro ao deletar registros!");
            return false;
        }
    }

    public ArrayList<ServiceBean> getServicesFromBalcony(BalconyBean balconyBean)
    {
        if (balconyBean != null)
        {
            return map.get(balconyBean);
        }
        return null;
    }
    
}
