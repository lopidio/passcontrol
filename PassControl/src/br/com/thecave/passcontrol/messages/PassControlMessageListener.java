/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.messages;

/**
 *
 * @author guilherme
 */
public interface PassControlMessageListener {
    
    /**
     * Observer Design Pattern
     * @param message 
     */
    void onMessageReceive(PassControlMessage message);
    
}
