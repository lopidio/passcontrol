/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.generic;

import br.com.thecave.passcontrol.db.bean.UserBean;
import br.com.thecave.passcontrol.db.dao.UserDAO;
import br.com.thecave.passcontrol.messages.ClientLoginRequest;
import br.com.thecave.passcontrol.messages.ClientLoginResponse;
import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.messages.PassControlMessage;
import br.com.thecave.passcontrol.messages.PassControlMessageListener;
import br.com.thecave.passcontrolserver.PassControlServer;
import java.net.Socket;

/**
 *
 * @author lopidio
 */
public class ClientLoginMessageListener implements PassControlMessageListener
{
    @Override
    public void onMessageReceive(PassControlMessage message, Socket socket) 
    {
        ClientLoginRequest initRequest = (ClientLoginRequest)message;
        UserBean bean = UserDAO.selectFromName(initRequest.getUser());

        //Inicializa com uma mensagem default
        ClientLoginResponse response = new ClientLoginResponse(null, message.getFrom());

        //Verifica se existe o usuário no banco
        if (bean != null)
        {
            if (bean.getPassword().equals(initRequest.getPassword()))
            {
                response.setUser(bean);
            }
        }
        else
        {
            if (isSuperUser(initRequest.getUser(), initRequest.getPassword()))
            {
                response.setUser(bean);
            }
        }
        //Devolve a mensagem para o servidor
        PassControlServer.getInstance().getServer().addResponseToSend(socket, response);
    }
    
    
    /**
     * Metodo para verificar se o usuário é o Super
     * @param user String com o nome do usuário
     * @param pass String com a senha digitada
     * @return true se fo,r false se não
     */
    public boolean isSuperUser(String name, String pass)
    {
        if(name.equalsIgnoreCase("admin"))
        {
            if(pass.equals("supasscontroladmin"))
            {
                return true;
            }
        }
        return false;
    }    
    
}
