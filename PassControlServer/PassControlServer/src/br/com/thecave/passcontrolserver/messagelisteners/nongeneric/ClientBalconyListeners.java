/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.nongeneric;

import br.com.thecave.passcontrolserver.PassControlServer;
import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.ClientBean;
import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.db.dao.BalconyDAO;
import br.com.thecave.passcontrolserver.db.dao.ClientDAO;
import br.com.thecave.passcontrolserver.db.dao.QueuesManagerDAO;
import br.com.thecave.passcontrolserver.db.dao.ServiceDAO;
import br.com.thecave.passcontrolserver.messagelisteners.generic.ClientListeners;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyCallNextClientRequest;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyFinalizeCurrentClient;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyInitRequest;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyInitResponse;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyLogin;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyRecoverClientMessage;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.balcony.BalconySkipCurrentClient;
import br.com.thecave.passcontrolserver.messages.generic.ChangeActorMessage;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import br.com.thecave.passcontrolserver.util.QueueElementHandler;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lopidio
 */
public class ClientBalconyListeners implements ClientListeners
{
    private static HashMap<Integer, Socket> loggedBalconySocketList = new HashMap<>();
    private static ArrayList<Socket> clientsSocketAsBalcony = new ArrayList<>();

    @Override
    public void addListenersCallback(ServerCommunicationThread server)
    {
        
        //Chamados pelo balcony login
        server.addMessageListener(new BalconyInitListener(), BalconyInitRequest.class);
        server.addMessageListener(new BalconyLoginListener(), BalconyLogin.class);
        server.addMessageListener(new ChangeActorMessageBalconyListener(), ChangeActorMessage.class);
        //Chamado pelo balcony 'de verdade'
        
        //Balcony livre
        server.addMessageListener(new BalconyCallNextClientRequestListener(), BalconyCallNextClientRequest.class);
        //Rechamar último chamado
        server.addMessageListener(new BalconyShowClientMessageListener(), BalconyShowClientMessage.class);
        //Finalizar atendimento
        server.addMessageListener(new BalconyFinalizeCurrentClientListener(), BalconyFinalizeCurrentClient.class);
        //Pular atendimento atual
        server.addMessageListener(new BalconySkipCurrentClientListener(), BalconySkipCurrentClient.class);
        //Recupera algum atendimento, caso haja
        server.addMessageListener(new BalconyRecoverClientMessageListener(), BalconyRecoverClientMessage.class);
        
    }
    
    private static class BalconyInitListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
//            BalconyInitRequest balconyInitRequestMessage = (BalconyInitRequest)message;
            refreshLoggedBalconySocketList();
            
            //Resposta default
            BalconyInitResponse balconyInitResponse = new BalconyInitResponse(null);
            
            //Pego todos os guichês
            ArrayList<BalconyBean> balconyBeans = BalconyDAO.selectAll();

            //Se a consulta deu certo
            if (balconyBeans != null)
            {
                //Removo os que já estão sendo usados 
                for (Map.Entry<Integer, Socket> alreadyLogged : loggedBalconySocketList.entrySet()) 
                {
                    for (BalconyBean balconyBean : balconyBeans) 
                    {
                        if (balconyBean.getId() == alreadyLogged.getKey())
                        {
                            balconyBeans.remove(balconyBean);
                            break;
                        }
                    }
                }
                //Altero a resposta default
                balconyInitResponse.setAvaliableBalconys(balconyBeans);
                
            }
            
