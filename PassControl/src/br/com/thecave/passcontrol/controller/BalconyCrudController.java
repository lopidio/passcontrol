package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.admin.BalconyCrud;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.BalconyTypesServiceBean;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListBalcony;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListBalconyResponse;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class BalconyCrudController extends PassControlController
{
    BalconyCrud screen; 
    ArrayList<BalconyBean> balconys;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.screen = (BalconyCrud) passControlPanel;
        balconys = new ArrayList<>();
    }
    
    public void loadBalconys()
    {
        AdministratorListBalcony listBalcony = new AdministratorListBalcony();
        AdministratorListBalconyResponse response = Main.getInstance().
                                                        getCommunicationThread().sendMessageToServerAndWaitForResponseOrTimeout(listBalcony, AdministratorListBalconyResponse.class, 2000);
        
        balconys = response.getBalconyBeans();
    }

    public void saveBalcony( BalconyBean balconyBean, BalconyTypesServiceBean typesServiceBean )
    {
        
    }
    
    public ArrayList<BalconyBean> getBalconyBeans()
    {
        return balconys;
    }

    public void defineCBNames( JComboBox cbBalconyName )
    {
        ArrayList<BalconyBean> balconys = getBalconyBeans();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        for(BalconyBean bean : balconys)
        {
            model.addElement(bean.getNumber());
        }
        cbBalconyName.setModel(model);
    }
}
