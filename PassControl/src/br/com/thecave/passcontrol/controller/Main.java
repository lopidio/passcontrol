package br.com.thecave.passcontrol.controller;

import br.com.thecave.passcontrolserver.util.PassControlConfigurationSynchronizer;
import br.com.thecave.passcontrol.screens.DefaultScreen;
import br.com.thecave.passcontrolserver.communicationThread.ClientCommunicationThread;
import br.com.thecave.passcontrolserver.db.bean.UserBean;
import br.com.thecave.passcontrol.screens.MainFrame;
import br.com.thecave.passcontrol.topbar.LoginTopBar;
import br.com.thecave.passcontrol.utils.Printer;
import br.com.thecave.passcontrolserver.messages.generic.ChangeActorMessage;
import br.com.thecave.passcontrolserver.messages.generic.ClientLogoff;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

public class Main
{

    /**
     * Singleton instance and method
     */
    private static Main singletonInstance = null;

    public static Main getInstance()
    {
        if ( singletonInstance == null )
        {
            singletonInstance = new Main();
        }
        return singletonInstance;
    }
    /**
     * Private attributes
     */
    private ClientCommunicationThread communicationThread;
    private UserBean currentUser = null;
    private MainFrame mainFrame;
    private MessageActors currentActor = MessageActors.NotIdentified;
    private Printer printer;

    private Main()
    {

        mainFrame = new MainFrame();

        communicationThread = new ClientCommunicationThread();
        //mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        mainFrame.setSize(1351, 719);
        mainFrame.setVisible(true);       
    }

    public static void main( String args[] )
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        for ( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels() )
        {
            if ( "Nimbus".equals(info.getName()) )
            {
                try
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                }
                catch ( ClassNotFoundException ex )
                {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch ( InstantiationException ex )
                {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch ( IllegalAccessException ex )
                {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch ( UnsupportedLookAndFeelException ex )
                {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }

        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                //Creates
                Main main = getInstance();
                new Thread(main.communicationThread).start();
                //Adiciona o carregador de arquivo de configuração
                PassControlConfigurationSynchronizer.getInstance().addClientListeners(main.communicationThread);
                                
                //Inicia os dois panels principais
                main.mainFrame.activatePassControlTopBar(new LoginTopBar());
                main.mainFrame.setEnableNavigatorMenu(false);        
                main.mainFrame.activatePassControlPanel(new DefaultScreen());
            }
        });
    }
    
    /**
     * Retorna um bean representando o usuário atual da aplicação
     *
     * @return
     */
    public UserBean getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser( UserBean currentUser )
    {
        this.currentUser = currentUser;
    }

    public MainFrame getMainFrame()
    {
        return mainFrame;
    }

    public ClientCommunicationThread getCommunicationThread()
    {
        return communicationThread;
    }

    public boolean isLoggedIn()
    {
        return currentUser != null;
    }

    public void logoff()
    {
        communicationThread.addBroadcastToSend(new ClientLogoff());
        setCurrentUser(null);
    }

    public MessageActors getCurrentActor() {
        return currentActor;
    }

    public void setCurrentActors(MessageActors newActor) 
    {
        communicationThread.addBroadcastToSend(new ChangeActorMessage(currentActor, newActor));
        this.currentActor = newActor;
    }
    
    
}
