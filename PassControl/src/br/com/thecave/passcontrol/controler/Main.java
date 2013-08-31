package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.communicationThread.ClientCommunicationThread;
import br.com.thecave.passcontrol.messages.MessageActors;
import br.com.thecave.passcontrol.screens.AdministratorScreen;
import br.com.thecave.passcontrol.screens.ChooseModulesScreen;
import br.com.thecave.passcontrol.screens.LoginScreen;
import br.com.thecave.passcontrol.screens.LoginScreenResetPassword;
import br.com.thecave.passcontrol.screens.UserScreen;
import javax.swing.JFrame;

public class Main 
{
    public static LoginScreen login;
    public static LoginScreenResetPassword reset;
    public static ChooseModulesScreen chooseModules;
    public static AdministratorScreen adminScreen;
    public static ClientCommunicationThread communicationThread;
    public static UserScreen userScreen;
        
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
                login           = new LoginScreen();
                reset           = new LoginScreenResetPassword();
                chooseModules   = new ChooseModulesScreen();
                adminScreen     = new AdministratorScreen();
                userScreen      = new UserScreen();
                
                communicationThread = new ClientCommunicationThread(
                        MessageActors.NotIdentified, 
                        "127.0.0.1", 
                        23073);
                new Thread(communicationThread).start();
                
                login.setExtendedState(login.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                reset.setExtendedState(reset.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                chooseModules.setExtendedState(chooseModules.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                adminScreen.setExtendedState(adminScreen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                userScreen.setExtendedState(userScreen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                login.setVisible(true);
            }
        });
    }    
}
