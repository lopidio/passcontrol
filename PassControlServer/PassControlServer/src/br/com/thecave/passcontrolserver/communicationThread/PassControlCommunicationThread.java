/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.communicationThread;

import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import br.com.thecave.passcontrolserver.util.Watchdog;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
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
     * Escutadores de mensagens (Tentei armazenar classes ao invés de string. Não consegui)
     */
    protected ConcurrentHashMap<String, ArrayList<PassControlMessageListener>> passControlMessageListeners;
    
    /**
     * 
     */
    private final ArrayBlockingQueue<PassControlMessage> messagesToSend;

    /**
     * 
     */
    private final ConcurrentHashMap<Socket, ArrayList<PassControlMessage>> responsesToSend;
    
    /**
     * 
     * @param heartBeatMessage 
     */            
    public PassControlCommunicationThread() 
    {
        this.heartBeatMessage = new HeartBeatMessage(MessageActors.NotIdentified, MessageActors.NotIdentified);
        passControlMessageListeners = new ConcurrentHashMap<>();
        running = false;
        watchdog = new Watchdog(HeartBeatMessage.HEART_BEAT_TIME);    
        messagesToSend = new ArrayBlockingQueue<>(50);
        responsesToSend = new ConcurrentHashMap<>();
    }
    
    synchronized public <Message extends PassControlMessage> void removeListener(PassControlMessageListener listener, Class<Message> clazz) {
        String typeToListenTo = clazz.getSimpleName();
        ArrayList<PassControlMessageListener> list = passControlMessageListeners.get(typeToListenTo);
        if (list == null)
        {
            return;
        }
        list.remove(listener);
        
        passControlMessageListeners.put(typeToListenTo, list);
    }
    
    
    synchronized public <Message extends PassControlMessage> void addMessageListener(PassControlMessageListener listener, Class<Message> clazz)
    {
        String typeToListenTo = clazz.getSimpleName();        
        ArrayList<PassControlMessageListener> list = passControlMessageListeners.get(typeToListenTo);
        if (list == null)
        {
            list = new ArrayList<>();
        }
        list.add(listener);
        
        passControlMessageListeners.put(typeToListenTo, list);
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
        
        refreshHeartbeat(message.getFrom());
        if (!message.getType().equals("HeartBeatMessage"))
            System.out.println("Mensagem será enviada " + message.getType());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(message);
//        System.out.println("Mensagem enviada com sucesso");
        return true;
    }
    
    /**
     * Atualiza as propriedades do heart beat
     */
    private void refreshHeartbeat(MessageActors actorSender)
    {
        if (actorSender != heartBeatMessage.getFrom())
        {
            //Se o remetente for o servidor
            if (actorSender != MessageActors.ServerActor)
            {
                setHeartBeat(new HeartBeatMessage(actorSender, MessageActors.AllActors));                                
            }
            else
            {
                setHeartBeat(new HeartBeatMessage(actorSender, MessageActors.ServerActor));                
            }
        }        
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
//        System.out.println("Mensagem será recebida");
        ObjectInputStream input = new ObjectInputStream(inputStream);
        PassControlMessage message = (PassControlMessage) input.readObject();
        if (!message.getType().equals("HeartBeatMessage"))
            System.out.println("Mensagem recebida com sucesso " + message.getType());
        return message;
    }
    
    public void redirectReceivedMessage(PassControlMessage message, Socket socket)
    {
        if (message.getType().equals("HeartBeatMessage"))
            return;
        
        ArrayList<PassControlMessageListener> listenersArray = passControlMessageListeners.get(message.getType());
        //Não há escutador cadastradoI
        if (listenersArray == null || listenersArray.isEmpty())
        {
            System.out.println("Nenhum escutador cadastrado para esse tipo de mensagem: " + message.getType());
            return;
        }
       
        //Filtra só para os escutadores do tipo recebido
        for (PassControlMessageListener listener : listenersArray)
        {
            listener.onMessageReceive(message, socket);
        }        
    }
    
    /**
     * Periodicamente envia mensagens ao outro lado da conexão para verificar se ainda está ativo
     * @return true caso tenha enviado a mensagem
     */
    protected boolean checkMessageHeartBeat()
    {
        //Periodicamente, o cliente tenta enviar uma mensagem ao servidor, só para indicar que tá vivo
        //E ajuda o servidor a identificar os clientes que estão mortos e tal
        if (watchdog.hasTimedOut())
        {
            System.out.println("HeartBeat");
            watchdog = new Watchdog(HeartBeatMessage.HEART_BEAT_TIME);
            addBroadcastToSend(heartBeatMessage);
            return true;
        }
        return false;
    }
    
    /**
     * Adiciona a mensagem para TODOS os destinatários da mensagem. (Pesquisados por message.getTo()).
     * @param message 
     */    
    synchronized public void addBroadcastToSend(PassControlMessage message)
    {
        messagesToSend.add(message);
    }
        
    /**
     * Adiciona a mensagem para o único destinatário da mensagem. (Pesquisados por socket).
     * @param message 
     */    
    synchronized public void addResponseToSend(Socket socket, PassControlMessage message)
    {
        ArrayList<PassControlMessage> list = responsesToSend.get(socket);
        if (list == null)
        {
            list = new ArrayList<>();
        }
        list.add(message);
        
        responsesToSend.put(socket, list);       
    }
    
    /**
     * Retorna se existe alguma mensagem cadastrada e não enviada
     * @return true, caso haja
     */
    public synchronized boolean isOutBufferEmpty()
    {
        return messagesToSend.isEmpty() && responsesToSend.isEmpty();
    }
    
    private void sendMessagesBroadcastOnBuffer()
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
            for (Map.Entry<Socket, ArrayList<PassControlMessage>> entry : responsesToSend.entrySet()) 
            {
                Socket socket = entry.getKey();
                ArrayList<PassControlMessage> responsesList = entry.getValue();
                for (PassControlMessage response : responsesList) 
                {
                    try {
                        sendMessage(socket, response);
                    } catch (IOException ex) {
                        Logger.getLogger(PassControlCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                
    
            }
            responsesToSend.clear();
        }
    }
    
    protected void setHeartBeat(HeartBeatMessage heartBeatMessage) {
        this.heartBeatMessage = heartBeatMessage;
    }   
    
    /*
     * Envia todas as mensagens de todos os tipos
     */
    protected void flushBuffer()
    {
        sendResponsesOnBuffer();        
        sendMessagesBroadcastOnBuffer();
    }
    
    /**
     * Envia a mensagem para TODOS os destinatários da mensagem. (Pesquisados por message.getTo()).
     * @param message 
     */    
    protected abstract void sendBroadcastMessage(PassControlMessage message);

    abstract void stop();

}

