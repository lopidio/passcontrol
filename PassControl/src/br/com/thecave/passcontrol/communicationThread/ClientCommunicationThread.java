/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

import br.com.thecave.passcontrol.messages.ClientInitializationRequest;
import br.com.thecave.passcontrol.messages.ClientInitializationResponse;
import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.messages.PassControlMessage;
import br.com.thecave.passcontrol.util.Watchdog;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilherme
 */
public class ClientCommunicationThread extends PassControlCommunicationThread {

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
     * Tempo periódico para manutenção da conexão
     */
    private static final long HEART_BEAT_TIME = 10*1000; //10 segundos...
    
    /**
     * Quem é o cliente responsável pela minha manutenção
     */
    MessageActors actor;

    /**
     * Construtor da comunicação do cliente
     *
     * @param serverIP
     * @param port
     */
    public ClientCommunicationThread(MessageActors actor, String serverIP, int port){
        super(new HeartBeatMessage(actor, MessageActors.ServerActor));
        this.port = port;
        this.serverIP = serverIP;
        this.actor = actor;
        statusConnectionListeners = new ArrayList<>();
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
    public void sendMessage(PassControlMessage message){
        try
        {
            super.sendMessage(socket, message);
        }
        catch (IOException exc)
        {
            finalizeConnection();
        }
    }

    private MessageActors getActor() {
        return actor;
    }
    
    
    @Override
    public void stop() 
    {
        running = false;
        finalizeConnection();
    }
    //REGIÃO DE TESTES
    public static void main(String[] args) {
            ClientCommunicationThread me = new ClientCommunicationThread(MessageActors.ViewerActor, "127.0.0.1", 23073);
            new Thread(me).start();
        
            
            //Espera a conexão ser fechada...
            try 
            {
                Thread.sleep(1000);
            } catch (InterruptedException ex) 
            {
                Logger.getLogger(ClientCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ClientInitializationRequest message = new ClientInitializationRequest(me.getActor(), "guigui", "123456senha");

            ClientInitializationResponse response;
            response = (ClientInitializationResponse)me.sendMessageAndWaitForResponseOrTimeout
                    (message, ClientInitializationResponse.class.getSimpleName(), 5*1000);
            if (response != null)
                System.out.println("PermissionCode: " + response.getPermissionCode());
            else
                System.out.println("TIME OUT!");
            me.stop();
            System.out.println("Fim execução cliente");
    }
    //FIM DA REGIÃO DE TESTES
    @Override
    public void run() {
        running = true;
        while (running) 
        {
            try 
            {
                if (socket == null || socket.isClosed()) 
                {
                    //Tenta estabelecer uma conexão
                    System.out.println("Cliente tentando estabelecer conexão");
                    socket = new Socket(serverIP, port);      
                    onChangeStatusConnection();                    
                    System.out.println("Conexão estabelecida");
                }
                InputStream inputStream = socket.getInputStream();
                
                //O cliente está sempre escutando o socket
                while (true) 
                {
                    //Possui mensagem para ser lida
                    if (inputStream.available() > 0) 
                    {
                        PassControlMessage message = handleIncomingMessage(inputStream);
                        redirectReceivedMessage(message);
                    }
                    checkMessageProtocol();
                }
            }
            catch (ClassNotFoundException | IOException exc) 
            {
                onChangeStatusConnection();
                System.err.println(exc.getMessage());
            }
            finally 
            {
                finalizeConnection();
            }
        }

    }
    
    private void onChangeStatusConnection()
    {
        boolean connectionOff = (socket != null && !socket.isClosed());
        for (StatusConnectionListener statusConnectionListener : statusConnectionListeners) 
        {
            statusConnectionListener.onChangeConnection(connectionOff);
        }
    }
    
    private void finalizeConnection()
    {
        try 
        {
            System.out.println("Conexão do cliente encerrada");
            if (socket != null)
                socket.close();
        } catch (IOException ex) 
        {
            Logger.getLogger(ClientCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            socket = null;
        }        
    }
}
