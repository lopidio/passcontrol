/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.nongeneric;

import br.com.thecave.passcontrolserver.PassControlServer;
import br.com.thecave.passcontrolserver.communicationThread.ClientUserSocketPair;
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
import br.com.thecave.passcontrolserver.messages.balcony.BalconyInitCurrentClient;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyLogin;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;
import br.com.thecave.passcontrolserver.messages.balcony.BalconySkipCurrentClient;
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
    private static HashMap<BalconyBean, Socket> loggedBalconySocketList = new HashMap<>();

    @Override
    public void addListenersCallback(ServerCommunicationThread server)
    {
        
        //Chamados pelo balcony login
        server.addMessageListener(new BalconyInitListener(), BalconyInitRequest.class);
        server.addMessageListener(new BalconyLoginListener(), BalconyLogin.class);
        
        //Chamado pelo balcony 'de verdade'
        
        //Balcony livre
        server.addMessageListener(new BalconyCallNextClientRequestListener(), BalconyCallNextClientRequest.class);
        //Rechamar último chamado
        server.addMessageListener(new BalconyShowClientMessageListener(), BalconyShowClientMessage.class);
        //Iniciar atendimento
        server.addMessageListener(new BalconyInitCurrentClientListener(), BalconyInitCurrentClient.class);
        //Finalizar atendimento
        server.addMessageListener(new BalconyFinalizeCurrentClientListener(), BalconyFinalizeCurrentClient.class);
        //Pular atendimento atual
        server.addMessageListener(new BalconySkipCurrentClientListener(), BalconySkipCurrentClient.class);
        
    }
    
    private static class BalconyInitListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
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
                for (Map.Entry<BalconyBean, Socket> unnavaliable : loggedBalconySocketList.entrySet()) 
                {
                    balconyBeans.remove(unnavaliable.getKey());
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
        ArrayList<ClientUserSocketPair> balconysLoggedOnServer = PassControlServer.getInstance().getServer().getClientsList().get(MessageActors.BalconyActor); 
        System.out.println("Número de guichês já logados: " + balconysLoggedOnServer.size() );
        
        //Copia de segurança
        HashMap<BalconyBean, Socket> safeCopy = new HashMap<>(loggedBalconySocketList);
        //Itero por todos os meus guichês
        for (Map.Entry<BalconyBean, Socket> entry : safeCopy.entrySet()) 
        {
            BalconyBean balconyBean = entry.getKey();
            Socket socket = entry.getValue();

            boolean existeUmCorrespondente = false;
            //Se algum dos balconys logado
            for (ClientUserSocketPair clientUserSocketPair : balconysLoggedOnServer)
            {
                if (clientUserSocketPair.getSocket().equals(socket))
                {
                    existeUmCorrespondente = true;
                    break;
                }
            }
            if (!existeUmCorrespondente)
            {
                System.out.println("BalconyListener::Guichê: [" + balconyBean.getNumber()+ "] não está mais logado");                        
                loggedBalconySocketList.remove(balconyBean);
            }

        }
    }
    
    private static class BalconyLoginListener implements PassControlMessageListener {

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
            BalconyLogin balconyLogin = (BalconyLogin)message;
            BalconyBean balconyBean = balconyLogin.getBalconyBean();

//            System.out.println("Guichê inicializado com o número " + balconyBean.getNumber() );
            
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, message, MessageActors.BalconyActor);
            
            //O guichê não está em uso
            if (loggedBalconySocketList.get(balconyBean) == null)
            {
                //Adiciono na lista de usados
                loggedBalconySocketList.put(balconyBean, socket);
                System.out.println("Guichê [" + balconyBean.getNumber() + "]" + " logado com sucesso!");
                confirmationResponse.setComment("Guichê [" + balconyBean.getNumber() + "]" + " logado com sucesso!");
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
                if (socket == loggedBalconySocketList.get(balconyCallNextClient.getBalconyBean()) && socket != null)
                {
                    //Repasso a informação para o escolhedor do próximo cliente
                    QueueElementHandler.getInstance().balconyIsWaiting(balconyCallNextClient.getBalconyBean(), loggedBalconySocketList.get(balconyCallNextClient.getBalconyBean()));
                    confirmationResponse.setStatusOperation(true);
                    confirmationResponse.setComment("O guichê está no aguardo do próximo cliente.");
                }
                    confirmationResponse.setComment("O guichê não foi corretamente logado.");
                
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
//    private static class BalconyShowClientInitListener implements PassControlMessageListener
//    {
//
//        @Override
//        public void onMessageReceive(PassControlMessage message, Socket socket) 
//        {
//            BalconyShowClientInit balconyShowClientInit = (BalconyShowClientInit)message;
//            QueuesManagerBean queuesManagerBean = balconyShowClientInit.getQueuesManagerBean();
//
//            ServerCommunicationThread server = PassControlServer.getInstance().getServer();
//
//            ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, balconyShowClientInit, MessageActors.BalconyActor);
//
//            //Verifico se esse cliente ainda não foi chamado por nenhum outro guichê
//            if (QueuesManagerDAO.selectFromId(queuesManagerBean.getId()).getIdBalcony() == 0)
//            {
//                //Se a atualização tiver sido bem sucedida
//                if (QueuesManagerDAO.update(queuesManagerBean))
//                {                
//                    //Informa à todos os QueuePoppers que esse Cliente foi chamado
//                    balconyShowClientInit.setTo(MessageActors.QueuePopActor);
//                    server.addBroadcastToSend(balconyShowClientInit);
//
//                    //Informa à todos os visualizadores que esse Cliente foi chamado
//                    balconyShowClientInit.setTo(MessageActors.ViewerActor);
//                    server.addBroadcastToSend(balconyShowClientInit);                
//                }
//            }
//            else
//            {
//                confirmationResponse.setStatusOperation(false);                
//                confirmationResponse.setComment("Cliente já atendido por outro guichê");
//            }
//            server.addResponseToSend(socket, confirmationResponse);
//
//        }
//
//    }    
    
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
    }

    public static HashMap<BalconyBean, Socket> getUsedBalconys() 
    {
        //Atualiza a lista
        refreshLoggedBalconySocketList();
        return loggedBalconySocketList;
    }    

    private static class BalconyInitCurrentClientListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            //Atendimento vai começar (seto a hora que começou)
            BalconyInitCurrentClient balconyInitCurrentClient = (BalconyInitCurrentClient)message;
            balconyInitCurrentClient.getQueuesManagerBean().setCheckout(QueuesManagerDAO.dateFormat.format(new Date()));
            boolean status = QueuesManagerDAO.update(balconyInitCurrentClient.getQueuesManagerBean());
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(status, balconyInitCurrentClient, MessageActors.BalconyActor);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, confirmationResponse);
        }
    }

    private static class BalconyFinalizeCurrentClientListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            //O atendimento foi finalizado
            //Não sei o que fazer aqui. :D
            
            //A não ser que criemos uma coluna HoraEncerramento, ou coisa parecida...
        }
    }

    private static class BalconySkipCurrentClientListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            //O atendimento não vai existir
            //De que o balcão que atendeu foi o 'não houve balcão'
            BalconySkipCurrentClient balconySkipCurrentClient = (BalconySkipCurrentClient)message;
            balconySkipCurrentClient.getQueuesManagerBean().setIdBalcony(QueueElementHandler.QUEUE_ELEMENT_SKIPPED_BALCONY_ID);
            boolean status = QueuesManagerDAO.update(balconySkipCurrentClient.getQueuesManagerBean());
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(status, balconySkipCurrentClient, MessageActors.BalconyActor);
            PassControlServer.getInstance().getServer().addResponseToSend(socket, confirmationResponse);
                       
        }
    }
    
}
