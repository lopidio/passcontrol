/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.nongeneric;

import br.com.thecave.passcontrolserver.PassControlServer;
import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.db.bean.ClientBean;
import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.db.dao.BalconyDAO;
import br.com.thecave.passcontrolserver.db.dao.ClientDAO;
import br.com.thecave.passcontrolserver.db.dao.QueuesManagerDAO;
import br.com.thecave.passcontrolserver.db.dao.ServiceDAO;
import br.com.thecave.passcontrolserver.messagelisteners.generic.ClientListeners;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherAddClient;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherAddQueueElement;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherLoadClientFromRegistration;
import br.com.thecave.passcontrolserver.messages.viewer.ViewerQueueRequest;
import br.com.thecave.passcontrolserver.messages.viewer.ViewerQueueResponse;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author lopidio
 */
public class ClientViewerListener  implements ClientListeners
{
    @Override
    public void addListenersCallback(ServerCommunicationThread server) 
    {
        server.addMessageListener(new ViewerQueueRequestListener(), ViewerQueueRequest.class);        
    }
    
    static class ViewerQueueRequestListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
            //Retorna um cliente do registro
//            ViewerQueueRequest viewerQueueRequest = (ViewerQueueRequest)message;
            
            
            //Informo ao server que quero todos os últimos clientes de hoje de cada serviço
            //Respondo ao cliente
            ArrayList<BalconyShowClientMessage> showClientMessages = new ArrayList<>();
            ArrayList<QueuesManagerBean> lastCalledClients = QueuesManagerDAO.selectLastCalledClientsFromEachServicesToday();
            for (QueuesManagerBean queuesManagerBean : lastCalledClients) 
            {
                
                String clientName = "";
                ClientBean clientBean = ClientDAO.selectFromId(queuesManagerBean.getIdClient());
                if (clientBean != null)
                {
                    clientName = clientBean.getName();
                }

                //Captura o nome do serviço
                String serviceName = ServiceDAO.selectFromId(queuesManagerBean.getIdService()).getName();
                //Captura o nome do guichê
                String balconyName = BalconyDAO.selectFromId(queuesManagerBean.getIdBalcony()).getNumber();                

                BalconyShowClientMessage clientMessage = new BalconyShowClientMessage(clientName, serviceName, balconyName, queuesManagerBean, MessageActors.ServerActor, MessageActors.AllActors);
                //adiciono na lista de retorno
                showClientMessages.add(clientMessage);
            }
            ViewerQueueResponse queueResponse = new ViewerQueueResponse(showClientMessages);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, queueResponse);
        }
    }
        
}
