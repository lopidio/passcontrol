/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.messages.viewer;

import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.messages.PassControlMessage;

/**
 *
 * @author guilherme
 */
public class ViewerQueueRequest extends PassControlMessage{
    /**
     * Quantidade de elementos que se deseja requisitar
     * Ex.: Se 5, o server retornará, no máximo, os últimos 5 clientes chamados
     */
    int queueSize;

    public ViewerQueueRequest(int queueSize) {
        super(MessageActors.ViewerActor, MessageActors.ServerActor);
        this.queueSize = queueSize;
    }

    /**
     * Setter do tamanho da fila que se deseja requisitar
     * @return tamanho da fila
     */
    public int getQueueSize() {
        return queueSize;
    }

    /**
     * Setter do tamanho da fila que se deseja requisitar
     * @param queueSize novo tamanho
     */
    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

}
