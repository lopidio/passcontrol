package br.com.thecave.passcontrol.screens.admin;

import br.com.thecave.passcontrol.controller.BalconyCrudController;
import br.com.thecave.passcontrol.controller.Main;
import br.com.thecave.passcontrol.screens.PassControlPanel;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import br.com.thecave.passcontrol.utils.PassControlFont;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.util.IValidation;
import br.com.thecave.passcontrolserver.util.ValidationPerform;
import br.com.thecave.passcontrolserver.util.validations.ValidIsEmpty;
import static java.awt.Component.LEFT_ALIGNMENT;
import static java.awt.Component.RIGHT_ALIGNMENT;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

/**
 *
 * @author Arleudo
 */
public class BalconyCrud extends PassControlPanel 
{
    BalconyCrudController controller = null;
    private boolean adicionarPage;
    private ArrayList<JCheckBox> checkBoxs;
    Box leftBox;
    Box rightBox;
    /**
     * Creates new form AdminScreen
     */
    public BalconyCrud() 
    {
        super("Cadastro de Usuários", new BalconyCrudController());
        this.controller = (BalconyCrudController) getPanelController();        
        initComponents();
        jpSecundario.setVisible(false);     
        scrollPanel.setLayout(new FlowLayout());        

        leftBox = Box.createVerticalBox();
        leftBox.setAlignmentX(LEFT_ALIGNMENT);        
        rightBox = Box.createVerticalBox();
        rightBox.setAlignmentX(RIGHT_ALIGNMENT);        
        scrollPanel.add(leftBox);
        scrollPanel.add(Box.createHorizontalStrut(20));
        scrollPanel.add(rightBox);        
    }
    
    /**
     * Cria um checkbox para cada serviço
     */
    private ArrayList<JCheckBox> createCheckBoxFromServicesList(ArrayList<ServiceBean> servicesBean)
    {
        ArrayList<JCheckBox> retorno = new ArrayList<>(servicesBean.size());
        for (ServiceBean service : servicesBean) 
        {
            JCheckBox novoCheckBox = new JCheckBox(service.getName());
            retorno.add(novoCheckBox);
        }
        return retorno;
    }
    
    private void defineServices()
    {
        leftBox.removeAll();
        rightBox.removeAll();
        checkBoxs = createCheckBoxFromServicesList(controller.getServices());
        for (int i = 0; i < checkBoxs.size(); ++i)
        {
            JCheckBox box = checkBoxs.get(i);
            box.setFont(PassControlFont.getInstance().getSizedFont(14));
            //Os pares ficam na esquerda e tal...
            if (i%2 == 0)
            {
                leftBox.add(box);                
            }
            else
            {
                rightBox.add(box);                                
            }
        }
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
        jlAdminPic = new javax.swing.JLabel();
        jpBarraLateral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbEditar = new javax.swing.JButton();
        jbNovo = new javax.swing.JButton();
        jpSecundario = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jbAdicionar = new javax.swing.JButton();
        cbBalconysName = new javax.swing.JComboBox();
        jbRemove = new javax.swing.JButton();
        jpServices = new javax.swing.JScrollPane();
        scrollPanel = new javax.swing.JPanel();

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

        jlAdminPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/balcony_button.png"))); // NOI18N
        add(jlAdminPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 11, -1, -1));

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

        jLabel2.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel2.setText("Nome do guichê");

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

        cbBalconysName.setEditable(true);
        cbBalconysName.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        cbBalconysName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbBalconysName.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cbBalconysNameActionPerformed(evt);
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

