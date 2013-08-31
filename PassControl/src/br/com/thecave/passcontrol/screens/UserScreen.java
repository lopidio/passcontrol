package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controler.Main;
import br.com.thecave.passcontrol.controler.Usuario;
import br.com.thecave.passcontrol.util.Validation;
import br.com.thecave.passcontrol.util.Validation.ErrorsField;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Classe que será utilizada como template para todas as outras telas que irão
 * envolver os CRUD's, ela irá conter métodos de controle de botões e menus.
 * 
 * @author Antonio Arleudo da costa
 */
public class UserScreen extends javax.swing.JFrame 
{
    //==============================================================================
    /**
     * Contrutor
     */
    public UserScreen() 
    {
        initComponents();
        onVisible();
        resetScreen();
    }
    //==============================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgTipoUser = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jlUser = new javax.swing.JLabel();
        jlLogout = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pnButtons = new javax.swing.JPanel();
        btSave = new javax.swing.JButton();
        btNew = new javax.swing.JButton();
        btClean = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbUser = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfSenha = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        rbAdmin = new javax.swing.JRadioButton();
        rbUser = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        tfLogin = new javax.swing.JTextField();
        elName = new javax.swing.JLabel();
        elLogin = new javax.swing.JLabel();
        elSenha = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmFile = new javax.swing.JMenu();
        jmNew = new javax.swing.JMenuItem();
        jmEdit = new javax.swing.JMenuItem();
        jmSave = new javax.swing.JMenuItem();
        jmDelete = new javax.swing.JMenuItem();
        jmClean = new javax.swing.JMenuItem();
        jmCancel = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmVoltar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro de Usuários");
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

        pnButtons.setBackground(new java.awt.Color(255, 255, 255));

