package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.communicationThread.ClientCommunicationThread;
import br.com.thecave.passcontrol.db.bean.UserBean;
import br.com.thecave.passcontrol.screens.AdministratorScreen;
import br.com.thecave.passcontrol.screens.ChooseModulesScreen;
import br.com.thecave.passcontrol.screens.LoginScreen;
import br.com.thecave.passcontrol.screens.LoginScreenResetPassword;
import br.com.thecave.passcontrol.screens.MainScreen;
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
    private LoginScreen loginScreen;
    private LoginScreenResetPassword resetScreen;
    private ChooseModulesScreen chooseModulesScreen;
    private AdministratorScreen adminScreen;
    private ClientCommunicationThread communicationThread;
    private UserScreen userScreen;
    private ServiceScreen serviceScreen;
    private BalconyScreen balconyScreen;
    private BalconyTypesScreen typesScreen;
    private UserBean currentUser = null;
    private MainScreen mainScreen;

    private Main()
    {
                loginScreen               = new LoginScreen();
                resetScreen               = new LoginScreenResetPassword();
                chooseModulesScreen       = new ChooseModulesScreen();
                adminScreen               = new AdministratorScreen();
                userScreen                = new UserScreen();
                serviceScreen             = new ServiceScreen();
                balconyScreen             = new BalconyScreen();
                typesScreen               = new BalconyTypesScreen();
                mainScreen                = new MainScreen();
                
                communicationThread = new ClientCommunicationThread(
                        "127.0.0.1", 
                        23073);
                
                loginScreen.setExtendedState(loginScreen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                resetScreen.setExtendedState(resetScreen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                chooseModulesScreen.setExtendedState(chooseModulesScreen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                adminScreen.setExtendedState(adminScreen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                userScreen.setExtendedState(userScreen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                serviceScreen.setExtendedState(serviceScreen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                balconyScreen.setExtendedState(balconyScreen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                typesScreen.setExtendedState(typesScreen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                mainScreen.setExtendedState(typesScreen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                loginScreen.setVisible(true);        
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
                PresentationControler.getInstance().setLabel(getInstance().adminScreen.getLbImage());
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
    
    /**
     * GETTERS!!
     */
    public LoginScreen getLoginScreen() {
        return loginScreen;
    }

    public LoginScreenResetPassword getResetScreen() {
        return resetScreen;
    }

    public ChooseModulesScreen getChooseModulesScreen() {
        return chooseModulesScreen;
    }

    public AdministratorScreen getAdminScreen() {
        return adminScreen;
    }

    public ClientCommunicationThread getCommunicationThread() {
        return communicationThread;
    }

    public UserScreen getUserScreen() {
        return userScreen;
    }

    public ServiceScreen getServiceScreen() {
        return serviceScreen;
    }

    public BalconyScreen getBalconyScreen() {
        return balconyScreen;
    }

    public BalconyTypesScreen getTypesScreen() {
        return typesScreen;
    }
    
    
    
}
