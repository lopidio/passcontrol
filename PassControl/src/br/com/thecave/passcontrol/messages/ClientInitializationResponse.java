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
    boolean loginStatus;

    public ClientInitializationResponse(int permissionCode, boolean loginStatus, MessageActors to) {
        super(MessageActors.ServerActor, to);
        this.permissionCode = permissionCode;
        this.loginStatus = loginStatus;
    }

    public int getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(int permissionCode) {
        this.permissionCode = permissionCode;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
    
    

}
