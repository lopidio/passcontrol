/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.balcony;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;

/**
 *
 * @author guilherme
 */
public class BalconyLogin extends PassControlMessage
{
    /**
     * Número do guichê
     */
    String number;
    
    /**
     * Índice do tipo de guichê selecionado do array de tipos
     */
    String balconyType;

    public BalconyLogin(String number, String balconyType) 
    {
        super(MessageActors.BalconyActor, MessageActors.ServerActor);
        this.number = number;
        this.balconyType = balconyType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getBalconyType() {
        return balconyType;
    }

    public void setBalconyType(String balconyType) {
        this.balconyType = balconyType;
    }
    
    
    
}
