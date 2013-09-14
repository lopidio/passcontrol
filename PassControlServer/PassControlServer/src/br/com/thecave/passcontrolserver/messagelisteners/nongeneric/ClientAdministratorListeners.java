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
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.db.dao.BalconyDAO;
import br.com.thecave.passcontrolserver.db.dao.BalconyTypesServiceDAO;
import br.com.thecave.passcontrolserver.db.dao.ServiceDAO;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorAddBalcony;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorAddService;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListBalcony;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListBalconyResponse;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorRemoveBalcony;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorRemoveService;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorUpdateBalcony;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorUpdateService;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorUpdateUser;
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
        server.addMessageListener(new AddUserListener(), AdministratorAddUser.class);
        server.addMessageListener(new ListUserListener(), AdministratorListUser.class);
        server.addMessageListener(new UpdateUserListener(), AdministratorUpdateUser.class);
        server.addMessageListener(new RemoveUserListener(), AdministratorRemoveUser.class);
        ///Service
        server.addMessageListener(new AddServiceListener(), AdministratorAddService.class);
                    //Listar serviço é mensagem genérica
        server.addMessageListener(new UpdateServiceListener(), AdministratorUpdateService.class);
        server.addMessageListener(new RemoveServiceListener(), AdministratorRemoveService.class);
        ///Balcony
        server.addMessageListener(new AddBalconyListener(), AdministratorAddBalcony.class);
        server.addMessageListener(new ListBalconyListener(), AdministratorListBalcony.class);
        server.addMessageListener(new UpdateBalconyListener(), AdministratorUpdateBalcony.class);
        server.addMessageListener(new RemoveBalconyListener(), AdministratorRemoveBalcony.class);
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

    private static class UpdateUserListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            AdministratorUpdateUser administratorUpdateUser = (AdministratorUpdateUser)message;
            boolean status = UserDAO.update(administratorUpdateUser.getUserBean());
            ConfirmationResponse response = new ConfirmationResponse(status, message, MessageActors.AdministratorActor);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);
        }       
    }

    private static class UpdateServiceListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            AdministratorUpdateService administratorUpdateService = (AdministratorUpdateService)message;
            boolean status = ServiceDAO.update(administratorUpdateService.getServiceBean());
            ConfirmationResponse response = new ConfirmationResponse(status, message, MessageActors.AdministratorActor);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);
        }       
    }

    private static class AddBalconyListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            AdministratorAddBalcony addServiceMessage = (AdministratorAddBalcony)message;
            boolean status = false;
            for (ServiceBean serviceBeans : addServiceMessage.getServiceBeans()) 
            {
                status = BalconyTypesServiceDAO.insert(addServiceMessage.getBalconyBean(), serviceBeans);
            }
            status = status && BalconyDAO.insert(addServiceMessage.getBalconyBean());
            ConfirmationResponse response = new ConfirmationResponse(status, message, MessageActors.AdministratorActor);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);
        }       
    }

    private static class UpdateBalconyListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            AdministratorUpdateBalcony administratorUpdateBalcony = (AdministratorUpdateBalcony)message;
            boolean status = BalconyDAO.update(administratorUpdateBalcony.getBalconyBean());
            ConfirmationResponse response = new ConfirmationResponse(status, message, MessageActors.AdministratorActor);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);
        }       
    }

    private static class RemoveBalconyListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            AdministratorRemoveBalcony administratorRemoveBalcony = (AdministratorRemoveBalcony)message;
            boolean status = BalconyDAO.delete(administratorRemoveBalcony.getBalconyBean());            
            ConfirmationResponse response = new ConfirmationResponse(status, message, MessageActors.AdministratorActor);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);
        }       
    }

    private static class ListBalconyListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
//            AdministratorListBalcony administratorListBalcony = (AdministratorListBalcony)message;
            ArrayList<BalconyBean> beans = BalconyDAO.selectAll();
            AdministratorListBalconyResponse administratorListBalconyResponse = new AdministratorListBalconyResponse(beans);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, administratorListBalconyResponse);
        }       
    }
        
}

