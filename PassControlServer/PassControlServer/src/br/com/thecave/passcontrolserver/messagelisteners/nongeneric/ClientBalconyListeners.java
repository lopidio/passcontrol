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
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author lopidio
 */
public class ClientBalconyListeners implements ClientListeners
{

    @Override
    public void addListenersCallback()
    {
        ServerCommunicationThread server = PassControlServer.getInstance().getServer();
        server.addMessageListener(new ClientBalconyListeners.BalconyInitListener(), BalconyInitRequest.class);
        server.addMessageListener(new ClientBalconyListeners.BalconyLoginListener(), BalconyLogin.class);
        server.addMessageListener(new ClientBalconyListeners.BalconyRecallLastClientListener(), BalconyRecallLastClient.class);
        server.addMessageListener(new ClientBalconyListeners.BalconyCallNextClientRequestListener(), BalconyCallNextClientRequest.class);
    }
    
    private static class BalconyInitListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
//            BalconyInitRequest balconyInitRequestMessage = (BalconyInitRequest)message;
            BalconyInitResponse balconyInitResponse = new BalconyInitResponse(PassControlServer.getInstance().getAvaliableBalconys());
            PassControlServer.getInstance().getServer().addResponseToSend(socket, balconyInitResponse);
        }       
    }

    private static class BalconyLoginListener implements PassControlMessageListener {

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
            BalconyLogin balconyLogin = (BalconyLogin)message;

            System.out.println("Guichê inicializado com o número " + balconyLogin.getBalconyBean().getNumber() );
            
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, message, MessageActors.BalconyActor);
            confirmationResponse.setStatusOperation(PassControlServer.getInstance().setBalconyUsing(balconyLogin.getBalconyBean(), true));
            
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
        
}
