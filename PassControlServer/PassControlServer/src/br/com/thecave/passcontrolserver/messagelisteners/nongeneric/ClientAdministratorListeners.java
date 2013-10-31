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
import br.com.thecave.passcontrolserver.db.dao.QueuesManagerDAO;
import br.com.thecave.passcontrolserver.db.dao.ServiceDAO;
import br.com.thecave.passcontrolserver.messagelisteners.generic.ClientListeners;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorAddBalcony;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorAddService;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorAddSlideImage;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListBalcony;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorListBalconyResponse;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorRemoveBalcony;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorRemoveService;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorRemoveSlideImage;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorSetAutomaticQueueChooser;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorSetTimeSlideInterval;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorUpdateBalcony;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorUpdateService;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorUpdateUser;
import br.com.thecave.passcontrolserver.util.ConfigurationFile;
import br.com.thecave.passcontrolserver.util.FileUtils;
import br.com.thecave.passcontrolserver.util.PassControlConfigurationSynchronizer;
import br.com.thecave.passcontrolserver.util.QueueElementHandler;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 *
 * @author guilherme
 */
public class ClientAdministratorListeners implements ClientListeners
{
    final static String IMAGES_SLIDE_PATH = "imgs/slide/";
    @Override
    public void addListenersCallback(ServerCommunicationThread server)
    {        
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
        
        ///Comuns        
        server.addMessageListener(new AdministratorSetAutomaticQueueChooserListener(), AdministratorSetAutomaticQueueChooser.class);
        server.addMessageListener(new AdministratorAddSlideAnimation(), AdministratorAddSlideImage.class);
        server.addMessageListener(new AdministratorRemoveSlideAnimation(), AdministratorRemoveSlideImage.class);
        server.addMessageListener(new AdministratorSetTimeIntervalAnimation(), AdministratorSetTimeSlideInterval.class);
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
                if (PassControlServer.getInstance().getServer().isUserLogged(bean))
                {
                    response.setComment("Usuário não pode ser deletado. Usuário está logado no momento.");                    
                }
                else if (UserDAO.delete(bean))
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
                
                if (QueuesManagerDAO.selectAvaliableTuplesFromService(bean))
                {
                    response.setComment("Não foi possível deletar serviço. Existe cliente não atendido esperando esse serviço.");                    
                }
                
                if (ServiceDAO.delete(bean))
                {
                    BalconyTypesServiceDAO.deleteAllFromServiceId(bean);
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
            status = BalconyDAO.insert(addServiceMessage.getBalconyBean());
            BalconyBean insertedBean = BalconyDAO.selectFromNumber(addServiceMessage.getBalconyBean().getNumber());
            if (insertedBean != null)
            {
                for (ServiceBean serviceBeans : addServiceMessage.getServiceBeans()) 
                {
                    status = status && BalconyTypesServiceDAO.insert(insertedBean, serviceBeans);
                }
            }
            else
            {
                status = false;
            }
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
            BalconyTypesServiceDAO.deleteAllFromBalcony(administratorUpdateBalcony.getBalconyBean());
            for (ServiceBean serviceBean: administratorUpdateBalcony.getServices()) 
            {
                status = status && BalconyTypesServiceDAO.insert(administratorUpdateBalcony.getBalconyBean(), serviceBean);
            }

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
            
            ConfirmationResponse response = new ConfirmationResponse(false, message, MessageActors.AdministratorActor);
            if (ClientBalconyListeners.getLoggedBalconysID().get(administratorRemoveBalcony.getBalconyBean().getId()) != null)
            {
                response.setComment("Não foi possível deletar guichê. Guichê em uso no momento");
            }
            else
            {
                BalconyTypesServiceDAO.deleteAllFromBalcony(administratorRemoveBalcony.getBalconyBean());
                BalconyDAO.delete(administratorRemoveBalcony.getBalconyBean());
                response.setStatusOperation(true);      
                response.setComment("Guichê removido com sucesso");                
                
            }
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);
        }       
    }

    private static class ListBalconyListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
