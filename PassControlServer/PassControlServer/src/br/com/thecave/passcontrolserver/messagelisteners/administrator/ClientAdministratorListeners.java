/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.administrator;

import br.com.thecave.passcontrol.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrol.db.bean.UserBean;
import br.com.thecave.passcontrol.db.dao.UserDAO;
import br.com.thecave.passcontrol.messages.ConfirmationResponse;
import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.messages.PassControlMessage;
import br.com.thecave.passcontrol.messages.PassControlMessageListener;
import br.com.thecave.passcontrol.messages.administrator.AdministratorAddUser;
import br.com.thecave.passcontrol.messages.administrator.AdministratorListUser;
import br.com.thecave.passcontrol.messages.administrator.AdministratorListUserResponse;
import br.com.thecave.passcontrol.messages.administrator.AdministratorRemoveUser;
import br.com.thecave.passcontrolserver.PassControlServer;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public class ClientAdministratorListeners
{
    public static void addListenersCallback()
    {
        
        ServerCommunicationThread server = PassControlServer.getInstance().getServer();
        server.addMessageListener(new AddUserListener(), "AdministratorAddUser");
        server.addMessageListener(new ListUserListener(), "AdministratorListUser");
        server.addMessageListener(new RemoveUserListener(), "AdministratorRemoveUser");
    }
    
    private static class AddUserListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            AdministratorAddUser addUserMessage = (AdministratorAddUser)message;
            boolean status = UserDAO.insert(addUserMessage.getBean());
            ConfirmationResponse response = new ConfirmationResponse(status, message, message.getFrom());
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);
        }       
    }
    
    private static class ListUserListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            AdministratorListUser addUserMessage = (AdministratorListUser)message;
            ArrayList<UserBean> users = UserDAO.selectAll();
            AdministratorListUserResponse response = new AdministratorListUserResponse(users);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);
        }       
    }
    
    
    private static class RemoveUserListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            AdministratorRemoveUser addUserMessage = (AdministratorRemoveUser)message;
            ConfirmationResponse response = new ConfirmationResponse(false, message, MessageActors.AdministratorActor);
            
            UserBean bean = UserDAO.selectFromId(addUserMessage.getId());
            if (bean != null)
            {
                if (UserDAO.delete(bean))
                {
                    response.setStatusOperation(true);
                    response.setComment("Usuário deletado com sucesso");
                }
            }
           
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);
        }       
    }
    
    
}

