package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controler.Main;
import br.com.thecave.passcontrol.viewer.PresentationControler;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Antonio Arleudo da costa
 */
public final class AdministratorScreen extends javax.swing.JFrame {

    /**
     * Creates new form AdministratorScreen
     */
    public AdministratorScreen() 
    {
        initComponents();
        setImageCenter();
        this.addComponentListener(new ComponentAdapter() 
        {
            @Override
            public void componentShown(ComponentEvent e) 
            {
                jlUser.setText("Usuário: " + Main.getInstance().getCurrentUser().getName());
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
        lbImage = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmBaseDados = new javax.swing.JMenu();
        jmGuiche = new javax.swing.JMenuItem();
        jmUser = new javax.swing.JMenuItem();
        jmServicos = new javax.swing.JMenuItem();
        jmTipos = new javax.swing.JMenuItem();
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

        lbImage.setBackground(new java.awt.Color(0, 0, 0));
        lbImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lbImage, javax.swing.GroupLayout.DEFAULT_SIZE, 1251, Short.MAX_VALUE)
                .addGap(48, 48, 48))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(lbImage, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addGap(48, 48, 48))
        );

        jmBaseDados.setMnemonic('B');
        jmBaseDados.setText("Base de Dados          ");

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

        jmTipos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        jmTipos.setMnemonic('t');
        jmTipos.setText("Tipos de Guichê");
        jmTipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmTiposActionPerformed(evt);
            }
        });
        jmBaseDados.add(jmTipos);
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

        jMenuBar1.add(jmBaseDados);

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

        jMenuBar1.add(jmImagem);

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

        jMenuBar1.add(jmApresentacao);

        jmGerenAuto.setMnemonic('G');
        jmGerenAuto.setText("Gerenciamento da Fila");

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

        jMenuBar1.add(jmGerenAuto);

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

    private void jmListImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmListImagesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmListImagesActionPerformed

    private void jmGuicheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmGuicheActionPerformed
        openGuiche();
    }//GEN-LAST:event_jmGuicheActionPerformed

    private void jmUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmUserActionPerformed
        openUser();
    }//GEN-LAST:event_jmUserActionPerformed

    private void jmServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmServicosActionPerformed
        openServices();
    }//GEN-LAST:event_jmServicosActionPerformed

    private void jmVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmVoltarActionPerformed
       voltar();
    }//GEN-LAST:event_jmVoltarActionPerformed

    private void jmSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSairActionPerformed
        sair();
    }//GEN-LAST:event_jmSairActionPerformed

    private void jmLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmLogOutActionPerformed
        performLogout();
    }//GEN-LAST:event_jmLogOutActionPerformed

    private void jmAlterImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAlterImageActionPerformed
        alterMainImage();
    }//GEN-LAST:event_jmAlterImageActionPerformed

    private void jmAlterTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAlterTimeActionPerformed
        alterTimePresentation();
    }//GEN-LAST:event_jmAlterTimeActionPerformed

    private void jmAddImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAddImageActionPerformed
        addImage();
    }//GEN-LAST:event_jmAddImageActionPerformed

    private void jmRemoveImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmRemoveImagesActionPerformed
        removeImage();
    }//GEN-LAST:event_jmRemoveImagesActionPerformed

    private void jmAutomaticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAutomaticActionPerformed
        autoManagerQueue();
    }//GEN-LAST:event_jmAutomaticActionPerformed

    private void jmManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmManualActionPerformed
        manualManagerQueue();
    }//GEN-LAST:event_jmManualActionPerformed

    private void jmTiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmTiposActionPerformed
        openTiposGuiche();
    }//GEN-LAST:event_jmTiposActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel jlLogout;
    private javax.swing.JLabel jlUser;
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
    private javax.swing.JLabel lbImage;
    // End of variables declaration//GEN-END:variables

    public void setImageCenter()
    {
        //TODO: definir imagem atraves de arquivo de configuração
        Image img= Toolkit.getDefaultToolkit().getImage("imgs/quadro.jpg");
        ImageIcon ic = new ImageIcon(img);
        getLbImage().setIcon(ic);
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
            Main.getInstance().getLoginScreen().setVisible(true);
        }
        else
        {
            // do nothing
        }
    }
    
    public void openGuiche()
    {
        this.setVisible(false);
        Main.getInstance().getBalconyScreen().setVisible(true);
    }
    
     private void openTiposGuiche() 
    {
        this.setVisible(false);
        Main.getInstance().getTypesScreen().setVisible(true);
    }
    
    public void openUser()
    {
        this.setVisible(false);
        Main.getInstance().getUserScreen().setVisible(true);
    }
    
    public void openServices()
    {
        this.setVisible(false);
        Main.getInstance().getServiceScreen().setVisible(true);
    }

    private void voltar() 
    {
        this.setVisible(false);
        Main.getInstance().getChooseModulesScreen().setVisible(true);
    }

    private void sair() 
    {
        System.exit(0);
    }

    private void alterMainImage() 
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Files", "jpg", "png");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Escolha uma imagem!");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
        {
            Image img = Toolkit.getDefaultToolkit().getImage(chooser.getSelectedFile().toString());
            ImageIcon ic = new ImageIcon(img);
            getLbImage().setIcon(ic);
        }
    }
    /**
     * Adiciona uma imagem na fila de apresentação 
     */
    private void addImage() 
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Files", "jpg", "png");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Escolha uma imagem!");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
        {
            // salva a imagem escolhida no diretório de apresentação e adiciona
            // esta imagem na fila de apresentação
            Image img = Toolkit.getDefaultToolkit().getImage(chooser.getSelectedFile().toString());
            PresentationControler controler = PresentationControler.getInstance();
            controler.addImage(img);
        }
    }
    /**
     * Remove uma imagem na fila de apresentação 
     */
    private void removeImage() 
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(".\\imgs\\presentation"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Files", "jpg", "png");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Escolha uma imagem!");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
        {
            // remove a imagem da lista de apresentação
            Image img = Toolkit.getDefaultToolkit().getImage(chooser.getSelectedFile().toString());
            PresentationControler controler = PresentationControler.getInstance();
            controler.remove(img);
        }
    }
    /**
     * Altera o tempo da apresentação das imagens
     */
    private void alterTimePresentation() 
    {
        long newTime;
        try
        {
            newTime = Long.parseLong(JOptionPane.showInputDialog
                ("Insira o novo tempo de apresentação em segundos"));
            PresentationControler.getInstance().setTime(newTime);
            JOptionPane.showMessageDialog(null, 
                    "Tempo alterado com sucesso!");
        }
        catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(null, 
                    "Tempo deve ser somente números!", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @return the lbImage
     */
    public javax.swing.JLabel getLbImage() 
    {
        return lbImage;
    }

    private void autoManagerQueue() 
    {
        JOptionPane.showMessageDialog(null, 
                    "Gerenciamento automático(A FAZER)!");
    }

    private void manualManagerQueue() 
    {
        JOptionPane.showMessageDialog(null, 
                    "Gerenciamento manual(A FAZER)!");
    }   
}

