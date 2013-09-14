/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.util;

import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;

/**
 *
 * @author guilherme
 */
public class AutomaticNextQueueChooser 
{
    private boolean on = false;

    /**
     * Singleton properties
     */
    private static AutomaticNextQueueChooser instance = null;
    public synchronized static AutomaticNextQueueChooser getInstance()
    {
        if (instance == null)
            instance = new AutomaticNextQueueChooser();
        return instance;
    }
        
    public QueuesManagerBean chooseNextElement(int balconyId)
    {
        //Fluxo:
        /**
         * 
         * Verifica quais serviços esse guichê atende.
         * 
         * Verifica se tem alguém com prioridade máxima.
         *      Se tiver:
         *          retorna o próximo de prioridade máxima;
         *      Se não:
         *          Verifica se tem algum elemento com prioridade alta, média ou baixa
         *              Se tiver:
         *                  Verifica qual, dessas 3 prioridades, foi a última a ser chamada;
         *                  Incrementa em um esse valor;
         *                  Retorna o próximo dessa prioridade incrementada;
         *              Se não:
         *              Verifica se tem algum da prioridade mínima
         *                  Se tiver:
         *                      retorna o próximo de prioridade mínima;
         *                  Se não:
         *                      retorna nulo;
         */
        
        
        return new QueuesManagerBean();
    }
    
    public boolean isOn() 
    {
        return on;
    }

    public void setOn(boolean on) 
    {
        this.on = on;
    }
    
    
}
