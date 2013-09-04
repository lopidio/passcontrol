/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.topbar.LoginTopBar;

/**
 *
 * @author Arleudo
 */
public class ResetTopBarControler extends PassControlController
{

    public void performReset() 
    {
        //TODO: Mandar um email e tal
        
        Main.getInstance().getMainFrame().activatePassControlTopBar(new LoginTopBar());
    } 

    public void backToLoginTopbar() 
    {
        Main.getInstance().getMainFrame().activatePassControlTopBar(new LoginTopBar());
    }
    
}
