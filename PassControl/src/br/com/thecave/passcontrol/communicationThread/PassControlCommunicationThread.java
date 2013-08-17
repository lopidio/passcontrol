/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

import br.com.thecave.passcontrol.messages.PassControlMessage;
import com.sun.corba.se.impl.orbutil.concurrent.Mutex;
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

    Mutex mutex;

    public PassControlCommunicationThread() 
    {
        mutex = new Mutex();
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
            mutex.acquire();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            objectOutputStream.close();
            System.out.println("Mensagem enviada com sucesso");
            retorno = true;
        } catch (IOException | InterruptedException ex) 
        {
            Logger.getLogger(PassControlCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            mutex.release();
        }
        return retorno;
    }

    /**
     * Recebe a mensagem
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    protected void handleIncomingMessage(InputStream inputStream) throws IOException, ClassNotFoundException 
    {
        try
        {
            System.out.println("Mensagem será recebida");
            mutex.acquire();
            ObjectInputStream input = new ObjectInputStream(inputStream);
            mutex.release();
            PassControlMessage message = null;

            message = (PassControlMessage)input.readObject();
            System.out.println("Mensagem recebida com sucesso");
            //Pede que a subclasse trate a mensagem corretamente
            handleMessage(message);
            
            //TODO HANDLE THIS
        } catch (InterruptedException ex) {
            Logger.getLogger(PassControlCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            mutex.release();
        }
    }

    abstract void handleMessage(PassControlMessage message);
    abstract void sendMessage(PassControlMessage message);
}
