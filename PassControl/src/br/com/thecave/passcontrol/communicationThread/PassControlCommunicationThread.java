/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

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
    protected HashMap<String, ArrayList<PassControlMessageListener>> passControlMessageListeners;
    
    /**
     * 
     */
    private final ArrayList<PassControlMessage> messagesToSend;
            
    public PassControlCommunicationThread(HeartBeatMessage heartBeatMessage) 
    {
        this.heartBeatMessage = heartBeatMessage;
        passControlMessageListeners = new HashMap<>();
        running = false;
        watchdog = new Watchdog(HeartBeatMessage.HEART_BEAT_TIME);    
        messagesToSend = new ArrayList<>();
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
    protected PassControlMessage handleIncomingMessage(Socket socket) throws IOException, ClassNotFoundException {
        InputStream inputStream = socket.getInputStream();
        System.out.println("Mensagem será recebida");
        ObjectInputStream input = new ObjectInputStream(inputStream);
        PassControlMessage message = (PassControlMessage) input.readObject();
        message.setSocket(socket);
        System.out.println("Mensagem recebida com sucesso: " + message.getType());
        return message;
    }
    
    protected void redirectReceivedMessage(PassControlMessage message)
    {
        ArrayList<PassControlMessageListener> listenersArray = passControlMessageListeners.get(message.getType());
        //Não há escutador cadastradoI
        if (listenersArray == null)
            return;
        
        //Filtra só para os escutadores do tipo recebido
        for (PassControlMessageListener listener : listenersArray)
        {
            listener.onMessageReceive(message);
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
    
    public synchronized boolean isOutBufferEmpty()
    {
        return messagesToSend.isEmpty();
    }
    
    protected void sendMessagesOnBuffer()
    {
        synchronized(messagesToSend)
        {
            for (PassControlMessage passControlMessage : messagesToSend) 
            {
                sendMessage(passControlMessage);
            }
            messagesToSend.clear();
        }
    }
    
    protected abstract void sendMessage(PassControlMessage message);

    abstract void stop();

}
