/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.nongeneric;

import br.com.thecave.passcontrolserver.PassControlServer;
import br.com.thecave.passcontrolserver.communicationThread.ClientUserSocketPair;
import br.com.thecave.passcontrolserver.communicationThread.ServerCommunicationThread;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.dao.BalconyDAO;
import br.com.thecave.passcontrolserver.messagelisteners.generic.ClientListeners;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyCallNextClientRequest;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyCallNextClientResponse;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyInitRequest;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyInitResponse;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyLogin;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyRecallLastClient;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lopidio
 */
public class ClientBalconyListeners implements ClientListeners
{
    private static HashMap<BalconyBean, Socket> unnavaliableBalconySocket = new HashMap<>();

    @Override
    public void addListenersCallback()
    {
        ServerCommunicationThread server = PassControlServer.getInstance().getServer();
        server.addMessageListener(new BalconyInitListener(), BalconyInitRequest.class);
        server.addMessageListener(new BalconyLoginListener(), BalconyLogin.class);
        server.addMessageListener(new BalconyRecallLastClientListener(), BalconyRecallLastClient.class);
        server.addMessageListener(new BalconyCallNextClientRequestListener(), BalconyCallNextClientRequest.class);
    }
    
    private static class BalconyInitListener implements PassControlMessageListener
    {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
//            BalconyInitRequest balconyInitRequestMessage = (BalconyInitRequest)message;
            
            //Resposta default
            BalconyInitResponse balconyInitResponse = new BalconyInitResponse(null);
            
            //Pego todos os guichês
            ArrayList<BalconyBean> balconyBeans = BalconyDAO.selectAll();

            //Se a consulta deu certo
            if (balconyBeans != null)
            {
                refreshUnnavaliableBalconySocket();
                //Removo os que já estão sendo usados 
                for (Map.Entry<BalconyBean, Socket> unnavaliable : unnavaliableBalconySocket.entrySet()) 
                {
                    balconyBeans.remove(unnavaliable.getKey());
                }
                //Altero a resposta default
                balconyInitResponse.setAvaliableBalconys(balconyBeans);
                
            }
            
            PassControlServer.getInstance().getServer().addResponseToSend(socket, balconyInitResponse);
        }       

        private void refreshUnnavaliableBalconySocket() 
        {
            //Verifico se tem algum socket meu que não tá logado no servidor
            
            //Pego todos os clientes logados como BALCONY ACTOR
            ArrayList<ClientUserSocketPair> balconysLoggedOnServer = PassControlServer.getInstance().getServer().getClientsList().get(MessageActors.BalconyActor); 

            //Copia de segurança
            HashMap<BalconyBean, Socket> safeCopy = new HashMap<>(unnavaliableBalconySocket);
            //Itero por todos os meus guichês
            for (Map.Entry<BalconyBean, Socket> entry : safeCopy.entrySet()) {
                BalconyBean balconyBean = entry.getKey();
                Socket socket = entry.getValue();
                
                boolean existeUmCorrespondente = false;
                for (ClientUserSocketPair clientUserSocketPair : balconysLoggedOnServer)
                {
                    if (clientUserSocketPair.getSocket().equals(socket))
                    {
                        existeUmCorrespondente = true;
                    }
                }
                if (!existeUmCorrespondente)
                {
                    unnavaliableBalconySocket.remove(balconyBean);
                }
                
            }
        }
    }

    private static class BalconyLoginListener implements PassControlMessageListener {

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
            BalconyLogin balconyLogin = (BalconyLogin)message;
            BalconyBean balconyBean = balconyLogin.getBalconyBean();

            System.out.println("Guichê inicializado com o número " + balconyBean.getNumber() );
            
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, message, MessageActors.BalconyActor);
            
            //O guichê não está em uso
            if (unnavaliableBalconySocket.get(balconyBean) == null)
            {
                //Adiciono na lista de usados
                unnavaliableBalconySocket.put(balconyBean, socket);
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
       
    private static class BalconyRecallLastClientListener implements PassControlMessageListener {
        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) {
            //BalconyCallNextClientRequest
//BalconyCallNextClientResponse

            BalconyRecallLastClient balconyRecall = (BalconyRecallLastClient)message;

            System.out.println("Guichê " + balconyRecall.getBalconyID() + " rechamando último cliente");
            
            //Repassa as informações para os viewers...
            
            ConfirmationResponse confirmationResponse = new ConfirmationResponse(true, message, MessageActors.BalconyActor);
            
            PassControlServer.getInstance().getServer().addResponseToSend(socket, confirmationResponse);
        }
    }

    private static class BalconyCallNextClientRequestListener implements PassControlMessageListener {

        @Override
        public void onMessageReceive(PassControlMessage message, Socket socket) 
        {
            BalconyCallNextClientRequest balconyCallNextClient = (BalconyCallNextClientRequest)message;
            System.out.println("Guichê " + balconyCallNextClient.getBalconyID() + " chamando o próximo");
            
            //Envio a informação para os PUSHERS, caso esteja manual
            
            BalconyCallNextClientResponse balconyCallNextClientResponse = new BalconyCallNextClientResponse("Guiguinha cliente", "Fila macarrão", "48-R");
            PassControlServer.getInstance().getServer().addResponseToSend(socket, balconyCallNextClientResponse);
        }
    }

    //Envia o elemento de volta para o balcony
    public static boolean sendElementQueueToBalcony(BalconyBean balconyBean)
    {
        return true;
    }
    
}
