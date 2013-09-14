/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.nongeneric;

import br.com.thecave.passcontrolserver.PassControlServer;
import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.dao.BalconyDAO;
import br.com.thecave.passcontrolserver.messagelisteners.generic.ClientListeners;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyCallNextClientRequest;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyCallNextClientResponse;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyInitRequest;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyInitResponse;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyLogin;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyRecallLastClient;
import br.com.thecave.passcontrolserver.messages.generic.ChangeActorMessage;
import br.com.thecave.passcontrolserver.messages.generic.ClientLogoff;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lopidio
 */
public class ClientBalconyListeners implements ClientListeners
{
    private static HashMap<BalconyBean, Socket> unnavaliableBalconySocket = new HashMap<>();

    @Override
    public void addListenersCallback()
    {
        ServerCommunicationThread server = PassControlServer.getInstance().getServer();
        server.addMessageListener(new BalconyInitListener(), BalconyInitRequest.class);
        server.addMessageListener(new BalconyLoginListener(), BalconyLogin.class);
        server.addMessageListener(new BalconyRecallLastClientListener(), BalconyRecallLastClient.class);
        server.addMessageListener(new BalconyCallNextClientRequestListener(), BalconyCallNextClientRequest.class);
        server.addMessageListener(new BalconyChangeActorListener(), ChangeActorMessage.class);
        server.addMessageListener(new BalconyLogoffListener(), ClientLogoff.class);
    }
    
    private static class BalconyInitListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
//            BalconyInitRequest balconyInitRequestMessage = (BalconyInitRequest)message;
            
            //Resposta default
            BalconyInitResponse balconyInitResponse = new BalconyInitResponse(null);
            
            //Pego todos os guichês
            ArrayList<BalconyBean> balconyBeans = BalconyDAO.selectAll();

            //Removo aqueles que estão em uso
            if (balconyBeans != null)
            {
                //Removo os que já estão sendo usados 
                for (Map.Entry<BalconyBean, Socket> unnavaliable : unnavaliableBalconySocket.entrySet()) 
                {
                    balconyBeans.remove(unnavaliable.getKey());
                }
                //Altero a resposta default
                balconyInitResponse.setAvaliableBalconys(balconyBeans);
                
            }
            
            PassControlServer.getInstance().getServer().addResponseToSend(socket, balconyInitResponse);
        }       
    }

    private static class BalconyLoginListener implements PassControlMessageListener {

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
            BalconyLogin balconyLogin = (BalconyLogin)message;

            System.out.println("Guichê inicializado com o número " + balconyLogin.getBalconyBean().getNumber() );
            
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, message, MessageActors.BalconyActor);
            
            //Informo que esse guichê não está mais disponível
            unnavaliableBalconySocket.put(balconyLogin.getBalconyBean(), socket);
            
            PassControlServer.getInstance().getServer().addResponseToSend(socket, confirmationResponse);
        }

    }
       
    private static class BalconyRecallLastClientListener implements PassControlMessageListener {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
            //BalconyCallNextClientRequest
//BalconyCallNextClientResponse

            BalconyRecallLastClient balconyRecall = (BalconyRecallLastClient)message;

            System.out.println("Guichê " + balconyRecall.getBalconyID() + " rechamando último cliente");
            
            //Repassa as informações para os viewers...
            
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, message, MessageActors.BalconyActor);
            
            PassControlServer.getInstance().getServer().addResponseToSend(socket, confirmationResponse);
        }
    }

    private static class BalconyCallNextClientRequestListener implements PassControlMessageListener {

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            BalconyCallNextClientRequest balconyCallNextClient = (BalconyCallNextClientRequest)message;
            System.out.println("Guichê " + balconyCallNextClient.getBalconyID() + " chamando o próximo");
            
            //Envio a informação para os PUSHERS, caso esteja manual
            
            BalconyCallNextClientResponse balconyCallNextClientResponse = new BalconyCallNextClientResponse("Guiguinha cliente", "Fila macarrão", "48-R");
            PassControlServer.getInstance().getServer().addResponseToSend(socket, balconyCallNextClientResponse);
        }
    }

    private static class BalconyChangeActorListener implements PassControlMessageListener {

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
//            ChangeActorMessage changeActorMessage = (ChangeActorMessage)message;

            removeUnnavaliableBalconyFromSocket(socket);

        }


    }

    private static class BalconyLogoffListener implements PassControlMessageListener{

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
//            ClientLogoff clientLogoff = (ClientLogoff)message;
            removeUnnavaliableBalconyFromSocket(socket);
        }
    }
    
    private static boolean removeUnnavaliableBalconyFromSocket(Socket socket)
    {
        //Verifico se um desses sockets estava associado à um guichê
        //Se tiver, digo que esse guichê está disponível
        for (Map.Entry<BalconyBean, Socket> unnavaliable : unnavaliableBalconySocket.entrySet()) {
            BalconyBean balconyBean = unnavaliable.getKey();
            Socket unnavaliableSocket = unnavaliable.getValue();
            if (socket.equals(unnavaliable))
            {
                unnavaliableBalconySocket.remove(balconyBean);
                return true;
            }
        }        
        return false;
    }
        
}
