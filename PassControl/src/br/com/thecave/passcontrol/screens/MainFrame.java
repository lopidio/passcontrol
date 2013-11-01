package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controller.Main;
import br.com.thecave.passcontrol.controller.PassControlController;
import br.com.thecave.passcontrol.topbar.LoginTopBar;
import br.com.thecave.passcontrol.topbar.MainTopBar;
import br.com.thecave.passcontrol.topbar.PassControlTopBar;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JMenu;

/**
 *
 * @author Antonio Arleudo da costa
 */
public final class MainFrame extends javax.swing.JFrame
{

    /**
     * Creates new form AdministratorScreen
     */
    public MainFrame()
    {
        initComponents();

        topBar.setLayout(new BoxLayout(topBar, BoxLayout.Y_AXIS));
        passControlPanel.setLayout(new BoxLayout(passControlPanel, BoxLayout.Y_AXIS));
    }

    public void activatePassControlPanel( PassControlPanel newPassControlPanel )
    {
        for ( Component component : passControlPanel.getComponents() )
        {
            try
            {
                PassControlPanel castPrevPassControl = (PassControlPanel) component;
                PassControlController passControlController = castPrevPassControl.getPanelController();
                if ( passControlController != null )
                {
                    passControlController.removeMessageListeners();
                }
            }
            catch ( ClassCastException exc )
            {
                //do nothing
            }
        }

        //Verifica se tem controller, caso tenha, inicializa e cadastra os escutadores de eventos
        if ( newPassControlPanel.initializeController() )
        {
            //Adiciona aos escutadores de eventos
            newPassControlPanel.getPanelController().addMessageListeners();
        }

        //Remove o atual
        passControlPanel.removeAll();

        //Adiciona o novo
        passControlPanel.add(newPassControlPanel);

        //Seta o título atual
        setTitle(newPassControlPanel.getPassControlPanelTitle());

        //Adiciono os novos menus do ítem
        menuBar.removeAll();
        menuBar.add(jmNavegar);
        for ( JMenu novoMenu : newPassControlPanel.createMenuItems() )
        {
            //Insere em segundo lugar. O primeiro sempre vai ser navegar
            menuBar.add(novoMenu, 1);
        }
        menuBar.add(jmSobre);
        menuBar.add(jmAjuda);


        passControlPanel.setVisible(true);
        passControlPanel.revalidate();
        passControlPanel.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();

        //Mais ou menos assim
    }

    public void activatePassControlTopBar( PassControlTopBar newPassControlTopBar )
    {
        for ( Component component : topBar.getComponents() )
        {
            try
            {
                PassControlPanel castPrevPassControl = (PassControlPanel) component;
                castPrevPassControl.getPanelController().removeMessageListeners();
            }
            catch ( ClassCastException exc )
            {
                //do nothing
            }
        }

        //Verifica se tem controller, caso tenha, inicializa e cadastra os escutadores de eventos
        if ( newPassControlTopBar.initializeController() )
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
        menuBar.setEnabled(false);
    }

    public void enableControlPanel()
    {
        passControlPanel.setEnabled(true);
        menuBar.setEnabled(true);
    }

    public PassControlPanel getCurrentPassControlPanel()
    {
        return (PassControlPanel) passControlPanel.getComponent(0);
    }

    public PassControlTopBar getCurrentPassControlTopBar()
    {
        return (PassControlTopBar) topBar.getComponent(0);
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
        jMenuItem1 = new javax.swing.JMenuItem();
        jmAjuda = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

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
            .addGap(0, 597, Short.MAX_VALUE)
        );

        jmNavegar.setText("Navegar");
        jmNavegar.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N

        jmMainPage.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmMainPage.setText("Página principal");
        jmMainPage.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmMainPageActionPerformed(evt);
            }
        });
        jmNavegar.add(jmMainPage);
        jmNavegar.add(jSeparator2);

        jmLogoff.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmLogoff.setText("Logoff");
        jmLogoff.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmLogoffActionPerformed(evt);
            }
        });
        jmNavegar.add(jmLogoff);
        jmNavegar.add(jSeparator1);

        jmSair.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jmSair.setText("Sair");
        jmSair.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jmSairActionPerformed(evt);
            }
        });
        jmNavegar.add(jmSair);

        menuBar.add(jmNavegar);

        jmSobre.setText("Sobre");
        jmSobre.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jMenuItem1.setText("Equipe de desenvolvimento e contato");
        jmSobre.add(jMenuItem1);

        menuBar.add(jmSobre);

        jmAjuda.setText("Ajuda");
        jmAjuda.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        jMenuItem2.setText("Documento de ajuda");
        jmAjuda.add(jMenuItem2);

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
        if ( Main.getInstance().isLoggedIn() )
        {
            activateLoginPage();
            setEnableNavigatorMenu(false);
            //Informa ao servidor que o usuário realizou logoff
            Main.getInstance().logoff();
        }
    }
    
    public void activateLoginPage()
    {
        activatePassControlPanel(new DefaultScreen());
        activatePassControlTopBar(new LoginTopBar());  
    }
    
    
    public void activateMainButtonsPage()
    {
        activatePassControlPanel(new ButtonsModulesScreen());
        activatePassControlTopBar(new MainTopBar());        
    }

    private void jmLogoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmLogoffActionPerformed
        performLogoutAction();
    }//GEN-LAST:event_jmLogoffActionPerformed

    private void jmMainPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmMainPageActionPerformed
        activateMainButtonsPage();
    }//GEN-LAST:event_jmMainPageActionPerformed

    public void setEnableNavigatorMenu( boolean enabled )
    {
        jmLogoff.setEnabled(enabled);
        jmMainPage.setEnabled(enabled);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
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
