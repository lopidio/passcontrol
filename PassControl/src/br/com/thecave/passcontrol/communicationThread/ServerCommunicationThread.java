/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.messages.PassControlMessage;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author guilherme
 */
public class ServerCommunicationThread extends PassControlCommunicationThread {

    int port;
    /**
     * Server que escuta a porta
     */
    ServerSocket serverSocket;
    /**
     * Mapa de clientes identificados pelo ator
     */
    HashMap<MessageActors, ArrayList<Socket>> clients;

    public ServerCommunicationThread(int port) {
        this.port = port;
        clients = new HashMap<>();
    }

    public ServerCommunicationThread() {
    }

    @Override
    void handleMessage(PassControlMessage message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void sendMessage(PassControlMessage message) {
        
        //Se a mensagem for para todos...
        if (message.getTo() == MessageActors.AllActors) {
            for (Map.Entry<MessageActors, ArrayList<Socket>> entry : clients.entrySet()) {
                ArrayList<Socket> arrayList = entry.getValue();
                for (Socket client : arrayList) 
                {
                    //Manda para todos
                    sendMessage(client, message);
                  
                }
            }
        } else {
            //Filtra só para os clientes desejados
            for (Socket client : clients.get(message.getTo())) {
                sendMessage(client, message);
            }
        }
    }
}
