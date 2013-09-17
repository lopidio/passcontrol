/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver;

import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.messagelisteners.generic.ClientGenericListeners;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientAdministratorListeners;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientBalconyListeners;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherAddQueueElement;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lopidio
 */
public class PassControlServer {

    /**
     * Propriedades singleton
     */
    private static PassControlServer singletonInstance = null;
    
    public synchronized static PassControlServer getInstance()
    {
        if (singletonInstance == null)
            singletonInstance = new PassControlServer();
        return singletonInstance;
    }

    public static String generatePassNumber(QueuePusherAddQueueElement addQueueElement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Thread que lida com a comunicação com os clientes
     */
    private ServerCommunicationThread server;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        ServerCommunicationThread server = getInstance().server;
        
        //Fazer uma lista de ClientListeners???
        
        // Cadastra os listeners relativos às mensagens do administrador
        new ClientAdministratorListeners().addListenersCallback(server);
        new ClientBalconyListeners().addListenersCallback(server);
        new ClientGenericListeners().addListenersCallback(server);
        new Thread(server).start();
    }

    /**
     * Construtor
     */
    public PassControlServer() 
    {
        try 
        {        
            server = new ServerCommunicationThread(23073);
        }
        catch (IOException ex) 
        {
            Logger.getLogger(PassControlServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized ServerCommunicationThread getServer() 
    {
        return server;
    }

}
