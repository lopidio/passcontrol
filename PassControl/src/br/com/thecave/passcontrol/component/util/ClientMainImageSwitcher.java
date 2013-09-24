/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.component.util;

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
public class ClientMainImageSwitcher implements PassControlMessageListener
{
    private final static String MAIN_IMAGE_PATH = "imgs/main/mainImage.png";
    private Image mainImage = null;
    /**
     * Singleton properties
     */
    private static ClientMainImageSwitcher instance = null;
    public static synchronized ClientMainImageSwitcher getInstance()
    {
        if (instance == null)
            instance = new ClientMainImageSwitcher();
        return instance;
    }

    public ClientMainImageSwitcher() 
    {
        //carrega a imagem principal
        mainImage = new ImageIcon(MAIN_IMAGE_PATH).getImage();
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
        
}
