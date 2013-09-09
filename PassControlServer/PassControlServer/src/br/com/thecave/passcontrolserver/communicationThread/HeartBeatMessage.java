/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.communicationThread;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 * Mensagem mandada constantemente para verificar status da conexão
 * @author lopidio
 */
public class HeartBeatMessage extends PassControlMessage
{
    public static final long HEART_BEAT_TIME = 30*1000; //In miliseconds

    public HeartBeatMessage(MessageActors from, MessageActors to) {
        super(from, to);
    }
    
}
