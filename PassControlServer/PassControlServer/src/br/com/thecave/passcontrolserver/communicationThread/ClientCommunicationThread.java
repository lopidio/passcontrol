/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.communicationThread;

import br.com.thecave.passcontrolserver.messages.generic.ClientLoginRequest;
import br.com.thecave.passcontrolserver.messages.generic.ClientLoginResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
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
     * Construtor da comunicação do cliente
     *
     * @param serverIP
     * @param port
     */
    public ClientCommunicationThread(String serverIP, int port){
        this.port = port;
        this.serverIP = serverIP;
        statusConnectionListeners = new ArrayList<>();
        setHeartBeat(new HeartBeatMessage(MessageActors.NotIdentified, MessageActors.ServerActor));
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
    //REGIÃO DE TESTES
    public static void main(String[] args) {
            ClientCommunicationThread me = new ClientCommunicationThread("127.0.0.1", 23073);
            new Thread(me).start();
        
            ClientLoginRequest initRequest = new ClientLoginRequest(MessageActors.AdministratorActor, "guigui", "123456senha");

            ClientLoginResponse initResponse = (ClientLoginResponse)me.sendMessageAndWaitForResponseOrTimeout
                    (initRequest, ClientLoginResponse.class.getSimpleName(), 30*1000);

            if (initResponse != null)
            {
                if (initResponse.getUser() != null)
                {
                    System.out.println("PermissionCode: " + initResponse.getUser().getType());
                    System.out.println("Status login: true");
                }
                else
                {
                    System.out.println("Usuário e senha incompatíveis");
                }
            }
            else
                System.out.println("TIME OUT!");
            me.stop();
            System.out.println("Fim execução cliente");
    }
    //FIM DA REGIÃO DE TESTES
    
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
                        PassControlMessage message = handleIncomingMessage(socket);
                        redirectReceivedMessage(message, socket);
                    }
                    //Verifica a necessidade de envio do heart beat
                    checkMessageHeartBeat();
                    //Envia as mensagens que estão no buffer
                    flushBuffer();
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
    
    /**
     * Invocado quando quando a conexão muda de status
     */
    private void onChangeStatusConnection()
    {
        boolean connectionOff = (socket != null && !socket.isClosed());
        for (StatusConnectionListener statusConnectionListener : statusConnectionListeners) 
        {
            statusConnectionListener.onChangeConnection(connectionOff);
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
            socket = null;
        }        
    }
}
