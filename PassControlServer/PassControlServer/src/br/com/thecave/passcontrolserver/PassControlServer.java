/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver;

import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.db.dao.QueuesManagerDAO;
import br.com.thecave.passcontrolserver.messagelisteners.generic.ClientGenericListeners;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientAdministratorListeners;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientBalconyListeners;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientQueuePopperListener;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientQueuePusherListener;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientViewerListener;
import br.com.thecave.passcontrolserver.messages.queuepusher.QueuePusherAddQueueElement;
import br.com.thecave.passcontrolserver.util.QueueElementHandler;
import java.io.IOException;

/**
 *
 * @author lopidio
 */
public class PassControlServer 
{

    /**
     * Propriedades singleton
     */
    private static PassControlServer singletonInstance = null;
    
    public synchronized static PassControlServer getInstance()
    {
        if (singletonInstance == null)
        {
            try 
            {
                singletonInstance = new PassControlServer();
            } catch (IOException ex) {
                return null;
            }
        }
        return singletonInstance;
    }

    public static String generatePassNumber(QueuePusherAddQueueElement addQueueElement) 
    {
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String prefixCode = "";
        for (int i = addQueueElement.getServiceBean().getId(); i > 0;)
        {
            prefixCode += alphabet.charAt(i%alphabet.length());
            i /= alphabet.length();
        }
        prefixCode += "-";
        int id = QueuesManagerDAO.getCountClientsOfService(addQueueElement.getServiceBean().getId())%100; //MOD 100
        return prefixCode+id;
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
        if (server != null)
        {
            // Cadastra os listeners
            new ClientAdministratorListeners().addListenersCallback(server);
            new ClientBalconyListeners().addListenersCallback(server);
            new ClientGenericListeners().addListenersCallback(server);
            new ClientQueuePusherListener().addListenersCallback(server);
            new ClientQueuePopperListener().addListenersCallback(server);
            new ClientViewerListener().addListenersCallback(server);
            new PassControlSystemTray().run();
            QueueElementHandler.getInstance().addListenersCallback(); //

            new Thread(server).start();
        }
        else
        {
            System.out.println("Impossível executar o servidor");
        }
    }

    /**
     * Construtor
     */
    public PassControlServer() throws IOException 
    {
        server = new ServerCommunicationThread(23073);
    }

    public synchronized ServerCommunicationThread getServer() 
    {
        return server;
    }

}
