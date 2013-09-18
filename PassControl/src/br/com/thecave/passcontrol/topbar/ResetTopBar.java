package br.com.thecave.passcontrol.topbar;

import br.com.thecave.passcontrol.controller.ResetTopBarControler;

/**
 *
 * @author Arleudo
 */
public class ResetTopBar extends PassControlTopBar
{

    private ResetTopBarControler resetTopBarControler;

    /**
     * Creates new form TopBar
     */
    public ResetTopBar()
    {
        super(new ResetTopBarControler());
        initComponents();
        resetTopBarControler = (ResetTopBarControler) getPanelController();
        addPanelConnectionInfo();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlIcon = new javax.swing.JLabel();
        jlBarra = new javax.swing.JLabel();
        jlName = new javax.swing.JLabel();
        jtfLogin = new javax.swing.JTextField();
        jbOk = new javax.swing.JButton();
        jlLogin = new javax.swing.JLabel();
        jbCancel = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 193));
        setPreferredSize(new java.awt.Dimension(1376, 128));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_medium.png"))); // NOI18N
        add(jlIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 6, 106, 116));

        jlBarra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/barra.png"))); // NOI18N
        add(jlBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 6, -1, 116));

        jlName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/name_system.png"))); // NOI18N
        add(jlName, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 6, 222, 116));
        add(jtfLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1121, 23, 236, -1));

        jbOk.setText("OK");
        jbOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbOkActionPerformed(evt);
            }
        });
        add(jbOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(1257, 69, 100, -1));

        jlLogin.setFont(new java.awt.Font("Square721 BT", 1, 14)); // NOI18N
        jlLogin.setForeground(new java.awt.Color(255, 255, 255));
        jlLogin.setText("Login:");
        add(jlLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 29, -1, -1));

        jbCancel.setText("Cancelar");
        jbCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelActionPerformed(evt);
            }
        });
        add(jbCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1121, 69, 100, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jbOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOkActionPerformed
        resetTopBarControler.performReset();
    }//GEN-LAST:event_jbOkActionPerformed

    private void jbCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelActionPerformed
        resetTopBarControler.backToLoginTopbar();
    }//GEN-LAST:event_jbCancelActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbOk;
    private javax.swing.JLabel jlBarra;
    private javax.swing.JLabel jlIcon;
    private javax.swing.JLabel jlLogin;
    private javax.swing.JLabel jlName;
    private javax.swing.JTextField jtfLogin;
    // End of variables declaration//GEN-END:variables
}
