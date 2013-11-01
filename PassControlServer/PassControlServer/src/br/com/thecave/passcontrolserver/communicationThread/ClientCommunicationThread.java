/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.communicationThread;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.util.ConfigurationFile;
import br.com.thecave.passcontrolserver.util.PassControlConfigurationSynchronizer;
import br.com.thecave.passcontrolserver.util.Watchdog;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilherme
 */
public final class ClientCommunicationThread extends PassControlCommunicationThread {

    
    /**
     * Informa o momento em que a conexão foi fechada
     */
    Date connectionInstant = null;
    
    /**
     * Informa o status da conexão
     */
    boolean connectionStatus = false;
    
    /**
     * Lista de escutadores de status da conexão
     */
    ArrayList<StatusConnectionListener> statusConnectionListeners;
    
    /**
     * Endereço de IP do servidor
     */
    String serverIP;
    /**
     * Porta do servidor
     */
    int port;
    /**
     * Canal de comunicação
     */
    Socket socket = null;

    /**
     * Construtor da comunicação do cliente
     *
     * @param serverIP
     * @param port
     */
    public ClientCommunicationThread()
    {
        statusConnectionListeners = new ArrayList<>();
        setHeartBeat(new HeartBeatMessage(MessageActors.NotIdentified, MessageActors.ServerActor));
        refreshConnectionProperties();
    }   
    
    /**
     * Adiciona um escutador de status da conexão
     * @param newListener
     */
    public void addStatusConnectionListeners(StatusConnectionListener newListener)
    {
        statusConnectionListeners.add(newListener);
    }
    
    /**
     * Remove um escutador de status de conexão
     * @param listener
     */
    public void removeStatusConnectionListener(StatusConnectionListener listener)
    {
        statusConnectionListeners.remove(listener);
    }

    /**
     * Envia a mensagem ao servidor
     *
     * @param message
     */
    @Override
    protected void sendBroadcastMessage(PassControlMessage message){
        try
        {
            super.sendMessage(socket, message);
        }
        catch (IOException exc)
        {
            finalizeConnection();
        }
    }

    
    @Override
    public void stop() 
    {
        running = false;
        finalizeConnection();
    }

    /**
     * Envia a mensagem e espera a resposta ou timeout
     * 
     * @param message Mensagem a ser enviada
     * @param typeToListenTo Tipo da resposta esperado
     * @param timeout Tempo de espera (em milissegundos) (se não positivo, espera infinitamente)
     * @return Mensagem esperada ou null
     */
    public <Message extends PassControlMessage> Message sendMessageToServerAndWaitForResponseOrTimeout(PassControlMessage message, Class<Message> clazz, long timeout)
    {
        GenericPassControlMessageListener listener = new GenericPassControlMessageListener();

        addMessageListener(listener, clazz);
        message.setTo(MessageActors.ServerActor);
        Watchdog timeOutWatcher = new Watchdog(timeout);
        addBroadcastToSend(message);
        while (!timeOutWatcher.hasTimedOut()) //Enquando não deu timeout
        {          
            if (listener.hasReceivedMessage())
            {
//                System.out.println("Retorno já foi recebido");
                break;
            }
            else
            {
                try {
//                    System.out.println("Tentativa frustrada de leitura número: " + ++x);                    
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PassControlCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        removeListener(listener, clazz);

        return (Message)listener.getReceivedMessage();
    }
        
    /**
     * Envia a mensagem e espera a resposta ou timeout
     * 
     * @param message Mensagem a ser enviada
     * @param typeToListenTo Tipo da resposta esperado
     * @return Mensagem esperada ou null
     */
    public <Message extends PassControlMessage> Message sendMessageToServerAndWaitForResponse(PassControlMessage message, Class<Message> clazz)
    {
        GenericPassControlMessageListener listener = new GenericPassControlMessageListener();

        addMessageListener(listener, clazz);
        message.setTo(MessageActors.ServerActor);
        addBroadcastToSend(message);
        while (true)//Espera infinitamente 
        {          
            if (listener.hasReceivedMessage())
            {
                break;
            }
            else
            {
                try {
//                    System.out.println("Tentativa frustrada de leitura número: " + ++x);                    
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PassControlCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        removeListener(listener, clazz);

        return (Message)listener.getReceivedMessage();
    }    
    
    @Override
    public void run() {
        running = true;
        System.out.println("Cliente tentando estabelecer conexão");
        
        while (running) 
        {
            try 
            {
                if (socket == null || socket.isClosed()) 
                {
                    //Tenta estabelecer uma conexão
                    socket = null;
                    
//                    SocketAddress sockaddr = new InetSocketAddress(serverIP, port);
                    socket = new Socket(serverIP, port);
                    //Tenta durante 10 segundos...
//                    socket.connect(sockaddr, 10000);                    
                    
                    connectionInstant = new Date();
                    System.out.println("Conexão estabelecida");
                    onChangeStatusConnection();                                                        
                }
                
                InputStream inputStream = socket.getInputStream();
                
                //O cliente está sempre escutando o socket
                while (true) 
                {
                    //Possui mensagem para ser lida
                    if (inputStream.available() > 0) 
                    {
                        PassControlMessage message = handleIncomingMessage(socket);
                        redirectReceivedMessage(message, socket);
                    }
                    //Verifica a necessidade de envio do heart beat
                    checkMessageHeartBeat();
                    //Envia as mensagens que estão no buffer
                    flushBuffer();
                }
            }
            catch (NoRouteToHostException | ConnectException exc)
            {
                refreshConnectionProperties();
            }
            catch (ClassNotFoundException | IOException exc) 
            {
                exc.printStackTrace();
            }
            finally 
            {
                finalizeConnection();                
            }
        }

    }
    
    public void refreshConnectionProperties()
    {
        ConfigurationFile configurationFile = PassControlConfigurationSynchronizer.getInstance().getConfigurationFile();
        port = configurationFile.getPortServer();
        String oldIP = serverIP;
        serverIP = configurationFile.getIpServer();   
        if (oldIP != serverIP)
        {
            System.out.println("IP do server: " + serverIP);
        }
        finalizeConnection();
    }
    
    /**
     * Invocado quando quando a conexão muda de status
     */
    private void onChangeStatusConnection()
    {
        boolean connection = (socket != null && !socket.isClosed());
        if (connection != connectionStatus)
        {
            connectionStatus = connection;            
            for (StatusConnectionListener statusConnectionListener : statusConnectionListeners) 
            {
                statusConnectionListener.onChangeConnection(connection);
            }
        }
    }
    
    /**
     * Encerra a conexão com o servidor
     */
    private void finalizeConnection()
    {
        try 
        {
            //System.out.println("Conexão do cliente encerrada");
            if (socket != null)
                socket.close();
        } catch (IOException ex) 
        {
            Logger.getLogger(ClientCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            connectionInstant = null;
            onChangeStatusConnection();                            
            socket = null;
        }        
    }

    public boolean getConnectionStatus() {
        return connectionStatus;
    }

    public Date getConnectionInstant() {
        return connectionInstant;
    }
    
    
}
