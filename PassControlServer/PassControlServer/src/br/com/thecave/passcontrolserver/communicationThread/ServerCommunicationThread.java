/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.communicationThread;

import br.com.thecave.passcontrolserver.db.bean.UserBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.util.ConfigurationFile;
import br.com.thecave.passcontrolserver.util.PassControlConfigurationSynchronizer;
import br.com.thecave.passcontrolserver.util.Watchdog;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Enumeration;
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
    ConcurrentHashMap<MessageActors, ArrayList<ClientUserSocketPair>> clientsList;
    
    /**
     * Construtor
     * @param port porta do socket TCP
     * @throws IOException 
     */
    public ServerCommunicationThread(int port) throws IOException
    {
        markToReset = false;
        ConfigurationFile configurationFile = PassControlConfigurationSynchronizer.getInstance().getConfigurationFile();
        configurationFile.setPortServer(port); 
        configurationFile.setIpServer(getLocalIp());
        System.out.println("IP do servidor: " + getLocalIp());

        serverSocketListener = new ServerSocketListener(port, this);
        setHeartBeat(new HeartBeatMessage(MessageActors.ServerActor, MessageActors.AllActors));
        //Executa a thread que escuta a porta
        new Thread(serverSocketListener).start();
        clientsList = new ConcurrentHashMap<>();
    }
    
    private String getLocalIp()
    {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()){
                NetworkInterface current = interfaces.nextElement();
                if (!current.isUp() || current.isLoopback() || current.isVirtual()) 
                    continue;
                Enumeration<InetAddress> addresses = current.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress current_addr = addresses.nextElement();
                    if (current_addr.isLoopbackAddress()) 
                        continue;
                    if (current_addr instanceof Inet4Address)
                        return current_addr.getHostAddress();
                }
            
            }
        } catch (SocketException ex) {
            Logger.getLogger(ServerCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";                
    }
    /**
     * Interrompe a thread
     */
    @Override
    public void stop() {
        running = false;
        //Percorre todos os socket e os finaliza
        for (Map.Entry<MessageActors, ArrayList<ClientUserSocketPair>> entry : clientsList.entrySet()) {
            ArrayList<ClientUserSocketPair> arrayList = entry.getValue();
            for (ClientUserSocketPair client : arrayList) {
                client.closeConnection();
            }
        }

        serverSocketListener.stop();

    }
    
    /**
     * Retorna o número de clientes conectados no instante atual
     */
    public int getNumClients()
    {
        //Somatório
        int retorno = 0;
        //Percorre todo o mapa
        for (Map.Entry<MessageActors, ArrayList<ClientUserSocketPair>> entry : clientsList.entrySet()) 
        {
            ArrayList<ClientUserSocketPair> arrayList = entry.getValue();
            retorno += arrayList.size();
        }
        return retorno;
    }
    
    private void displayClientsList()
    {
        System.out.println("Número de clientes conectados: " + getNumClients());
        for (Map.Entry<MessageActors, ArrayList<ClientUserSocketPair>> entry : clientsList.entrySet()) 
        {
            ArrayList<ClientUserSocketPair> arrayList = entry.getValue();
            if (arrayList.size() > 0)
            {
                System.out.println(entry.getKey().name() + " -> " + arrayList.size());
            }
        }        
        
    }

    @Override
    public void run() 
    {
        running = true;
       
        while (running) 
        {
            //Verifica a necessidade de mandar um heart beat
            if (checkMessageHeartBeat())
            {
                displayClientsList();
            }
                
            //Itera por todos os atores de conexão
            for (Map.Entry<MessageActors, ArrayList<ClientUserSocketPair>> entry : clientsList.entrySet()) 
            {
                markToReset = false;
                
                MessageActors currentActor = entry.getKey();
                ArrayList<ClientUserSocketPair> arrayList = entry.getValue();
                //itera por todos os usuários de cada ator
                try
                {
                    for (ClientUserSocketPair client : arrayList) 
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
                            //Se não for um ponteiro perdido, eu dou logoff
                            if (client != null)
                                client.logoff();                            
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

    /**
     * Envia a mensagem para TODOS os destinatários da mensagem. (Pesquisados por message.getTo()).
     * @param message 
     */
    @Override
    protected void sendBroadcastMessage(PassControlMessage message)
    {
        if (!message.getType().equals("HeartBeatMessage"))            
            System.out.println("Mensagem enviada para todos os clientes do tipo " + message.getTo().name()); 
        
        HashMap<MessageActors, ArrayList<ClientUserSocketPair>> clientsMarkedToBeRemoved = new HashMap<>();
        if (message.getTo() != MessageActors.AllActors) 
        {
           
            
            //Filtra só para os clientes desejados
            MessageActors actorToReceive = message.getTo();
            ArrayList<ClientUserSocketPair> selectedClients = clientsList.get(actorToReceive);
            if (selectedClients != null) 
            {
                for (ClientUserSocketPair client : selectedClients) 
                {
                    try
                    {
                        //Manda para todos
                        sendMessage(client.getSocket(), message);
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
            //Percorro todas as listas
            for (Map.Entry<MessageActors, ArrayList<ClientUserSocketPair>> entry : clientsList.entrySet()) 
            {
                //Percorro todos os elementos de todas as listas
                for (ClientUserSocketPair client : entry.getValue()) 
                {
                    try
                    {
                        //Manda para todos
                        sendMessage(client.getSocket(), message);
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
        for (Map.Entry<MessageActors, ArrayList<ClientUserSocketPair>> entry : clientsMarkedToBeRemoved.entrySet()) 
        {
            //Ator chave
            MessageActors messageActor = entry.getKey();
            //Copia a lista atual
            ArrayList<ClientUserSocketPair> currentClient = clientsList.get(messageActor);
            for (ClientUserSocketPair client : entry.getValue()) 
            {
                //Se não for um ponteiro perdido, eu dou logoff
                if (client != null)
                    client.logoff();
                //Remove da lista atual
                currentClient.remove(client);
            }
            //Atualiza
            clientsList.put(messageActor, currentClient);
        }
        
    }

    public void addClient(MessageActors messageActors, ClientUserSocketPair newClient) 
    {
        markToReset = true;
        addClientToMap(clientsList, messageActors, newClient);
    }

    public void removeClient(MessageActors messageActors, ClientUserSocketPair newClient) 
    {
        markToReset = true;
        removeClientFromMap(clientsList, messageActors, newClient);
    }
    
    /**
     * Insere um elemento no HashMap de um ArrayList
     * @param map HashMap a se inserir
     * @param actorKey Chave do HashMap
     * @param clientValue Valor a ser inserido na lista do HashMap
     */
    private static void addClientToMap(AbstractMap<MessageActors, ArrayList<ClientUserSocketPair>> map, MessageActors actorKey, ClientUserSocketPair clientValue)
    {
        //Recupera a lista anterior
        ArrayList<ClientUserSocketPair> listaDeClientesDoMesmoTipo;
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
    private static void removeClientFromMap(AbstractMap<MessageActors, ArrayList<ClientUserSocketPair>> map, MessageActors actorKey, ClientUserSocketPair client)
    {
        //Recupera a lista anterior
        ArrayList<ClientUserSocketPair> listaDeClientesDoMesmoTipo;
        listaDeClientesDoMesmoTipo = map.get(actorKey);
        if (listaDeClientesDoMesmoTipo == null) {
            listaDeClientesDoMesmoTipo = new ArrayList<>();
        }

        //Adiciona mais um cliente
        listaDeClientesDoMesmoTipo.remove(client);

        //insere a lista novamente
        map.put(actorKey, listaDeClientesDoMesmoTipo);
        
    }
    
    /**
     * 
     * @param <Message> Classe da mensagem que deve ser recebida
     * @param message Mensagem a ser passada por parâmetro
     * @param clazz Classe da mensagem que deve ser recebida
     * @return Um mapa de Socket-Mensagem
     */
    public <Message extends PassControlMessage> HashMap<Socket, Message> sendMessageToClientsAndWaitForResponse(PassControlMessage message, Class<Message> clazz)
    {
        GenericBufferPassControlMessageListener listener = new GenericBufferPassControlMessageListener(clientsList.get(message.getTo()).size());

        addMessageListener(listener, clazz);
        addBroadcastToSend(message);
        while (true)//Espera infinitamente 
        {          
            if (listener.hasReceivedAllMessages())
            {
                break;
            }
            else
            {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PassControlCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        removeListener(listener, clazz);

        return (HashMap<Socket, Message>)listener.getMessagesReceived();
    }
    
    /**
     * 
     * @param <Message> Classe da mensagem que deve ser recebida
     * @param message Mensagem a ser passada por parâmetro
     * @param clazz Classe da mensagem que deve ser recebida
     * @param timeout in milliseconds
     * @return Um mapa de Socket-Mensagem
     */
    public <Message extends PassControlMessage> HashMap<Socket, Message> sendMessageToClientsAndWaitForResponseOrTimeout(PassControlMessage message, Class<Message> clazz, long timeout)
    {
        GenericBufferPassControlMessageListener listener = new GenericBufferPassControlMessageListener(clientsList.get(message.getTo()).size());

        addMessageListener(listener, clazz);
        Watchdog timeOutWatcher = new Watchdog(timeout);
        addBroadcastToSend(message);
        while (!timeOutWatcher.hasTimedOut()) //Enquanto não der timeout
        {          
            if (listener.hasReceivedAllMessages())
            {
                break;
            }
            else
            {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PassControlCommunicationThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        removeListener(listener, clazz);

        return (HashMap<Socket, Message>)listener.getMessagesReceived();
    }
    
    
    private void repositionClient(MessageActors currentActor, MessageActors newActor, ClientUserSocketPair client) {
        //Remove da lista anterior e adiciona na nova lista
        System.out.println("Reposicionando clientes actors: " + currentActor.name() + " -> " + newActor.name());
        removeClientFromMap(clientsList, currentActor, client);
        addClientToMap(clientsList, newActor, client);
    }

    //Retorna true caso tenha lido alguma coisa
    private boolean checkInputStream(MessageActors currentActor, ClientUserSocketPair client) throws IOException, ClassNotFoundException, NullPointerException
    {
        if (client == null || client.getSocket() == null)
            return false;
            
        InputStream inputStream = client.getSocket().getInputStream();
        if (inputStream == null)
            return false;
        //Se possui mensagem para ser lida
        if (inputStream.available() > 0) 
        {

            //Recebe a mensagem
            PassControlMessage message = handleIncomingMessage(client.getSocket());
            MessageActors newActor = message.getFrom();
            //Caso o ator tenha mudado de papel
            if (newActor != currentActor) {
                repositionClient(currentActor, newActor, client);
            }

            //Distribui a mensagem para os listener que querem essa mensagem
            redirectReceivedMessage(message, client.getSocket());
            return true;

        }
        return false;
    }

    public void userLogin(Socket socket, UserBean userBean)
    {
        ClientUserSocketPair pair = getPairFromSocket(socket);
        if (pair != null)
            pair.login(userBean);
    }
    
    public void userLogoff(Socket socket)
    {
        ClientUserSocketPair pair = getPairFromSocket(socket);
        if (pair != null)
            pair.logoff();
    }
    
    public ClientUserSocketPair getPairFromSocket(Socket socket)
    {
        //Por todo o mapa
        for (Map.Entry<MessageActors, ArrayList<ClientUserSocketPair>> entry : clientsList.entrySet()) 
        {
            //Por todos os clientes
            for (ClientUserSocketPair clientUserSocketPair : entry.getValue()) 
            {
                //Aquele que tiver o mesmo socket...
                if (clientUserSocketPair.getSocket().equals(socket))
                {
                    return clientUserSocketPair;
                }
            }
        }    
        return null;
    }

    public ConcurrentHashMap<MessageActors, ArrayList<ClientUserSocketPair>> getClientsList() {
        return clientsList;
    }
    
    public boolean isUserLogged(UserBean user)
    {
        //Por todo o mapa
        for (Map.Entry<MessageActors, ArrayList<ClientUserSocketPair>> entry : clientsList.entrySet()) 
        {
            //Por todos os clientes
            for (ClientUserSocketPair clientUserSocketPair : entry.getValue()) 
            {
                //Aquele que tiver o mesmo socket...
                if (clientUserSocketPair.getUser().equals(user))
                {
                    return true;
                }
            }
        } 
        return false;
    }


}
