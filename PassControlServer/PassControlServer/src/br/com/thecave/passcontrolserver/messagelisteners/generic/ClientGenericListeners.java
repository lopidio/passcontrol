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
import br.com.thecave.passcontrolserver.messages.generic.ClientListService;
import br.com.thecave.passcontrolserver.messages.generic.ClientListServiceResponse;
import br.com.thecave.passcontrolserver.messages.generic.ClientLogoff;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MainImageSetter;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.util.UserPermission;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author lopidio
 */
public class ClientGenericListeners implements ClientListeners
{
    
    @Override
    public void addListenersCallback(ServerCommunicationThread server) 
    {
        server.addMessageListener(new ClientLoginMessageListener(), ClientLoginRequest.class);
        server.addMessageListener(new ClientLogoffMessageListener(), ClientLogoff.class);
        server.addMessageListener(new ClientListServiceListener(), ClientListService.class);

    }    

    public static class ClientLoginMessageListener implements PassControlMessageListener
    {

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            ClientLoginRequest initRequest = (ClientLoginRequest)message;

            ServerCommunicationThread server = PassControlServer.getInstance().getServer();
            
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
                    //Se o usuário já está logado
                    if (server.isUserLogged(bean))
                    {
                        response.setComment("Usuário já está logado!");
                    }
                    else if (bean.getPassword().equals(initRequest.getPassword()))
                    {
                        response.setUser(bean);
                    }
                    else
                    {
                        response.setComment("Senha inválida!");
                    }
                }
                else
                {
                    response.setComment("Usuário não encontrado!");
                }

            }

            if (response.getUser() != null)
            {
                //Registra o login!
                server.userLogin(socket, response.getUser());
            }

            //Devolve a mensagem para o servidor
            server.addResponseToSend(socket, response);  
            
            //Envia a imagem principal
            MainImageSetter mainImageSetter = new MainImageSetter(null, null);
            mainImageSetter.setFrom(MessageActors.ServerActor);
            mainImageSetter.setFrom(MessageActors.AllActors);
            
            //Avisa o cliente para mudar a imagem principal
            server.addResponseToSend(socket, mainImageSetter);            
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
