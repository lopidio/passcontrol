package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controler.LoginTopBarControler;

/**
 *
 * @author Arleudo
 */
public class LoginTopBar extends javax.swing.JPanel 
{
    private LoginTopBarControler loginTopBarControler;

    /**
     * Creates new form TopBar
     */
    public LoginTopBar() 
    {
        initComponents();
        loginTopBarControler = new LoginTopBarControler(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlIcon = new javax.swing.JLabel();
        jlBarra = new javax.swing.JLabel();
        jlName = new javax.swing.JLabel();
        jtfLogin = new javax.swing.JTextField();
        jtfSenha = new javax.swing.JPasswordField();
        jbOk = new javax.swing.JButton();
        jlLogin = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbForgottPassword = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(0, 153, 191));

        jlIcon.setIcon(new javax.swing.ImageIcon("E:\\Developer\\Repositorio\\PassControl\\PassControl\\imgs\\resources\\icon_medium.png")); // NOI18N

        jlBarra.setIcon(new javax.swing.ImageIcon("E:\\Developer\\Repositorio\\PassControl\\PassControl\\imgs\\resources\\barra.png")); // NOI18N

        jlName.setIcon(new javax.swing.ImageIcon("E:\\Developer\\Repositorio\\PassControl\\PassControl\\imgs\\resources\\name_system.png")); // NOI18N

        jbOk.setText("OK");
        jbOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbOkActionPerformed(evt);
            }
        });

        jlLogin.setFont(new java.awt.Font("Square721 BT", 1, 14)); // NOI18N
        jlLogin.setForeground(new java.awt.Color(255, 255, 255));
        jlLogin.setText("Login:");

        jLabel2.setFont(new java.awt.Font("Square721 BT", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Senha:");

        lbForgottPassword.setFont(new java.awt.Font("Square721 BT", 1, 14)); // NOI18N
        lbForgottPassword.setForeground(new java.awt.Color(255, 255, 255));
        lbForgottPassword.setText("Esqueceu a senha?");
        lbForgottPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbForgottPasswordMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jlIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlName, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 692, Short.MAX_VALUE)
                        .addComponent(jlLogin))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(285, 685, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbForgottPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbOk, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jtfLogin)
                        .addComponent(jtfSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlLogin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbOk)
                            .addComponent(lbForgottPassword))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jlName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbForgottPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbForgottPasswordMouseClicked
        loginTopBarControler.resetPassword();
    }//GEN-LAST:event_lbForgottPasswordMouseClicked

    private void jbOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOkActionPerformed
        loginTopBarControler.performLogin();
    }//GEN-LAST:event_jbOkActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbOk;
    private javax.swing.JLabel jlBarra;
    private javax.swing.JLabel jlIcon;
    private javax.swing.JLabel jlLogin;
    private javax.swing.JLabel jlName;
    private javax.swing.JTextField jtfLogin;
    private javax.swing.JPasswordField jtfSenha;
    private javax.swing.JLabel lbForgottPassword;
    // End of variables declaration//GEN-END:variables
}
