/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messagelisteners.generic;

import br.com.thecave.passcontrolserver.db.bean.UserBean;
import br.com.thecave.passcontrolserver.db.dao.UserDAO;
import br.com.thecave.passcontrolserver.messages.generic.ClientLoginRequest;
import br.com.thecave.passcontrolserver.messages.generic.ClientLoginResponse;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessageListener;
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

        //Inicializa com uma mensagem default
        ClientLoginResponse response = new ClientLoginResponse(null, message.getFrom());

            
            //Verifica se é super usuário
        if (isSuperUser(initRequest.getUser(), initRequest.getPassword()))
        {
            UserBean superBean = new UserBean();
            superBean.setLogin(initRequest.getUser());
            superBean.setName("Super Usuário");
            superBean.setType(0);
            response.setUser(superBean);
        }
        else
        {
            UserBean bean = UserDAO.selectFromName(initRequest.getUser());
            //Verifica se existe o usuário no banco
            if (bean != null)
            {
                if (bean.getPassword().equals(initRequest.getPassword()))
                {
                    response.setUser(bean);
                }
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