        javax.swing.GroupLayout scrollPanelLayout = new javax.swing.GroupLayout(scrollPanel);
        scrollPanel.setLayout(scrollPanelLayout);
        scrollPanelLayout.setHorizontalGroup(
            scrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );
        scrollPanelLayout.setVerticalGroup(
            scrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 189, Short.MAX_VALUE)
        );

        jpServices.setViewportView(scrollPanel);

        javax.swing.GroupLayout jpSecundarioLayout = new javax.swing.GroupLayout(jpSecundario);
        jpSecundario.setLayout(jpSecundarioLayout);
        jpSecundarioLayout.setHorizontalGroup(
            jpSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSecundarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSecundarioLayout.createSequentialGroup()
                        .addComponent(jbAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpSecundarioLayout.createSequentialGroup()
                        .addGroup(jpSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(cbBalconysName, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpServices, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpSecundarioLayout.setVerticalGroup(
            jpSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSecundarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbBalconysName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jpServices, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jpSecundarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAdicionar)
                    .addComponent(jbRemove))
                .addGap(38, 38, 38))
        );

        add(jpSecundario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 290, 380));
    }// </editor-fold>//GEN-END:initComponents

    private void jbNovoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbNovoActionPerformed
    {//GEN-HEADEREND:event_jbNovoActionPerformed
        jpSecundario.setVisible(true);
        jbNovo.setVisible(false);
        jbEditar.setVisible(false);
        jbRemove.setVisible(false);
        jbAdicionar.setEnabled(true);
        cbBalconysName.setModel(new DefaultComboBoxModel());
        jmVoltar.setVisible(true);
        adicionarPage = true;
        jbAdicionar.setText("Adicionar");
        controller.refreshAttributes();

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("");
        cbBalconysName.setModel(model);
        
        defineServices();        
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
        jbRemove.setVisible(true);
        adicionarPage = false;
        
        // carregar a tela com as informações
        controller.refreshAttributes();
        controller.defineCBNames(cbBalconysName);
        
        defineServices();
        
        BalconyBean bean = extractBeanFromSelectedComboBoxItem();
        ArrayList<ServiceBean> services = controller.getServicesFromBalcony(bean);
        selectCheckBoxFromServicesList(services);
        
    }//GEN-LAST:event_jbEditarActionPerformed

    private JCheckBox getCheckBoxFromServices(ServiceBean bean)
    {
        for(JCheckBox box : checkBoxs)
        {
            if(box.getText().equals(bean.getName()))
                return box;
        }
        return null;
    }
    
    private void jbAdicionarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbAdicionarActionPerformed
    {//GEN-HEADEREND:event_jbAdicionarActionPerformed
        String balconyName = "";
        boolean retorno = false;
        if(cbBalconysName.getSelectedItem() != null)
            balconyName = cbBalconysName.getSelectedItem().toString();
        
        // adicionando as validações
        ArrayList<IValidation> validations = new ArrayList<>();
        validations.add(new ValidIsEmpty());
        
        // não permite inserir um registro com o nome vazio
        while( !ValidationPerform.valid(balconyName, validations))
        {
            balconyName = JOptionPane.showInputDialog(ValidationPerform.getComment());
            if (balconyName == null)
                return;
            DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
            boxModel.addElement(balconyName);
            cbBalconysName.setModel(boxModel);
        }

        ArrayList<ServiceBean> arrayList = getSelectedServices();
        if (arrayList.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "É preciso selecionar serviço(s)", "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // se tiver clicado em novo
            if(adicionarPage)
            {
                BalconyBean balconyBean = new BalconyBean();            
                balconyBean.setNumber(cbBalconysName.getSelectedItem().toString());
                retorno = controller.saveBalcony(balconyBean, arrayList);
            }
            // se tiver clicado em editar
            else
            {
                // construindo o bean com as informações da tela
                BalconyBean balconyBean = extractBeanFromSelectedComboBoxItem();
                if (balconyBean == null)
                {
                    return;
                }            
                retorno = controller.updateBalcony(balconyBean, arrayList);
            }
        }
        //Analisa o retorno
        if(retorno)
        {
            voltar(); // limpa a tela
        }
    }//GEN-LAST:event_jbAdicionarActionPerformed

    private void cbBalconysNameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cbBalconysNameActionPerformed
    {//GEN-HEADEREND:event_cbBalconysNameActionPerformed
        if (!adicionarPage)
        {
            BalconyBean selectedBalconyBean = extractBeanFromSelectedComboBoxItem();
            selectCheckBoxFromServicesList(controller.getServicesFromBalcony(selectedBalconyBean));
        }
    }//GEN-LAST:event_cbBalconysNameActionPerformed

    private void jbRemoveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbRemoveActionPerformed
    {//GEN-HEADEREND:event_jbRemoveActionPerformed
        BalconyBean bean = extractBeanFromSelectedComboBoxItem();
        if(bean != null)
        {
            controller.deleteBalcony(bean);
            voltar();
        }
        else
            JOptionPane.showMessageDialog(null, "Não existe nenhum registro a ser deletado!");
    }//GEN-LAST:event_jbRemoveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgPermissao;
    private javax.swing.JComboBox cbBalconysName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbAdicionar;
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbNovo;
    private javax.swing.JButton jbRemove;
    private javax.swing.JLabel jlAdminPic;
    private javax.swing.JMenu jmAdmin;
    private javax.swing.JMenuItem jmAdminstrador;
    private javax.swing.JMenuItem jmVoltar;
    private javax.swing.JPanel jpBarraLateral;
    private javax.swing.JPanel jpSecundario;
    private javax.swing.JScrollPane jpServices;
    private javax.swing.JPanel scrollPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public ArrayList<JMenu> createMenuItems() 
    {
        ArrayList<JMenu> ret = new ArrayList<JMenu>();
        ret.add(jmAdmin);
        jmVoltar.setVisible(false);
        return ret;
    }


    private BalconyBean extractBeanFromSelectedComboBoxItem()
    {
        for(BalconyBean bean : controller.getBalconyBeans())
        {
            if(bean.getNumber().equals(cbBalconysName.getSelectedItem().toString()))
                return bean;
        }
        return null;
    }

    private void voltar()
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new BalconyCrud());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }

    private ArrayList<ServiceBean> getSelectedServices( )
    {
        ArrayList<ServiceBean> arrayList = new ArrayList<>();
        // pegando somente os serviços selecionados
        for(int i = 0; i < checkBoxs.size(); i++)
        {
            if(checkBoxs.get(i).isSelected())
            {
                arrayList.add(controller.getServices().get(i));
            }
        }
        return arrayList;
    }

    private void selectCheckBoxFromServicesList( ArrayList<ServiceBean> services )
    {
        //Desseleciona todos, antes
        for (JCheckBox box : checkBoxs) 
        {
            box.setSelected(false);
        }
        if (services != null)
        {
            for ( ServiceBean serviceBean : services )
            {
                JCheckBox box = getCheckBoxFromServices(serviceBean);
                //Seleciona os que devem ser selecionados
                if(box != null)
                {
                    box.setSelected(true);
                }
            }
        }
    }
}
