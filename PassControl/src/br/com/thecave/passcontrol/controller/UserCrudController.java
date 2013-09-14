package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.admin.UserCrud;
import br.com.thecave.passcontrolserver.db.bean.UserBean;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListUser;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListUserResponse;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorRemoveBalcony;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class UserCrudController extends PassControlController
{
    UserCrud screen;
    ArrayList<UserBean> users;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.screen = (UserCrud) passControlPanel;
        users = new ArrayList<>();
    } 
    
    public void loadUsers()
    {
        AdministratorListUser listUser = new AdministratorListUser();
        AdministratorListUserResponse response = Main.getInstance().getCommunicationThread().
                sendMessageToServerAndWaitForResponse(listUser, AdministratorListUserResponse.class);
        
        users = response.getUsers();
    }

    public ArrayList<UserBean> getUserBeans()
    {
        return users;
    }
}
