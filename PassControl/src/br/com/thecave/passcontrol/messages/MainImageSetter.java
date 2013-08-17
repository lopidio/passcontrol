/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.messages;

import java.awt.Image;

/**
 *
 * @author guilherme
 */
public class MainImageSetter extends PassControlMessage{
    /**
     * Representa a própria imagem
     */
    Image image;
    
    /**
     * Armazena o nome em que a imagem deverá ser salva
     */
    String imageName;

    public MainImageSetter(Image image, String imageName) {
        super(MessageActors.ServerActor, MessageActors.AllActors, "MainImageSetter");
        this.image = image;
        this.imageName = imageName;
    }

    /**
     * Getter da própria imagem
     * @return image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Setter da própria imagem
     * @param image imagem
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Retorna o nome da imagem
     * @return imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Altera o nome da imagem
     * @param imageName novo nome da imagem
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    
    
}
