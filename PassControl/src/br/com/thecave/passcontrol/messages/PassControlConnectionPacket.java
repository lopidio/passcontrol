/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.messages;

import java.net.Socket;

/**
 *
 * @author guilherme
 */
public class PassControlConnectionPacket
{
    PassControlMessage message;
    
    /**
     * Socket em que a comunicação ocorreu
     */
    Socket socket;

    public PassControlConnectionPacket(PassControlMessage message, Socket socket) {
        this.message = message;
        this.socket = socket;
    }
    
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public PassControlMessage getMessage() {
        return message;
    }

    public void setMessage(PassControlMessage message) {
        this.message = message;
    }


    
}
