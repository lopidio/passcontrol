/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.util;

import br.com.thecave.passcontrolserver.communicationThread.ClientCommunicationThread;
import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.messages.generic.ConfigurationFileAlterationMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import br.com.thecave.passcontrolserver.messages.generic.RequestConfigurationFile;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author lopidio
 */
public final class PassControlConfigurationSynchronizer implements PassControlMessageListener
{
    private final static String CONFIGURATION_FILE_PATH = "config/configuration.ser";
    private Image mainImage = null;
    private ConfigurationFile configurationFile;
    /**
     * Singleton properties
     */
    private static PassControlConfigurationSynchronizer instance = null;
    public static synchronized PassControlConfigurationSynchronizer getInstance()
    {
        if (instance == null)
            instance = new PassControlConfigurationSynchronizer();
        return instance;
    }

    public PassControlConfigurationSynchronizer() 
    {
        //Carrega também o arquivo de configuração!!
        loadConfigurationFile();
        
    }

    public void requestRefreshConfigurationFile(ClientCommunicationThread clientCommunicationThread)
    {
        RequestConfigurationFile requestConfigurationFile = new RequestConfigurationFile();
        clientCommunicationThread.addBroadcastToSend(requestConfigurationFile);
    }
    
    public Image getMainImage()
    {
        return mainImage;
    }
    
    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket) 
    {
        //Carrega o novo arquivo de configuração
        ConfigurationFileAlterationMessage configurationFileAlterationMessage = (ConfigurationFileAlterationMessage)message;
        configurationFile = configurationFileAlterationMessage.getConfigurationFile();
        saveConfigurationFile();
    }    

    public void addClientListeners(ClientCommunicationThread clientCommunicationThread) 
    {
        clientCommunicationThread.addMessageListener(this, ConfigurationFileAlterationMessage.class); 
    }

    /**
     * Envia à todos os clientes a alteração do arquivo de configuração
     * @param serverCommunicationThread 
     */
    public void sendConfigurationFileToClients(ServerCommunicationThread serverCommunicationThread)
    {
        ConfigurationFileAlterationMessage alterationMessage = new ConfigurationFileAlterationMessage(configurationFile);
        serverCommunicationThread.addBroadcastToSend(alterationMessage);
    }
    
    public void loadConfigurationFile()
    {
        try
        {
            FileInputStream fileIn = new FileInputStream(CONFIGURATION_FILE_PATH);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            configurationFile = (ConfigurationFile) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException exc)
        {
            configurationFile = new ConfigurationFile();
            saveConfigurationFile();
            System.out.println("Arquivo de configuração recriado");
        }        
        mainImage = configurationFile.getMainImage().getImage();
    }
    
    public void saveConfigurationFile()
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(CONFIGURATION_FILE_PATH);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(configurationFile);
            out.close();
            fileOut.close();
            System.out.printf("Arquivo de configuração salvo com sucesso");
        }catch(IOException i)
        {
        }        
    }

    public ConfigurationFile getConfigurationFile() {
        return configurationFile;
    }    
}
