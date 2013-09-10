/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.component.util;

import br.com.thecave.passcontrol.controler.Main;
import br.com.thecave.passcontrol.screens.MainFrame;
import br.com.thecave.passcontrolserver.communicationThread.ClientCommunicationThread;
import br.com.thecave.passcontrolserver.communicationThread.StatusConnectionListener;
import java.net.URL;
import java.util.Date;

/**
 *
 * @author guilherme
 */
public class PanelConnectionInfo extends javax.swing.JLabel implements StatusConnectionListener
{

    private static URL offlineIcon = null;
    private static URL onlineIcon = null;
    
    private void changePicture(boolean statusConnection) 
    {
        //Altero a cor do ícone de conexão
        if (statusConnection)
        {
            setIcon(new javax.swing.ImageIcon(onlineIcon)); // NOI18N
        }
        else
        {
            setIcon(new javax.swing.ImageIcon(offlineIcon)); // NOI18N        
        }
    }

    @Override
    public void onChangeConnection(boolean connectionStatus) 
    {       
        //Altero a cor do ícone de conexão
        MainFrame mainFrame = Main.getInstance().getMainFrame();
        if (connectionStatus)
        {
            mainFrame.enableControlPanel();
            changePicture(connectionStatus);
        }
        else
        {
            mainFrame.disableControlPanel();            
            changePicture(connectionStatus);
            
            if (Main.getInstance().isLoggedIn())
            {
                //TODO mostrar um Popup informando que o cliente foi deslogado automaticamente
                mainFrame.performLogoutAction();
            }
        }
    }

    /**
     * Creates new form PanelConnectionInfo
     */
    public PanelConnectionInfo() 
    {
       
        initComponents();

        if (offlineIcon == null)
            offlineIcon = getClass().getResource("/resources/offline.png");
        if (onlineIcon == null)
            onlineIcon = getClass().getResource("/resources/online.png");
        changePicture(false);          
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/offline.png"))); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setName("jLabel"); // NOI18N
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                formAncestorRemoved(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
    
        ClientCommunicationThread clientCommunicationThread = Main.getInstance().getCommunicationThread();
        clientCommunicationThread.addStatusConnectionListeners(this);
        changePicture(clientCommunicationThread.getConnectionStatus());
    }//GEN-LAST:event_formAncestorAdded

    private void formAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorRemoved
        // TODO add your handling code here:
        Main.getInstance().getCommunicationThread().removeStatusConnectionListener(this);
    }//GEN-LAST:event_formAncestorRemoved

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        if (Main.getInstance().getCommunicationThread().getConnectionInstant() != null)
        {

            Date now = new Date();

            long diffInMillies = now.getTime() - Main.getInstance().getCommunicationThread().getConnectionInstant().getTime();
            int seconds = (int) (diffInMillies/1000);
            int minutes = seconds/60;
            seconds %= 60;

            setToolTipText("Conectado. Duração da conexão: " + minutes + "m" + seconds + "s");
            changePicture(true);
        }
        else
        {
            changePicture(false);
            setToolTipText("Não conectado");
        }
    }//GEN-LAST:event_formMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
