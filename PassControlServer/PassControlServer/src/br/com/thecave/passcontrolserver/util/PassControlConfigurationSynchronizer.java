/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.util;

import br.com.thecave.passcontrolserver.communicationThread.ClientCommunicationThread;
import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.messages.generic.ConfigurationFileAlterationMessage;
import br.com.thecave.passcontrolserver.messages.generic.MainImageRequest;
import br.com.thecave.passcontrolserver.messages.generic.MainImageSetter;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.ImageIcon;
import sun.applet.Main;

/**
 *
 * @author lopidio
 */
public final class PassControlConfigurationSynchronizer
{
    private final static String MAIN_IMAGE_PATH = "imgs/main/mainImage.png";
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
        //carrega a imagem principal
        mainImage = new ImageIcon(MAIN_IMAGE_PATH).getImage();
        //A imagem não foi carregada com sucesso :/
        if (mainImage.getWidth(null) == -1 && mainImage.getHeight(null) == -1)
            mainImage = null;
        
        //Carrega também o arquivo de configuração!!
        configurationFile = new ConfigurationFile();
        loadConfigurationFile();
        
    }
    
    public void requestRefreshMainImage(ClientCommunicationThread clientCommunicationThread)
    {
        MainImageRequest mainImageRequest = new MainImageRequest();
        clientCommunicationThread.addBroadcastToSend(mainImageRequest);
    }
    
    public Image getMainImage()
    {
        return mainImage;
    }

    public void initializeAsClient(ClientCommunicationThread clientCommunicationThread) 
    {
        //Adiciona os listeners
        clientCommunicationThread.addMessageListener(new PassControlMessageListener() 
        {

            @Override
            public void onMessageReceive(PassControlMessage message, Socket socket) 
            {
                //Carrega a nova imagem principal
                MainImageSetter mainImageSetter = (MainImageSetter)message;
                mainImage = mainImageSetter.getImageIcon().getImage();

                //Simplesmente salva a imagem aqui
                FileUtils.saveImage(mainImage, MAIN_IMAGE_PATH);            
            }
        }, MainImageSetter.class);                
        
        
        clientCommunicationThread.addMessageListener(new PassControlMessageListener() 
        {
            @Override
            public void onMessageReceive(PassControlMessage message, Socket socket) 
            {
                //Carrega o novo arquivo de configuração
                loadConfigurationFile();
            }
        }, ConfigurationFileAlterationMessage.class);                       
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
        }catch(IOException i)
        {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Arquivo de configuração não encontrado");
            c.printStackTrace();
            return;
         }        
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
