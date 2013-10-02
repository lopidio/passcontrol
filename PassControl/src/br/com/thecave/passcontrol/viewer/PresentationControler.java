package br.com.thecave.passcontrol.viewer;

import br.com.thecave.passcontrolserver.util.Watchdog;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 *
 * @author Arleudo
 */
public class PresentationControler implements Runnable
{
    //----------------------------------------------------------------------------
    private ArrayList<Image> images;
    private long timePresentation;
    private int index;
    private ArrayList<PresentationControllerObserver> presentationControllerObservers;
    private boolean isRunning;
    //----------------------------------------------------------------------------
    // Construtor
    //----------------------------------------------------------------------------
    public PresentationControler()
    {
        isRunning = false;
        images = new ArrayList<>();
        index = 0;
        timePresentation = 5000;
        presentationControllerObservers = new ArrayList<>();
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
     * in miliseconds
     */
    public void setTime(long newTime)
    {
        this.timePresentation = newTime;
    }
    /**
     * 
     */
    public long getTime()
    {
        return this.timePresentation;
    }
    
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
        images.remove(img);
        return true;
    }
    //----------------------------------------------------------------------------
    /**
     * Remove uma imagem específica da lista
     */
    public boolean remove(int index)
    {
        if(!images.isEmpty() && index >= 0 && index < images.size())
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
        if (images.isEmpty())
            return null;       
        return images.get(index);
    }
    //----------------------------------------------------------------------------
    @Override
    public void run() 
    {
        isRunning = true;
        // inicia o contador
        Watchdog watchdog = new Watchdog(timePresentation);
        while(isRunning)
        {         
            if(watchdog.hasTimedOut())
            {
                if (index >= images.size() - 1)
                {
                    index = 0;
                }
                else
                {
                    ++index;
                }
//                System.out.println("Animation index: " + index + "/" + images.size());                
                for ( PresentationControllerObserver observer : presentationControllerObservers )
                {
                    observer.onPresentationChange(getCurrentImage());
                }
                watchdog = new Watchdog(timePresentation);                
           }
        }
    }
    //----------------------------------------------------------------------------
    public void addObserver(PresentationControllerObserver observer)
    {
        this.presentationControllerObservers.add(observer);
    }
    //----------------------------------------------------------------------------
    public void removeObserver(PresentationControllerObserver observer)
    {
        presentationControllerObservers.remove(observer);
    }
    //----------------------------------------------------------------------------

    public void stop() {
        isRunning = false;
    }
}
