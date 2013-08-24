/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

import br.com.thecave.passcontrol.messages.ClientInitializationRequest;
import br.com.thecave.passcontrol.messages.ClientInitializationResponse;
import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.messages.PassControlMessage;
import br.com.thecave.passcontrol.messages.PassControlMessageListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
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

    public ServerCommunicationThread(int port) throws IOException
    {
        super(new HeartBeatMessage(MessageActors.ServerActor, MessageActors.AllActors));
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
            initializationListener = new PassControlMessageListener() {
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
    public void run() 
    {
        running = true;
        int clientCount = -1;
        
        while (running) 
        {
            if (checkMessageProtocol())
                clientCount = 0;
            
            //Itera por todos os atores de conexão
            CONECTIONS_CLIENT:
            for (Map.Entry<MessageActors, ArrayList<Socket>> entry : clients.entrySet()) 
            {
                MessageActors currentActor = entry.getKey();
                ArrayList<Socket> arrayList = entry.getValue();
                //itera por todos os atores
                for (Socket client : arrayList) 
                {
                    if (clientCount >= 0)
                        ++clientCount;
                    try 
                    {         
                        if (checkInputStream(currentActor, client))
                            break CONECTIONS_CLIENT;
                    } catch (IOException | ClassNotFoundException | NullPointerException ex) 
                    {
                        System.out.println("Conexão perdida. Cliente será removido da lista de clientes");
                        removeClient(currentActor, client);
                        Logger.getLogger(ServerCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
                        break CONECTIONS_CLIENT;
                    }

                }
            }            
            if (clientCount >= 0)
            {
                System.out.println("Número de clientes conectados ao servidor: " + clientCount);
                clientCount = -1;
            }

        }
        
    }

    @Override
    void sendMessage(PassControlMessage message)
    {
        HashMap<MessageActors, ArrayList<Socket>> markedToBeRemoved = new HashMap<>();
        //Se a mensagem for para todos...
        if (message.getTo() == MessageActors.AllActors) 
        {
            //Percorro todas as listas
            for (Map.Entry<MessageActors, ArrayList<Socket>> entry : clients.entrySet()) 
            {
                //Percorro todos os elementos de todas as listas
                for (Socket client : entry.getValue()) 
                {
                    try
                    {
                        //Manda para todos
                        sendMessage(client, message);
                    }
                    catch (IOException exc)
                    {
                        //Adiciona cliente na lista de futura remoção
                        addClientToMap(markedToBeRemoved, entry.getKey(), client);
                    }
                }
            }
        }
        else
        {
            //Filtra só para os clientes desejados
            MessageActors actorToReceive = message.getTo();
            ArrayList<Socket> selectedClients = clients.get(actorToReceive);
            if (selectedClients != null) 
            {
                for (Socket client : selectedClients) 
                {
                    try
                    {
                        //Manda para todos
                        sendMessage(client, message);
                    }
                    catch (IOException exc)
                    {
                        //Adiciona cliente na lista de futura remoção
                        addClientToMap(markedToBeRemoved, actorToReceive, client);
                    }
                }
            }
        }
        
        //Para todos os clientes a serem removidos
        for (Map.Entry<MessageActors, ArrayList<Socket>> entry : markedToBeRemoved.entrySet()) 
        {
            MessageActors messageActors = entry.getKey();
            ArrayList<Socket> currentClient = clients.get(messageActors);
            for (Socket socket : entry.getValue()) 
            {
                currentClient.remove(socket);
            }
            clients.put(messageActors, currentClient);
        }
        
    }

    public void addClient(MessageActors messageActors, Socket newClient) 
    {
        addClientToMap(clients, messageActors, newClient);
    }

    public void removeClient(MessageActors messageActors, Socket newClient) 
    {
        removeClientFromMap(clients, messageActors, newClient);
    }
    
    /**
     * Insere um elemento no HashMap de um ArrayList
     * @param map HashMap a se inserir
     * @param actorKey Chave do HashMap
     * @param clientValue Valor a ser inserido na lista do HashMap
     */
    private static void addClientToMap(AbstractMap<MessageActors, ArrayList<Socket>> map, MessageActors actorKey, Socket clientValue)
    {
        //Recupera a lista anterior
        ArrayList<Socket> listaDeClientesDoMesmoTipo;
        listaDeClientesDoMesmoTipo = map.get(actorKey);
        if (listaDeClientesDoMesmoTipo == null) {
            listaDeClientesDoMesmoTipo = new ArrayList<>();
        }

        //Adiciona mais um cliente
        listaDeClientesDoMesmoTipo.add(clientValue);

        //insere a lista novamente
        map.put(actorKey, listaDeClientesDoMesmoTipo);
        
    }

    /**
     * Remove um elemento no HashMap de um ArrayList
     * @param map HashMap a se remover
     * @param actorKey Chave do HashMap
     * @param clientValue Valor a ser removido da lista do HashMap
     */
    private static void removeClientFromMap(AbstractMap<MessageActors, ArrayList<Socket>> map, MessageActors actorKey, Socket clientValue)
    {
        //Recupera a lista anterior
        ArrayList<Socket> listaDeClientesDoMesmoTipo;
        listaDeClientesDoMesmoTipo = map.get(actorKey);
        if (listaDeClientesDoMesmoTipo == null) {
            listaDeClientesDoMesmoTipo = new ArrayList<>();
        }

        //Adiciona mais um cliente
        listaDeClientesDoMesmoTipo.remove(clientValue);

        //insere a lista novamente
        map.put(actorKey, listaDeClientesDoMesmoTipo);
        
    }
    
    
    private void repositionClient(MessageActors currentActor, MessageActors newActor, Socket client) {
        //Remove da lista anterior e adiciona na nova lista
        removeClientFromMap(clients, currentActor, client);
        addClientToMap(clients, newActor, client);
    }

    //Retorna true caso tenha lido alguma coisa
    private boolean checkInputStream(MessageActors currentActor, Socket client) throws IOException, ClassNotFoundException, NullPointerException
    {
        InputStream inputStream = client.getInputStream();
        //Se possui mensagem para ser lida
        if (inputStream.available() > 0) 
        {

            //Recebe a mensagem
            PassControlMessage message = handleIncomingMessage(inputStream);
            MessageActors newActor = message.getFrom();
            //Só é necessário caso o ator ainda não tenha sido identificado
            if (newActor != currentActor) {
                repositionClient(currentActor, newActor, client);
            }

            //Distribui a mensagem para os listener que querem essa mensagem
            redirectReceivedMessage(message);
            return true;

        }
        return false;
    }
}
