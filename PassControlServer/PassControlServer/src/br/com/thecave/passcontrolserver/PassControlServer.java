/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver;

import br.com.thecave.passcontrol.communicationThread.ServerCommunicationThread;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lopidio
 */
public class PassControlServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            ServerCommunicationThread server = new ServerCommunicationThread(23073);
            new Thread(server).start();
            
        } catch (IOException ex) {
            Logger.getLogger(PassControlServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
