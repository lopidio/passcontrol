package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.component.util.QueueElementInfoSmall;
import br.com.thecave.passcontrol.controller.Main;
import br.com.thecave.passcontrol.controller.QueuePushController;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import br.com.thecave.passcontrol.utils.Printer;
import br.com.thecave.passcontrolserver.db.bean.ClientBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.db.bean.UserBean;
import br.com.thecave.passcontrolserver.util.IValidation;
import br.com.thecave.passcontrolserver.util.ValidationPerform;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class QueuePushScreen extends PassControlPanel
{

    QueuePushController controller = null;
    ClientBean clientBean;
    ServiceBean serviceBean;
    private boolean ready;

    public QueuePushScreen()
    {
        super("Escolha de Serviços", new QueuePushController());
        this.controller = (QueuePushController) getPanelController();
        initComponents();
        jpNovoCliente.setVisible(false);
        jpNovoAtendimento.setVisible(false);
        jpEditarCliente.setVisible(false);
        jmVoltar.setVisible(false);
        jSeparator1.setVisible(false);
        tfNomeNovoAtendimento.setEnabled(false);
        tfTelefoneNovoAtendimento.setEnabled(false);
        jpQueueInfo.setVisible(false);
        
        jpQueueInfo.setLayout(new BoxLayout(jpQueueInfo, BoxLayout.Y_AXIS));
        
        jlCadastroClienteErro.setVisible(false);
        jlNomeAtendimentoErro.setVisible(false);
        jlTelefoneAtendimentoErro.setVisible(false);
        jlTelefoneClienteErro.setVisible(false);
        jlNomeClienteErro.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jmChooseService = new javax.swing.JMenu();
        jmNovoCliente = new javax.swing.JMenuItem();
        jmNovoAtendimento = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmVoltar = new javax.swing.JMenuItem();
        jpBarraLateral = new javax.swing.JPanel();
        jbNovoCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jbNovoAtendimento = new javax.swing.JButton();
        jbEditarCliente = new javax.swing.JButton();
        jpQueueInfo = new javax.swing.JPanel();
        jpEditarCliente = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tfCadastroEditarCliente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfNomeEditarCliente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tfTelefoneEditarCliente = new javax.swing.JTextField();
        jbAtualizarCliente = new javax.swing.JButton();
        jlCadastroClienteErroEditar = new javax.swing.JLabel();
        jlNomeClienteErroEditar = new javax.swing.JLabel();
        jlTelefoneClienteErroEditar = new javax.swing.JLabel();
        jbRemoverCliente = new javax.swing.JButton();
        jpNovoCliente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfCadastroNovoCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfNomeNovoCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfTelefoneNovoCliente = new javax.swing.JTextField();
        jbInserirNovoCliente = new javax.swing.JButton();
        jlCadastroClienteErro = new javax.swing.JLabel();
        jlNomeClienteErro = new javax.swing.JLabel();
        jlTelefoneClienteErro = new javax.swing.JLabel();
        jpNovoAtendimento = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tfCadastroNovoAtendimento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfNomeNovoAtendimento = new javax.swing.JTextField();
        tfTelefoneNovoAtendimento = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbServico = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jbInserirNovoAtendimento = new javax.swing.JButton();
        jbLoadFromRegister = new javax.swing.JButton();
        jlNomeAtendimentoErro = new javax.swing.JLabel();
        jlTelefoneAtendimentoErro = new javax.swing.JLabel();
        jbBack = new javax.swing.JButton();

        jmChooseService.setMnemonic('e');
        jmChooseService.setText("Recepção");
        jmChooseService.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N

        jmNovoCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jmNovoCliente.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmNovoCliente.setMnemonic('c');
        jmNovoCliente.setText("Novo cliente");
        jmNovoCliente.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmNovoClienteActionPerformed(evt);
            }
        });
        jmChooseService.add(jmNovoCliente);

        jmNovoAtendimento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jmNovoAtendimento.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmNovoAtendimento.setMnemonic('a');
        jmNovoAtendimento.setText("Novo Atendimento");
        jmNovoAtendimento.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmNovoAtendimentoActionPerformed(evt);
            }
        });
        jmChooseService.add(jmNovoAtendimento);
        jmChooseService.add(jSeparator1);

        jmVoltar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jmVoltar.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmVoltar.setText("Voltar");
        jmVoltar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmVoltarActionPerformed(evt);
            }
        });
        jmChooseService.add(jmVoltar);

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

        jbNovoCliente.setBackground(new java.awt.Color(45, 123, 142));
        jbNovoCliente.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jbNovoCliente.setText("Novo Cliente");
        jbNovoCliente.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbNovoClienteActionPerformed(evt);
            }
        });
        add(jbNovoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 221, 37));

        jLabel1.setBackground(new java.awt.Color(150, 150, 150));
        jLabel1.setFont(new java.awt.Font("Square721 BT", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(200, 200, 200));
        jLabel1.setText("RECEPÇÃO");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(278, 11, -1, -1));

        jbNovoAtendimento.setBackground(new java.awt.Color(45, 123, 142));
        jbNovoAtendimento.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jbNovoAtendimento.setText("Novo Atendimento");
        jbNovoAtendimento.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbNovoAtendimentoActionPerformed(evt);
            }
        });
        add(jbNovoAtendimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 221, 37));

        jbEditarCliente.setBackground(new java.awt.Color(45, 123, 142));
        jbEditarCliente.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jbEditarCliente.setText("Editar Cliente");
        jbEditarCliente.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbEditarClienteActionPerformed(evt);
            }
        });
        add(jbEditarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 221, 37));

        jpQueueInfo.setMinimumSize(new java.awt.Dimension(220, 128));
        jpQueueInfo.setPreferredSize(new java.awt.Dimension(220, 128));

        javax.swing.GroupLayout jpQueueInfoLayout = new javax.swing.GroupLayout(jpQueueInfo);
        jpQueueInfo.setLayout(jpQueueInfoLayout);
        jpQueueInfoLayout.setHorizontalGroup(
            jpQueueInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jpQueueInfoLayout.setVerticalGroup(
            jpQueueInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        add(jpQueueInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 220, 128));

        jpEditarCliente.setMaximumSize(new java.awt.Dimension(345, 248));
        jpEditarCliente.setMinimumSize(new java.awt.Dimension(345, 248));
        jpEditarCliente.setPreferredSize(new java.awt.Dimension(345, 248));
        jpEditarCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel6.setText("Cadastro:");
        jpEditarCliente.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 14, -1, -1));

        tfCadastroEditarCliente.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        tfCadastroEditarCliente.setMaximumSize(new java.awt.Dimension(210, 27));
        tfCadastroEditarCliente.setMinimumSize(new java.awt.Dimension(210, 27));
        tfCadastroEditarCliente.setPreferredSize(new java.awt.Dimension(210, 27));
        tfCadastroEditarCliente.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                tfCadastroEditarClienteActionPerformed(evt);
            }
        });
        jpEditarCliente.add(tfCadastroEditarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 11, -1, -1));

        jLabel10.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel10.setText("Nome:");
        jpEditarCliente.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        tfNomeEditarCliente.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        tfNomeEditarCliente.setMaximumSize(new java.awt.Dimension(210, 27));
        tfNomeEditarCliente.setMinimumSize(new java.awt.Dimension(210, 27));
        tfNomeEditarCliente.setPreferredSize(new java.awt.Dimension(210, 27));
        jpEditarCliente.add(tfNomeEditarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 210, -1));

        jLabel11.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel11.setText("Telefone:");
        jpEditarCliente.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        tfTelefoneEditarCliente.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        tfTelefoneEditarCliente.setMaximumSize(new java.awt.Dimension(210, 27));
        tfTelefoneEditarCliente.setMinimumSize(new java.awt.Dimension(210, 27));
        tfTelefoneEditarCliente.setPreferredSize(new java.awt.Dimension(210, 27));
        jpEditarCliente.add(tfTelefoneEditarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 210, -1));

        jbAtualizarCliente.setBackground(new java.awt.Color(45, 123, 142));
        jbAtualizarCliente.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jbAtualizarCliente.setText("Atualizar");
        jbAtualizarCliente.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbAtualizarClienteActionPerformed(evt);
            }
        });
        jpEditarCliente.add(jbAtualizarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 199, 140, 37));

        jlCadastroClienteErroEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/errovalid.png"))); // NOI18N
        jpEditarCliente.add(jlCadastroClienteErroEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 11, 24, 24));

        jlNomeClienteErroEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/errovalid.png"))); // NOI18N
        jpEditarCliente.add(jlNomeClienteErroEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 24, 24));

        jlTelefoneClienteErroEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/errovalid.png"))); // NOI18N
        jpEditarCliente.add(jlTelefoneClienteErroEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 24, 24));

        jbRemoverCliente.setBackground(new java.awt.Color(45, 123, 142));
        jbRemoverCliente.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jbRemoverCliente.setText("Remover");
        jbRemoverCliente.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbRemoverClienteActionPerformed(evt);
            }
        });
        jpEditarCliente.add(jbRemoverCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 140, 37));

        add(jpEditarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        jpNovoCliente.setMaximumSize(new java.awt.Dimension(345, 248));
        jpNovoCliente.setMinimumSize(new java.awt.Dimension(345, 248));
        jpNovoCliente.setPreferredSize(new java.awt.Dimension(345, 248));
        jpNovoCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel2.setText("Cadastro:");
        jpNovoCliente.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 14, -1, -1));

        tfCadastroNovoCliente.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        tfCadastroNovoCliente.setMaximumSize(new java.awt.Dimension(210, 27));
        tfCadastroNovoCliente.setMinimumSize(new java.awt.Dimension(210, 27));
        tfCadastroNovoCliente.setPreferredSize(new java.awt.Dimension(210, 27));
        tfCadastroNovoCliente.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                tfCadastroNovoClienteActionPerformed(evt);
            }
        });
        jpNovoCliente.add(tfCadastroNovoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 11, -1, -1));

        jLabel3.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel3.setText("Nome:");
        jpNovoCliente.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        tfNomeNovoCliente.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        tfNomeNovoCliente.setMaximumSize(new java.awt.Dimension(210, 27));
        tfNomeNovoCliente.setMinimumSize(new java.awt.Dimension(210, 27));
        tfNomeNovoCliente.setPreferredSize(new java.awt.Dimension(210, 27));
        jpNovoCliente.add(tfNomeNovoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 210, -1));

        jLabel4.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel4.setText("Telefone:");
        jpNovoCliente.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        tfTelefoneNovoCliente.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        tfTelefoneNovoCliente.setMaximumSize(new java.awt.Dimension(210, 27));
        tfTelefoneNovoCliente.setMinimumSize(new java.awt.Dimension(210, 27));
        tfTelefoneNovoCliente.setPreferredSize(new java.awt.Dimension(210, 27));
        jpNovoCliente.add(tfTelefoneNovoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 210, -1));

        jbInserirNovoCliente.setBackground(new java.awt.Color(45, 123, 142));
        jbInserirNovoCliente.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jbInserirNovoCliente.setText("Inserir");
        jbInserirNovoCliente.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbInserirNovoClienteActionPerformed(evt);
            }
        });
        jpNovoCliente.add(jbInserirNovoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 199, 221, 37));

        jlCadastroClienteErro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/errovalid.png"))); // NOI18N
        jpNovoCliente.add(jlCadastroClienteErro, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 11, 24, 24));

        jlNomeClienteErro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/errovalid.png"))); // NOI18N
        jpNovoCliente.add(jlNomeClienteErro, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 24, 24));

        jlTelefoneClienteErro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/errovalid.png"))); // NOI18N
        jpNovoCliente.add(jlTelefoneClienteErro, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 24, 24));

        add(jpNovoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        jpNovoAtendimento.setMaximumSize(new java.awt.Dimension(345, 248));
        jpNovoAtendimento.setMinimumSize(new java.awt.Dimension(345, 248));
        jpNovoAtendimento.setPreferredSize(new java.awt.Dimension(345, 248));
        jpNovoAtendimento.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel5.setText("Cadastro:");
        jpNovoAtendimento.add(jLabel5);
        jLabel5.setBounds(10, 14, 69, 18);

        tfCadastroNovoAtendimento.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        tfCadastroNovoAtendimento.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                tfCadastroNovoAtendimentoActionPerformed(evt);
            }
        });
        jpNovoAtendimento.add(tfCadastroNovoAtendimento);
        tfCadastroNovoAtendimento.setBounds(89, 11, 182, 24);

        jLabel7.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel7.setText("Nome:");
        jpNovoAtendimento.add(jLabel7);
        jLabel7.setBounds(10, 56, 44, 18);

        tfNomeNovoAtendimento.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        tfNomeNovoAtendimento.setMaximumSize(new java.awt.Dimension(210, 27));
        tfNomeNovoAtendimento.setMinimumSize(new java.awt.Dimension(210, 27));
        tfNomeNovoAtendimento.setPreferredSize(new java.awt.Dimension(210, 27));
        jpNovoAtendimento.add(tfNomeNovoAtendimento);
        tfNomeNovoAtendimento.setBounds(88, 53, 210, 27);

        tfTelefoneNovoAtendimento.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        tfTelefoneNovoAtendimento.setMaximumSize(new java.awt.Dimension(210, 27));
        tfTelefoneNovoAtendimento.setMinimumSize(new java.awt.Dimension(210, 27));
        tfTelefoneNovoAtendimento.setPreferredSize(new java.awt.Dimension(210, 27));
        jpNovoAtendimento.add(tfTelefoneNovoAtendimento);
        tfTelefoneNovoAtendimento.setBounds(88, 98, 210, 27);

        jLabel8.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel8.setText("Telefone:");
        jpNovoAtendimento.add(jLabel8);
        jLabel8.setBounds(10, 103, 60, 18);

        cbServico.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        cbServico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jpNovoAtendimento.add(cbServico);
        cbServico.setBounds(88, 152, 210, 24);

        jLabel9.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jLabel9.setText("Serviço:");
        jpNovoAtendimento.add(jLabel9);
        jLabel9.setBounds(10, 155, 56, 18);

        jbInserirNovoAtendimento.setBackground(new java.awt.Color(45, 123, 142));
        jbInserirNovoAtendimento.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jbInserirNovoAtendimento.setText("Inserir");
        jbInserirNovoAtendimento.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbInserirNovoAtendimentoActionPerformed(evt);
            }
        });
        jpNovoAtendimento.add(jbInserirNovoAtendimento);
        jbInserirNovoAtendimento.setBounds(10, 197, 221, 37);

        jbLoadFromRegister.setBackground(new java.awt.Color(0, 153, 191));
        jbLoadFromRegister.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jbLoadFromRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Lupe_Small.png"))); // NOI18N
        jbLoadFromRegister.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Lupe_Small_Clicked.png"))); // NOI18N
        jbLoadFromRegister.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbLoadFromRegisterActionPerformed(evt);
            }
        });
        jpNovoAtendimento.add(jbLoadFromRegister);
        jbLoadFromRegister.setBounds(277, 8, 30, 30);

        jlNomeAtendimentoErro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/errovalid.png"))); // NOI18N
        jpNovoAtendimento.add(jlNomeAtendimentoErro);
        jlNomeAtendimentoErro.setBounds(310, 56, 15, 24);

        jlTelefoneAtendimentoErro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/errovalid.png"))); // NOI18N
        jpNovoAtendimento.add(jlTelefoneAtendimentoErro);
        jlTelefoneAtendimentoErro.setBounds(310, 101, 15, 24);

        add(jpNovoAtendimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        jbBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/queue_push_button.png"))); // NOI18N
        jbBack.setToolTipText("Clique para voltar à tela principal!");
        jbBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbBack.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HiperButton_Adicionar_Over.png"))); // NOI18N
        jbBack.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HiperButton_Adicionar_Clicked.png"))); // NOI18N
        jbBack.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbBackActionPerformed(evt);
            }
        });
        add(jbBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 150));
    }// </editor-fold>//GEN-END:initComponents

    private void jmNovoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmNovoClienteActionPerformed
        novoCliente();
    }//GEN-LAST:event_jmNovoClienteActionPerformed

    private void jbNovoClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbNovoClienteActionPerformed
    {//GEN-HEADEREND:event_jbNovoClienteActionPerformed
        novoCliente();
    }//GEN-LAST:event_jbNovoClienteActionPerformed

    private void jbNovoAtendimentoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbNovoAtendimentoActionPerformed
    {//GEN-HEADEREND:event_jbNovoAtendimentoActionPerformed
        novoAtendimento();
    }//GEN-LAST:event_jbNovoAtendimentoActionPerformed

    private void jmNovoAtendimentoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jmNovoAtendimentoActionPerformed
    {//GEN-HEADEREND:event_jmNovoAtendimentoActionPerformed
        novoAtendimento();
    }//GEN-LAST:event_jmNovoAtendimentoActionPerformed

    private void jmVoltarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jmVoltarActionPerformed
    {//GEN-HEADEREND:event_jmVoltarActionPerformed
        Main.getInstance().getMainFrame().activatePassControlPanel(new QueuePushScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }//GEN-LAST:event_jmVoltarActionPerformed

    private void jbInserirNovoClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbInserirNovoClienteActionPerformed
    {//GEN-HEADEREND:event_jbInserirNovoClienteActionPerformed
        ClientBean bean = new ClientBean();
        // validar campos posteriormente
        String nomeCliente = tfNomeNovoCliente.getText();
        
        bean.setName(nomeCliente);
        bean.setRegister(tfCadastroNovoCliente.getText());
        bean.setTelefone(tfTelefoneNovoCliente.getText());
        controller.insertNewClient(bean);
        Main.getInstance().getMainFrame().activatePassControlPanel(new QueuePushScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }//GEN-LAST:event_jbInserirNovoClienteActionPerformed

    private void tfCadastroNovoClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_tfCadastroNovoClienteActionPerformed
    {//GEN-HEADEREND:event_tfCadastroNovoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCadastroNovoClienteActionPerformed

    private void tfCadastroNovoAtendimentoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_tfCadastroNovoAtendimentoActionPerformed
    {//GEN-HEADEREND:event_tfCadastroNovoAtendimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCadastroNovoAtendimentoActionPerformed

    private void jbInserirNovoAtendimentoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbInserirNovoAtendimentoActionPerformed
    {//GEN-HEADEREND:event_jbInserirNovoAtendimentoActionPerformed
        inserirNovoAtendimento();
        jbInserirNovoAtendimento.setEnabled(false);        
    }//GEN-LAST:event_jbInserirNovoAtendimentoActionPerformed

    private void jbLoadFromRegisterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbLoadFromRegisterActionPerformed
    {//GEN-HEADEREND:event_jbLoadFromRegisterActionPerformed
        searchRegister();
    }//GEN-LAST:event_jbLoadFromRegisterActionPerformed

    private void jbEditarClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbEditarClienteActionPerformed
    {//GEN-HEADEREND:event_jbEditarClienteActionPerformed
        editarCliente();
    }//GEN-LAST:event_jbEditarClienteActionPerformed

    private void tfCadastroEditarClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_tfCadastroEditarClienteActionPerformed
    {//GEN-HEADEREND:event_tfCadastroEditarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCadastroEditarClienteActionPerformed

    private void jbAtualizarClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbAtualizarClienteActionPerformed
    {//GEN-HEADEREND:event_jbAtualizarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbAtualizarClienteActionPerformed

    private void jbRemoverClienteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbRemoverClienteActionPerformed
    {//GEN-HEADEREND:event_jbRemoverClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbRemoverClienteActionPerformed

    private void jbBackActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jbBackActionPerformed
    {//GEN-HEADEREND:event_jbBackActionPerformed
        Main.getInstance().getMainFrame().activatePassControlPanel(new QueuePushScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }//GEN-LAST:event_jbBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbServico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JButton jbAtualizarCliente;
    private javax.swing.JButton jbBack;
    private javax.swing.JButton jbEditarCliente;
    private javax.swing.JButton jbInserirNovoAtendimento;
    private javax.swing.JButton jbInserirNovoCliente;
    private javax.swing.JButton jbLoadFromRegister;
    private javax.swing.JButton jbNovoAtendimento;
    private javax.swing.JButton jbNovoCliente;
    private javax.swing.JButton jbRemoverCliente;
    private javax.swing.JLabel jlCadastroClienteErro;
    private javax.swing.JLabel jlCadastroClienteErroEditar;
    private javax.swing.JLabel jlNomeAtendimentoErro;
    private javax.swing.JLabel jlNomeClienteErro;
    private javax.swing.JLabel jlNomeClienteErroEditar;
    private javax.swing.JLabel jlTelefoneAtendimentoErro;
    private javax.swing.JLabel jlTelefoneClienteErro;
    private javax.swing.JLabel jlTelefoneClienteErroEditar;
    private javax.swing.JMenu jmChooseService;
    private javax.swing.JMenuItem jmNovoAtendimento;
    private javax.swing.JMenuItem jmNovoCliente;
    private javax.swing.JMenuItem jmVoltar;
    private javax.swing.JPanel jpBarraLateral;
    private javax.swing.JPanel jpEditarCliente;
    private javax.swing.JPanel jpNovoAtendimento;
    private javax.swing.JPanel jpNovoCliente;
    private javax.swing.JPanel jpQueueInfo;
    private javax.swing.JTextField tfCadastroEditarCliente;
    private javax.swing.JTextField tfCadastroNovoAtendimento;
    private javax.swing.JTextField tfCadastroNovoCliente;
    private javax.swing.JTextField tfNomeEditarCliente;
    private javax.swing.JTextField tfNomeNovoAtendimento;
    private javax.swing.JTextField tfNomeNovoCliente;
    private javax.swing.JTextField tfTelefoneEditarCliente;
    private javax.swing.JTextField tfTelefoneNovoAtendimento;
    private javax.swing.JTextField tfTelefoneNovoCliente;
    // End of variables declaration//GEN-END:variables

    @Override
    public ArrayList<JMenu> createMenuItems()
    {
        ArrayList<JMenu> retorno = new ArrayList<>();
        retorno.add(jmChooseService);

        return retorno;
    }

    private void novoCliente()
    {
        jpNovoCliente.setVisible(true);
        jbNovoAtendimento.setVisible(false);
        jbEditarCliente.setVisible(false);
        jbNovoCliente.setVisible(false);
        jSeparator1.setVisible(true);
        jmVoltar.setVisible(true);
        jmNovoAtendimento.setEnabled(false);
        jmNovoCliente.setEnabled(false);
        jpNovoCliente.setVisible(true);
        jpQueueInfo.setVisible(false);        
    }

    private void novoAtendimento()
    {
        jbNovoAtendimento.setVisible(false);
        jbNovoCliente.setVisible(false);
        jbEditarCliente.setVisible(false);
        jSeparator1.setVisible(true);
        jmVoltar.setVisible(true);
        jmNovoAtendimento.setEnabled(false);
        jmNovoCliente.setEnabled(false);
        jpNovoAtendimento.setVisible(true);
        jpQueueInfo.setVisible(false);
        jpEditarCliente.setVisible(false);
        jpNovoCliente.setVisible(false);
        
        // carregar os serviços e jogar no comboBox
        controller.defineServicos(cbServico);
                
    }

    public void showQueueElementInfo( JPanel elementInfo )
    {
        try
        {
            //Remove o que tinha anteriormente
            jpQueueInfo.removeAll();
            elementInfo.setSize(220, 128);
            
            jpQueueInfo.add(elementInfo);
            jpQueueInfo.setVisible(true);
            jpQueueInfo.repaint();
            jbNovoAtendimento.setEnabled(false);
            elementInfo.doLayout();
            elementInfo.validate();
            
            QueueElementInfoSmall small = (QueueElementInfoSmall)elementInfo;

            PrinterJob job = PrinterJob.getPrinterJob();
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            PageFormat pf = job.pageDialog(aset);
            aset.add(new Copies(2));
            job.setPrintable(new Printer(small.getUserPass(), 
                    small.getQueueName(), small.getClientName()), pf);
            if (job.printDialog(aset))
            {
                try
                {
                    job.print(aset);
                } 
                catch (PrinterException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
            
            Main.getInstance().getMainFrame().activatePassControlPanel(new QueuePushScreen());
            Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
            
        }
        catch ( Exception ex )
        {
            Logger.getLogger(QueuePushScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void searchRegister()
    {
        clientBean = controller.loadRegister(tfCadastroNovoAtendimento.getText());
        if(clientBean != null)
        {
            tfNomeNovoAtendimento.setText(clientBean.getName());
            tfTelefoneNovoAtendimento.setText(clientBean.getTelefone());
        }
        else
        {
            tfNomeNovoAtendimento.setText("");
            tfTelefoneNovoAtendimento.setText("");
        }
    }

    private void inserirNovoAtendimento()
    {
        jpNovoCliente.setVisible(false);
        jpNovoAtendimento.setVisible(true);
        // pegando o usuário logado
        UserBean userBean = Main.getInstance().getCurrentUser();
        // pegando o bean dos clientes
        String serviceName = "";
        if(cbServico.getSelectedItem() != null)
        {
            serviceName = cbServico.getSelectedItem().toString();
            serviceBean = controller.getService(serviceName);
            ready = true;
        }
        else
        {
            jlNomeAtendimentoErro.setVisible(true);
            jlNomeAtendimentoErro.setToolTipText("Campo não pode ser vazio!");
            ready = false;
        }
        
        if(ready)
        {
            if(controller.insertNewAtendimento(clientBean, userBean, serviceBean))
            {
                JOptionPane.showMessageDialog(null, "registro salvo com sucesso!");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Erro ao salvar registro!");
            }
        }
    }

    private void validarNome( String nomeCliente, ArrayList<IValidation> validations )
    {
        // validando o nome
        if( ValidationPerform.valid(nomeCliente, validations))
        {
            ready = true;
            jlNomeClienteErro.setVisible(false);
        }
        else
        {
            jlNomeClienteErro.setVisible(true);
            jlNomeClienteErro.setToolTipText(ValidationPerform.getComment());
            ready = false;
        }
    }

    private void editarCliente()
    {
        jpEditarCliente.setVisible(true);
        jpNovoAtendimento.setVisible(false);
        jpNovoCliente.setVisible(false);
        jbEditarCliente.setVisible(false);
        jbNovoAtendimento.setVisible(false);
        jbNovoCliente.setVisible(false);
        jlCadastroClienteErroEditar.setVisible(false);
        jlNomeClienteErroEditar.setVisible(false);
        jlTelefoneClienteErroEditar.setVisible(false);
    }
}
