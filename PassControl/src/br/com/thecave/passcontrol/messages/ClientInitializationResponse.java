/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.messages;

import java.awt.Image;
import java.net.Socket;

/**
 *
 * @author guilherme
 */
public class ClientInitializationResponse extends PassControlMessage
{
    int permissionCode;
    Image mainImage;

    public ClientInitializationResponse(MessageActors to, int permissionCode, Image mainImage, Socket socket) {
        super(MessageActors.ServerActor, to, socket);
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
