/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.utils;

import br.com.thecave.passcontrol.controller.Main;
import br.com.thecave.passcontrolserver.messages.generic.MainImageRequest;
import br.com.thecave.passcontrolserver.messages.generic.MainImageSetter;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import br.com.thecave.passcontrolserver.util.FileUtils;
import java.awt.Image;
import java.net.Socket;
import javax.swing.ImageIcon;

/**
 *
 * @author lopidio
 */
public class PassControlConfiguration implements PassControlMessageListener
{
    private final static String MAIN_IMAGE_PATH = "imgs/main/mainImage.png";
    private Image mainImage = null;
    /**
     * Singleton properties
     */
    private static PassControlConfiguration instance = null;
    public static synchronized PassControlConfiguration getInstance()
    {
        if (instance == null)
            instance = new PassControlConfiguration();
        return instance;
    }

    public PassControlConfiguration() 
    {
        //carrega a imagem principal
        mainImage = new ImageIcon(MAIN_IMAGE_PATH).getImage();
        //A imagem não foi carregada com sucesso :/
        if (mainImage.getWidth(null) == -1 && mainImage.getHeight(null) == -1)
            mainImage = null;
        
        //Carrega também o arquivo de configuração!!
        
    }
    
    public void requestRefreshMainImage()
    {
        MainImageRequest mainImageRequest = new MainImageRequest();
        Main.getInstance().getCommunicationThread().addBroadcastToSend(mainImageRequest);
    }
    
    public Image getMainImage()
    {
        return mainImage;
    }

    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket)
    {
        MainImageSetter mainImageSetter = (MainImageSetter)message;
        mainImage = mainImageSetter.getImageIcon().getImage();
        
        //Simplesmente salva a imagem aqui
        FileUtils.saveImage(mainImage, MAIN_IMAGE_PATH);
    }

    public void initialize() 
    {
        Main.getInstance().getCommunicationThread().addMessageListener(PassControlConfiguration.getInstance(), MainImageSetter.class);                
        //ConfigurationFile.class
        
        //Carregar esse também
    }
        
}
