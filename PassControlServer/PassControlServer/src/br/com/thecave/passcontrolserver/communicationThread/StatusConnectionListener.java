/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.communicationThread;

/**
 *
 * @author guilherme
 */
public interface StatusConnectionListener 
{
    /**
     * Método chamado sempre que o status da conexão for alterado
     * @param connectionStatus 
     */
    void onChangeConnection(boolean connectionStatus);
}
