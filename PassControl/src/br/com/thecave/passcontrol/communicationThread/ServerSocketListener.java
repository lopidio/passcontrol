/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

import br.com.thecave.passcontrol.messages.MessageActors;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilherme
 */
public class ServerSocketListener implements Runnable
{
    /**
     * Artifício utilizado para encerrar a thread
     */
    boolean running;
    
    /**
     * Porta do socket
     */
    int port;
    /**
     * Server que escuta a porta
     */
    ServerSocket serverSocket;   
    
    /**
     * Thread tratadora da comunicação
     */
    ServerCommunicationThread owner;

    public ServerSocketListener(int port, ServerCommunicationThread owner) throws IOException 
    {
        running = false;
        this.port = port;
        this.owner = owner;
        serverSocket = new ServerSocket(port);

    }
    
    @Override
    public void run() 
    {
        running = true;
        System.out.println("Server esperando conexão");
        while (running)
        {
            try {
                Socket newClient = serverSocket.accept();
                System.out.println("Novo cliente aceito");
                owner.addClient(MessageActors.NotIdentified, newClient);
            } catch (IOException ex) {
                Logger.getLogger(ServerSocketListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    void stop() {
        try {
            running = false;
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerSocketListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
