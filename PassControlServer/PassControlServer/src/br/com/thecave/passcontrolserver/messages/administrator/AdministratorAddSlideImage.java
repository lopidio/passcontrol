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
    Image image;
    String fileName;

    public AdministratorAddSlideImage(Image image, String fileName) 
    {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
        this.image      = image;
        this.fileName   = fileName;
    }    
}
