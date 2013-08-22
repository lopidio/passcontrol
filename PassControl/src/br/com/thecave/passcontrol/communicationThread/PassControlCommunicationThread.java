/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

import br.com.thecave.passcontrol.messages.GenericPassControlMessageListener;
import br.com.thecave.passcontrol.messages.PassControlMessage;
import br.com.thecave.passcontrol.messages.PassControlMessageListener;
import br.com.thecave.passcontrol.util.Watchdog;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
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
    HashMap<String, ArrayList<PassControlMessageListener>> passControlMessageListeners;

    public PassControlCommunicationThread() 
    {
        passControlMessageListeners = new HashMap<>();
        running = false;
    }
    
    synchronized public void removeListener(GenericPassControlMessageListener listener, String typeToListenTo) {
        ArrayList<PassControlMessageListener> list = passControlMessageListeners.get(typeToListenTo);
        if (list == null)
        {
            return;
        }
        list.remove(listener);
        
        passControlMessageListeners.put(typeToListenTo, list);
    }
    
    
    synchronized public void addMessageListener(PassControlMessageListener listener, String typeToListenTo)
    {
        ArrayList<PassControlMessageListener> list = passControlMessageListeners.get(typeToListenTo);
        if (list == null)
        {
            list = new ArrayList<>();
        }
        list.add(listener);
        
        passControlMessageListeners.put(typeToListenTo, list);
    }

    /**
     * Envia a mensagem e espera a resposta ou timeout
     * 
     * @param message Mensagem a ser enviada
     * @param typeToListenTo Tipo da resposta esperado
     * @param timeout Tempo de espera (em milissegundos)
     * @return Mensagem esperada ou null
     */
    public PassControlMessage sendMessageAndWaitForResponseOrTimeout(PassControlMessage message, String typeToListenTo, long timeout)
    {
        PassControlMessage retorno = null;
        GenericPassControlMessageListener listener = new GenericPassControlMessageListener();
                
        Watchdog watchdog = new Watchdog(timeout);
        
        addMessageListener(listener, typeToListenTo);
        sendMessage(message);
        
        while (!watchdog.hasTimedOut())
        {
            if (listener.hasReceivedMessage())
            {
                retorno = listener.getReceivedMessage();
                break;
            }
        }
        
        removeListener(listener, typeToListenTo);
        
        return retorno;
    }
    
    /**
     * Envia a mensagem
     *
     * @param message Mensagem a ser enviada
     * @return
     */
    synchronized protected Boolean sendMessage(Socket socket, PassControlMessage message) {
        boolean retorno = false;
        try {
            System.out.println("Mensagem será enviada");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
//            objectOutputStream.close();
            System.out.println("Mensagem enviada com sucesso");
            retorno = true;
        } catch (IOException ex) {
            Logger.getLogger(PassControlCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    /**
     * Recebe a mensagem
     *
     * @return a mensagem que foi recebida
     * @throws IOException
     * @throws ClassNotFoundException
     *
     */
    protected PassControlMessage handleIncomingMessage(InputStream inputStream) throws IOException, ClassNotFoundException {
        PassControlMessage message = null;
        System.out.println("Mensagem será recebida");
        ObjectInputStream input = new ObjectInputStream(inputStream);

        message = (PassControlMessage) input.readObject();
        System.out.println("Mensagem recebida com sucesso");
        return message;
    }
    
    protected void redirectMessage(PassControlMessage message)
    {
        //Filtra só para os escutadores do tipo recebido
        for (PassControlMessageListener listener : passControlMessageListeners.get(message.getType())) 
        {
            listener.onMessageReceive(message);
        }        
    }
    
    abstract void sendMessage(PassControlMessage message);

    abstract void stop();

}
