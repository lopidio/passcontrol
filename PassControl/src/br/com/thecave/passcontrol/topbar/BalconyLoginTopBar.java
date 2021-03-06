package br.com.thecave.passcontrol.topbar;

import br.com.thecave.passcontrol.controller.BalconyLoginTopBarController;
import br.com.thecave.passcontrol.controller.Main;
import br.com.thecave.passcontrol.screens.BalconyScreen;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Arleudo
 */
public class BalconyLoginTopBar extends PassControlTopBar
{

    private BalconyLoginTopBarController balconyLoginTopBarController;
    private BalconyScreen screen;

    /**
     * Creates new form TopBar
     */
    public BalconyLoginTopBar()
    {
        super(new BalconyLoginTopBarController());
        initComponents();
        balconyLoginTopBarController = (BalconyLoginTopBarController) getPanelController();
        lbNameUser.setText(Main.getInstance().getCurrentUser().getName());
        jbConfirm.setEnabled(false);
        addPanelConnectionInfo();
//        balconyLoginTopBarController.defineCBNames(cbNumero);
    }

    public void enableConfirmButton()
    {
        jbConfirm.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlIcon = new javax.swing.JLabel();
        jlBarra = new javax.swing.JLabel();
        jlName = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lbNameUser = new javax.swing.JLabel();
        lbLogout = new javax.swing.JLabel();
        lbNumero = new javax.swing.JLabel();
        cbNumero = new javax.swing.JComboBox();
        jbConfirm = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 191));
        setForeground(new java.awt.Color(0, 153, 191));
        setPreferredSize(new java.awt.Dimension(1376, 128));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_medium.png"))); // NOI18N
        jlIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlIconMouseClicked(evt);
            }
        });
        add(jlIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 3, 106, 122));

        jlBarra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/barra.png"))); // NOI18N
        add(jlBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 3, -1, 122));

        jlName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/name_system.png"))); // NOI18N
        jlName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlNameMouseClicked(evt);
            }
        });
        add(jlName, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 3, 222, 122));

        lblUser.setFont(new java.awt.Font("Square721 BT", 1, 14)); // NOI18N
        lblUser.setForeground(new java.awt.Color(255, 255, 255));
        lblUser.setText("Usuário:");
        add(lblUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1228, 3, -1, 22));

        lbNameUser.setFont(new java.awt.Font("Square721 BT", 1, 14)); // NOI18N
        lbNameUser.setForeground(new java.awt.Color(255, 255, 255));
        lbNameUser.setText("Isso ira mudar");
        add(lbNameUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1228, 31, -1, -1));

        lbLogout.setFont(new java.awt.Font("Square721 BT", 1, 14)); // NOI18N
        lbLogout.setForeground(new java.awt.Color(255, 255, 255));
        lbLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logout.png"))); // NOI18N
        lbLogout.setText("Logout");
        lbLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbLogoutMouseClicked(evt);
            }
        });
        add(lbLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1271, 107, -1, -1));

        lbNumero.setFont(new java.awt.Font("Square721 BT", 1, 14)); // NOI18N
        lbNumero.setForeground(new java.awt.Color(255, 255, 255));
        lbNumero.setText("Guichê:");
        add(lbNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, 28));

        cbNumero.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        cbNumero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        add(cbNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 19, 185, 30));

        jbConfirm.setFont(new java.awt.Font("Square721 BT", 0, 12)); // NOI18N
        jbConfirm.setText("OK");
        jbConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbConfirmActionPerformed(evt);
            }
        });
        add(jbConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(806, 19, -1, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void lbLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbLogoutMouseClicked
        Main.getInstance().getMainFrame().performLogoutAction();
    }//GEN-LAST:event_lbLogoutMouseClicked

    private void jbConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbConfirmActionPerformed
        balconyLoginTopBarController.confirmButtonPressed(cbNumero.getSelectedIndex());
        this.screen.initialize();
    }//GEN-LAST:event_jbConfirmActionPerformed

    private void jlIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlIconMouseClicked
        Main.getInstance().getMainFrame().activateMainButtonsPage();
    }//GEN-LAST:event_jlIconMouseClicked

    private void jlNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlNameMouseClicked
        Main.getInstance().getMainFrame().activateMainButtonsPage();
    }//GEN-LAST:event_jlNameMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbNumero;
    private javax.swing.JButton jbConfirm;
    private javax.swing.JLabel jlBarra;
    private javax.swing.JLabel jlIcon;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel lbLogout;
    private javax.swing.JLabel lbNameUser;
    private javax.swing.JLabel lbNumero;
    private javax.swing.JLabel lblUser;
    // End of variables declaration//GEN-END:variables

    public void setBalconyNumbers( ArrayList<BalconyBean> avaliableBalcony )
    {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        for ( BalconyBean balconyBean : avaliableBalcony )
        {
            defaultComboBoxModel.addElement(balconyBean.getNumber());
        }
        cbNumero.setModel(defaultComboBoxModel);
    }

    @Override
    public void blockPassControlTopBar()
    {
        cbNumero.setEnabled(false);
        jbConfirm.setEnabled(false);
    }

    @Override
    public void unblockPassControlTopBar()
    {
        cbNumero.setEnabled(true);
        jbConfirm.setEnabled(true);
    }

    public void setScreen( BalconyScreen screen )
    {
        this.screen = screen;
    }
}
