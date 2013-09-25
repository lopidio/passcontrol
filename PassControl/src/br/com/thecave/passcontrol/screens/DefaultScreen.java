package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controller.Main;
import br.com.thecave.passcontrolserver.util.PassControlConfigurationSynchronizer;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Arleudo
 */
public class DefaultScreen extends PassControlPanel
{

    /**
     * Creates new form DefaultScreen
     */
    public DefaultScreen()
    {
        //Acho que não preciso de controller
        super("Sistema Gerenciador de Filas", null);
        initComponents();
        
        //Atualizo a imagem principal
        PassControlConfigurationSynchronizer.getInstance().requestRefreshMainImage(Main.getInstance().getCommunicationThread());        
        //Seto a imagem principal (caso diferente da default)
        Image mainImage = PassControlConfigurationSynchronizer.getInstance().getMainImage();
        if (mainImage != null)
        {
            jlImageCenter.setIcon(new ImageIcon(mainImage));
        }
    }

    
    /**
     * This method is called from within the constructor to addClientListeners the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jlImageCenter = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jlImageCenter.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jlImageCenter.setForeground(new java.awt.Color(27, 147, 134));
        jlImageCenter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlImageCenter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/splash.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlImageCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 1347, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlImageCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlImageCenter;
    // End of variables declaration//GEN-END:variables

    @Override
    public ArrayList<JMenu> createMenuItems()
    {
        ArrayList<JMenu> retorno = new ArrayList<>();
        JMenu jmServerConfig = new javax.swing.JMenu();
        JMenuItem jmIP = new javax.swing.JMenuItem();
        JMenuItem jmPort = new javax.swing.JMenuItem();
        jmServerConfig.setFont(new Font("Square721 BT", Font.PLAIN, 14));

        jmServerConfig.setText("Configurações do servidor");

        jmIP.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        jmIP.setMnemonic('I');
        jmIP.setText("IP");
        jmIP.setFont(new Font("Square721 BT", Font.PLAIN, 14));
        jmIP.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed( java.awt.event.ActionEvent evt )
            {
//                jmAdminActionPerformed(evt);
            }
        });
        jmServerConfig.add(jmIP);

        jmPort.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jmPort.setMnemonic('P');
        jmPort.setFont(new Font("Square721 BT", Font.PLAIN, 14));
        jmPort.setText("Porta");
        jmPort.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed( java.awt.event.ActionEvent evt )
            {
//                jmGuicheActionPerformed(evt);
            }
        });
        jmServerConfig.add(jmPort);

        retorno.add(jmServerConfig);
        return retorno;
    }
}
