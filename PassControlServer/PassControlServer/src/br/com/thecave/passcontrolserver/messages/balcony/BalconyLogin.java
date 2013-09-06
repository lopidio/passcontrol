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
    int selectedBalconyNumber;
    
    /**
     * Índice do tipo de guichê selecionado do array de tipos
     */
    int selectedBalconyTypeIndex;

    public BalconyLogin(int selectedBalconyNumber, int selectedBalconyTypeIndex) 
    {
        super(MessageActors.BalconyActor, MessageActors.ServerActor);
        this.selectedBalconyNumber = selectedBalconyNumber;
        this.selectedBalconyTypeIndex = selectedBalconyTypeIndex;
    }

    public BalconyLogin(int selectedBalconyNumber, int selectedBalconyTypeIndex, String comment) {
        super(MessageActors.BalconyActor, MessageActors.ServerActor, comment);
        this.selectedBalconyNumber = selectedBalconyNumber;
        this.selectedBalconyTypeIndex = selectedBalconyTypeIndex;
    }
    
    
    
}