            PassControlServer.getInstance().getServer().addResponseToSend(socket, balconyInitResponse);
        }       

    }

    private static void refreshLoggedBalconySocketList() 
    {
        //Verifico se tem algum socket meu que não tá logado no servidor

        //Pego todos os clientes logados como BALCONY ACTOR
        synchronized (loggedBalconySocketList)
        {
            //Copia de segurança
            HashMap<Integer, Socket> safeCopy = new HashMap<>(loggedBalconySocketList);
            //Itero por todos os meus guichês
            for (Map.Entry<Integer, Socket> entry : safeCopy.entrySet()) 
            {
                Integer balconyBeanId = entry.getKey();
                Socket socket = entry.getValue();

                boolean existeUmCorrespondente = false;
                //Se algum dos balconys logado
                for (Socket balconysSocket : clientsSocketAsBalcony)
                {
                    if (balconysSocket.equals(socket))
                    {
                        existeUmCorrespondente = true;
                        break;
                    }
                }
                if (!existeUmCorrespondente)
                {
                    System.out.println("BalconyListener::Guichê: [" + balconyBeanId + "] não está mais logado");                        
                    loggedBalconySocketList.remove(balconyBeanId);
                }

            }
        }
    }
    
    private static class BalconyLoginListener implements PassControlMessageListener {

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
            BalconyLogin balconyLogin = (BalconyLogin)message;
            BalconyBean balconyLoginBean = balconyLogin.getBalconyBean();

//            System.out.println("Guichê inicializado com o número " + balconyBean.getNumber() );
            
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, message, MessageActors.BalconyActor);
            
            //O guichê não está em uso
            if (loggedBalconySocketList.get(balconyLoginBean.getId()) == null)
            {
                //Adiciono na lista de usados
                loggedBalconySocketList.put(balconyLoginBean.getId(), socket);
                System.out.println("Guichê [" + balconyLoginBean.getNumber() + "]" + " logado com sucesso!");
                confirmationResponse.setComment("Guichê [" + balconyLoginBean.getNumber() + "]" + " logado com sucesso!");
            }
            else
            {
                //Informo que esse guichê não está mais disponível
                confirmationResponse.setStatusOperation(false);
                confirmationResponse.setComment("Guichê em uso.");
            }
            
            PassControlServer.getInstance().getServer().addResponseToSend(socket, confirmationResponse);
        }

    }
    
    private static class BalconyCallNextClientRequestListener implements PassControlMessageListener {

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            BalconyCallNextClientRequest balconyCallNextClient = (BalconyCallNextClientRequest)message;
            
            //Ninguém bole no server enquanto esse bloco é bulido
            synchronized (PassControlServer.getInstance().getServer())
            {
                //Se eu ainda possuir o registro desse socket
                ConfirmationResponse confirmationResponse = new ConfirmationResponse(false, balconyCallNextClient, MessageActors.BalconyActor);
                if (socket == loggedBalconySocketList.get(balconyCallNextClient.getBalconyBean().getId()) && socket != null)
                {
                    //Repasso a informação para o escolhedor do próximo cliente
                    QueueElementHandler.getInstance().balconyIsWaiting(balconyCallNextClient.getBalconyBean(), loggedBalconySocketList.get(balconyCallNextClient.getBalconyBean().getId()));
                    confirmationResponse.setStatusOperation(true);
                    confirmationResponse.setComment("O guichê está no aguardo do próximo cliente.");
                }
                else
                {
                    confirmationResponse.setComment("O guichê não foi corretamente logado.");
                }
                
                PassControlServer.getInstance().getServer().addResponseToSend(socket, confirmationResponse);            
            }
        }
    }

    private static class BalconyShowClientMessageListener implements PassControlMessageListener
    {

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            //Rechamada de um cliente
            
            BalconyShowClientMessage showClientMessage = (BalconyShowClientMessage)message;
            //Envia para todos os viewers
            message.setFrom(MessageActors.ServerActor);
            message.setTo(MessageActors.ViewerActor);
            PassControlServer.getInstance().getServer().addBroadcastToSend(showClientMessage);
        }

    }  

    
    //Envia o elemento de volta para o balcony
    public static void sendBackElementQueueToBalcony(Socket socket, QueuesManagerBean queuesManagerBean)
    {
        String clientName = "";
        ClientBean clientBean = ClientDAO.selectFromId(queuesManagerBean.getIdClient());
        if (clientBean != null)
        {
            clientName = clientBean.getName();
        }
        
        //Captura o nome do serviço
        String serviceName = ServiceDAO.selectFromId(queuesManagerBean.getIdService()).getName();
        //Captura o nome do guichê
        String balconyName = BalconyDAO.selectFromId(queuesManagerBean.getIdBalcony()).getNumber();
        
        ServerCommunicationThread server = PassControlServer.getInstance().getServer();
        //Informa ao guichê que esse cliente deve ser chamado
        BalconyShowClientMessage balconyCallNextClientResponse = new BalconyShowClientMessage(clientName, serviceName, balconyName, queuesManagerBean, MessageActors.ServerActor, MessageActors.BalconyActor);
        server.addResponseToSend(socket, balconyCallNextClientResponse);

        //Informa ao viewer que aquele elemento foi chamado
        BalconyShowClientMessage viewerCallNextClient = new BalconyShowClientMessage(clientName, serviceName, balconyName, queuesManagerBean, MessageActors.ServerActor, MessageActors.ViewerActor);
        server.addBroadcastToSend(viewerCallNextClient);  
        
        //Informa à todos os poppers para atualizarem suas filas
        server.addBroadcastToSend(ClientQueuePopperListener.generateAllClientsToPopper());  
    }

    public static HashMap<Integer, Socket> getLoggedBalconysID() 
    {
        //Atualiza a lista
        refreshLoggedBalconySocketList();
        return loggedBalconySocketList;
    }    

    private static class BalconyFinalizeCurrentClientListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            //O atendimento foi finalizado
            BalconyFinalizeCurrentClient finalizeCurrentClient = (BalconyFinalizeCurrentClient)message;
            //Seto a hora da finalização
            finalizeCurrentClient.getQueuesManagerBean().setCheckout(QueuesManagerDAO.dateFormat.format(new Date()));

            //salvo as alterações no banco.
            boolean status = QueuesManagerDAO.update(finalizeCurrentClient.getQueuesManagerBean());        
            ConfirmationResponse response = new ConfirmationResponse(status, finalizeCurrentClient, MessageActors.BalconyActor);
            ServerCommunicationThread server = PassControlServer.getInstance().getServer();
            server.addResponseToSend(socket, response);
        }
    }

    private static class BalconySkipCurrentClientListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            //O atendimento não vai existir
            //Digo que o balcão que atendeu foi o 'não houve balcão'
            BalconySkipCurrentClient balconySkipCurrentClient = (BalconySkipCurrentClient)message;
            balconySkipCurrentClient.getQueuesManagerBean().setIdBalcony(QueueElementHandler.QUEUE_ELEMENT_SKIPPED_BALCONY_ID);
            boolean status = QueuesManagerDAO.update(balconySkipCurrentClient.getQueuesManagerBean());
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(status, balconySkipCurrentClient, MessageActors.BalconyActor);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, confirmationResponse);
                       
        }
    }

    private static class ChangeActorMessageBalconyListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            ChangeActorMessage changeActorMessage = (ChangeActorMessage)message;
            //Insere
            if (changeActorMessage.getFrom() == MessageActors.BalconyActor)
            {
               clientsSocketAsBalcony.add(socket);
               refreshLoggedBalconySocketList();
            }
            else if (changeActorMessage.getOldActor() == MessageActors.BalconyActor)
            {
                clientsSocketAsBalcony.remove(socket);
                refreshLoggedBalconySocketList();
            }
        }
    }

    private static class BalconyRecoverClientMessageListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            BalconyRecoverClientMessage recoverClientMessage = (BalconyRecoverClientMessage)message;
            ServerCommunicationThread server = PassControlServer.getInstance().getServer();

            //Informa ao guichê que esse cliente deve ser chamado
            BalconyShowClientMessage balconyCallNextClientResponse = new BalconyShowClientMessage(null, null, null, null, MessageActors.ServerActor, MessageActors.BalconyActor);
            balconyCallNextClientResponse.setComment("Não existe cliente para ser recuperado.");
            QueuesManagerBean queuesManagerBean = QueuesManagerDAO.recoverSkippedClientFromBalcony(recoverClientMessage.getBalcony());
            if (queuesManagerBean != null)
            {
            
                String clientName = "";
                ClientBean clientBean = ClientDAO.selectFromId(queuesManagerBean.getIdClient());
                if (clientBean != null)
                {
                    clientName = clientBean.getName();
                }

                //Captura o nome do serviço
                String serviceName = ServiceDAO.selectFromId(queuesManagerBean.getIdService()).getName();
                //Captura o nome do guichê
                String balconyName = BalconyDAO.selectFromId(queuesManagerBean.getIdBalcony()).getNumber();            

                balconyCallNextClientResponse = new BalconyShowClientMessage(clientName, serviceName, balconyName, queuesManagerBean, MessageActors.ServerActor, MessageActors.BalconyActor);
                balconyCallNextClientResponse.setComment("Cliente recuperado com sucesso");

                //Informa ao viewer que aquele elemento foi chamado
                BalconyShowClientMessage viewerCallNextClient = new BalconyShowClientMessage(clientName, serviceName, balconyName, queuesManagerBean, MessageActors.ServerActor, MessageActors.ViewerActor);
                server.addBroadcastToSend(viewerCallNextClient);              
                
            }
            //Envia de volta aos balconys
            server.addResponseToSend(socket, balconyCallNextClientResponse);
            
        }
    }
    
}
