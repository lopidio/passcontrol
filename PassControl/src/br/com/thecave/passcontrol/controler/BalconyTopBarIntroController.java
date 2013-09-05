/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.screens.DefaultScreen;
import br.com.thecave.passcontrol.topbar.BalconyTopBarIntro;
import br.com.thecave.passcontrol.topbar.LoginTopBar;
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
}
