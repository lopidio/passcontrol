/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver;

import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.db.dao.QueuesManagerDAO;
import br.com.thecave.passcontrolserver.messagelisteners.generic.ClientGenericListeners;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientAdministratorListeners;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientBalconyListeners;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientQueuePusherListener;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientViewerListener;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
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
        int id = QueuesManagerDAO.getCountOfClientsOfServiceToday(addQueueElement.getServiceBean().getId());
        return prefixCode+id;
    }
    
    /**
     * Thread que lida com a comunicação com os clientes
     */
    private ServerCommunicationThread server;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException 
    {
        ServerCommunicationThread server = getInstance().server;
        
        //Fazer uma lista de ClientListeners???
        
        // Cadastra os listeners relativos às mensagens do administrador
        new ClientAdministratorListeners().addListenersCallback(server);
        new ClientBalconyListeners().addListenersCallback(server);
        new ClientGenericListeners().addListenersCallback(server);
        new ClientQueuePusherListener().addListenersCallback(server);
        new ClientViewerListener().addListenersCallback(server);
        
        new Thread(server).start();
        
        Integer count = 0;
        while(true)
        {
            ++count;
            Thread.sleep(30000);
            Integer serviceId = count%10;
            BalconyShowClientMessage showClientMessage = new BalconyShowClientMessage("Irru"+count, serviceId+"", "c"+count, new QueuesManagerBean(), MessageActors.ServerActor, MessageActors.AllActors);
            server.addBroadcastToSend(showClientMessage);
        }
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
