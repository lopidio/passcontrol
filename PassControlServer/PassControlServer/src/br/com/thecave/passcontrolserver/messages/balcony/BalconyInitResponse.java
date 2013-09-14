/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.balcony;

import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import java.util.ArrayList;

/**
 *
 * @author guilherme
 */
public class BalconyInitResponse extends PassControlMessage
{
    /**
     * Tipos disponíveis do balcão
     */
    ArrayList<String> balconyTypes;
    
    /**
     * números disponíveis para o balcão
     */
    ArrayList<String> balconyNumbers;

    public BalconyInitResponse(ArrayList<String> balconyTypes, ArrayList<String> balconyNumbers) {
        super(MessageActors.ServerActor, MessageActors.BalconyActor);
        this.balconyTypes = balconyTypes;
        this.balconyNumbers = balconyNumbers;
    }

    public ArrayList<String> getBalconyTypes() {
        return balconyTypes;
    }

    public ArrayList<String> getBalconyNumbers() {
        return balconyNumbers;
    }
    
    
    
}
