package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author Arleudo
 */
public class AdministratorRemoveSlideImage extends PassControlMessage
{
    String fileName;

    public AdministratorRemoveSlideImage( String fileName) 
    {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
        this.fileName   = fileName;
    }    

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    
}
