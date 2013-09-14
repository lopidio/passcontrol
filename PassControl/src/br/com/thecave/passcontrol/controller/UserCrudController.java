package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.screens.admin.UserCrud;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class UserCrudController extends PassControlController
{
    UserCrud screen;

    @Override
    public void setPassControlPanel(JPanel passControlPanel) 
    {
        this.screen = (UserCrud) passControlPanel;
    }  
}
