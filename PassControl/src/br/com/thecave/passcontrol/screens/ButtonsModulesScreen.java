package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controller.ButtonModulesController;
import br.com.thecave.passcontrol.screens.admin.AdminScreen;
import br.com.thecave.passcontrol.controller.Main;
import br.com.thecave.passcontrol.topbar.BalconyTopBarIntro;
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
        Main.getInstance().getCommunicationThread().addBroadcastToSend(new ChangeActorMessage(MessageActors.NotIdentified));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmModules = new javax.swing.JMenu();
        jmAdmin = new javax.swing.JMenuItem();
        jmGuiche = new javax.swing.JMenuItem();
        jmViewer = new javax.swing.JMenuItem();
        jmQueuePush = new javax.swing.JMenuItem();
        jmQueuePop = new javax.swing.JMenuItem();
        jmConfig = new javax.swing.JMenuItem();
        jbBalcony = new javax.swing.JButton();
        jbViewer = new javax.swing.JButton();
        jbAdd = new javax.swing.JButton();
        jbConfig = new javax.swing.JButton();
        jbAdmin = new javax.swing.JButton();
        jbRemove = new javax.swing.JButton();

        jmModules.setText("Módulos");

        jmAdmin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jmAdmin.setMnemonic('a');
        jmAdmin.setText("Administrador");
        jmAdmin.setEnabled(false);
        jmAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAdminActionPerformed(evt);
            }
        });
        jmModules.add(jmAdmin);

        jmGuiche.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
        jmGuiche.setMnemonic('g');
        jmGuiche.setText("Guichês");
        jmGuiche.setEnabled(false);
        jmGuiche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmGuicheActionPerformed(evt);
            }
        });
        jmModules.add(jmGuiche);

        jmViewer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jmViewer.setMnemonic('v');
        jmViewer.setText("Visualizador");
        jmViewer.setEnabled(false);
        jmViewer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmViewerActionPerformed(evt);
            }
        });
        jmModules.add(jmViewer);

        jmQueuePush.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        jmQueuePush.setMnemonic('d');
        jmQueuePush.setText("Adicionar");
        jmQueuePush.setEnabled(false);
        jmQueuePush.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmQueuePushActionPerformed(evt);
            }
        });
        jmModules.add(jmQueuePush);

        jmQueuePop.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        jmQueuePop.setMnemonic('r');
        jmQueuePop.setText("Remover");
        jmQueuePop.setEnabled(false);
        jmQueuePop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmQueuePopActionPerformed(evt);
            }
        });
        jmModules.add(jmQueuePop);

        jmConfig.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jmConfig.setMnemonic('c');
        jmConfig.setText("Configurações");
        jmConfig.setEnabled(false);
        jmConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmConfigActionPerformed(evt);
            }
        });
        jmModules.add(jmConfig);

        setBackground(new java.awt.Color(255, 255, 255));

        jbBalcony.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/balcony_button.png"))); // NOI18N
        jbBalcony.setEnabled(false);
        jbBalcony.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBalconyActionPerformed(evt);
            }
        });

        jbViewer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/viewer_button.png"))); // NOI18N
        jbViewer.setEnabled(false);
        jbViewer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbViewerActionPerformed(evt);
            }
        });

        jbAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/queue_push_button.png"))); // NOI18N
        jbAdd.setEnabled(false);
        jbAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAddActionPerformed(evt);
            }
        });

        jbConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/config_button.png"))); // NOI18N
        jbConfig.setEnabled(false);
        jbConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbConfigActionPerformed(evt);
            }
        });

        jbAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/admin_button_1.png"))); // NOI18N
        jbAdmin.setEnabled(false);
        jbAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAdminActionPerformed(evt);
            }
        });

        jbRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/queue_pop_button.png"))); // NOI18N
        jbRemove.setEnabled(false);
        jbRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(255, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbBalcony, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbViewer, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jbAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jbConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbConfigActionPerformed
        openConfig();
    }//GEN-LAST:event_jbConfigActionPerformed

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

    private void jmConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmConfigActionPerformed
        openConfig();
    }//GEN-LAST:event_jmConfigActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAdd;
    private javax.swing.JButton jbAdmin;
    private javax.swing.JButton jbBalcony;
    private javax.swing.JButton jbConfig;
    private javax.swing.JButton jbRemove;
    private javax.swing.JButton jbViewer;
    private javax.swing.JMenuItem jmAdmin;
    private javax.swing.JMenuItem jmConfig;
    private javax.swing.JMenuItem jmGuiche;
    private javax.swing.JMenu jmModules;
    private javax.swing.JMenuItem jmQueuePop;
    private javax.swing.JMenuItem jmQueuePush;
    private javax.swing.JMenuItem jmViewer;
    // End of variables declaration//GEN-END:variables

    private void openAdmin()
    {
        Main.getInstance().getCommunicationThread().addBroadcastToSend(new ChangeActorMessage(MessageActors.AdministratorActor));
        Main.getInstance().getMainFrame().activatePassControlPanel(new AdminScreen());
    }

    private void openBalcony()
    {
        Main.getInstance().getCommunicationThread().addBroadcastToSend(new ChangeActorMessage(MessageActors.BalconyActor));
        BalconyScreen screen = new BalconyScreen();
        BalconyTopBarIntro topBarIntro = new BalconyTopBarIntro();
        topBarIntro.setScreen(screen);
        Main.getInstance().getMainFrame().activatePassControlPanel(screen);
        Main.getInstance().getMainFrame().activatePassControlTopBar(topBarIntro);
    }

    private void openViewer()
    {
        Main.getInstance().getCommunicationThread().addBroadcastToSend(new ChangeActorMessage(MessageActors.ViewerActor));
        Main.getInstance().getMainFrame().activatePassControlPanel(new ViewerScreen());
    }

    private void openQueuePush()
    {
        Main.getInstance().getCommunicationThread().addBroadcastToSend(new ChangeActorMessage(MessageActors.QueuePushActor));
        Main.getInstance().getMainFrame().activatePassControlPanel(new QueuePushScreen());
    }

    private void openQueuePop()
    {
        Main.getInstance().getCommunicationThread().addBroadcastToSend(new ChangeActorMessage(MessageActors.QueuePopActor));
        Main.getInstance().getMainFrame().activatePassControlPanel(new QueuePopScreen());
    }

    private void openConfig()
    {
        Main.getInstance().getMainFrame().activatePassControlPanel(new ConfigScreen());
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
        if ( userPermission.hasConfigPermission() )// É capaz de entrar na opção jbConfig
        {
            jbConfig.setEnabled(true);
            jmConfig.setEnabled(true);
        }
    }
}
