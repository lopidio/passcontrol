package br.com.thecave.passcontrol.topbar;

import br.com.thecave.passcontrol.controler.LoginTopBarController;
import br.com.thecave.passcontrol.controler.Main;

/**
 *
 * @author Arleudo
 */
public class LoginTopBar extends PassControlTopBar
{
    private LoginTopBarController loginTopBarControler;  

    /**
     * Creates new form TopBar
     */
    public LoginTopBar() 
    {        
        super(new LoginTopBarController());
        loginTopBarControler = (LoginTopBarController) getPanelController();
        initComponents();
    }
    
    public void incorrectUser()
    {
        jtfLogin.setText("");
        jtfSenha.setText("");
        verifyOkButton();
    }
    
    public String getUserName()
    {
        return jtfLogin.getText();
    }

    public String getUserPassword()
    {
        return new String(jtfSenha.getPassword());
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
        jtfSenha = new javax.swing.JPasswordField();
        jbOk = new javax.swing.JButton();
        jlLogin = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbForgottPassword = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 153, 191));
        setForeground(new java.awt.Color(0, 153, 191));
        setMaximumSize(new java.awt.Dimension(1376, 128));
        setPreferredSize(new java.awt.Dimension(1376, 128));

        jlIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_medium.png"))); // NOI18N

        jlBarra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/barra.png"))); // NOI18N

        jlName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/name_system.png"))); // NOI18N

        jtfLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfLoginKeyTyped(evt);
            }
        });

        jtfSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfSenhaKeyTyped(evt);
            }
        });

        jbOk.setText("OK");
        jbOk.setEnabled(false);
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/errovalid.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jlIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlName, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 691, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlLogin)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lbForgottPassword)
                        .addGap(18, 18, 18)
                        .addComponent(jbOk, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfLogin)
                            .addComponent(jtfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlBarra, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(jlIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlLogin)
                            .addComponent(jLabel1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbOk)
                            .addComponent(lbForgottPassword))))
                .addGap(6, 6, 6))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbForgottPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbForgottPasswordMouseClicked
        loginTopBarControler.resetPassword();
    }//GEN-LAST:event_lbForgottPasswordMouseClicked

    private void jbOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOkActionPerformed
        loginTopBarControler.performLogin();
    }//GEN-LAST:event_jbOkActionPerformed

    private void jtfLoginKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfLoginKeyTyped
        verifyOkButton();
    }//GEN-LAST:event_jtfLoginKeyTyped

    private void jtfSenhaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfSenhaKeyTyped
        verifyOkButton();
    }//GEN-LAST:event_jtfSenhaKeyTyped

    private void verifyOkButton()
    {
        System.err.println(getUserName() + " ; " + getUserPassword());
        if (getUserName().length() >= 4 && getUserPassword().length() >= 5)
        {
            jbOk.setEnabled(true);
        }
        else
        {
            jbOk.setEnabled(false);            
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
