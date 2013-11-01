package br.com.thecave.passcontrol.screens.admin;

import br.com.thecave.passcontrol.controller.Main;
import br.com.thecave.passcontrol.controller.UserCrudController;
import br.com.thecave.passcontrol.screens.PassControlPanel;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import br.com.thecave.passcontrolserver.db.bean.UserBean;
import br.com.thecave.passcontrolserver.util.IValidation;
import br.com.thecave.passcontrolserver.util.UserPermission;
import br.com.thecave.passcontrolserver.util.ValidationPerform;
import br.com.thecave.passcontrolserver.util.validations.ValidIsEmpty;
import br.com.thecave.passcontrolserver.util.validations.ValidPasswordSize;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenu;

/**
 *
 * @author Arleudo
 */
public class UserCrud extends PassControlPanel 
{
    UserCrudController controller = null;
    UserBean currentBean;
    private boolean insert;
    private boolean ready;
    /**
     * Creates new form AdminScreen
     */
    public UserCrud() 
    {
        super("Cadastro de Usuários", new UserCrudController());
        this.controller = (UserCrudController) getPanelController();        
        initComponents();
        currentBean = new UserBean();
        jpSecundario.setVisible(false);        
        defineCBNames();
        loadCurrentBean();
        writeScreenFromBean();
        jlNameError.setVisible(false);
        jlLoginError.setVisible(false);
        jlEmailErro.setVisible(false);
        jSenhaError.setVisible(false);
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

        jmAdmin = new javax.swing.JMenu();
        jmAdminstrador = new javax.swing.JMenuItem();
        jmVoltar = new javax.swing.JMenuItem();
        bgPermissao = new javax.swing.ButtonGroup();
        jpBarraLateral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbEditar = new javax.swing.JButton();
        jbNovo = new javax.swing.JButton();
        jpSecundario = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jbAdicionar = new javax.swing.JButton();
        cbLoginUser = new javax.swing.JComboBox();
        jbRemove = new javax.swing.JButton();
        tfNameUser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfSenha = new javax.swing.JPasswordField();
        jlNameError = new javax.swing.JLabel();
        jlLoginError = new javax.swing.JLabel();
        jSenhaError = new javax.swing.JLabel();
        jlEmailErro = new javax.swing.JLabel();
        cbUserPermission = new javax.swing.JComboBox();
        jbVoltar = new javax.swing.JButton();

        jmAdmin.setText("Administrar");
        jmAdmin.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N

        jmAdminstrador.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jmAdminstrador.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmAdminstrador.setMnemonic('a');
        jmAdminstrador.setText("Administrador");
        jmAdminstrador.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmAdminstradorActionPerformed(evt);
            }
        });
        jmAdmin.add(jmAdminstrador);

        jmVoltar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jmVoltar.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmVoltar.setMnemonic('v');
        jmVoltar.setText("Voltar");
        jmVoltar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmVoltarActionPerformed(evt);
            }
        });
        jmAdmin.add(jmVoltar);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jLabel1.setBackground(new java.awt.Color(150, 150, 150));
        jLabel1.setFont(new java.awt.Font("Square721 BT", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(200, 200, 200));
        jLabel1.setText("ADMINISTRANDO USUÁRIOS");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(278, 11, -1, -1));

        jbEditar.setBackground(new java.awt.Color(45, 123, 142));
        jbEditar.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jbEditar.setText("Editar");
        jbEditar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbEditarActionPerformed(evt);
            }
        });
        add(jbEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 221, 37));

        jbNovo.setBackground(new java.awt.Color(45, 123, 142));
        jbNovo.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jbNovo.setText("Novo");
        jbNovo.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbNovoActionPerformed(evt);
            }
        });
        add(jbNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 221, 37));

        jpSecundario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel2.setText("Login do Usuário");
        jpSecundario.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, -1, -1));

        jLabel3.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel3.setText("Nome do usuário");
        jpSecundario.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 91, -1, -1));

        jbAdicionar.setBackground(new java.awt.Color(45, 123, 142));
        jbAdicionar.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jbAdicionar.setText("Adicionar");
        jbAdicionar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbAdicionarActionPerformed(evt);
            }
        });
        jpSecundario.add(jbAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 134, -1));

        cbLoginUser.setEditable(true);
        cbLoginUser.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        cbLoginUser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbLoginUser.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cbLoginUserItemStateChanged(evt);
            }
        });
        cbLoginUser.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cbLoginUserActionPerformed(evt);
            }
        });
        jpSecundario.add(cbLoginUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 35, 208, -1));

        jbRemove.setBackground(new java.awt.Color(45, 123, 142));
        jbRemove.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jbRemove.setText("Remover");
        jbRemove.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbRemoveActionPerformed(evt);
            }
        });
        jpSecundario.add(jbRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 134, -1));

        tfNameUser.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        tfNameUser.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                tfNameUserFocusLost(evt);
            }
        });
        jpSecundario.add(tfNameUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 114, 208, -1));

        jLabel4.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel4.setText("Senha do usuário");
        jpSecundario.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 161, -1, -1));

        jLabel5.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel5.setText("Email do usuário");
        jpSecundario.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 233, -1, -1));

        tfEmail.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        tfEmail.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                tfEmailFocusLost(evt);
            }
        });
        jpSecundario.add(tfEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 256, 211, -1));

        jLabel6.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel6.setText("Permissão do usuário");
        jpSecundario.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 301, -1, -1));

        tfSenha.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        tfSenha.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                tfSenhaFocusLost(evt);
            }
        });
        jpSecundario.add(tfSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 184, 211, -1));

        jlNameError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/errovalid.png"))); // NOI18N
        jpSecundario.add(jlNameError, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 114, -1, 24));

        jlLoginError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/errovalid.png"))); // NOI18N
        jpSecundario.add(jlLoginError, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 35, -1, 27));

        jSenhaError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/errovalid.png"))); // NOI18N
        jpSecundario.add(jSenhaError, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 184, -1, 27));

        jlEmailErro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/errovalid.png"))); // NOI18N
        jpSecundario.add(jlEmailErro, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 256, -1, 27));

        cbUserPermission.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Triagem", "Recepção", "Visualizador", "Guichê" }));
        jpSecundario.add(cbUserPermission, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 210, -1));

        add(jpSecundario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 300, 410));

        jbVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/admin_button.png"))); // NOI18N
        jbVoltar.setToolTipText("Clique para voltar à tela principal!");
        jbVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbVoltar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HiperButton_Administrar_Over.png"))); // NOI18N
        jbVoltar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HiperButton_Administrar_Clicked.png"))); // NOI18N
        jbVoltar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbVoltarActionPerformed(evt);
            }
        });
        add(jbVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 150));
    }// </editor-fold>//GEN-END:initComponents

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbNovoActionPerformed
    {//GEN-HEADEREND:event_jbNovoActionPerformed
        jpSecundario.setVisible(true);
        jbNovo.setVisible(false);
        jbEditar.setVisible(false);
        jbRemove.setVisible(false);
        jbAdicionar.setEnabled(true);
        cbLoginUser.setModel(new DefaultComboBoxModel());
        jmVoltar.setVisible(true);
        insert = true;
        jbAdicionar.setText("Adicionar");
        tfNameUser.setText("");
        tfEmail.setText("");
        tfSenha.setText("");        
    }//GEN-LAST:event_jbNovoActionPerformed

    private void jmAdminstradorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jmAdminstradorActionPerformed
    {//GEN-HEADEREND:event_jmAdminstradorActionPerformed
        Main.getInstance().getMainFrame().activatePassControlPanel(new AdminScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }//GEN-LAST:event_jmAdminstradorActionPerformed

    private void jmVoltarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jmVoltarActionPerformed
    {//GEN-HEADEREND:event_jmVoltarActionPerformed
        voltar();
    }//GEN-LAST:event_jmVoltarActionPerformed

    private void jbEditarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbEditarActionPerformed
    {//GEN-HEADEREND:event_jbEditarActionPerformed
        jpSecundario.setVisible(true);
        jbAdicionar.setText("Atualizar");
        jbRemove.setEnabled(true);
        jbNovo.setVisible(false);
        jbEditar.setVisible(false);
        jmVoltar.setVisible(true);
        controller.defineCBNames(cbLoginUser);
        jbRemove.setVisible(true);
        insert = false;
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbAdicionarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbAdicionarActionPerformed
    {//GEN-HEADEREND:event_jbAdicionarActionPerformed
        String s = "";
        boolean ret = false;
        if(cbLoginUser.getSelectedItem() != null)
            s = cbLoginUser.getSelectedItem().toString();
        
        //criando as validações
        ArrayList<IValidation> validations = new ArrayList<>();
        validations.add(new ValidIsEmpty());        
        validLogin(validations);

        UserPermission normalUser = new UserPermission();
        normalUser.addPermission(UserPermission.BALCONY_PERMISSION_MASK).
                   addPermission(UserPermission.POPPER_PERMISSION_MASK).
                   addPermission(UserPermission.PUSHER_PERMISSION_MASK).
                   addPermission(UserPermission.VIEWER_PERMISSION_MASK);        
        
        if(ready)
        {
            // se tiver clicado em novo
            if(insert)
            {
                currentBean = new UserBean();
                writeBeanFromScreen();                
                ret = controller.saveUser(currentBean);
            }
            // se tiver clicado em editar
            else
            {
                writeBeanFromScreen();
                ret = controller.updateUser(currentBean);
            }
        }
        if(ret)
            voltar(); // limpa a tela
    }//GEN-LAST:event_jbAdicionarActionPerformed

    private void cbLoginUserActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cbLoginUserActionPerformed
    {//GEN-HEADEREND:event_cbLoginUserActionPerformed
       // writeScreenFromBean();
    }//GEN-LAST:event_cbLoginUserActionPerformed

    private void jbRemoveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbRemoveActionPerformed
    {//GEN-HEADEREND:event_jbRemoveActionPerformed
        loadCurrentBean();        
        controller.deleteUser(currentBean);
        voltar();
    }//GEN-LAST:event_jbRemoveActionPerformed

    private void tfNameUserFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_tfNameUserFocusLost
    {//GEN-HEADEREND:event_tfNameUserFocusLost
        //criando as validações
        ArrayList<IValidation> validations = new ArrayList<>();
        validations.add(new ValidIsEmpty());
        validName(validations);
    }//GEN-LAST:event_tfNameUserFocusLost

    private void tfSenhaFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_tfSenhaFocusLost
    {//GEN-HEADEREND:event_tfSenhaFocusLost
       //criando as validações
        ArrayList<IValidation> validations = new ArrayList<>();
        validations.add(new ValidPasswordSize());
        validSenha(validations);
    }//GEN-LAST:event_tfSenhaFocusLost

    private void tfEmailFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_tfEmailFocusLost
    {//GEN-HEADEREND:event_tfEmailFocusLost
         //criando as validações
        ArrayList<IValidation> validations = new ArrayList<>();
        validations.add(new ValidIsEmpty());
        validEmail(validations);
    }//GEN-LAST:event_tfEmailFocusLost

    private void cbLoginUserItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cbLoginUserItemStateChanged
    {//GEN-HEADEREND:event_cbLoginUserItemStateChanged
        if(!insert)
        {
            loadCurrentBean();
            writeScreenFromBean();
        }        
    }//GEN-LAST:event_cbLoginUserItemStateChanged

    private void jbVoltarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbVoltarActionPerformed
    {//GEN-HEADEREND:event_jbVoltarActionPerformed
        Main.getInstance().getMainFrame().activatePassControlPanel(new AdminScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }//GEN-LAST:event_jbVoltarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgPermissao;
    private javax.swing.JComboBox cbLoginUser;
    private javax.swing.JComboBox cbUserPermission;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jSenhaError;
    private javax.swing.JButton jbAdicionar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbRemove;
    private javax.swing.JButton jbVoltar;
    private javax.swing.JLabel jlEmailErro;
    private javax.swing.JLabel jlLoginError;
    private javax.swing.JLabel jlNameError;
    private javax.swing.JMenu jmAdmin;
    private javax.swing.JMenuItem jmAdminstrador;
    private javax.swing.JMenuItem jmVoltar;
    private javax.swing.JPanel jpBarraLateral;
    private javax.swing.JPanel jpSecundario;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfNameUser;
    private javax.swing.JPasswordField tfSenha;
    // End of variables declaration//GEN-END:variables

    @Override
    public ArrayList<JMenu> createMenuItems() 
    {
        ArrayList<JMenu> ret = new ArrayList<JMenu>();
        ret.add(jmAdmin);
        jmVoltar.setVisible(false);
        return ret;
    }

    private void defineCBNames()
    {
        controller.defineCBNames(cbLoginUser);
    }

    private void voltar()
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new UserCrud());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }

    private void validLogin( ArrayList<IValidation> validations ) throws HeadlessException
    {
        // não permite login em branco
        String login = "";
        if(cbLoginUser.getSelectedItem() != null)
        {
            login = cbLoginUser.getSelectedItem().toString();
           if(!ValidationPerform.valid(login, validations))
            {
                jlLoginError.setVisible(true);
                jlLoginError.setToolTipText(ValidationPerform.getComment());
                ready = false;
            }
            else
            {
                jlLoginError.setVisible(false);
                jlLoginError.setToolTipText("");
                ready = true;
            } 
        }
        else
        {
            ready = false;
        }
    }

    private void validEmail( ArrayList<IValidation> validations ) throws HeadlessException
    {
        // não permite email em branco
        if(!ValidationPerform.valid(tfEmail.getText(), validations))
        {
            jlEmailErro.setVisible(true);
            jlEmailErro.setToolTipText(ValidationPerform.getComment());
            ready = false;
        }
        else
        {
            jlEmailErro.setVisible(false);
            jlEmailErro.setToolTipText("");
            ready = true;
        }
    }

    private void validSenha( ArrayList<IValidation> validations ) throws HeadlessException
    {
        // não permite senha em branco nem menor que 6 digitos
        validations.add(new ValidPasswordSize());
        validations.add(new ValidIsEmpty());
        if(!ValidationPerform.valid(new String(tfSenha.getPassword()), validations))
        {
            jSenhaError.setVisible(true);
            jSenhaError.setToolTipText(ValidationPerform.getComment());
            ready = false;
        }
        else
        {
            jSenhaError.setVisible(false);
            jSenhaError.setToolTipText("");
            ready = true;
        }
    }

    /*
     * Valida o nome do usuário, não permitindo nome em branco
     */
    private void validName( ArrayList<IValidation> validations )
    {
        // não permite nome em branco
        validations.add(new ValidIsEmpty());
        if(!ValidationPerform.valid(tfNameUser.getText(), validations))
        {
            jlNameError.setVisible(true);
            jlNameError.setToolTipText(ValidationPerform.getComment());
            ready = false;
        }
        else
        {
            jlNameError.setVisible(false);
            jlNameError.setToolTipText("");
            ready = true;
        }
    }

    private void writeScreenFromBean()
    {
//        Administrador, Triagem, Recepção, Visualizador, Guichê
        tfNameUser.setText(currentBean.getName());
        tfEmail.setText(currentBean.getEmail());
        tfSenha.setText(currentBean.getPassword());
        
        if(currentBean.getType() == UserPermission.ALL_PERMISSION_MASK.getPermissionCode())
        {
            cbUserPermission.setSelectedIndex(0);
        }
        else if (currentBean.getType() == UserPermission.POPPER_PERMISSION_MASK.getPermissionCode())
        {
            cbUserPermission.setSelectedIndex(1);
        }
        else if (currentBean.getType() == UserPermission.PUSHER_PERMISSION_MASK.getPermissionCode())
        {
            cbUserPermission.setSelectedIndex(2);
        }
        else if (currentBean.getType() == UserPermission.VIEWER_PERMISSION_MASK.getPermissionCode())
        {
            cbUserPermission.setSelectedIndex(3);
        }
        else// if (currentBean.getType() == UserPermission.BALCONY_PERMISSION_MASK.getPermissionCode())
        {
            cbUserPermission.setSelectedIndex(4);
        }
    }
    
    private void writeBeanFromScreen()
    {
        currentBean.setEmail(tfEmail.getText());
        currentBean.setLogin(cbLoginUser.getSelectedItem().toString());
        currentBean.setName(tfNameUser.getText());
        currentBean.setPassword(new String(tfSenha.getPassword()));
                
        if (cbUserPermission.getSelectedIndex() == 0)
        {
            currentBean.setType(UserPermission.ALL_PERMISSION_MASK.getPermissionCode());
        }
        else if (cbUserPermission.getSelectedIndex() == 1)
        {
            currentBean.setType(UserPermission.POPPER_PERMISSION_MASK.getPermissionCode());
        }
        else if (cbUserPermission.getSelectedIndex() == 2)
        {
            currentBean.setType(UserPermission.PUSHER_PERMISSION_MASK.getPermissionCode());
        }
        else if (cbUserPermission.getSelectedIndex() == 3)
        {
            currentBean.setType(UserPermission.VIEWER_PERMISSION_MASK.getPermissionCode());
        }
        else// if (cbUserPermission.getSelectedIndex() == 4)
        {
            currentBean.setType(UserPermission.BALCONY_PERMISSION_MASK.getPermissionCode());
        }
    }
    private void loadCurrentBean()
    {
        controller.loadUsers();
        if(cbLoginUser.getSelectedIndex() >= 0 &&  cbLoginUser.getSelectedIndex() < controller.getUserBeans().size())
        {
            int index = cbLoginUser.getSelectedIndex();
            currentBean =  controller.getUserBeans().get(index);
        }
    }
}
