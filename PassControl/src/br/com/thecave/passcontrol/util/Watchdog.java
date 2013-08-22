/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.util;

public class Watchdog {

    private long until = -1;

    public Watchdog(long timeout) {
        if (timeout < 1) {
            throw new IllegalArgumentException("timeout lesser than 1.");
        }
        until = System.currentTimeMillis() + timeout;        
    }

    /**
     * Passa a retornar verdadeiro quando o timeout expira
     * @return 
     */
    public boolean hasTimedOut() {
        return System.currentTimeMillis() >= until;
    }
}