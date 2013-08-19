/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

import br.com.thecave.passcontrol.messages.ClientInitializationRequest;
import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.messages.PassControlMessage;
import br.com.thecave.passcontrol.messages.PassControlMessageListener;
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
     * Construtor da comunicação do cliente
     *
     * @param serverIP
     * @param port
     */
    public ClientCommunicationThread(String serverIP, int port) throws UnknownHostException, IOException {
        this.port = port;
        this.serverIP = serverIP;
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
    public void sendMessage(PassControlMessage message) {
        super.sendMessage(socket, message);
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

    public static void main(String[] args) {
        try {
            ClientCommunicationThread me = new ClientCommunicationThread("127.0.0.1", 23073);

            new Thread(me).start();
            ClientInitializationRequest message = new ClientInitializationRequest(MessageActors.ViewerActor, "guigui", "123456senha");

            me.sendMessage(message);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    void handleMessage(PassControlMessage message) 
    {
        //Class<?> act = Class.forName("com.bla.TestActivity");
        //Filtra só para os escutadores do tipo recebido
        for (PassControlMessageListener listener : passControlMessageListeners.get(message.getType())) 
        {
            listener.onMessageReceive(message);
        }

    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                if (socket == null) {
                    //Tenta estabelecer uma conexão
                    System.out.println("Cliente tentando estabelecer conexão");
                    socket = new Socket(serverIP, port);
                    System.out.println("Conexão estabelecida");
                }
                InputStream inputStream = socket.getInputStream();

                //O cliente está sempre escutando o socket
                while (true) {
                    //Possui mensagem para ser lida
                    if (inputStream.available() > 0) {
                        handleIncomingMessage(inputStream);
                    }
                }

            } catch (UnknownHostException unknownExc) {
                System.err.println(unknownExc.getMessage());
            } catch (IOException ioExc) {
                System.err.println(ioExc.getMessage());


            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PassControlCommunicationThread.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                socket = null;
            }
        }

    }
}
