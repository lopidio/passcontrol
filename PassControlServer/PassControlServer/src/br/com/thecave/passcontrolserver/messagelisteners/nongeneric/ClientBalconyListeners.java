/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.nongeneric;

import br.com.thecave.passcontrolserver.PassControlServer;
import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.db.dao.UserDAO;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyInitRequest;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyInitResponse;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyLogin;
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
public class ClientBalconyListeners 
{
    public static void addListenersCallback()
    {
        ServerCommunicationThread server = PassControlServer.getInstance().getServer();
        server.addMessageListener(new ClientBalconyListeners.BalconyInitListener(), "BalconyInitRequest");
        server.addMessageListener(new ClientBalconyListeners.BalconyLoginListener(), "BalconyLogin");
    }
    
    private static class BalconyInitListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
//            BalconyInitRequest balconyInitRequestMessage = (BalconyInitRequest)message;
            
            ArrayList<String> types = new ArrayList<>();
            types.add("Tipo comum");
            types.add("Prioritário");
            types.add("Caseiro");
            
            ArrayList<String> number = new ArrayList<>();
            number.add("1+");
            number.add("2+");
            number.add("14=");
            
            BalconyInitResponse balconyInitResponse = new BalconyInitResponse(types, number);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, balconyInitResponse);
        }       
    }

    private static class BalconyLoginListener implements PassControlMessageListener {

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
            BalconyLogin balconyLogin = (BalconyLogin)message;

            System.out.println("Guichê inicializado com o número " + balconyLogin.getNumber() + " e atende " + balconyLogin.getType() );
            
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, message, MessageActors.BalconyActor);
            
            PassControlServer.getInstance().getServer().addResponseToSend(socket, confirmationResponse);
        }

    }
        
}
