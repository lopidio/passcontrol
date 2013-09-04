package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controler.PassControlController;
import br.com.thecave.passcontrol.topbar.LoginTopBar;
import br.com.thecave.passcontrol.topbar.PassControlTopBar;
import java.awt.Component;
import javax.swing.BoxLayout;

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

        topBar.setLayout(new BoxLayout(topBar, BoxLayout.Y_AXIS));
        passControlPanel.setLayout(new BoxLayout(passControlPanel, BoxLayout.Y_AXIS));
        
        activatePassControlPanel(new DefaultScreen());
        activatePassControlMenu(new LoginTopBar());
        
    }
    
    public void activatePassControlPanel(PassControlPanel newPassControlPanel)
    {
        for (Component component : passControlPanel.getComponents())
        {
            try
            {
                PassControlPanel castPrevPassControl = (PassControlPanel)component;
                castPrevPassControl.getPanelController().removeMessageListeners();
            }catch (ClassCastException exc)
            {
                //do nothing
            }
        }


        //Remove o atual
        passControlPanel.removeAll();
        //Adiciona o novo
        passControlPanel.add(newPassControlPanel);
        //Seta o título atual
        setTitle(newPassControlPanel.getName());
        //Seta o menuBar atual
        if (newPassControlPanel.createMenu() != null)
        {
            setJMenuBar(newPassControlPanel.getMenu());
        }
        
        //Adiciona aos escutadores de eventos
        PassControlController newController = newPassControlPanel.getPanelController();
        if (newController != null)
        {
            newPassControlPanel.getPanelController().addMessageListeners();
        }
        
        passControlPanel.setVisible(true);
        passControlPanel.revalidate();
        passControlPanel.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();
        
        //Mais ou menos assim
    }
    
    public void activatePassControlMenu(PassControlTopBar passControlTopBar)
    {
        for (Component component : topBar.getComponents())
        {
            try
            {
                PassControlPanel castPrevPassControl = (PassControlPanel)component;
                castPrevPassControl.getPanelController().removeMessageListeners();
            }catch (ClassCastException exc)
            {
                //do nothing
            }
        }


        //Remove o atual
        topBar.removeAll();
        //Adiciona o novo
        topBar.add(passControlTopBar);
        
        //Adiciona aos escutadores de eventos
        PassControlController newController = passControlTopBar.getPanelController();
        if (newController != null)
        {
            passControlTopBar.getPanelController().addMessageListeners();
        }
        
        topBar.setVisible(true);
        topBar.revalidate();
        topBar.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();
        
        //Mais ou menos assim
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topBar = new javax.swing.JPanel();
        passControlPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle de Senhas");
        setBackground(new java.awt.Color(255, 255, 255));
        setName("frmScreenLogin"); // NOI18N

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1351, Short.MAX_VALUE)
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );

        passControlPanel.setBackground(new java.awt.Color(215, 45, 65));

        javax.swing.GroupLayout passControlPanelLayout = new javax.swing.GroupLayout(passControlPanel);
        passControlPanel.setLayout(passControlPanelLayout);
        passControlPanelLayout.setHorizontalGroup(
            passControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1351, Short.MAX_VALUE)
        );
        passControlPanelLayout.setVerticalGroup(
            passControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(passControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel passControlPanel;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables


}

