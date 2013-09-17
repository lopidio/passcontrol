package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.admin.UserCrud;
import br.com.thecave.passcontrolserver.db.bean.UserBean;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorAddUser;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListUser;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListUserResponse;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorRemoveUser;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorUpdateUser;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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

    public void defineCBNames( JComboBox cbName )
    {
        loadUsers();
        ArrayList<UserBean> beans = getUserBeans();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        for(UserBean bean : beans)
        {
            model.addElement(bean.getName());
        }
        cbName.setModel(model);
    }

    public boolean saveUser( UserBean bean )
    {
        if(!findOldRegister(bean))
        {
            // enviando o bean ao servidor
            AdministratorAddUser addUser = new AdministratorAddUser(bean);
            ConfirmationResponse response = Main.getInstance().getCommunicationThread().
                                sendMessageToServerAndWaitForResponseOrTimeout(addUser, 
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

    public boolean updateUser( UserBean bean )
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
                AdministratorUpdateUser update = new AdministratorUpdateUser(bean);
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

    public void deleteUser( UserBean bean )
    {
        AdministratorRemoveUser removeUser = new AdministratorRemoveUser(bean.getId());
        
        ConfirmationResponse response = Main.getInstance().getCommunicationThread().
                            sendMessageToServerAndWaitForResponseOrTimeout(removeUser, 
                                                                           ConfirmationResponse.class, 
                                                                           2000);
        if(response.getStatusOperation())
            JOptionPane.showMessageDialog(null, "Registro deletado com sucesso!");
        else
            JOptionPane.showMessageDialog(null, "Erro ao deletar registro!");
    }
    
     /**
     * Verifica na lista de beans se já existe um outro bean com o mesmo nome
     * @param name Nome a ser verificado
     * @return true se existe, false se não existe
     */
    private boolean findOldRegister( UserBean bean )
    {
        loadUsers();
        for ( UserBean userBean : users )
        {
            if(userBean.equals(bean))
                return true;
        }
        return false;
    }
}
