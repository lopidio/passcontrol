/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.generic;

/**
 *
 * @author guilherme
 */
public class ClientListService extends PassControlMessage
{    
     public ClientListService(MessageActors from) {
        super(from, MessageActors.ServerActor);
    }   
    
}
