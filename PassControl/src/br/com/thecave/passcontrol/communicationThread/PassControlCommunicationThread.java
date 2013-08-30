/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

import br.com.thecave.passcontrol.messages.PassControlMessage;
import br.com.thecave.passcontrol.messages.PassControlConnectionPacket;
import br.com.thecave.passcontrol.messages.PassControlConnectionMessageListener;
import br.com.thecave.passcontrol.util.Watchdog;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilherme
 */
public abstract class PassControlCommunicationThread implements Runnable {

    /**
     * Atributo verificador de envio de mensagens de protocolo
     */
    private Watchdog watchdog;
    
    /**
     * Mensagem de protocolo
     */
    private HeartBeatMessage heartBeatMessage;
    /**
     * Artifício para encerrar thread
     */
    protected boolean running;
    /**
     * Escutadores de mensagens
     */
    protected HashMap<String, ArrayList<PassControlConnectionMessageListener>> passControlMessageListeners;
    
    /**
     * 
     */
    private final ArrayBlockingQueue<PassControlMessage> messagesToSend;

    /**
     * 
     */
    private final ArrayBlockingQueue<PassControlConnectionPacket> responsesToSend;    
    
    /**
     * 
     * @param heartBeatMessage 
     */            
    public PassControlCommunicationThread(HeartBeatMessage heartBeatMessage) 
    {
        this.heartBeatMessage = heartBeatMessage;
        passControlMessageListeners = new HashMap<>();
        running = false;
        watchdog = new Watchdog(HeartBeatMessage.HEART_BEAT_TIME);    
        messagesToSend = new ArrayBlockingQueue<>(50);
        responsesToSend = new ArrayBlockingQueue<>(50);
    }
    
    synchronized public void removeListener(GenericPassControlMessageListener listener, String typeToListenTo) {
        ArrayList<PassControlConnectionMessageListener> list = passControlMessageListeners.get(typeToListenTo);
        if (list == null)
        {
            return;
        }
        list.remove(listener);
        
        passControlMessageListeners.put(typeToListenTo, list);
    }
    
    
    synchronized public void addMessageListener(PassControlConnectionMessageListener listener, String typeToListenTo)
    {
        ArrayList<PassControlConnectionMessageListener> list = passControlMessageListeners.get(typeToListenTo);
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
    public PassControlConnectionPacket sendMessageAndWaitForResponseOrTimeout(PassControlMessage message, String typeToListenTo, long timeout)
    {
        PassControlConnectionPacket retorno = null;
        GenericPassControlMessageListener listener = new GenericPassControlMessageListener();

        Watchdog timeOutWatcher = new Watchdog(timeout);

        addMessageListener(listener, typeToListenTo);
        addMessageToSend(message);

        while (!timeOutWatcher.hasTimedOut())
        {
            if (!isOutBufferEmpty())
            {
                System.out.println("Mensagem ainda não enviada");   
                break;
            }
            System.out.println("Mensagem enviada e aguardando retorno");            
            if (listener.hasReceivedMessage())
            {
                System.out.println("Retorno já foi recebido");
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
    synchronized protected Boolean sendMessage(Socket socket, PassControlMessage message) throws IOException {
        if (socket == null)
            throw new IOException("Null socket");

        System.out.println("Mensagem será enviada");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(message);
        System.out.println("Mensagem enviada com sucesso " + message.getType());
        return true;
    }

    /**
     * Recebe a mensagem
     *
     * @return a mensagem que foi recebida
     * @throws IOException
     * @throws ClassNotFoundException
     *
     */
    protected PassControlConnectionPacket handleIncomingMessage(Socket socket) throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();
        System.out.println("Mensagem será recebida");
        ObjectInputStream input = new ObjectInputStream(inputStream);
        PassControlMessage message = (PassControlMessage) input.readObject();
        PassControlConnectionPacket receivedMessage = new PassControlConnectionPacket(message, socket);
        System.out.println("Mensagem recebida com sucesso: " + message.getType());
        return receivedMessage;
    }
    
    protected void redirectReceivedMessage(PassControlConnectionPacket receivedPacket)
    {
        ArrayList<PassControlConnectionMessageListener> listenersArray = passControlMessageListeners.get(receivedPacket.getMessage().getType());
        //Não há escutador cadastradoI
        if (listenersArray == null)
            return;
        
        //Filtra só para os escutadores do tipo recebido
        for (PassControlConnectionMessageListener listener : listenersArray)
        {
            listener.onMessageReceive(receivedPacket);
        }        
    }
    
    /**
     * Periodicamente envia mensagens ao outro lado da conexão para verificar se ainda está ativo
     * @return true caso tenha enviado a mensagem
     */
    protected boolean checkMessageProtocol()
    {
        //Periodicamente, o cliente tenta enviar uma mensagem ao servidor, só para indicar que tá vivo
        //E ajuda o servidor a identificar os clientes que estão mortos e tal
        if (watchdog.hasTimedOut())
        {
            System.out.println("Heart beat");
            watchdog = new Watchdog(HeartBeatMessage.HEART_BEAT_TIME);
            addMessageToSend(heartBeatMessage);
            return true;
        }
        return false;
    }
    
    synchronized public void addMessageToSend(PassControlMessage message)
    {
        messagesToSend.add(message);
    }
        
    synchronized public void addResponseToSend(PassControlConnectionPacket message)
    {
        responsesToSend.add(message);
    }
    
    public synchronized boolean isOutBufferEmpty()
    {
        return messagesToSend.isEmpty() && responsesToSend.isEmpty();
    }
    
    private void sendMessagesOnBuffer()
    {
        synchronized(messagesToSend)
        {
            for (PassControlMessage passControlMessage : messagesToSend) 
            {
                sendBroadcastMessage(passControlMessage);
            }
            messagesToSend.clear();
        }
    }    
    private void sendResponsesOnBuffer()
    {
        synchronized(responsesToSend)
        {
            for (PassControlConnectionPacket passControlPacket : responsesToSend) 
            {
                sendResponseMessage(passControlPacket);
            }
            messagesToSend.clear();
        }
    }
    
    protected void flushBuffer()
    {
        sendMessagesOnBuffer();
        sendResponsesOnBuffer();
    }
    
    protected abstract void sendBroadcastMessage(PassControlMessage message);

    abstract void stop();

    private void sendResponseMessage(PassControlConnectionPacket passControlPacket)
    {
        try {
            sendMessage(passControlPacket.getSocket(), passControlPacket.getMessage());       
        } catch (IOException ex) {
            //Cliente não existe mais.. Será deletado no futuro
            Logger.getLogger(PassControlCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);            
        }
    }

}
