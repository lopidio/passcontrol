package br.com.thecave.passcontrol.messages.administrator;

import br.com.thecave.passcontrol.db.bean.UserBean;
import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.messages.PassControlMessage;

/**
 *
 * @author Arleudo
 */
public class AdministratorAddUser extends PassControlMessage
{
    private UserBean bean;
    
    public AdministratorAddUser() 
    {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
    }

    /**
     * @return the bean
     */
    public UserBean getBean() 
    {
        return bean;
    }

    /**
     * @param bean the bean to set
     */
    public void setBean(UserBean bean) 
    {
        this.bean = bean;
    }   
}
