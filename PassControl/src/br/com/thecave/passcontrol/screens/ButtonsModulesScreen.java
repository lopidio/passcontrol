package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controller.ButtonModulesController;
import br.com.thecave.passcontrol.screens.admin.AdminScreen;
import br.com.thecave.passcontrol.controller.Main;
import br.com.thecave.passcontrol.topbar.BalconyLoginTopBar;
import br.com.thecave.passcontrol.topbar.ViewerTopBar;
import br.com.thecave.passcontrolserver.messages.generic.ChangeActorMessage;
import br.com.thecave.passcontrolserver.messages.generic.MessageActors;
import br.com.thecave.passcontrolserver.util.UserPermission;
import java.util.ArrayList;
import javax.swing.JMenu;

/**
 *
 * @author Arleudo
 */
public class ButtonsModulesScreen extends PassControlPanel
{

    ButtonModulesController modulesController = null;

    @Override
    public ArrayList<JMenu> createMenuItems()
    {
        ArrayList<JMenu> retorno = new ArrayList<>();
        retorno.add(jmModules);

        return retorno;
    }

    /**
     * Creates new form DefaultScreen
     */
    public ButtonsModulesScreen()
    {
        //Acho que não preciso de controller
        super("Sistema Gerenciador de Filas", new ButtonModulesController());
        modulesController = (ButtonModulesController) getPanelController();
        initComponents();

        verifyPermissions();
        Main.getInstance().getMainFrame().setEnableNavigatorMenu(true);
        Main.getInstance().setCurrentActors(MessageActors.NotIdentified);
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

        jmModules = new javax.swing.JMenu();
        jmAdmin = new javax.swing.JMenuItem();
        jmGuiche = new javax.swing.JMenuItem();
        jmViewer = new javax.swing.JMenuItem();
        jmQueuePush = new javax.swing.JMenuItem();
        jmQueuePop = new javax.swing.JMenuItem();
        jbBalcony = new javax.swing.JButton();
        jbViewer = new javax.swing.JButton();
        jbAdd = new javax.swing.JButton();
        jbAdmin = new javax.swing.JButton();
        jbRemove = new javax.swing.JButton();

        jmModules.setText("Módulos");

        jmAdmin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jmAdmin.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmAdmin.setMnemonic('a');
        jmAdmin.setText("Administrador");
        jmAdmin.setEnabled(false);
        jmAdmin.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmAdminActionPerformed(evt);
            }
        });
        jmModules.add(jmAdmin);

        jmGuiche.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
        jmGuiche.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmGuiche.setMnemonic('g');
        jmGuiche.setText("Guichês");
        jmGuiche.setEnabled(false);
        jmGuiche.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmGuicheActionPerformed(evt);
            }
        });
        jmModules.add(jmGuiche);

        jmViewer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jmViewer.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmViewer.setMnemonic('v');
        jmViewer.setText("Visualizador");
        jmViewer.setEnabled(false);
        jmViewer.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmViewerActionPerformed(evt);
            }
        });
        jmModules.add(jmViewer);

        jmQueuePush.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        jmQueuePush.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmQueuePush.setMnemonic('d');
        jmQueuePush.setText("Adicionar");
        jmQueuePush.setEnabled(false);
        jmQueuePush.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmQueuePushActionPerformed(evt);
            }
        });
        jmModules.add(jmQueuePush);

        jmQueuePop.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        jmQueuePop.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmQueuePop.setMnemonic('r');
        jmQueuePop.setText("Remover");
        jmQueuePop.setEnabled(false);
        jmQueuePop.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmQueuePopActionPerformed(evt);
            }
        });
        jmModules.add(jmQueuePop);

        setBackground(new java.awt.Color(255, 255, 255));

        jbBalcony.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/balcony_button.png"))); // NOI18N
        jbBalcony.setToolTipText("Clique para abrir a tela do guichê.");
        jbBalcony.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbBalcony.setEnabled(false);
        jbBalcony.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HiperButtons_Balcony_Over.png"))); // NOI18N
        jbBalcony.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HiperButton_Balcony_Clicked.png"))); // NOI18N
        jbBalcony.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbBalconyActionPerformed(evt);
            }
        });

        jbViewer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/viewer_button.png"))); // NOI18N
        jbViewer.setToolTipText("Clique para abrir a tela de visualizador.");
        jbViewer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbViewer.setEnabled(false);
        jbViewer.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HiperButton_Visualizador_Over.png"))); // NOI18N
        jbViewer.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HiperButton_Visualizador_Clicked.png"))); // NOI18N
        jbViewer.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbViewerActionPerformed(evt);
            }
        });

        jbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/queue_push_button.png"))); // NOI18N
        jbAdd.setToolTipText("Clique para abrir a tela de recepção.");
        jbAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbAdd.setEnabled(false);
        jbAdd.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HiperButton_Adicionar_Over.png"))); // NOI18N
        jbAdd.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HiperButton_Adicionar_Clicked.png"))); // NOI18N
        jbAdd.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbAddActionPerformed(evt);
            }
        });

        jbAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/admin_button.png"))); // NOI18N
        jbAdmin.setToolTipText("Clique aqui para tela de administração.");
        jbAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbAdmin.setEnabled(false);
        jbAdmin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HiperButton_Administrar_Over.png"))); // NOI18N
        jbAdmin.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HiperButton_Administrar_Clicked.png"))); // NOI18N
        jbAdmin.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbAdminActionPerformed(evt);
            }
        });

        jbRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/queue_pop_button.png"))); // NOI18N
        jbRemove.setToolTipText("Clique para abrir a tela de remoção da fila.");
        jbRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbRemove.setEnabled(false);
        jbRemove.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HiperButton_Remover_Over.png"))); // NOI18N
        jbRemove.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HiperButton_Remover_Clicked.png"))); // NOI18N
        jbRemove.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jbRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(255, Short.MAX_VALUE)
                        .addComponent(jbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jbBalcony, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jbViewer, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(420, 420, 420)
                        .addComponent(jbAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jbRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(244, 244, 244))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbViewer, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbBalcony, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(307, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAdminActionPerformed
        openAdmin();
    }//GEN-LAST:event_jbAdminActionPerformed

    private void jbBalconyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBalconyActionPerformed
        openBalcony();
    }//GEN-LAST:event_jbBalconyActionPerformed

    private void jbViewerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbViewerActionPerformed
        openViewer();
    }//GEN-LAST:event_jbViewerActionPerformed

    private void jbAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAddActionPerformed
        openQueuePush();
    }//GEN-LAST:event_jbAddActionPerformed

    private void jbRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoveActionPerformed
        openQueuePop();
    }//GEN-LAST:event_jbRemoveActionPerformed

    private void jmAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAdminActionPerformed
        openAdmin();
    }//GEN-LAST:event_jmAdminActionPerformed

    private void jmGuicheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmGuicheActionPerformed
        openBalcony();
    }//GEN-LAST:event_jmGuicheActionPerformed

    private void jmViewerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmViewerActionPerformed
        openViewer();
    }//GEN-LAST:event_jmViewerActionPerformed

    private void jmQueuePushActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmQueuePushActionPerformed
        openQueuePush();
    }//GEN-LAST:event_jmQueuePushActionPerformed

    private void jmQueuePopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmQueuePopActionPerformed
        openQueuePop();
    }//GEN-LAST:event_jmQueuePopActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAdd;
    private javax.swing.JButton jbAdmin;
    private javax.swing.JButton jbBalcony;
    private javax.swing.JButton jbRemove;
    private javax.swing.JButton jbViewer;
    private javax.swing.JMenuItem jmAdmin;
    private javax.swing.JMenuItem jmGuiche;
    private javax.swing.JMenu jmModules;
    private javax.swing.JMenuItem jmQueuePop;
    private javax.swing.JMenuItem jmQueuePush;
    private javax.swing.JMenuItem jmViewer;
    // End of variables declaration//GEN-END:variables

    private void openAdmin()
    {
        Main.getInstance().setCurrentActors(MessageActors.AdministratorActor);
        Main.getInstance().getMainFrame().activatePassControlPanel(new AdminScreen());
    }

    private void openBalcony()
    {
        Main.getInstance().setCurrentActors(MessageActors.BalconyActor);
        BalconyScreen screen = new BalconyScreen();
        BalconyLoginTopBar topBarIntro = new BalconyLoginTopBar();
        topBarIntro.setScreen(screen);
        Main.getInstance().getMainFrame().activatePassControlPanel(screen);
        Main.getInstance().getMainFrame().activatePassControlTopBar(topBarIntro);
    }

    private void openViewer()
    {
        Main.getInstance().setCurrentActors(MessageActors.ViewerActor);
        Main.getInstance().getMainFrame().activatePassControlPanel(new ViewerScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new ViewerTopBar());
    }

    private void openQueuePush()
    {
        Main.getInstance().setCurrentActors(MessageActors.QueuePushActor);
        Main.getInstance().getMainFrame().activatePassControlPanel(new QueuePushScreen());
    }

    private void openQueuePop()
    {
        Main.getInstance().setCurrentActors(MessageActors.QueuePopActor);
        Main.getInstance().getMainFrame().activatePassControlPanel(new QueuePopScreen());
    }

    private void verifyPermissions()
    {
        int permissionCode = Main.getInstance().getCurrentUser().getType();
        UserPermission userPermission = new UserPermission(permissionCode);
        if ( userPermission.hasAdminPermission() )// É capaz de entrar na opção jbAdmin
        {
            jbAdmin.setEnabled(true);
            jmAdmin.setEnabled(true);
        }
        if ( userPermission.hasBalconyPermission() )// É capaz de entrar na opção jbBalcony
        {
            jbBalcony.setEnabled(true);
            jmGuiche.setEnabled(true);
        }
        if ( userPermission.hasViewerPermission() )// É capaz de entrar na opção jbViewer
        {
            jbViewer.setEnabled(true);
            jmViewer.setEnabled(true);
        }
        if ( userPermission.hasPusherPermission() )// É capaz de entrar na opção jbAdd
        {
            jbAdd.setEnabled(true);
            jmQueuePush.setEnabled(true);
        }
        if ( userPermission.hasPopperPermission() )// É capaz de entrar na opção jbRemove
        {
            jbRemove.setEnabled(true);
            jmQueuePop.setEnabled(true);
        }
    }
}
