/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controler.Main;
import br.com.thecave.passcontrolserver.communicationThread.StatusConnectionListener;

/**
 *
 * @author guilherme
 */
public class PanelConnectionInfo extends javax.swing.JLabel implements StatusConnectionListener{

    @Override
    public void onChangeConnection(boolean connectionStatus) 
    {
        //Altero a cor do ícone de conexão
        if (connectionStatus)
        {
            Main.getInstance().getMainFrame().enableControlPanel();
            setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/online.png"))); // NOI18N
        }
        else
        {
            Main.getInstance().getMainFrame().disableControlPanel();            
            setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/offline.png"))); // NOI18N        
        }
    }

    /**
     * Creates new form PanelConnectionInfo
     */
    public PanelConnectionInfo() {
        initComponents();
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
        setMaximumSize(new java.awt.Dimension(22, 22));
        setMinimumSize(new java.awt.Dimension(22, 22));
        setName("jLabel"); // NOI18N
        setPreferredSize(new java.awt.Dimension(22, 22));
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
        Main.getInstance().getCommunicationThread().addStatusConnectionListeners(this);
    }//GEN-LAST:event_formAncestorAdded

    private void formAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorRemoved
        // TODO add your handling code here:
        Main.getInstance().getCommunicationThread().removeStatusConnectionListener(this);
    }//GEN-LAST:event_formAncestorRemoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}