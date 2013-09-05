package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrolserver.communicationThread.ClientCommunicationThread;
import br.com.thecave.passcontrolserver.db.bean.UserBean;
import br.com.thecave.passcontrol.screens.MainFrame;
import br.com.thecave.passcontrol.viewer.PresentationControler;
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
                        "192.168.0.194", 
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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    try {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedLookAndFeelException ex) {
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
    
    public ClientCommunicationThread getCommunicationThread() {
        return communicationThread;
    }
}
