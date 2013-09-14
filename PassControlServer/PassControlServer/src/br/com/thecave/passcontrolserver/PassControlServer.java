/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver;

import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.messagelisteners.generic.ClientGenericListeners;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientAdministratorListeners;
import br.com.thecave.passcontrolserver.messagelisteners.nongeneric.ClientBalconyListeners;
import java.io.IOException;
import java.util.ArrayList;
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
        new ClientAdministratorListeners().addListenersCallback();
        new ClientBalconyListeners().addListenersCallback();
        new ClientGenericListeners().addListenersCallback();
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
