package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.screens.MainTopBar;

/**
 *
 * @author Arleudo
 */
public class MainTopBarController 
{
    MainTopBar mainTopBar;
    
    public MainTopBarController(MainTopBar mainTopBar)
    {
        this.mainTopBar = mainTopBar;
    }
    
    public void performlogout() 
    {
        //TODO: implement
        //Main.getInstance().getMainScreen().setTopBar(new LoginTopBar());
    }
}
