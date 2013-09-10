/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.communicationThread;

import br.com.thecave.passcontrolserver.db.bean.UserBean;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lopidio
 */
public class ClientUserSocketPair 
{
    /**
     * Usuário logado. (null enquanto não logado)
     */
    UserBean user = null;
    
    /**
     * Socket que representa o cliente
     */
    Socket socket;
    
    /**
     * Hora de login
     */
    Date loginTime = null;

    public ClientUserSocketPair(Socket socket)
    {
        this.socket = socket;
    }
    
    public void login(UserBean user)
    {
        this.user = user;
        loginTime = new java.util.Date();
    }
    
    public void logoff()
    {
        this.user = null;
        loginTime = null;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    
    public UserBean getUser() {
        return user;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    public void closeConnection()
    {
        logoff();        
        try {
            if (isConnected())
                socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientUserSocketPair.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            socket = null;
        }
    }
    
    public boolean isConnected()
    {
        return socket != null;
    }
    
}
