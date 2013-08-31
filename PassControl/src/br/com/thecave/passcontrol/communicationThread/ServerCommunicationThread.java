/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.communicationThread;

import br.com.thecave.passcontrol.db.bean.UserBean;
import br.com.thecave.passcontrol.db.dao.UserDAO;
import br.com.thecave.passcontrol.messages.ClientInitializationRequest;
import br.com.thecave.passcontrol.messages.ClientInitializationResponse;
import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.messages.PassControlMessage;
import br.com.thecave.passcontrol.messages.PassControlMessageListener;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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

    /**
     * Flag que indica que a iteração dos loops deve ser reiniciada
     */
    boolean markToReset;
    
    ServerSocketListener serverSocketListener;
    /**
     * Mapa de clientes identificados pelo ator
     */
    ConcurrentHashMap<MessageActors, ArrayList<Socket>> clients;
    
    public ServerCommunicationThread(int port) throws IOException
    {
        super(new HeartBeatMessage(MessageActors.ServerActor, MessageActors.AllActors));
        markToReset = false;
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
                public void onMessageReceive(PassControlMessage message, Socket socket) {
                        ClientInitializationRequest initRequest = (ClientInitializationRequest)message;
                        UserBean bean = UserDAO.selectFromName(initRequest.getUser());
                        ClientInitializationResponse response = 
                                new ClientInitializationResponse(0, false , message.getFrom());

                        if (bean != null)
                        {
                            if (bean.getPassword().equals(initRequest.getPassword()))
                            {
                                response = new ClientInitializationResponse(bean.getType(), false , message.getFrom());
                            }
                        }
                                               
                        server.addResponseToSend(socket, response);

                }
            };

            server.addMessageListener(initializationListener, ClientInitializationRequest.class.getSimpleName());

            new Thread(server).start();

        } catch (IOException ex) {
            Logger.getLogger(ServerCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int getNumClients()
    {
        int retorno = 0;
        for (Map.Entry<MessageActors, ArrayList<Socket>> entry : clients.entrySet()) 
        {
            ArrayList<Socket> arrayList = entry.getValue();
            for (Socket socket : arrayList) 
            {
                ++retorno;
            }

        }
        return retorno;
    }
    

    //FIM DA REGIÃO DE TESTES
    @Override
    public void run() 
    {
        running = true;
       
        while (running) 
        {
            if (checkMessageProtocol())
            {
                System.out.println("Número de clientes conectados: " + getNumClients());
            }
                
            
            //Itera por todos os atores de conexão
            for (Map.Entry<MessageActors, ArrayList<Socket>> entry : clients.entrySet()) 
            {
                markToReset = false;
                
                MessageActors currentActor = entry.getKey();
                ArrayList<Socket> arrayList = entry.getValue();
                //itera por todos os atores
                try
                {
                    for (Socket client : arrayList) 
                    {
                        try 
                        {   
                            if (client == null)
                            {
                                markToReset = true;
                            }
                            if (checkInputStream(currentActor, client))
                                markToReset = true;
                        } 
                        catch (IOException | ClassNotFoundException | NullPointerException ex) 
                        {
                            markToReset = true;
                            System.out.println("Conexão perdida. Cliente será removido da lista de clientes");
                            removeClient(currentActor, client);
                            Logger.getLogger(ServerCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (markToReset)
                        {
                            break;
                        }
                    }
                }
                catch (ConcurrentModificationException exc)
                {
                    markToReset = true;
                }
                if (markToReset)
                {
                    break;
                }
            }            
            //Verifica se existe alguma mensagem para enviar. E envia.
            flushBuffer();
        }
        
    }

    
    @Override
    protected void sendBroadcastMessage(PassControlMessage message)
    {
        HashMap<MessageActors, ArrayList<Socket>> clientsMarkedToBeRemoved = new HashMap<>();
        if (message.getTo() != MessageActors.AllActors) 
        {
            System.out.println("Mensagem enviada para todos os clientes do tipo " + message.getTo().name());            
            
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
                        addClientToMap(clientsMarkedToBeRemoved, actorToReceive, client);
                    }
                }
            }
        }        
        //Se a mensagem for para todos, TODOS MESMO!!...
        else // if (message.getTo() == MessageActors.AllActors) 
        {
            System.out.println("Mensagem enviada para todos os clientes do tipo " + message.getTo().name());            
            
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
                        addClientToMap(clientsMarkedToBeRemoved, entry.getKey(), client);
                    }
                }
            }
        }

        
        //Para todos os clientes a serem removidos
        for (Map.Entry<MessageActors, ArrayList<Socket>> entry : clientsMarkedToBeRemoved.entrySet()) 
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
        markToReset = true;
        addClientToMap(clients, messageActors, newClient);
    }

    public void removeClient(MessageActors messageActors, Socket newClient) 
    {
        markToReset = true;
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
     * @param client Valor a ser removido da lista do HashMap
     */
    private static void removeClientFromMap(AbstractMap<MessageActors, ArrayList<Socket>> map, MessageActors actorKey, Socket client)
    {
        //Recupera a lista anterior
        ArrayList<Socket> listaDeClientesDoMesmoTipo;
        listaDeClientesDoMesmoTipo = map.get(actorKey);
        if (listaDeClientesDoMesmoTipo == null) {
            listaDeClientesDoMesmoTipo = new ArrayList<>();
        }

        //Adiciona mais um cliente
        listaDeClientesDoMesmoTipo.remove(client);

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
        if (client == null)
            return false;
            
        InputStream inputStream = client.getInputStream();
        if (inputStream == null)
            return false;
        //Se possui mensagem para ser lida
        if (inputStream.available() > 0) 
        {

            //Recebe a mensagem
            PassControlMessage message = handleIncomingMessage(client);
            MessageActors newActor = message.getFrom();
            //Caso o ator tenha mudado de papel
            if (newActor != currentActor) {
                repositionClient(currentActor, newActor, client);
            }

            //Distribui a mensagem para os listener que querem essa mensagem
            redirectReceivedMessage(message, client);
            return true;

        }
        return false;
    }
}
