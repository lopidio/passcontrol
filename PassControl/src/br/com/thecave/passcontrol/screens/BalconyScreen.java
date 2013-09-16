/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.component.util.QueueElementInfoBig;
import br.com.thecave.passcontrol.controller.BalconyController;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyCallNextClientResponse;

/**
 *
 * @author Arleudo
 */
public class BalconyScreen extends PassControlPanel 
{
    
    BalconyController controller = null;

    /**
     * Creates new form AdminScreen
     */
    public BalconyScreen() 
    {
        super("Guichê", new BalconyController());
        controller = (BalconyController) getPanelController();
        initComponents();
        
        jbGuicheLivre.setVisible(false);
        jbRepetir.setVisible(false);
        jpSenha.setVisible(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbGuicheLivre = new javax.swing.JButton();
        jbRepetir = new javax.swing.JButton();
        jpSenha = new javax.swing.JPanel();
        jlImage = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        jbGuicheLivre.setFont(new java.awt.Font("Square721 BT", 0, 18)); // NOI18N
        jbGuicheLivre.setText("Guichê Livre");
        jbGuicheLivre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuicheLivreActionPerformed(evt);
            }
        });
        add(jbGuicheLivre);
        jbGuicheLivre.setBounds(950, 410, 260, 120);

        jbRepetir.setFont(new java.awt.Font("Square721 BT", 0, 18)); // NOI18N
        jbRepetir.setText("Repetir Chamada");
        jbRepetir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRepetirActionPerformed(evt);
            }
        });
        add(jbRepetir);
        jbRepetir.setBounds(150, 410, 260, 120);
        add(jpSenha);
        jpSenha.setBounds(510, 100, 350, 210);

        jlImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/balcony_big.png"))); // NOI18N
        jlImage.setToolTipText("");
        add(jlImage);
        jlImage.setBounds(10, 11, 1347, 778);
    }// </editor-fold>//GEN-END:initComponents

    private void jbGuicheLivreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuicheLivreActionPerformed
        controller.callNextClient();
        jbRepetir.setEnabled(true);
    }//GEN-LAST:event_jbGuicheLivreActionPerformed

    private void jbRepetirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRepetirActionPerformed
        controller.recallNextClient();
    }//GEN-LAST:event_jbRepetirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbGuicheLivre;
    private javax.swing.JButton jbRepetir;
    private javax.swing.JLabel jlImage;
    private javax.swing.JPanel jpSenha;
    // End of variables declaration//GEN-END:variables

    
    public void initialize(BalconyBean balconyBean)
    {
        jbRepetir.setVisible(true);
        jbRepetir.setEnabled(false);
        jbGuicheLivre.setVisible(true);        
        controller.setBalconyBean(balconyBean);
    }

    public void showPanelQueueInfo(BalconyCallNextClientResponse response) 
    {
        QueueElementInfoBig queueElementInfoBig = new QueueElementInfoBig(response.getClientName(), response.getServiceType(), response.getQueuesManagerBean().getPassNumber());
        jpSenha.add(queueElementInfoBig);
        jpSenha.setVisible(true);
    }
}