        btSave.setText("Salvar");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        btNew.setText("Novo");
        btNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewActionPerformed(evt);
            }
        });

        btClean.setText("Limpar");
        btClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCleanActionPerformed(evt);
            }
        });

        btDelete.setText("Deletar");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        btCancel.setText("Cancelar");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        btEdit.setText("Editar");
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnButtonsLayout = new javax.swing.GroupLayout(pnButtons);
        pnButtons.setLayout(pnButtonsLayout);
        pnButtonsLayout.setHorizontalGroup(
            pnButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnButtonsLayout.createSequentialGroup()
                        .addComponent(btNew, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnButtonsLayout.createSequentialGroup()
                        .addComponent(btClean, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnButtonsLayout.createSequentialGroup()
                        .addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnButtonsLayout.setVerticalGroup(
            pnButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnButtonsLayout.createSequentialGroup()
                .addGroup(pnButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNew, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(pnButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btClean, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Id do Usuário");

        cbUser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Nome do Usuário");

        tfName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNameFocusLost(evt);
            }
        });

        jLabel3.setText("Senha do Usuário");

        tfSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfSenhaFocusLost(evt);
            }
        });

        jLabel4.setText("Tipo de Usuário");

        bgTipoUser.add(rbAdmin);
        rbAdmin.setText("Administrador");

        bgTipoUser.add(rbUser);
        rbUser.setText("Usuário");

        jLabel5.setText("Login do Usuário");

        tfLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfLoginFocusLost(evt);
            }
        });

        elName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/errovalid.png"))); // NOI18N

        elLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/errovalid.png"))); // NOI18N

        elSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/errovalid.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 945, Short.MAX_VALUE)
                        .addComponent(pnButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(tfSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(elSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfLogin, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(122, 122, 122)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(rbAdmin)
                                            .addComponent(rbUser)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(elName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(elLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbAdmin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addComponent(rbUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(elLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(tfLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(elSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(tfSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(pnButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jmFile.setMnemonic('B');
        jmFile.setText("Arquivo");

        jmNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        jmNew.setMnemonic('n');
        jmNew.setText("Novo");
        jmNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmNewActionPerformed(evt);
            }
        });
        jmFile.add(jmNew);

        jmEdit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        jmEdit.setMnemonic('e');
        jmEdit.setText("Editar");
        jmEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmEditActionPerformed(evt);
            }
        });
        jmFile.add(jmEdit);

        jmSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jmSave.setMnemonic('S');
        jmSave.setText("Salvar");
        jmSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSaveActionPerformed(evt);
            }
        });
        jmFile.add(jmSave);

        jmDelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        jmDelete.setMnemonic('d');
        jmDelete.setText("Deletar");
        jmDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmDeleteActionPerformed(evt);
            }
        });
        jmFile.add(jmDelete);

        jmClean.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
        jmClean.setMnemonic('l');
        jmClean.setText("Limpar");
        jmClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCleanActionPerformed(evt);
            }
        });
        jmFile.add(jmClean);

        jmCancel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jmCancel.setMnemonic('c');
        jmCancel.setText("Cancelar");
        jmCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCancelActionPerformed(evt);
            }
        });
        jmFile.add(jmCancel);
        jmFile.add(jSeparator1);

        jmVoltar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jmVoltar.setMnemonic('v');
        jmVoltar.setText("Voltar");
        jmVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmVoltarActionPerformed(evt);
            }
        });
        jmFile.add(jmVoltar);
        jmFile.add(jSeparator2);

        jmSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        jmSair.setMnemonic('i');
        jmSair.setText("Sair");
        jmSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSairActionPerformed(evt);
            }
        });
        jmFile.add(jmSair);

        jMenuBar1.add(jmFile);

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

    private void jlLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlLogoutMouseClicked
        performLogout();
    }//GEN-LAST:event_jlLogoutMouseClicked

    private void jmNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmNewActionPerformed
        newBean();
    }//GEN-LAST:event_jmNewActionPerformed

    private void jmSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSaveActionPerformed
        saveBean();
    }//GEN-LAST:event_jmSaveActionPerformed

    private void jmDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmDeleteActionPerformed
        deleteBean();
    }//GEN-LAST:event_jmDeleteActionPerformed

    private void jmVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmVoltarActionPerformed
        voltar();
    }//GEN-LAST:event_jmVoltarActionPerformed

    private void jmSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSairActionPerformed
        sair();
    }//GEN-LAST:event_jmSairActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        saveBean();
    }//GEN-LAST:event_btSaveActionPerformed

    private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
        newBean();
    }//GEN-LAST:event_btNewActionPerformed

    private void btCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCleanActionPerformed
        clearScreen();
    }//GEN-LAST:event_btCleanActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        deleteBean();
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        resetScreen();
    }//GEN-LAST:event_btCancelActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        liberateFields();
    }//GEN-LAST:event_btEditActionPerformed

    private void jmCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCancelActionPerformed
        resetScreen();
    }//GEN-LAST:event_jmCancelActionPerformed

    private void jmCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCleanActionPerformed
        clearScreen();
    }//GEN-LAST:event_jmCleanActionPerformed

    private void jmEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmEditActionPerformed
        liberateFields();
    }//GEN-LAST:event_jmEditActionPerformed

    private void tfNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNameFocusLost
        validarNome();
    }//GEN-LAST:event_tfNameFocusLost

    private void tfLoginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfLoginFocusLost
        validarLogin();
    }//GEN-LAST:event_tfLoginFocusLost

    private void tfSenhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfSenhaFocusLost
        validarSenha();
    }//GEN-LAST:event_tfSenhaFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgTipoUser;
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btClean;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btSave;
    private javax.swing.JComboBox cbUser;
    private javax.swing.JLabel elLogin;
    private javax.swing.JLabel elName;
    private javax.swing.JLabel elSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel jlLogout;
    private javax.swing.JLabel jlUser;
    private javax.swing.JMenuItem jmCancel;
    private javax.swing.JMenuItem jmClean;
    private javax.swing.JMenuItem jmDelete;
    private javax.swing.JMenuItem jmEdit;
    private javax.swing.JMenu jmFile;
    private javax.swing.JMenuItem jmNew;
    private javax.swing.JMenuItem jmSair;
    private javax.swing.JMenuItem jmSave;
    private javax.swing.JMenuItem jmVoltar;
    private javax.swing.JPanel pnButtons;
    private javax.swing.JRadioButton rbAdmin;
    private javax.swing.JRadioButton rbUser;
    private javax.swing.JTextField tfLogin;
    private javax.swing.JTextField tfName;
    private javax.swing.JPasswordField tfSenha;
    // End of variables declaration//GEN-END:variables

    //==============================================================================   
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
            resetScreen();
            this.setVisible(false);
            Main.login.setVisible(true);
        }
        else
        {
            // do nothing
        }
    }
    //==============================================================================
    public final void onVisible() 
    {
        this.addComponentListener(new ComponentAdapter() 
        {
            @Override
            public void componentShown(ComponentEvent e) 
            {
                jlUser.setText("Usuário: " + Usuario.getInstance().getName());
            }
        });
    }
    //==============================================================================
    /**
     * Metodo para retornar a tela como de início
     */
    private void resetScreen() 
    {
        // trava os botões
        btCancel.setEnabled(false);
        btClean.setEnabled(false);
        btDelete.setEnabled(false);
        btSave.setEnabled(false);
        
        // trava os menus
        jmCancel.setEnabled(false);
        jmClean.setEnabled(false);
        jmDelete.setEnabled(false);
        jmSave.setEnabled(false);
        
        // habilita os botoes iniciais
        btNew.setEnabled(true);
        btEdit.setEnabled(true);
        
        // habilita os menus iniciais
        jmNew.setEnabled(true);
        jmEdit.setEnabled(true);
        
        // desabilita os campos
        tfName.setEnabled(false);
        tfSenha.setEnabled(false);
        tfLogin.setEnabled(false);
        rbAdmin.setEnabled(false);
        rbUser.setEnabled(false);
        
        // escondendo os erros label
        elLogin.setVisible(false);
        elName.setVisible(false);
        elSenha.setVisible(false);
        
        // limpando qualquer campo editado
        clearScreen();
    }
