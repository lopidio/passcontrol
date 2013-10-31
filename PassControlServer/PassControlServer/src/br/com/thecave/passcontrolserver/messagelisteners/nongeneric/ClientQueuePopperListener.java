/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.nongeneric;

import br.com.thecave.passcontrolserver.PassControlServer;
import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.db.dao.ClientDAO;
import br.com.thecave.passcontrolserver.db.dao.QueuesManagerDAO;
import br.com.thecave.passcontrolserver.db.dao.ServiceDAO;
import br.com.thecave.passcontrolserver.messagelisteners.generic.ClientListeners;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import br.com.thecave.passcontrolserver.messages.queuepopper.QueuePopperNewClientAdded;
import br.com.thecave.passcontrolserver.messages.viewer.QueuePopperRefreshAllRequest;
import br.com.thecave.passcontrolserver.messages.viewer.QueuePopperRefreshResponse;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author lopidio
 */
public class ClientQueuePopperListener implements ClientListeners
{
    @Override
    public void addListenersCallback(ServerCommunicationThread server) 
    {
        server.addMessageListener(new QueuePopperRefreshAllRequestListener(), QueuePopperRefreshAllRequest.class);        
    }
    
    public static QueuePopperRefreshResponse generateAllClientsToPopper()
    {
            ArrayList<QueuePopperNewClientAdded> waitingClients = new ArrayList<>();
            
            ArrayList<QueuesManagerBean> queuesManagerBeanList = QueuesManagerDAO.selectAllClientsWaiting();
            for (QueuesManagerBean queuesManagerBean : queuesManagerBeanList) 
            {
                ServiceBean serviceBean = ServiceDAO.selectFromId(queuesManagerBean.getIdService());
                String clientName = "";
                if (queuesManagerBean.getIdClient() != 0)
                {
                    clientName = ClientDAO.selectFromId(queuesManagerBean.getIdClient()).getName();
                }
                waitingClients.add(new QueuePopperNewClientAdded(queuesManagerBean, serviceBean, clientName, "-"));
            }
            //Envio a mensagem de volta
            return new QueuePopperRefreshResponse(waitingClients);        
    }

    private static class QueuePopperRefreshAllRequestListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
//            QueuePopperRefreshAllRequest queuePopperRefreshAllRequest = (QueuePopperRefreshAllRequest)message;

            PassControlServer.getInstance().getServer().addResponseToSend(socket, generateAllClientsToPopper());
        }

    }
 
    
}
