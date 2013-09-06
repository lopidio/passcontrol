/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.screens.DefaultScreen;
import br.com.thecave.passcontrol.topbar.BalconyTopBarIntro;
import br.com.thecave.passcontrol.topbar.LoginTopBar;
import br.com.thecave.passcontrolserver.messages.generic.PassControlMessage;
import java.net.Socket;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class BalconyTopBarIntroController extends PassControlController 
{
    BalconyTopBarIntro topBarIntro = null;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        topBarIntro = (BalconyTopBarIntro)passControlPanel;
    }   

    public void performlogout() 
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new DefaultScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new LoginTopBar());
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
