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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilherme
 */
public class ClientCommunicationThread extends PassControlCommunicationThread {

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
    Socket socket;
    
    /**
     * Tempo periódico para manutenção da conexão
     */
    private static final long HEART_BEAT_TIME = 10*1000;
    
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
    public ClientCommunicationThread(MessageActors actor, String serverIP, int port) throws UnknownHostException, IOException {
        this.port = port;
        this.serverIP = serverIP;
        this.actor = actor;
        System.out.println("Cliente tentando estabelecer conexão");
        socket = new Socket(serverIP, port);
        System.out.println("Conexão estabelecida");
    }

    /**
     * Envia a mensagem ao servidor
     *
     * @param message
     */
    @Override
    public void sendMessage(PassControlMessage message) throws IOException {
        super.sendMessage(socket, message);
    }

    private MessageActors getActor() {
        return actor;
    }
    
    
    @Override
    public void stop() {
        running = false;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //REGIÃO DE TESTES
    public static void main(String[] args) {
        try {
            ClientCommunicationThread me = new ClientCommunicationThread(MessageActors.ViewerActor, "127.0.0.1", 23073);

            new Thread(me).start();
            ClientInitializationRequest message = new ClientInitializationRequest(me.getActor(), "guigui", "123456senha");

            ClientInitializationResponse response;
            response = (ClientInitializationResponse)me.sendMessageAndWaitForResponseOrTimeout
                    (message, ClientInitializationResponse.class.getSimpleName(), 30*1000);
            if (response != null)
                System.out.println(response.getPermissionCode());
            else
                System.out.println("TIME OUT!");
            me.stop();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                    System.out.println("Conexão estabelecida");
                }
                InputStream inputStream = socket.getInputStream();

                Watchdog watchdog = new Watchdog(HEART_BEAT_TIME);
                
                //O cliente está sempre escutando o socket
                while (true) 
                {
                    //Possui mensagem para ser lida
                    if (inputStream.available() > 0) 
                    {
                        PassControlMessage message = handleIncomingMessage(inputStream);
                        redirectMessage(message);
                    }
                    //Periodicamente, o cliente tenta enviar uma mensagem ao servidor, só para indicar que tá vivo
                    //E ajuda o servidor a identificar os clientes que estão mortos e tal
                    if (watchdog.hasTimedOut())
                    {
                        watchdog = new Watchdog(HEART_BEAT_TIME);
                        sendMessage(new PassControlMessage(actor, MessageActors.ServerActor));
                    }
                }

            }
            catch (UnknownHostException unknownExc) 
            {
                System.err.println(unknownExc.getMessage());
            }
            catch (IOException ioExc) 
            {
                System.err.println(ioExc.getMessage());
            }
            catch (ClassNotFoundException ex) 
            {
                Logger.getLogger(PassControlCommunicationThread.class
                        .getName()).log(Level.SEVERE, null, ex);
            } 
            finally 
            {
                try 
                {
                    System.out.println("Conexão do cliente encerrada");
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

    }
}
