/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.messages.PassControlMessage;
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
    void handleMessage(PassControlMessage message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stop()
    {
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
    
    
    @Override
    public void run() {
        running = true;

        while (running) {
            for (Map.Entry<MessageActors, ArrayList<Socket>> entry : clients.entrySet()) {
                MessageActors currentActor = entry.getKey();
                ArrayList<Socket> arrayList = entry.getValue();
                for (Socket client : arrayList) {
                    InputStream inputStream = null;
                    try {
                        inputStream = client.getInputStream();
                        //Possui mensagem para ser lida
                        if (inputStream.available() > 0) {
                            handleIncomingMessage(inputStream);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ServerCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServerCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            inputStream.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ServerCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
            for (Socket client : clients.get(message.getTo())) {
                sendMessage(client, message);
            }
        }
    }

    public void addClient(MessageActors messageActors, Socket newClient) {
        //Recupera a lista anterior
        ArrayList<Socket> listaDeClientesDoMesmoTipo = clients.get(newClient);

        //Adiciona mais um cliente
        listaDeClientesDoMesmoTipo.add(newClient);

        //insere a lista novamente
        clients.put(messageActors, listaDeClientesDoMesmoTipo);
    }

    public void removeClient(MessageActors messageActors, Socket newClient) {
        //Recupera a lista anterior
        ArrayList<Socket> listaDeClientesDoMesmoTipo = clients.get(newClient);

        //Remove um cliente
        listaDeClientesDoMesmoTipo.remove(newClient);

        //insere a lista novamente
        clients.put(messageActors, listaDeClientesDoMesmoTipo);
    }
}
