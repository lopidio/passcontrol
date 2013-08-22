/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

import br.com.thecave.passcontrol.messages.ClientInitializationRequest;
import br.com.thecave.passcontrol.messages.ClientInitializationResponse;
import br.com.thecave.passcontrol.messages.GenericPassControlMessageListener;
import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.messages.PassControlMessage;
import br.com.thecave.passcontrol.messages.PassControlMessageListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilherme
 */
public class ServerCommunicationThread extends PassControlCommunicationThread {

    ServerSocketListener serverSocketListener;
    /**
     * Mapa de clientes identificados pelo ator
     */
    ConcurrentHashMap<MessageActors, ArrayList<Socket>> clients;

    public ServerCommunicationThread(int port) throws IOException {
        serverSocketListener = new ServerSocketListener(port, this);
        //Executa a thread que escuta a porta
        new Thread(serverSocketListener).start();
        clients = new ConcurrentHashMap<>();
    }


    @Override
    public void stop() {
        running = false;
        try {

            //Percorre todos os socket e os finaliza
            for (Map.Entry<MessageActors, ArrayList<Socket>> entry : clients.entrySet()) {
                ArrayList<Socket> arrayList = entry.getValue();
                for (Socket client : arrayList) {
                    client.close();
                }
            }

            serverSocketListener.stop();

        } catch (IOException ex) {
            Logger.getLogger(ClientCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //REGIÃO DE TESTES
    static ServerCommunicationThread server;

    public static void main(String[] args) {
        try {
            server = new ServerCommunicationThread(23073);

            PassControlMessageListener initializationListener;
            initializationListener = new PassControlMessageListener()
            {

                @Override
                public void onMessageReceive(PassControlMessage message) {
                    ClientInitializationResponse response = new ClientInitializationResponse(message.getFrom(), 15, null);
                    server.sendMessage(response);
                    
                }
                
            };

            server.addMessageListener(initializationListener, ClientInitializationRequest.class.getSimpleName());

            new Thread(server).start();
                        
        } catch (IOException ex) {
            Logger.getLogger(ServerCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //FIM DA REGIÃO DE TESTES
    
    @Override
    public void run() {
        running = true;

        while (running) {
            //Itera por todos os atores de conexão
            for (Map.Entry<MessageActors, ArrayList<Socket>> entry : clients.entrySet()) {
                MessageActors currentActor = entry.getKey();
                ArrayList<Socket> arrayList = entry.getValue();
                //itera por todos os atores
                for (Socket client : arrayList) {
                    InputStream inputStream = null;
                    try {
                        inputStream = client.getInputStream();
                        //Se possui mensagem para ser lida
                        if (inputStream.available() > 0) {
                            //Recebe a mensagem
                            PassControlMessage message = handleIncomingMessage(inputStream);
                            MessageActors newActor = message.getFrom();
                            //Só é necessário caso o ator ainda não tenha sido identificado
                            if (newActor != currentActor) 
                            {
                                removeClient(currentActor, client);
                                addClient(newActor, client);
                            }
                            
                            //Distribui a mensagem para os listener que querem essa mensagem
                            redirectMessage(message);
                        }
                    } catch (IOException ex) {
                        removeClient(currentActor, client);
                        Logger.getLogger(ServerCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServerCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
                    } 

                }
            }
        }
    }

    @Override
    void sendMessage(PassControlMessage message) {

        //Se a mensagem for para todos...
        if (message.getTo() == MessageActors.AllActors) {
            for (Map.Entry<MessageActors, ArrayList<Socket>> entry : clients.entrySet()) {
                ArrayList<Socket> arrayList = entry.getValue();
                for (Socket client : arrayList) {
                    //Manda para todos
                    sendMessage(client, message);

                }
            }
        } else {
            //Filtra só para os clientes desejados
            ArrayList<Socket> selectedClients = clients.get(message.getTo());
            if (selectedClients != null)
            {
                for (Socket client : selectedClients) {
                    sendMessage(client, message);
                }                
            }
        }
    }

    public void addClient(MessageActors messageActors, Socket newClient) {
        //Recupera a lista anterior
        ArrayList<Socket> listaDeClientesDoMesmoTipo;
        listaDeClientesDoMesmoTipo = clients.get(newClient);
        if (listaDeClientesDoMesmoTipo == null) {
            listaDeClientesDoMesmoTipo = new ArrayList<>();
        }

        //Adiciona mais um cliente
        listaDeClientesDoMesmoTipo.add(newClient);

        //insere a lista novamente
        clients.put(messageActors, listaDeClientesDoMesmoTipo);
    }

    public void removeClient(MessageActors messageActors, Socket newClient) {
        //Recupera a lista anterior
        ArrayList<Socket> listaDeClientesDoMesmoTipo = clients.get(newClient);
        if (listaDeClientesDoMesmoTipo == null) {
            listaDeClientesDoMesmoTipo = new ArrayList<>();
        }        

        //Remove um cliente
        listaDeClientesDoMesmoTipo.remove(newClient);

        //insere a lista novamente
        clients.put(messageActors, listaDeClientesDoMesmoTipo);
    }
}