//            AdministratorListBalcony administratorListBalcony = (AdministratorListBalcony)message;
            ArrayList<BalconyBean> balconyBeans = BalconyDAO.selectAll();
            HashMap<BalconyBean, ArrayList<ServiceBean>> balconyServiceBeans = new HashMap<>();
            
            if (balconyBeans != null)
            {
                //PAra todos os guichês
                for (BalconyBean balconyBean : balconyBeans) 
                {
                    balconyServiceBeans.put(balconyBean, BalconyTypesServiceDAO.selectAllServicesFromBalcony(balconyBean));                        
                }
            }
            
            AdministratorListBalconyResponse administratorListBalconyResponse = new AdministratorListBalconyResponse(balconyServiceBeans);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, administratorListBalconyResponse);
        }       
    }

    private static class AdministratorAddSlideAnimation implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive( PassControlMessage message, Socket socket )
        {
            ServerCommunicationThread server = PassControlServer.getInstance().getServer();
            AdministratorAddSlideImage administratorAddSlideImage = (AdministratorAddSlideImage)message;
            //Confirma o recebimento da resposta
            
            //Salva a imagem na pasta correta
//            boolean status = FileUtils.saveImage(administratorAddSlideImage.getImage().getImage(), IMAGES_SLIDE_PATH + administratorAddSlideImage.getFileName());

            //Altero o arquivo de configurações e mando para os clientes
            ConfigurationFile configurationFile = PassControlConfigurationSynchronizer.getInstance().getConfigurationFile();
            configurationFile.getImgsSlide().put(administratorAddSlideImage.getFileName(), new ImageIcon(administratorAddSlideImage.getImage().getImage()));
            PassControlConfigurationSynchronizer.getInstance().saveConfigurationFile();
            PassControlConfigurationSynchronizer.getInstance().sendConfigurationFileToClients(server);

            //Envio a resposta para o administrador
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, message, MessageActors.AdministratorActor);
            server.addResponseToSend(socket, confirmationResponse);
        }
    }

    private static class AdministratorRemoveSlideAnimation implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive( PassControlMessage message, Socket socket )
        {
            ServerCommunicationThread server = PassControlServer.getInstance().getServer();
            AdministratorRemoveSlideImage administratorRemoveSlideImage = (AdministratorRemoveSlideImage)message;
            //Confirma o recebimento da resposta
            
            //Salva a imagem na pasta correta
            boolean status = FileUtils.deleteFile(IMAGES_SLIDE_PATH + administratorRemoveSlideImage.getFileName());

            //Altero o arquivo de configurações e mando para os clientes
            ConfigurationFile configurationFile = PassControlConfigurationSynchronizer.getInstance().getConfigurationFile();
            configurationFile.getImgsSlide().remove(administratorRemoveSlideImage.getFileName());
            PassControlConfigurationSynchronizer.getInstance().saveConfigurationFile();
            PassControlConfigurationSynchronizer.getInstance().sendConfigurationFileToClients(server);

            //Envio a resposta para o administrador
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(status, administratorRemoveSlideImage, MessageActors.AdministratorActor);
            server.addResponseToSend(socket, confirmationResponse);

        }
    }

    private static class AdministratorSetAutomaticQueueChooserListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive( PassControlMessage message, Socket socket )
        {
            AdministratorSetAutomaticQueueChooser chooserMessage = (AdministratorSetAutomaticQueueChooser)message;
            
            //Altera a configuração
            QueueElementHandler.getInstance().setAutomaticChooseOn(chooserMessage.isIsOn());
            
            //Confirma o recebimento da resposta            
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, message, MessageActors.AdministratorActor);
            ServerCommunicationThread server = PassControlServer.getInstance().getServer();
            server.addResponseToSend(socket, confirmationResponse);
            
            //Altero o arquivo de configurações e mando para os clientes
            ConfigurationFile configurationFile = PassControlConfigurationSynchronizer.getInstance().getConfigurationFile();
            configurationFile.setGerenciamentoAutomatico(chooserMessage.isIsOn());
            PassControlConfigurationSynchronizer.getInstance().saveConfigurationFile();
            PassControlConfigurationSynchronizer.getInstance().sendConfigurationFileToClients(server);

        }
    }

    private static class AdministratorSetTimeIntervalAnimation implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive( PassControlMessage message, Socket socket )
        {
            AdministratorSetTimeSlideInterval administratorSetTimeSlideInterval = (AdministratorSetTimeSlideInterval)message;
            
            //Confirma o recebimento da resposta            
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, message, MessageActors.AdministratorActor);
            ServerCommunicationThread server = PassControlServer.getInstance().getServer();
            server.addResponseToSend(socket, confirmationResponse);

            //Altero o arquivo de configurações e mando para os clientes
            ConfigurationFile configurationFile = PassControlConfigurationSynchronizer.getInstance().getConfigurationFile();
            configurationFile.setSlideShowSpeed(administratorSetTimeSlideInterval.getTimeInterval());
            PassControlConfigurationSynchronizer.getInstance().saveConfigurationFile();
            PassControlConfigurationSynchronizer.getInstance().sendConfigurationFileToClients(server);
            
        }
    }
}

