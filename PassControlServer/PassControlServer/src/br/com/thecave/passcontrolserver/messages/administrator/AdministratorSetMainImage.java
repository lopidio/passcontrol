/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import java.awt.Image;

/**
 *
 * @author lopidio
 */
public class AdministratorSetMainImage extends PassControlMessage
{
    /**
     * Imagem em si
     */
    Image image;
    
    /**
     * Nome que a imagem deverá ser salva
     */
    String name;

    public AdministratorSetMainImage(Image image, String name) 
    {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
        this.image = image;
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
