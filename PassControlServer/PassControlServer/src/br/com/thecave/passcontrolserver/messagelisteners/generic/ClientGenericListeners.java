/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.generic;

import br.com.thecave.passcontrolserver.db.bean.UserBean;
import br.com.thecave.passcontrolserver.db.dao.UserDAO;
import br.com.thecave.passcontrolserver.messages.generic.ClientLoginRequest;
import br.com.thecave.passcontrolserver.messages.generic.ClientLoginResponse;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import br.com.thecave.passcontrolserver.PassControlServer;
import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.db.dao.ServiceDAO;
import br.com.thecave.passcontrolserver.messages.generic.ClientListServiceResponse;
import br.com.thecave.passcontrolserver.messages.generic.ClientLogoff;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.util.UserPermission;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author lopidio
 */
public class ClientGenericListeners
{
    
    public static void addListenersCallback() 
    {
        ServerCommunicationThread server = PassControlServer.getInstance().getServer();
        server.addMessageListener(new ClientLoginMessageListener(), ClientLoginRequest.class);
        server.addMessageListener(new ClientLogoffMessageListener(), ClientLogoff.class);
        server.addMessageListener(new ClientListServiceListener(), ClientListServiceResponse.class);
    }    

    public static class ClientLoginMessageListener implements PassControlMessageListener
    {

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            ClientLoginRequest initRequest = (ClientLoginRequest)message;

            //Inicializa com uma mensagem default
            ClientLoginResponse response = new ClientLoginResponse(null, message.getFrom());


            //Verifica se é super usuário
            if (isSuperUser(initRequest.getUser(), initRequest.getPassword()))
            {
                UserBean superBean = new UserBean();
                superBean.setLogin(initRequest.getUser());
                superBean.setName("Super Usuário");
                superBean.setType(UserPermission.ALL_PERMISSION_MASK.getPermissionCode());
                response.setUser(superBean);
            }
            else
            {
                UserBean bean = UserDAO.selectFromName(initRequest.getUser());
                //Verifica se existe o usuário no banco
                if (bean != null)
                {
                    if (bean.getPassword().equals(initRequest.getPassword()))
                    {
                        response.setUser(bean);
                    }
                }

            }

            if (response.getUser() != null)
            {
                //TODO tenho que verificar se o usuário já está em uso...
                PassControlServer.getInstance().getServer().userLogin(socket, response.getUser());
            }

            //Devolve a mensagem para o servidor
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);        
        }

        /**
         * Metodo para verificar se o usuário é o Super
         * @param user String com o nome do usuário
         * @param pass String com a senha digitada
         * @return true se fo,r false se não
         */
        public static boolean isSuperUser(String name, String pass)
        {
            if(name.equalsIgnoreCase("admin"))
            {
                if(pass.equals("supasscontroladmin"))
                {
                    return true;
                }
            }
            return false;
        }    

    }
    
    public static class ClientLogoffMessageListener implements PassControlMessageListener
    {

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
//            ClientLogoff clientLogoff = (ClientLogoff)message;
            ServerCommunicationThread server = PassControlServer.getInstance().getServer();
            server.userLogoff(socket);
            
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, message, message.getFrom());
            server.addResponseToSend(socket, confirmationResponse);
        }
    }
    
    
    private static class ClientListServiceListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
//            ClientListService listUserMessage = (ClientListService)message;
            ArrayList<ServiceBean> services = ServiceDAO.selectAll();
            ClientListServiceResponse response = new ClientListServiceResponse(services, message.getFrom());
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);
        }       
    }
        
    
}
