/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.nongeneric;

import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.db.bean.UserBean;
import br.com.thecave.passcontrolserver.db.dao.UserDAO;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorAddUser;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListUser;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListUserResponse;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorRemoveUser;
import br.com.thecave.passcontrolserver.PassControlServer;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.db.dao.ServiceDAO;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorAddService;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListService;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListServiceResponse;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorRemoveService;
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
        
        ///User
        server.addMessageListener(new AddUserListener(), "AdministratorAddUser");
        server.addMessageListener(new ListUserListener(), "AdministratorListUser");
        server.addMessageListener(new RemoveUserListener(), "AdministratorRemoveUser");
        ///Service
        server.addMessageListener(new AddServiceListener(), "AdministratorAddService");
        server.addMessageListener(new ListServiceListener(), "AdministratorListService");
        server.addMessageListener(new RemoveServiceListener(), "AdministratorRemoveService");
    }
    
    //User Listeners
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
//            AdministratorListUser listUserMessage = (AdministratorListUser)message;
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
            AdministratorRemoveUser remUserMessage = (AdministratorRemoveUser)message;
            ConfirmationResponse response = new ConfirmationResponse(false, message, MessageActors.AdministratorActor);
            
            UserBean bean = UserDAO.selectFromId(remUserMessage.getId());
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
        
    //Service Listeners
    private static class AddServiceListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            AdministratorAddService addServiceMessage = (AdministratorAddService)message;
            boolean status = ServiceDAO.insert(addServiceMessage.getBean());
            ConfirmationResponse response = new ConfirmationResponse(status, message, MessageActors.AdministratorActor);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);
        }       
    }
    
    private static class ListServiceListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
//            AdministratorListService listUserMessage = (AdministratorListService)message;
            ArrayList<ServiceBean> services = ServiceDAO.selectAll();
            AdministratorListServiceResponse response = new AdministratorListServiceResponse(services);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);
        }       
    }
    
    
    private static class RemoveServiceListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            AdministratorRemoveService removeUserMessage = (AdministratorRemoveService)message;
            ConfirmationResponse response = new ConfirmationResponse(false, message, MessageActors.AdministratorActor);
            
            ServiceBean bean = ServiceDAO.selectFromId(removeUserMessage.getId());
            if (bean != null)
            {
                if (ServiceDAO.delete(bean))
                {
                    response.setStatusOperation(true);
                    response.setComment("Serviço deletado com sucesso");
                }
            }
           
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);
        }       
    }
    
        
}

