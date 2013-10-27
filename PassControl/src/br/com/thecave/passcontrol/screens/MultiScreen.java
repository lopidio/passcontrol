package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.screens.admin.*;
import br.com.thecave.passcontrol.controller.Main;
import br.com.thecave.passcontrol.controller.MultiScreenController;
import br.com.thecave.passcontrol.controller.UserCrudController;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import java.util.ArrayList;
import javax.swing.JMenu;

/**
 *
 * @author Arleudo
 */
public class MultiScreen extends PassControlPanel 
{
    MultiScreenController controller = null;
    /**
     * Creates new form AdminScreen
     */
    public MultiScreen() 
    {
        super("MultiScreen", new MultiScreenController());
        this.controller = (MultiScreenController) getPanelController();        
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

        jmAdmin = new javax.swing.JMenu();
        jmAdminstrador = new javax.swing.JMenuItem();
        jmVoltar = new javax.swing.JMenuItem();
        bgPermissao = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();

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

        jTabbedPane1.setMaximumSize(new java.awt.Dimension(1350, 600));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(1350, 600));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1350, 600));
        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jmAdminstradorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jmAdminstradorActionPerformed
    {//GEN-HEADEREND:event_jmAdminstradorActionPerformed
        Main.getInstance().getMainFrame().activatePassControlPanel(new AdminScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }//GEN-LAST:event_jmAdminstradorActionPerformed

    private void jmVoltarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jmVoltarActionPerformed
    {//GEN-HEADEREND:event_jmVoltarActionPerformed
        Main.getInstance().getMainFrame().activatePassControlPanel(new ButtonsModulesScreen());
        Main.getInstance().getMainFrame().activatePassControlTopBar(new MainTopBar());
    }//GEN-LAST:event_jmVoltarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgPermissao;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenu jmAdmin;
    private javax.swing.JMenuItem jmAdminstrador;
    private javax.swing.JMenuItem jmVoltar;
    // End of variables declaration//GEN-END:variables

    @Override
    public ArrayList<JMenu> createMenuItems() 
    {
        ArrayList<JMenu> ret = new ArrayList<JMenu>();
        ret.add(jmAdmin);
        jmVoltar.setVisible(false);
        return ret;
    }

}