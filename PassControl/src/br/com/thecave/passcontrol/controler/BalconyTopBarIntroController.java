/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.screens.BalconyScreen;
import br.com.thecave.passcontrol.topbar.BalconyTopBarIntro;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyInitRequest;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyInitResponse;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyLogin;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class BalconyTopBarIntroController extends PassControlController 
{
    BalconyTopBarIntro topBarIntro = null;


    @Override
    public void initialize() {
        BalconyInitRequest balconyInitRequest = new BalconyInitRequest();
        BalconyInitResponse balconyInitResponse = (BalconyInitResponse)Main.getInstance().getCommunicationThread().
                    sendMessageAndWaitForResponseOrTimeout(balconyInitRequest, "BalconyInitResponse", 1000);
        
        if (balconyInitResponse != null)
        {
            topBarIntro.setBalconyTypes(balconyInitResponse.getBalconyTypes());
            topBarIntro.setBalconyNumbers(balconyInitResponse.getBalconyNumbers());
            topBarIntro.enableConfirmButton();
        }
    }
    
    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        topBarIntro = (BalconyTopBarIntro)passControlPanel;
    }   

    @Override
    public void performBack() 
    {
    }

    public void confirmButtonPressed(String number, String type) 
    {
        BalconyLogin balconyLogin = new BalconyLogin(number, type);
        ConfirmationResponse response = (ConfirmationResponse)Main.getInstance().getCommunicationThread().
                    sendMessageAndWaitForResponseOrTimeout(balconyLogin, "ConfirmationResponse", 1000);
        
        if (response != null)
        {
            if (response.getStatusOperation())
            {
                topBarIntro.setEnabled(false);
                BalconyScreen balconyScreen = (BalconyScreen)Main.getInstance().getMainFrame().getCurrentPassControlPanel();
                balconyScreen.initialize();
            }
        }
    }

    //Fluxo:
    /**
     * ********** Já no construtor
     * Envio um BalconyInitRequest ao servidor. Apenas para requisitar os números de balcão e os tipos disponíveis
     * Recebo um BalconyInitResponse e inicializo meus dois componentes.
     * 
     * **** Ao apertar OK
     * Ao apertar OK, envio um BalconyLogin com as duas informações desta tela ao servidor.
     * Passo para a próxima tela
     */
    
}
