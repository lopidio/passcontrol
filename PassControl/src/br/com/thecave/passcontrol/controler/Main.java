package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.communicationThread.ClientCommunicationThread;
import br.com.thecave.passcontrol.db.bean.UserBean;
import br.com.thecave.passcontrol.screens.AdministratorScreen;
import br.com.thecave.passcontrol.screens.ChooseModulesScreen;
import br.com.thecave.passcontrol.screens.LoginScreen;
import br.com.thecave.passcontrol.screens.LoginScreenResetPassword;
import br.com.thecave.passcontrol.screens.MainFrame;
import br.com.thecave.passcontrol.screens.crud.BalconyScreen;
import br.com.thecave.passcontrol.screens.crud.BalconyTypesScreen;
import br.com.thecave.passcontrol.screens.crud.ServiceScreen;
import br.com.thecave.passcontrol.screens.crud.UserScreen;
import br.com.thecave.passcontrol.viewer.PresentationControler;
import javax.swing.JFrame;

public class Main 
{
    /**
     * Singleton instance and method
     */
    private static Main singletonInstance = null;
    public static Main getInstance()
    {
        if (singletonInstance == null)
            singletonInstance = new Main();
        return singletonInstance;
    }
    
    /**
     * Private attributes
     */
    private ClientCommunicationThread communicationThread;
    private UserBean currentUser = null;
    private MainFrame mainFrame;

    private Main()
    {

                mainFrame                = new MainFrame();
                
                communicationThread = new ClientCommunicationThread(
                        "127.0.0.1", 
                        23073);
                mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                mainFrame.setVisible(true);        
    }
    
    
    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                //Creates
                Main app = getInstance();
                new Thread(app.communicationThread).start();
                new Thread(PresentationControler.getInstance()).start();
//                PresentationControler.getInstance().setLabel(getInstance().adminScreen.getLbImage());
            }
        });
    }   

    /**
     * Retorna um bean representando o usuário atual da aplicação
     * @return 
     */
    public UserBean getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserBean currentUser) {
        this.currentUser = currentUser;
    }  
    
    public MainFrame getMainFrame()
    {
        return mainFrame;
    }
    
    /**
     * GETTERS!!
     */
    public LoginScreen getLoginScreen() {
        return null;
    }

    public LoginScreenResetPassword getResetScreen() {
        return null;
    }

    public ChooseModulesScreen getChooseModulesScreen() {
        return null;
    }

    public AdministratorScreen getAdminScreen() {
        return null;
    }

    public ClientCommunicationThread getCommunicationThread() {
        return communicationThread;
    }

    public UserScreen getUserScreen() {
        return null;
    }

    public ServiceScreen getServiceScreen() {
        return null;
    }

    public BalconyScreen getBalconyScreen() {
        return null;
    }

    public BalconyTypesScreen getTypesScreen() {
        return null;
    }
    
    
    
}
