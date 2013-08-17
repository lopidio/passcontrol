/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

import br.com.thecave.passcontrol.messages.PassControlMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilherme
 */
public abstract class PassControlCommunicationThread implements Runnable {

    /**
     * Artifício para encerrar thread
     */
    protected boolean running;

    public PassControlCommunicationThread() 
    {
        running = false;
    }
    
    /**
     * Envia a mensagem
     * @param message Mensagem a ser enviada
     * @return 
     */
    protected Boolean sendMessage(Socket socket, PassControlMessage message)
    {
        boolean retorno = false;
        try 
        {
            System.out.println("Mensagem será enviada");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
//            objectOutputStream.close();
            System.out.println("Mensagem enviada com sucesso");
            retorno = true;
        } catch (IOException ex) 
        {
            Logger.getLogger(PassControlCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    /**
     * Recebe a mensagem
     * @return a mensagem que foi recebida
     * @throws IOException
     * @throws ClassNotFoundException 
     * 
     */
    protected PassControlMessage handleIncomingMessage(InputStream inputStream) throws IOException, ClassNotFoundException 
    {
        PassControlMessage message = null;
            System.out.println("Mensagem será recebida");
            ObjectInputStream input = new ObjectInputStream(inputStream);

            message = (PassControlMessage)input.readObject();
            System.out.println("Mensagem recebida com sucesso");
            //Pede que a subclasse trate a mensagem corretamente
            handleMessage(message);

            return message;
    }

    abstract void handleMessage(PassControlMessage message);
    abstract void sendMessage(PassControlMessage message);
    abstract void stop();            
}
