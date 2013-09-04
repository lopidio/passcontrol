package br.com.thecave.passcontrol.viewer;

import br.com.thecave.passcontrol.util.Watchdog;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Arleudo
 */
public class PresentationControler implements Runnable
{
    //----------------------------------------------------------------------------
    private ArrayList<Image> images;
    private long timePresentation;
    private static PresentationControler instance;
    private int index;
    private JLabel label;
    //----------------------------------------------------------------------------
    // Construtor
    //----------------------------------------------------------------------------
    private PresentationControler()
    {
        images = new ArrayList<>();
        index = 0;
        timePresentation = 5000;
        Image img = Toolkit.getDefaultToolkit().getImage("imgs/presentation/quadro.jpg");
        images.add(img);
        //Não preciso instanciar Label aqui? Oo
    }
    /**
     * 
     */
    public static PresentationControler getInstance()
    {
        if(instance == null)
            instance = new PresentationControler();
        return instance;
    }
    //----------------------------------------------------------------------------
    /**
     * @return the images
     */
    public ArrayList<Image> getImages() 
    {
        return images;
    }
    //----------------------------------------------------------------------------
    /**
     * @param images the images to set
     */
    public void setImages(ArrayList<Image> images) 
    {
        this.images = images;
    }
    /**
     * 
     */
    public void setTime(long newTime)
    {
        this.timePresentation = newTime * 1000;
    }
    /**
     * 
     */
    public long getTime()
    {
        return this.timePresentation;
    }
    
    public void setLabel(JLabel label)
    {
        this.label = label;
    }
    //----------------------------------------------------------------------------
    /**
     * Adiciona uma imagem no final da lista
     */
    public void addImage(Image img)
    {
        images.add(img);
    }
    //----------------------------------------------------------------------------
    /**
     * Remove uma imagem específica da lista
     */
    public boolean remove(Image img)
    {
        if(images.size() > 1)
        {
            images.remove(img);
            return true;
        }
        else
            return false;        
    }
    //----------------------------------------------------------------------------
    /**
     * Remove uma imagem específica da lista
     */
    public boolean remove(int index)
    {
        if(images.size() > 1 || index > -1)
        {
            images.remove(index);
            return true;
        }
        else
            return false;        
    }
    //----------------------------------------------------------------------------
    public Image getCurrentImage()
    {
        if(index > images.size()-1)
        {
            index = 0;
        }
        return this.images.get(this.index);
    }
    //----------------------------------------------------------------------------
    @Override
    public void run() 
    {
        // inicia o contador
        Watchdog watchdog = new Watchdog(timePresentation);
        while(true)
        {         
            if(watchdog.hasTimedOut())
            {
                index++;
                Image img = getCurrentImage();
                if(img != null && label != null)
                {
                    ImageIcon ic = new ImageIcon(img);
                    label.setIcon(ic);
                }
                watchdog = new Watchdog(timePresentation);
           }
        }
    }
    //----------------------------------------------------------------------------
}
