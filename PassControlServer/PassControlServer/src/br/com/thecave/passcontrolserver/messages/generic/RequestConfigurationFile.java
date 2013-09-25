/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.generic;

/**
 *
 * @author guilherme
 */
public class RequestConfigurationFile extends PassControlMessage
{

    public RequestConfigurationFile()
    {
        super(MessageActors.NotIdentified, MessageActors.ServerActor);
    }
    
}
