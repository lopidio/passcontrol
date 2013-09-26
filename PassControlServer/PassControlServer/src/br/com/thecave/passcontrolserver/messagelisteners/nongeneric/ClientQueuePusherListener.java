/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.nongeneric;

import br.com.thecave.passcontrolserver.PassControlServer;
import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.db.bean.ClientBean;
import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.db.dao.ClientDAO;
import br.com.thecave.passcontrolserver.db.dao.QueuesManagerDAO;
import br.com.thecave.passcontrolserver.messagelisteners.generic.ClientListeners;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherAddClient;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherAddQueueElement;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherLoadClientFromRegistration;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherLoadClientResponse;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author lopidio
 */
public class ClientQueuePusherListener implements ClientListeners
{
    @Override
    public void addListenersCallback(ServerCommunicationThread server) 
    {
        server.addMessageListener(new QueuePusherAddQueueElementListener(), QueuePusherAddQueueElement.class);        
        server.addMessageListener(new QueuePusherLoadClientFromRegistrationListener(), QueuePusherLoadClientFromRegistration.class);        
        server.addMessageListener(new QueuePusherAddClientListener(), QueuePusherAddClient.class);        
    }

    private static class QueuePusherAddQueueElementListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
            QueuePusherAddQueueElement addQueueElement = (QueuePusherAddQueueElement)message;
            String passNumber = PassControlServer.generatePassNumber(addQueueElement);

            //Vou inserir um novo elemento
            QueuesManagerBean queuesManagerBean = new QueuesManagerBean();

            //Insiro o cliente, caso não seja nulo
            ClientBean clientBean = addQueueElement.getClientBean();
            String clientName = "";
            if (clientBean != null)
            {
                clientName = clientBean.getName();
            }

            queuesManagerBean.setCheckin(QueuesManagerDAO.dateFormat.format(new Date()));            
            queuesManagerBean.setIdService(addQueueElement.getServiceBean().getId());
            queuesManagerBean.setIdUserCheckin(addQueueElement.getUserBean().getId());
            
            //Crio o número
            queuesManagerBean.setPassNumber(passNumber);
            QueuesManagerDAO.insert(queuesManagerBean);
            
            //Informo ao pusher que deu certo!!
            BalconyShowClientMessage response = new BalconyShowClientMessage(clientName, addQueueElement.getServiceBean().getName(), null, queuesManagerBean, MessageActors.ServerActor, MessageActors.QueuePushActor);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, response);                                
            
            ///Informo à todos os QueuePoppers pra inserir esse elemento
            response.setTo(MessageActors.QueuePopActor);
            PassControlServer.getInstance().getServer().addBroadcastToSend(response);
        }
    }

    private static class QueuePusherLoadClientFromRegistrationListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
            //Retorna um cliente do registro
            QueuePusherLoadClientFromRegistration loadClient = (QueuePusherLoadClientFromRegistration)message;
            ClientBean clientBean = ClientDAO.selectFromRegister(loadClient.getRegister());
            QueuePusherLoadClientResponse clientResponse = new QueuePusherLoadClientResponse(clientBean);
            if(clientBean == null)
            {
                clientResponse.setComment("Nenhum Registro encontrado!");
            }
            else
            {
                clientResponse.setComment("Registro encontrado com sucesso!");
            } 
            PassControlServer.getInstance().getServer().addResponseToSend(socket, clientResponse);
        }
    }

    private static class QueuePusherAddClientListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
            //Retorna um cliente do registro
            QueuePusherAddClient queuePusherAddClient = (QueuePusherAddClient)message;
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, queuePusherAddClient, MessageActors.QueuePushActor);
           
            if (!ClientDAO.insert(queuePusherAddClient.getClientBean()))
            {
                confirmationResponse.setStatusOperation(false);
                confirmationResponse.setComment("Erro ao inserir cliente!");
            }
            
            PassControlServer.getInstance().getServer().addResponseToSend(socket, confirmationResponse);
        }
    }
    
}
