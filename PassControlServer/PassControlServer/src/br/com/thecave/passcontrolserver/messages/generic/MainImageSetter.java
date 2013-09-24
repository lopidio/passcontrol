/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.generic;

import javax.swing.ImageIcon;

/**
 *
 * @author guilherme
 */
public class MainImageSetter extends PassControlMessage{
    /**
     * Representa a própria imagem
     */
    ImageIcon imageIcon;

    public MainImageSetter(ImageIcon imageIcon) {
        super(MessageActors.ServerActor, MessageActors.AllActors);
        this.imageIcon = imageIcon;
    }
    
    /**
     * Getter da própria imagem
     * @return imageIcon
     */
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * Setter da própria imagem
     * @param imageIcon imagem
     */
    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }
    
}
