package br.com.thecave.passcontrol.screens.admin;

import br.com.thecave.passcontrol.controller.Main;
import br.com.thecave.passcontrol.controller.ServiceCrudController;
import br.com.thecave.passcontrol.screens.PassControlPanel;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.util.IValidation;
import br.com.thecave.passcontrolserver.util.ValidationPerform;
import br.com.thecave.passcontrolserver.util.validations.ValidIsEmpty;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

/**
 *
 * @author Arleudo
 */
public class ServiceCrud extends PassControlPanel
{

    ServiceCrudController controller = null;
    private boolean insert;
    private boolean ready;
    private ServiceBean currentBean;

    /**
     * Creates new form AdminScreen
     */
    public ServiceCrud()
    {
        super("Cadastro de Serviços", new ServiceCrudController());
        this.controller = (ServiceCrudController) getPanelController();
        initComponents();
        currentBean = new ServiceBean();
        jpSecundario.setVisible(false);
        defineCBNames();
        loadCurrentBean();
        writeScreenFromBean();
        jlNameErro.setVisible(false);
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
        jpBarraLateral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbEditar = new javax.swing.JButton();
        jbNovo = new javax.swing.JButton();
        jpSecundario = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbPrioridade = new javax.swing.JComboBox();
        jbAdicionar = new javax.swing.JButton();
        cbName = new javax.swing.JComboBox();
        jbRemove = new javax.swing.JButton();
        jlNameErro = new javax.swing.JLabel();
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
        jLabel1.setText("ADMINISTRANDO SERVIÇOS");
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

        jLabel2.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel2.setText("Nome do serviço");

        jLabel3.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel3.setText("Prioridade");

        cbPrioridade.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        cbPrioridade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mínima", "Baixa", "Média", "Alta", "Máxima" }));

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

        cbName.setEditable(true);
        cbName.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        cbName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbName.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cbNameItemStateChanged(evt);
            }
        });
        cbName.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cbNameActionPerformed(evt);
            }
        });

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

        jlNameErro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/errovalid.png"))); // NOI18N

        javax.swing.GroupLayout jpSecundarioLayout = new javax.swing.GroupLayout(jpSecundario);
        jpSecundario.setLayout(jpSecundarioLayout);
        jpSecundarioLayout.setHorizontalGroup(
            jpSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSecundarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpSecundarioLayout.createSequentialGroup()
                        .addComponent(jbAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jbRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpSecundarioLayout.createSequentialGroup()
                        .addGroup(jpSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(jpSecundarioLayout.createSequentialGroup()
                                .addGroup(jpSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbName, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbPrioridade, javax.swing.GroupLayout.Alignment.LEADING, 0, 208, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlNameErro, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpSecundarioLayout.setVerticalGroup(
            jpSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSecundarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlNameErro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbName))
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPrioridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jpSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAdicionar)
                    .addComponent(jbRemove))
                .addGap(38, 38, 38))
        );

        add(jpSecundario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 300, 260));

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
        cbName.setModel(new DefaultComboBoxModel());
        jmVoltar.setVisible(true);
        insert = true;
        cbPrioridade.setSelectedIndex(2);
        jbAdicionar.setText("Adicionar");

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
        controller.defineCBNames(cbName);
        jbRemove.setVisible(true);
        insert = false;
    }//GEN-LAST:event_jbEditarActionPerformed

    private void jbAdicionarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbAdicionarActionPerformed
    {//GEN-HEADEREND:event_jbAdicionarActionPerformed
        // verificando se ja existe um com mesmo nome
        if ( verificarRegistroAntigo() )
        {
            jlNameErro.setVisible(true);
            jlNameErro.setToolTipText("Já existe um registro com esse nome!");
        }
        else
        {
            jlNameErro.setVisible(false);
            String s = "";
            boolean ret = false;
            if ( cbName.getSelectedItem() != null )
            {
                s = cbName.getSelectedItem().toString();
            }

            ArrayList<IValidation> validations = new ArrayList<>();
            validations.add(new ValidIsEmpty());

            // não permite inserir um registro com o nome vazio
            if ( !ValidationPerform.valid(s, validations) )
            {
                jlNameErro.setVisible(true);
                jlNameErro.setToolTipText(ValidationPerform.getComment());
                ready = false;
            }
            else
            {
                jlNameErro.setVisible(false);
                ready = true;
            }

            if ( ready )
            {
                // se tiver clicado em novo
                if ( insert )
                {
                    currentBean = new ServiceBean();
                    writeBeanFromScreen();
                    ret = controller.saveService(currentBean);
                }
                // se tiver clicado em editar
                else
                {
                    writeBeanFromScreen();
                    ret = controller.updateService(currentBean);
                }
            }
            if ( ret )
            {
                voltar(); // limpa a tela
            }
        }
    }//GEN-LAST:event_jbAdicionarActionPerformed

    private void cbNameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cbNameActionPerformed
    {//GEN-HEADEREND:event_cbNameActionPerformed
        // writeScreenFromBean();
    }//GEN-LAST:event_cbNameActionPerformed

    private void jbRemoveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbRemoveActionPerformed
    {//GEN-HEADEREND:event_jbRemoveActionPerformed
        loadCurrentBean();
        controller.deleteService(currentBean);
        voltar();
    }//GEN-LAST:event_jbRemoveActionPerformed

    private void cbNameItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cbNameItemStateChanged
    {//GEN-HEADEREND:event_cbNameItemStateChanged
        if ( !insert )
        {
            loadCurrentBean();
            writeScreenFromBean();
        }
    }//GEN-LAST:event_cbNameItemStateChanged

    private void jbVoltarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbVoltarActionPerformed
    {//GEN-HEADEREND:event_jbVoltarActionPerformed
        Main.getInstance().getMainFrame().activatePassControlPanel(new AdminScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }//GEN-LAST:event_jbVoltarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbName;
    private javax.swing.JComboBox cbPrioridade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jbAdicionar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbRemove;
    private javax.swing.JButton jbVoltar;
    private javax.swing.JLabel jlNameErro;
    private javax.swing.JMenu jmAdmin;
    private javax.swing.JMenuItem jmAdminstrador;
    private javax.swing.JMenuItem jmVoltar;
    private javax.swing.JPanel jpBarraLateral;
    private javax.swing.JPanel jpSecundario;
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
        controller.defineCBNames(cbName);
    }

    private void voltar()
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new ServiceCrud());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }

    private void loadCurrentBean()
    {
        controller.loadServices();
        if ( cbName.getSelectedIndex() >= 0 && cbName.getSelectedIndex() < controller.getServices().size() )
        {
            int index = cbName.getSelectedIndex();
            currentBean = controller.getServices().get(index);
        }
    }

    private void writeScreenFromBean()
    {
        loadCurrentBean();
        int index = currentBean.getPriority() - 1;
        cbPrioridade.setSelectedIndex(index);
    }

    private void writeBeanFromScreen()
    {
        currentBean.setName(cbName.getSelectedItem().toString());
        currentBean.setPriority(cbPrioridade.getSelectedIndex() + 1);
    }

    private boolean verificarRegistroAntigo()
    {
        controller.loadServices();
        ArrayList<ServiceBean> serviceBeans = controller.getServices();
        String registro = cbName.getSelectedItem().toString();
        
        for ( ServiceBean bean : serviceBeans )
        {
            String str = bean.getName();
            if(str.equalsIgnoreCase(registro))
                return true;
        }
        return false;
    }
}
