/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controler.Main;
import br.com.thecave.passcontrol.controler.Usuario;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author arleudo
 */
public class ChooseModulesScreen extends javax.swing.JFrame {

    /**
     * Creates new form LoginScreen
     */
    public ChooseModulesScreen() 
    {
        initComponents();
        this.addComponentListener(new ComponentAdapter() 
        {
            @Override
            public void componentShown(ComponentEvent e) 
            {
                jlUser.setText("Usuário: " + Usuario.getInstance().getName());
            }
        });        
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
        jlUser = new javax.swing.JLabel();
        jlLogout = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jbAdmin = new javax.swing.JButton();
        jbBalcony = new javax.swing.JButton();
        jbViewer = new javax.swing.JButton();
        jbInsert = new javax.swing.JButton();
        jbRemove = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmIniciar = new javax.swing.JMenu();
        jmAdmin = new javax.swing.JMenuItem();
        jmBalcony = new javax.swing.JMenuItem();
        jmViewer = new javax.swing.JMenuItem();
        jmInsert = new javax.swing.JMenuItem();
        jmRemove = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle de Senhas");
        setBackground(new java.awt.Color(255, 255, 255));
        setName("frmScreenLogin"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(187, 215, 248));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 136, 219), 3));
        jPanel1.setAutoscrolls(true);

        jlUser.setText("User: ");

        jlLogout.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jlLogout.setForeground(new java.awt.Color(0, 0, 255));
        jlLogout.setText("Log Out");
        jlLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlLogoutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlLogout, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlUser, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jlUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlLogout)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));

        jbAdmin.setText("Administrador");
        jbAdmin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 136, 219), 5, true));
        jbAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAdminActionPerformed(evt);
            }
        });

        jbBalcony.setText("Balcony");
        jbBalcony.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 136, 219), 5, true));
        jbBalcony.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBalconyActionPerformed(evt);
            }
        });

        jbViewer.setText("Visualizador");
        jbViewer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 136, 219), 5, true));
        jbViewer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbViewerActionPerformed(evt);
            }
        });

        jbInsert.setText("Inserir");
        jbInsert.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 136, 219), 5, true));
        jbInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbInsertActionPerformed(evt);
            }
        });

        jbRemove.setText("Remover");
        jbRemove.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 136, 219), 5, true));
        jbRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbBalcony, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbViewer, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(386, 386, 386)
                        .addComponent(jbInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(197, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBalcony, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbViewer, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );

        jmIniciar.setText("Iniciar");

        jmAdmin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jmAdmin.setMnemonic('A');
        jmAdmin.setText("Administrador");
        jmAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAdminActionPerformed(evt);
            }
        });
        jmIniciar.add(jmAdmin);

        jmBalcony.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        jmBalcony.setMnemonic('B');
        jmBalcony.setText("Balcony");
        jmBalcony.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmBalconyActionPerformed(evt);
            }
        });
        jmIniciar.add(jmBalcony);

        jmViewer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jmViewer.setMnemonic('V');
        jmViewer.setText("Visualizador");
        jmViewer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmViewerActionPerformed(evt);
            }
        });
        jmIniciar.add(jmViewer);

        jmInsert.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        jmInsert.setMnemonic('I');
        jmInsert.setText("Inserir");
        jmInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmInsertActionPerformed(evt);
            }
        });
        jmIniciar.add(jmInsert);

        jmRemove.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        jmRemove.setMnemonic('R');
        jmRemove.setText("Remover");
        jmRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRemoveActionPerformed(evt);
            }
        });
        jmIniciar.add(jmRemove);
        jmIniciar.add(jSeparator1);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setMnemonic('L');
        jMenuItem1.setText("Log Out");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jmIniciar.add(jMenuItem1);
        jmIniciar.add(jSeparator2);

        jmExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jmExit.setMnemonic('S');
        jmExit.setText("Sair");
        jmExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmExitActionPerformed(evt);
            }
        });
        jmIniciar.add(jmExit);

        jMenuBar1.add(jmIniciar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jmExitActionPerformed

    private void jbAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAdminActionPerformed
        openAdministrator();
    }//GEN-LAST:event_jbAdminActionPerformed

    private void jmAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAdminActionPerformed
        openAdministrator();
    }//GEN-LAST:event_jmAdminActionPerformed

    private void jbBalconyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBalconyActionPerformed
        openBalcony();
    }//GEN-LAST:event_jbBalconyActionPerformed

    private void jmBalconyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmBalconyActionPerformed
        openBalcony();
    }//GEN-LAST:event_jmBalconyActionPerformed

    private void jbViewerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbViewerActionPerformed
        openViewer();
    }//GEN-LAST:event_jbViewerActionPerformed

    private void jmViewerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmViewerActionPerformed
       openViewer();
    }//GEN-LAST:event_jmViewerActionPerformed

    private void jbInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInsertActionPerformed
        openQueueInsert();
    }//GEN-LAST:event_jbInsertActionPerformed

    private void jbRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoveActionPerformed
       openQueueRemove();
    }//GEN-LAST:event_jbRemoveActionPerformed

    private void jmInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmInsertActionPerformed
        openQueueInsert();
    }//GEN-LAST:event_jmInsertActionPerformed

    private void jmRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRemoveActionPerformed
        openQueueRemove();
    }//GEN-LAST:event_jmRemoveActionPerformed

    private void jlLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlLogoutMouseClicked
        performLogout();
    }//GEN-LAST:event_jlLogoutMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        performLogout();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JButton jbAdmin;
    private javax.swing.JButton jbBalcony;
    private javax.swing.JButton jbInsert;
    private javax.swing.JButton jbRemove;
    private javax.swing.JButton jbViewer;
    private javax.swing.JLabel jlLogout;
    private javax.swing.JLabel jlUser;
    private javax.swing.JMenuItem jmAdmin;
    private javax.swing.JMenuItem jmBalcony;
    private javax.swing.JMenuItem jmExit;
    private javax.swing.JMenu jmIniciar;
    private javax.swing.JMenuItem jmInsert;
    private javax.swing.JMenuItem jmRemove;
    private javax.swing.JMenuItem jmViewer;
    // End of variables declaration//GEN-END:variables

    private void openAdministrator()
    {
        this.setVisible(false);
        Main.adminScreen.setVisible(true);
    }
    
    private void openBalcony()
    {
        //TODO: implements
        JOptionPane.showMessageDialog(null, "Balcony");
    }
    
    private void openViewer()
    {
        //TODO: implements
        JOptionPane.showMessageDialog(null, "Viewer");
    }
    
    private void openQueueInsert()
    {
        //TODO: implements
        JOptionPane.showMessageDialog(null, "QueueInsert");
    }
    
    private void openQueueRemove()
    {
        //TODO: implements
        JOptionPane.showMessageDialog(null, "QueueRemove");
    }
    /**
     * Exibe uma caixa de seleção pra confirmar o logout do usuário
     */
    private void performLogout()
    {
        int result = JOptionPane.showConfirmDialog(null, 
                    "Você deseja realmente sair? " 
                       ,"Message", JOptionPane.WARNING_MESSAGE
                    , JOptionPane.OK_CANCEL_OPTION);
        
        if( JOptionPane.CANCEL_OPTION == result )
        {
            // do nothing
        }
        else if( JOptionPane.OK_OPTION == result)
        {
            this.setVisible(false);
            Main.login.setVisible(true);
        }
        else
        {
            // do nothing
        }
    }  
}