//==============================================================================
    /**
     * Irá voltar para a tela de administrador
     */
    private void voltar() 
    {
        resetScreen();
        this.setVisible(false);
        Main.adminScreen.setVisible(true);        
    }
    //==============================================================================
    /**
     * Encerra o programa
     */
    private void sair() 
    {
        System.exit(0);
    }   
    //==============================================================================
    /**
     * Desabilita botoes que não estejam relacionados com a inserção de um 
     * novo registro e habilita os que tem algo relacionado
     */
    public void controlButtons() 
    {
        btDelete.setEnabled(false);
        btEdit.setEnabled(false);
        btNew.setEnabled(false);
        btCancel.setEnabled(true);
        btClean.setEnabled(true);
        btSave.setEnabled(true);
        
        jmDelete.setEnabled(false);
        jmEdit.setEnabled(false);
        jmNew.setEnabled(false);
        jmCancel.setEnabled(true);
        jmClean.setEnabled(true);
        jmSave.setEnabled(true);
    }
    //==============================================================================
    /**
     * 
     */
    public void liberateFields()
    {
        cbUser.setSelectedItem(null);
        cbUser.setEnabled(false);
        tfName.setEnabled(true);
        tfSenha.setEnabled(true);
        tfLogin.setEnabled(true);
        rbAdmin.setEnabled(true);
        rbUser.setEnabled(true);        
    }
    //==============================================================================
    /**
     * Irá limpar os campos editados
     */
    public void clearScreen()
    {
        tfName.setText("");
        tfSenha.setText("");
        tfLogin.setText("");
        rbUser.setSelected(true);
    }
    //==============================================================================
    /**
     * 
     */
    public void newBean() 
    {
        // controle de botoes
        controlButtons();
        // libera os campos editaveis
        liberateFields();
    }
    //==============================================================================
    public void deleteBean()
    {
    }
    //==============================================================================
    public void saveBean()
    {
    }
    //==============================================================================
    private boolean validarNome() 
    {
        return Validation.validarCampo(tfName, elName);
    }
    //==============================================================================
    private boolean validarLogin() 
    {
        return Validation.validarCampo(tfLogin, elLogin);
    }
    //==============================================================================
    private boolean validarSenha() 
    {
        return Validation.validarCampo(tfSenha, elSenha);
    }
    //==============================================================================
}

