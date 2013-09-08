/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.screens.admin;

import br.com.thecave.passcontrol.controler.AdminController;
import br.com.thecave.passcontrol.screens.PassControlPanel;
import java.util.ArrayList;
import javax.swing.JMenu;

/**
 *
 * @author Arleudo
 */
public class AdminScreen extends PassControlPanel 
{
    AdminController controller = null;
    /**
     * Creates new form AdminScreen
     */
    public AdminScreen() 
    {
        super("Administrador", new AdminController());
        controller = (AdminController) getPanelController();
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

        jmBaseDados = new javax.swing.JMenu();
        jmTipos = new javax.swing.JMenuItem();
        jmGuiche = new javax.swing.JMenuItem();
        jmUser = new javax.swing.JMenuItem();
        jmServicos = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmVoltar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmLogOut = new javax.swing.JMenuItem();
        jmSair = new javax.swing.JMenuItem();
        jmImagem = new javax.swing.JMenu();
        jmAlterImage = new javax.swing.JMenuItem();
        jmApresentacao = new javax.swing.JMenu();
        jmAddImage = new javax.swing.JMenuItem();
        jmListImages = new javax.swing.JMenuItem();
        jmRemoveImages = new javax.swing.JMenuItem();
        jmAlterTime = new javax.swing.JMenuItem();
        jmGerenAuto = new javax.swing.JMenu();
        jmAutomatic = new javax.swing.JMenuItem();
        jmManual = new javax.swing.JMenuItem();
        jlImage = new javax.swing.JLabel();
        ifGuiche = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();

        jmBaseDados.setMnemonic('B');
        jmBaseDados.setText("Administrar      ");

        jmTipos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        jmTipos.setMnemonic('t');
        jmTipos.setText("Tipos de Guichê");
        jmTipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmTiposActionPerformed(evt);
            }
        });
        jmBaseDados.add(jmTipos);

        jmGuiche.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
        jmGuiche.setMnemonic('G');
        jmGuiche.setText("Guichê");
        jmGuiche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmGuicheActionPerformed(evt);
            }
        });
        jmBaseDados.add(jmGuiche);

        jmUser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        jmUser.setMnemonic('U');
        jmUser.setText("Usuário");
        jmUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmUserActionPerformed(evt);
            }
        });
        jmBaseDados.add(jmUser);

        jmServicos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jmServicos.setMnemonic('s');
        jmServicos.setText("Serviços");
        jmServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmServicosActionPerformed(evt);
            }
        });
        jmBaseDados.add(jmServicos);
        jmBaseDados.add(jSeparator1);

        jmVoltar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jmVoltar.setMnemonic('v');
        jmVoltar.setText("Voltar");
        jmVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmVoltarActionPerformed(evt);
            }
        });
        jmBaseDados.add(jmVoltar);
        jmBaseDados.add(jSeparator2);

        jmLogOut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
        jmLogOut.setMnemonic('l');
        jmLogOut.setText("Log Out");
        jmLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmLogOutActionPerformed(evt);
            }
        });
        jmBaseDados.add(jmLogOut);

        jmSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jmSair.setMnemonic('s');
        jmSair.setText("Sair");
        jmSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSairActionPerformed(evt);
            }
        });
        jmBaseDados.add(jmSair);

        jmImagem.setMnemonic('I');
        jmImagem.setText("Imagem           ");

        jmAlterImage.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        jmAlterImage.setMnemonic('I');
        jmAlterImage.setText("Alterar imagem principal");
        jmAlterImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAlterImageActionPerformed(evt);
            }
        });
        jmImagem.add(jmAlterImage);

        jmApresentacao.setMnemonic('A');
        jmApresentacao.setText("Apresentação            ");

        jmAddImage.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmAddImage.setMnemonic('d');
        jmAddImage.setText("Adicionar imagem");
        jmAddImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAddImageActionPerformed(evt);
            }
        });
        jmApresentacao.add(jmAddImage);

        jmListImages.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmListImages.setMnemonic('L');
        jmListImages.setText("Listar imagens");
        jmListImages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmListImagesActionPerformed(evt);
            }
        });
        jmApresentacao.add(jmListImages);

        jmRemoveImages.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmRemoveImages.setMnemonic('R');
        jmRemoveImages.setText("Remover imagem");
        jmRemoveImages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmRemoveImagesActionPerformed(evt);
            }
        });
        jmApresentacao.add(jmRemoveImages);

        jmAlterTime.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmAlterTime.setMnemonic('t');
        jmAlterTime.setText("Alterar tempo da apresentação");
        jmAlterTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAlterTimeActionPerformed(evt);
            }
        });
        jmApresentacao.add(jmAlterTime);

        jmGerenAuto.setMnemonic('G');
        jmGerenAuto.setText("Gerenciamento da Fila      ");

        jmAutomatic.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmAutomatic.setMnemonic('A');
        jmAutomatic.setText("Gerenciamento Automático");
        jmAutomatic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAutomaticActionPerformed(evt);
            }
        });
        jmGerenAuto.add(jmAutomatic);

        jmManual.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmManual.setMnemonic('M');
        jmManual.setText("Gerenciamento Manual");
        jmManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmManualActionPerformed(evt);
            }
        });
        jmGerenAuto.add(jmManual);

        setBackground(new java.awt.Color(255, 255, 255));

        jlImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/admin_big.png"))); // NOI18N
        jlImage.setToolTipText("");

        ifGuiche.setVisible(true);

        jLabel1.setText("Guichê");

        javax.swing.GroupLayout ifGuicheLayout = new javax.swing.GroupLayout(ifGuiche.getContentPane());
        ifGuiche.getContentPane().setLayout(ifGuicheLayout);
        ifGuicheLayout.setHorizontalGroup(
            ifGuicheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ifGuicheLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ifGuicheLayout.setVerticalGroup(
            ifGuicheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ifGuicheLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ifGuiche, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlImage, javax.swing.GroupLayout.DEFAULT_SIZE, 1748, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlImage, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(ifGuiche, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jmGuicheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmGuicheActionPerformed
        openBalconyCrud();
    }//GEN-LAST:event_jmGuicheActionPerformed

    private void jmUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmUserActionPerformed
    }//GEN-LAST:event_jmUserActionPerformed

    private void jmServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmServicosActionPerformed
    }//GEN-LAST:event_jmServicosActionPerformed

    private void jmTiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmTiposActionPerformed
        controller.openBalconyTypeCrud();
    }//GEN-LAST:event_jmTiposActionPerformed

    private void jmVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmVoltarActionPerformed
        controller.performBack();
    }//GEN-LAST:event_jmVoltarActionPerformed

    private void jmLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmLogOutActionPerformed
        controller.performLogout();
    }//GEN-LAST:event_jmLogOutActionPerformed

    private void jmSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jmSairActionPerformed

    private void jmAlterImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAlterImageActionPerformed
    }//GEN-LAST:event_jmAlterImageActionPerformed

    private void jmAddImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAddImageActionPerformed
    }//GEN-LAST:event_jmAddImageActionPerformed

    private void jmListImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmListImagesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmListImagesActionPerformed

    private void jmRemoveImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRemoveImagesActionPerformed
    }//GEN-LAST:event_jmRemoveImagesActionPerformed

    private void jmAlterTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAlterTimeActionPerformed
    }//GEN-LAST:event_jmAlterTimeActionPerformed

    private void jmAutomaticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAutomaticActionPerformed
    }//GEN-LAST:event_jmAutomaticActionPerformed

    private void jmManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmManualActionPerformed
    }//GEN-LAST:event_jmManualActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame ifGuiche;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel jlImage;
    private javax.swing.JMenuItem jmAddImage;
    private javax.swing.JMenuItem jmAlterImage;
    private javax.swing.JMenuItem jmAlterTime;
    private javax.swing.JMenu jmApresentacao;
    private javax.swing.JMenuItem jmAutomatic;
    private javax.swing.JMenu jmBaseDados;
    private javax.swing.JMenu jmGerenAuto;
    private javax.swing.JMenuItem jmGuiche;
    private javax.swing.JMenu jmImagem;
    private javax.swing.JMenuItem jmListImages;
    private javax.swing.JMenuItem jmLogOut;
    private javax.swing.JMenuItem jmManual;
    private javax.swing.JMenuItem jmRemoveImages;
    private javax.swing.JMenuItem jmSair;
    private javax.swing.JMenuItem jmServicos;
    private javax.swing.JMenuItem jmTipos;
    private javax.swing.JMenuItem jmUser;
    private javax.swing.JMenuItem jmVoltar;
    // End of variables declaration//GEN-END:variables

    @Override
    public ArrayList<JMenu> createMenuItems() 
    {
        ArrayList<JMenu> retorno = new ArrayList<>();
        retorno.add(jmGerenAuto);
        retorno.add(jmApresentacao);
        retorno.add(jmImagem);
        retorno.add(jmBaseDados);
        
        return retorno;
    }

    private void openBalconyCrud() 
    {
        controller.openBalconyCrud();
    }
}
