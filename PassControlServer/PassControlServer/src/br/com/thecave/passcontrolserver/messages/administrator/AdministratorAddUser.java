package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.db.bean.UserBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author Arleudo
 */
public class AdministratorAddUser extends PassControlMessage
{
    private UserBean bean;
    
    public AdministratorAddUser(UserBean bean) 
    {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
        this.bean = bean;
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
