package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import java.awt.Image;

/**
 *
 * @author Arleudo
 */
public class AdministratorAddSlideImage extends PassControlMessage
{
    private Image image;
    private String fileName;

    public AdministratorAddSlideImage(Image image, String fileName) 
    {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
        this.image      = image;
        this.fileName   = fileName;
    }    

    /**
     * @return the image
     */
    public Image getImage()
    {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage( Image image )
    {
        this.image = image;
    }

    /**
     * @return the fileName
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName( String fileName )
    {
        this.fileName = fileName;
    }
}
