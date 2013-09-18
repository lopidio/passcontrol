package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrol.topbar.LoginTopBar;
import br.com.thecave.passcontrol.topbar.ResetTopBar;
import javax.swing.JPanel;

/**
 *
 * @author Arleudo
 */
public class ResetTopBarControler extends PassControlController
{

    ResetTopBar resetTopBar = null;

    @Override
    public void setPassControlPanel( JPanel passControlPanel )
    {
        resetTopBar = (ResetTopBar) passControlPanel;
    }

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
