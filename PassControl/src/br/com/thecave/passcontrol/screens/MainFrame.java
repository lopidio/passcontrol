package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controler.Main;
import br.com.thecave.passcontrol.controler.PassControlController;
import br.com.thecave.passcontrol.topbar.LoginTopBar;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import br.com.thecave.passcontrol.topbar.PassControlTopBar;
import br.com.thecave.passcontrolserver.messages.generic.ClientLogoff;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JMenu;

/**
 *
 * @author Antonio Arleudo da costa
 */
public final class MainFrame extends javax.swing.JFrame {
    private int DEFAULT_MENU_COUNT = -1;

    /**
     * Creates new form AdministratorScreen
     */
    public MainFrame() 
    {
        initComponents();

        topBar.setLayout(new BoxLayout(topBar, BoxLayout.Y_AXIS));
        passControlPanel.setLayout(new BoxLayout(passControlPanel, BoxLayout.Y_AXIS));
        DEFAULT_MENU_COUNT = menuBar.getMenuCount();
        
        activatePassControlPanel(new DefaultScreen());
        activatePassControlTopBar(new LoginTopBar());
        setEnableNavigatorMenu(false);
        
    }
    
    public void activatePassControlPanel(PassControlPanel newPassControlPanel)
    {
        for (Component component : passControlPanel.getComponents())
        {
            try
            {
                PassControlPanel castPrevPassControl = (PassControlPanel)component;
                PassControlController passControlController = castPrevPassControl.getPanelController();
                if (passControlController != null)
                {
                    passControlController.removeMessageListeners();
                }
            }catch (ClassCastException exc)
            {
                //do nothing
            }
        }

        //Verifica se tem controller, caso tenha, inicializa e cadastra os escutadores de eventos
        if (newPassControlPanel.initializeController())
        {
            //Adiciona aos escutadores de eventos
            newPassControlPanel.getPanelController().addMessageListeners();
        }        

        //Remove o atual
        passControlPanel.removeAll();
        
        //Adiciona o novo
        passControlPanel.add(newPassControlPanel);
        
        //Removo os ítens antigos do menu
        for (int i = 0; i < menuBar.getMenuCount() - DEFAULT_MENU_COUNT ; ++i)//2 pq sempre existem dois valores
        {
            menuBar.remove(i);
        }
        
        //Seta o título atual
        setTitle(newPassControlPanel.getPassControlPanelTitle());
        
        //Adiciono os novos menus do ítem
        for (JMenu novoMenu : newPassControlPanel.createMenuItems())
        {
            menuBar.add(novoMenu, 0);
        }
        
        passControlPanel.setVisible(true);
        passControlPanel.revalidate();
        passControlPanel.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();
        
        //Mais ou menos assim
    }
    
    public void activatePassControlTopBar(PassControlTopBar newPassControlTopBar)
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

        //Verifica se tem controller, caso tenha, inicializa e cadastra os escutadores de eventos
        if (newPassControlTopBar.initializeController())
        {
            //Adiciona aos escutadores de eventos
            newPassControlTopBar.getPanelController().addMessageListeners();
        }        
                

        //Remove o atual
        topBar.removeAll();
        //Adiciona o novo
        topBar.add(newPassControlTopBar);
        

        topBar.setVisible(true);
        topBar.revalidate();
        topBar.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();
        
        //Mais ou menos assim
    }
    
    public void disableControlPanel()
    {
        passControlPanel.setEnabled(false);
        //ítens do menu
        for (int i = 0; i < menuBar.getMenuCount() - DEFAULT_MENU_COUNT ; ++i)//Pq existem os valores que nunca são removidos
        {
            menuBar.setEnabled(false);
        }        
    }
    
    public void enableControlPanel()
    {
        passControlPanel.setEnabled(true);
        //ítens do menu
        for (int i = 0; i < menuBar.getMenuCount() - DEFAULT_MENU_COUNT; ++i)//Pq existem os valores que nunca são removidos
        {
            menuBar.setEnabled(true);
        }        
    }
        
    public PassControlPanel getCurrentPassControlPanel()
    {
        return (PassControlPanel)passControlPanel.getComponent(0);        
    }
    
    public PassControlTopBar getCurrentPassControlTopBar()
    {
        return (PassControlTopBar)topBar.getComponent(0);
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
        menuBar = new javax.swing.JMenuBar();
        jmNavegar = new javax.swing.JMenu();
        jmMainPage = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmLogoff = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmSair = new javax.swing.JMenuItem();
        jmSobre = new javax.swing.JMenu();
        jmAjuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle de Senhas");
        setBackground(new java.awt.Color(255, 255, 255));
        setName("frmScreenLogin"); // NOI18N
        setResizable(false);

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
            .addGap(0, 589, Short.MAX_VALUE)
        );

        jmNavegar.setText("Navegar");

        jmMainPage.setText("Página principal");
        jmMainPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmMainPageActionPerformed(evt);
            }
        });
        jmNavegar.add(jmMainPage);
        jmNavegar.add(jSeparator2);

        jmLogoff.setText("Logoff");
        jmLogoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmLogoffActionPerformed(evt);
            }
        });
        jmNavegar.add(jmLogoff);
        jmNavegar.add(jSeparator1);

        jmSair.setText("Sair");
        jmSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSairActionPerformed(evt);
            }
        });
        jmNavegar.add(jmSair);

        menuBar.add(jmNavegar);

        jmSobre.setText("Sobre");
        menuBar.add(jmSobre);

        jmAjuda.setText("Ajuda");
        menuBar.add(jmAjuda);

        setJMenuBar(menuBar);

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

    private void jmSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSairActionPerformed
        System.exit(0);        
    }//GEN-LAST:event_jmSairActionPerformed

    public void performLogoutAction()
    {
        activatePassControlPanel(new DefaultScreen());
        activatePassControlTopBar(new LoginTopBar());
        setEnableNavigatorMenu(false);                      
        //Informa ao servidor que o usuário realizou logoff
        Main.getInstance().getCommunicationThread().addBroadcastToSend(new ClientLogoff());        
    }
    
    private void jmLogoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmLogoffActionPerformed
        performLogoutAction();
    }//GEN-LAST:event_jmLogoffActionPerformed

    private void jmMainPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMainPageActionPerformed
        activatePassControlPanel(new ButtonsModulesScreen());
        activatePassControlTopBar(new MainTopBar());
    }//GEN-LAST:event_jmMainPageActionPerformed

    public void setEnableNavigatorMenu(boolean enabled)
    {
        jmLogoff.setEnabled(enabled);
        jmMainPage.setEnabled(enabled);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenu jmAjuda;
    private javax.swing.JMenuItem jmLogoff;
    private javax.swing.JMenuItem jmMainPage;
    private javax.swing.JMenu jmNavegar;
    private javax.swing.JMenuItem jmSair;
    private javax.swing.JMenu jmSobre;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel passControlPanel;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables


}

