package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controler.PassControlController;
import br.com.thecave.passcontrol.topbar.LoginTopBar;
import br.com.thecave.passcontrol.topbar.PassControlTopBar;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;

/**
 *
 * @author Antonio Arleudo da costa
 */
public final class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form AdministratorScreen
     */
    public MainFrame() 
    {
        initComponents();

        topBar.setLayout(new BoxLayout(topBar, BoxLayout.Y_AXIS));
        passControlPanel.setLayout(new BoxLayout(passControlPanel, BoxLayout.Y_AXIS));
        
        activatePassControlPanel(new DefaultScreen());
        activatePassControlTopBar(new LoginTopBar());
        
//        JFrame desktopPane = new JFrame();
//        QueueElementInfo queueElementInfo = new QueueElementInfo("sasas", "dsfsdfsdf", "rtgrtgrt");
////        try {
////            queueElementInfo.fadeIn(0.01f);
////        } catch (Exception ex) {
////            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
////        }
//        desktopPane.add(queueElementInfo);
//        desktopPane.setSize(800, 600);
////        desktopPane.repaint();
////        desktopPane.revalidate();
//        desktopPane.setVisible(true);
        
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


        //Remove o atual
        passControlPanel.removeAll();
        //Adiciona o novo
        passControlPanel.add(newPassControlPanel);
        
        //Removo os ítens antigos do menu
        for (int i = 0; i < menuBar.getMenuCount() - 2 ; ++i)//2 pq sempre existem dois valores
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
        
        //Verifica se tem controller, caso tenha, inicializa e cadastra os escutadores de eventos
        if (newPassControlPanel.initializeController())
        {
            //Adiciona aos escutadores de eventos
            newPassControlPanel.getPanelController().addMessageListeners();
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


        //Remove o atual
        topBar.removeAll();
        //Adiciona o novo
        topBar.add(newPassControlTopBar);
        
        //Verifica se tem controller, caso tenha, inicializa e cadastra os escutadores de eventos
        if (newPassControlTopBar.initializeController())
        {
            //Adiciona aos escutadores de eventos
            newPassControlTopBar.getPanelController().addMessageListeners();
        }        
        
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
        for (int i = 0; i < menuBar.getMenuCount() - 2 ; ++i)//2 pq sempre existem dois valores
        {
            menuBar.setEnabled(false);
        }        
    }
    
    public void enableControlPanel()
    {
        passControlPanel.setEnabled(true);
        //ítens do menu
        for (int i = 0; i < menuBar.getMenuCount() - 2 ; ++i)//2 pq sempre existem dois valores
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
            .addGap(0, 565, Short.MAX_VALUE)
        );

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

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jmAjuda;
    private javax.swing.JMenu jmSobre;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel passControlPanel;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables


}

