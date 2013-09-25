package br.com.thecave.passcontrolserver.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author anton_000
 */
public class FileUtils 
{
    public static boolean saveFile(String path, String content)
    {
        FileWriter writer = null;
        PrintWriter saida = null;
        try 
        {
            writer = new FileWriter(new File(path),false);
            saida = new PrintWriter(writer);
            saida.println(content);
            writer.close();
            saida.close();
            return true;
        } catch (IOException ex) 
        {
         return false;
        }
    }    
    
    /**
     * Salva uma image.
     * png ou jpg
     * @param image
     * @param imageName
     * @return 
     */
    public static boolean saveImage(Image image, String imageName) 
    {
        try 
        {
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();        

            ImageIO.write(bufferedImage, "png",new File(imageName));            
            ImageIO.write(bufferedImage, "jpg",new File(imageName));            
            return true;
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
