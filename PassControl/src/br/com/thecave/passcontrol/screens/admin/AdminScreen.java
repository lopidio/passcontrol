package br.com.thecave.passcontrol.screens.admin;

import br.com.thecave.passcontrol.controller.AdminController;
import br.com.thecave.passcontrol.controller.Main;
import br.com.thecave.passcontrol.screens.PassControlPanel;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorAddSlideImage;
import br.com.thecave.passcontrolserver.messages.administrator.AdministratorRemoveSlideImage;
import br.com.thecave.passcontrolserver.messages.generic.ConfirmationResponse;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private void initComponents()
    {

        jmBaseDados = new javax.swing.JMenu();
        jmGuiche = new javax.swing.JMenuItem();
        jmUser = new javax.swing.JMenuItem();
        jmServicos = new javax.swing.JMenuItem();
        jmImagem = new javax.swing.JMenu();
        jmAlterImage = new javax.swing.JMenuItem();
        jmApresentacao = new javax.swing.JMenu();
        jmAddImage = new javax.swing.JMenuItem();
        jmRemoveImages = new javax.swing.JMenuItem();
        jmAlterTime = new javax.swing.JMenuItem();
        jmGerenAuto = new javax.swing.JMenu();
        jmAutomatic = new javax.swing.JMenuItem();
        jmManual = new javax.swing.JMenuItem();
        jlImage = new javax.swing.JLabel();
        ifGuiche = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        jpBarraLateral = new javax.swing.JPanel();
        btGuiche = new javax.swing.JButton();
        btServicos = new javax.swing.JButton();
        btUsuario = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jmBaseDados.setMnemonic('B');
        jmBaseDados.setText("Administrar      ");
        jmBaseDados.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N

        jmGuiche.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
        jmGuiche.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmGuiche.setMnemonic('G');
        jmGuiche.setText("Guichê");
        jmGuiche.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmGuicheActionPerformed(evt);
            }
        });
        jmBaseDados.add(jmGuiche);

        jmUser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        jmUser.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmUser.setMnemonic('U');
        jmUser.setText("Usuário");
        jmUser.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmUserActionPerformed(evt);
            }
        });
        jmBaseDados.add(jmUser);

        jmServicos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jmServicos.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmServicos.setMnemonic('s');
        jmServicos.setText("Serviços");
        jmServicos.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmServicosActionPerformed(evt);
            }
        });
        jmBaseDados.add(jmServicos);

        jmImagem.setMnemonic('I');
        jmImagem.setText("Imagem           ");
        jmImagem.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N

        jmAlterImage.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        jmAlterImage.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmAlterImage.setMnemonic('I');
        jmAlterImage.setText("Alterar imagem principal");
        jmAlterImage.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmAlterImageActionPerformed(evt);
            }
        });
        jmImagem.add(jmAlterImage);

        jmApresentacao.setMnemonic('A');
        jmApresentacao.setText("Apresentação            ");
        jmApresentacao.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N

        jmAddImage.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmAddImage.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmAddImage.setMnemonic('d');
        jmAddImage.setText("Adicionar imagem");
        jmAddImage.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmAddImageActionPerformed(evt);
            }
        });
        jmApresentacao.add(jmAddImage);

        jmRemoveImages.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmRemoveImages.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmRemoveImages.setMnemonic('R');
        jmRemoveImages.setText("Remover imagem");
        jmRemoveImages.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmRemoveImagesActionPerformed(evt);
            }
        });
        jmApresentacao.add(jmRemoveImages);

        jmAlterTime.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmAlterTime.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmAlterTime.setMnemonic('t');
        jmAlterTime.setText("Alterar tempo da apresentação");
        jmAlterTime.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmAlterTimeActionPerformed(evt);
            }
        });
        jmApresentacao.add(jmAlterTime);

        jmGerenAuto.setMnemonic('G');
        jmGerenAuto.setText("Gerenciamento da Fila      ");
        jmGerenAuto.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N

        jmAutomatic.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmAutomatic.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmAutomatic.setMnemonic('A');
        jmAutomatic.setText("Gerenciamento Automático");
        jmAutomatic.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmAutomaticActionPerformed(evt);
            }
        });
        jmGerenAuto.add(jmAutomatic);

        jmManual.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jmManual.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmManual.setMnemonic('M');
        jmManual.setText("Gerenciamento Manual");
        jmManual.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmManualActionPerformed(evt);
            }
        });
        jmGerenAuto.add(jmManual);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/admin_button_1.png"))); // NOI18N
        jlImage.setToolTipText("");
        add(jlImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

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

        add(ifGuiche, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));

        jpBarraLateral.setPreferredSize(new java.awt.Dimension(6, 0));

        javax.swing.GroupLayout jpBarraLateralLayout = new javax.swing.GroupLayout(jpBarraLateral);
        jpBarraLateral.setLayout(jpBarraLateralLayout);
        jpBarraLateralLayout.setHorizontalGroup(
            jpBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );
        jpBarraLateralLayout.setVerticalGroup(
            jpBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );

        add(jpBarraLateral, new org.netbeans.lib.awtextra.AbsoluteConstraints(267, 11, -1, 290));

        btGuiche.setBackground(new java.awt.Color(45, 123, 142));
        btGuiche.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        btGuiche.setText("Guichê");
        btGuiche.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btGuicheActionPerformed(evt);
            }
        });
        add(btGuiche, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 176, -1));

        btServicos.setBackground(new java.awt.Color(45, 123, 142));
        btServicos.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        btServicos.setText("Serviços");
        btServicos.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btServicosActionPerformed(evt);
            }
        });
        add(btServicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 176, -1));

        btUsuario.setBackground(new java.awt.Color(45, 123, 142));
        btUsuario.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        btUsuario.setText("Usuários");
        btUsuario.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btUsuarioActionPerformed(evt);
            }
        });
        add(btUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 176, -1));

        jLabel2.setBackground(new java.awt.Color(150, 150, 150));
        jLabel2.setFont(new java.awt.Font("Square721 BT", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(200, 200, 200));
        jLabel2.setText("ADMINISTRADOR");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jmGuicheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmGuicheActionPerformed
        openBalconyCrud();
    }//GEN-LAST:event_jmGuicheActionPerformed

    private void jmUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmUserActionPerformed
        controller.openUserCrud();
    }//GEN-LAST:event_jmUserActionPerformed

    private void jmServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmServicosActionPerformed
        controller.openServiceCrud();
    }//GEN-LAST:event_jmServicosActionPerformed

    private void jmAlterImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAlterImageActionPerformed
       if(controller.alterImage())
       {
           JOptionPane.showMessageDialog(null, "Imagem alterada com sucesso!");
       }
       else
       {
           JOptionPane.showMessageDialog(null, "Imagem não alterada!");
       }
    }//GEN-LAST:event_jmAlterImageActionPerformed

    private void jmAddImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAddImageActionPerformed

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if ( returnVal == JFileChooser.APPROVE_OPTION )
        {
            Image img = Toolkit.getDefaultToolkit().getImage(chooser.getSelectedFile().getName());
            AdministratorAddSlideImage slideImage = new AdministratorAddSlideImage(img , 
                    chooser.getSelectedFile().getName());
            ConfirmationResponse response = Main.getInstance().getCommunicationThread().
                    sendMessageToServerAndWaitForResponseOrTimeout(slideImage, ConfirmationResponse.class, 2000);
            
            if(response != null)
            {
                JOptionPane.showMessageDialog(null, "Imagem adicionada com sucesso!");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Conexão perdida com o servidor!");
            }
        }        
    }//GEN-LAST:event_jmAddImageActionPerformed

    private void jmRemoveImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRemoveImagesActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if ( returnVal == JFileChooser.APPROVE_OPTION )
        {
            Image img = Toolkit.getDefaultToolkit().getImage(chooser.getSelectedFile().getName());
            AdministratorRemoveSlideImage slideImage = new AdministratorRemoveSlideImage(img , 
                    chooser.getSelectedFile().getName());
            ConfirmationResponse response = Main.getInstance().getCommunicationThread().
                    sendMessageToServerAndWaitForResponseOrTimeout(slideImage, ConfirmationResponse.class, 2000);
            
            if(response != null)
            {
                JOptionPane.showMessageDialog(null, "Imagem removida com sucesso!");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Conexão perdida com o servidor!");
            }
        }
    }//GEN-LAST:event_jmRemoveImagesActionPerformed

    private void jmAlterTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAlterTimeActionPerformed
    }//GEN-LAST:event_jmAlterTimeActionPerformed

    private void jmAutomaticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAutomaticActionPerformed
    }//GEN-LAST:event_jmAutomaticActionPerformed

    private void jmManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmManualActionPerformed
    }//GEN-LAST:event_jmManualActionPerformed

    private void btUsuarioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btUsuarioActionPerformed
    {//GEN-HEADEREND:event_btUsuarioActionPerformed
        controller.openUserCrud();
    }//GEN-LAST:event_btUsuarioActionPerformed

    private void btServicosActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btServicosActionPerformed
    {//GEN-HEADEREND:event_btServicosActionPerformed
        controller.openServiceCrud();
    }//GEN-LAST:event_btServicosActionPerformed

    private void btGuicheActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btGuicheActionPerformed
    {//GEN-HEADEREND:event_btGuicheActionPerformed
        controller.openBalconyCrud();
    }//GEN-LAST:event_btGuicheActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGuiche;
    private javax.swing.JButton btServicos;
    private javax.swing.JButton btUsuario;
    private javax.swing.JInternalFrame ifGuiche;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JMenuItem jmManual;
    private javax.swing.JMenuItem jmRemoveImages;
    private javax.swing.JMenuItem jmServicos;
    private javax.swing.JMenuItem jmUser;
    private javax.swing.JPanel jpBarraLateral;
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
