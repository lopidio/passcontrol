/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.administrator;

import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author guilherme
 */
public class AdministratorAddService extends PassControlMessage
{
    private ServiceBean bean;
    
    public AdministratorAddService() 
    {
        super(MessageActors.AdministratorActor, MessageActors.ServerActor);
    }

    /**
     * @return the bean
     */
    public ServiceBean getBean() 
    {
        return bean;
    }

    /**
     * @param bean the bean to set
     */
    public void setBean(ServiceBean bean) 
    {
        this.bean = bean;
    }       
}
