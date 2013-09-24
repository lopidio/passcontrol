package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import javax.swing.ImageIcon;

/**
 *
 * @author Arleudo
 */
public class AdministratorAddSlideImage extends PassControlMessage
{
    ImageIcon image;
    String fileName;

    public AdministratorAddSlideImage(ImageIcon image, String fileName) 
    {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
        this.image      = image;
        this.fileName   = fileName;
    }    

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    
}
