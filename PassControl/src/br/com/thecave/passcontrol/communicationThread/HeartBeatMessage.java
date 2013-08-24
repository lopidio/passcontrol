/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.messages.PassControlMessage;

/**
 * Mensagem mandada constantemente para verificar status da conexão
 * @author lopidio
 */
public class HeartBeatMessage extends PassControlMessage
{
    public static final long HEART_BEAT_TIME = 20*1000;

    public HeartBeatMessage(MessageActors from, MessageActors to) {
        super(from, to);
    }
    
}
