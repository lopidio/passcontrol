/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.component.util;

import br.com.thecave.passcontrolserver.messages.generic.MainImageSetter;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import javax.imageio.ImageIO;

/**
 *
 * @author lopidio
 */
public class ClientMainImageSwitcher implements PassControlMessageListener
{
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
        ///mainImage = new ...
    }
    
    
    
    public Image getMainImage()
    {
        return mainImage;
    }
    
    public void verifyImageChange()
    {
        
    }
    
    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket)
    {
        MainImageSetter mainImageSetter = (MainImageSetter)message;
        //Trocar a imagem aqui. :D
        BufferedImage bufferedImage = toBufferedImage(mainImage);

        
//        BufferedImage bufferedImage= new BufferedImage(toolkitImage.getWidth(), toolkitImage.getHeight(), BufferedImage.TYPE_INT_RGB);
//        image.getGraphics().drawImage(toolkitImage, 0, 0, null);
//        ImageIO.write(bufferedImage, "jpg", new File("C:\\myImage.jpg"));
    }
    
    private static void save(BufferedImage image, String ext) 
    {
        String fileName = "savingAnImage";
        File file = new File(fileName + "." + ext);
        try {
            ImageIO.write(image, ext, file);  // ignore returned boolean
        } catch(IOException e) {
            System.out.println("Write error for " + file.getPath() +
                               ": " + e.getMessage());
        }
    }    
    
    private static BufferedImage toBufferedImage(Image src) 
    {
        int w = src.getWidth(null);
        int h = src.getHeight(null);
        int type = BufferedImage.TYPE_INT_RGB; // other options
        BufferedImage dest = new BufferedImage(w, h, type);
        Graphics2D g2 = dest.createGraphics();
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return dest;
    }
    
}
