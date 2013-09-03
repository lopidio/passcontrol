package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controler.Main;
import br.com.thecave.passcontrol.viewer.PresentationControler;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Antonio Arleudo da costa
 */
public final class MainScreen extends javax.swing.JFrame {

    /**
     * Creates new form AdministratorScreen
     */
    public MainScreen() 
    {
        initComponents();

        //activatePassControlPanel(new LoginScreen());
        
    }
    
    public void activatePassControlPanel(PassControlPanel passControlPanel)
    {
        //Remove o atual
        //Adiciona o novo
        setTitle(passControlPanel.getName());
        menuBar = passControlPanel.getMenu();
        
        //Mais ou menos assim
    }
    
    public void setTopMenuBar(JMenuBar menuBar)
    {
        //Remove o atual
        //Adiciona o novo
        
        //Mais ou menos assim
        
        //E águas paradas
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topMenu = new javax.swing.JPanel();
        passControlPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle de Senhas");
        setBackground(new java.awt.Color(255, 255, 255));
        setName("frmScreenLogin"); // NOI18N

        topMenu.setBackground(new java.awt.Color(187, 215, 248));
        topMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 136, 219), 3));
        topMenu.setAutoscrolls(true);

        javax.swing.GroupLayout topMenuLayout = new javax.swing.GroupLayout(topMenu);
        topMenu.setLayout(topMenuLayout);
        topMenuLayout.setHorizontalGroup(
            topMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1345, Short.MAX_VALUE)
        );
        topMenuLayout.setVerticalGroup(
            topMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 93, Short.MAX_VALUE)
        );

        passControlPanel.setBackground(new java.awt.Color(255, 255, 255));
        passControlPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));

        javax.swing.GroupLayout passControlPanelLayout = new javax.swing.GroupLayout(passControlPanel);
        passControlPanel.setLayout(passControlPanelLayout);
        passControlPanelLayout.setHorizontalGroup(
            passControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1345, Short.MAX_VALUE)
        );
        passControlPanelLayout.setVerticalGroup(
            passControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
        );

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(passControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel passControlPanel;
    private javax.swing.JPanel topMenu;
    // End of variables declaration//GEN-END:variables


}

