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
public class ClientInitializationResponse extends PassControlMessage
{
    int permissionCode;
    Image mainImage;

    public ClientInitializationResponse(MessageActors to, int permissionCode, Image mainImage) {
        super(MessageActors.ServerActor, to);
        this.permissionCode = permissionCode;
        this.mainImage = mainImage;
    }

    public int getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(int permissionCode) {
        this.permissionCode = permissionCode;
    }

    public Image getMainImage() {
        return mainImage;
    }

    public void setMainImage(Image mainImage) {
        this.mainImage = mainImage;
    }
    
}
