/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.component.util;

import br.com.thecave.passcontrolserver.messages.generic.MainImageSetter;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import java.net.Socket;

/**
 *
 * @author lopidio
 */
public class ClientMainImageSwitcherListener implements PassControlMessageListener
{
    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket)
    {
        MainImageSetter mainImageSetter = (MainImageSetter)message;
        //Trocar a imagem aqui. :D
    }
    
}
