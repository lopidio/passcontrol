/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.generic;

import java.net.Socket;

/**
 *
 * @author guilherme
 */
public interface PassControlMessageListener {
    
    /**
     * Observer Design Pattern
     * @param message 
     */
    void onMessageReceive(PassControlMessage message, Socket socket);
    
}
