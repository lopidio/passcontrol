/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.screens;

import java.util.ArrayList;
import javax.swing.JMenu;

/**
 *
 * @author Arleudo
 */
public class QueuePopScreen extends PassControlPanel 
{

    /**
     * Creates new form AdminScreen
     */
    public QueuePopScreen() 
    {
        super("Controle de Fila", null);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jmBalcony = new javax.swing.JMenu();
        jmVoltar = new javax.swing.JMenuItem();
        jmLogout = new javax.swing.JMenuItem();
        jmSair = new javax.swing.JMenuItem();
        jlImage = new javax.swing.JLabel();

        jmBalcony.setText("Guichê");

        jmVoltar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jmVoltar.setMnemonic('v');
        jmVoltar.setText("Voltar");
        jmBalcony.add(jmVoltar);

        jmLogout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
        jmLogout.setMnemonic('l');
        jmLogout.setText("Logout");
        jmBalcony.add(jmLogout);

        jmSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jmSair.setMnemonic('s');
        jmSair.setText("Sair");
        jmBalcony.add(jmSair);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        jlImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/queue_pop_big.png"))); // NOI18N
        jlImage.setToolTipText("");
        add(jlImage);
        jlImage.setBounds(10, 11, 1347, 778);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlImage;
    private javax.swing.JMenu jmBalcony;
    private javax.swing.JMenuItem jmLogout;
    private javax.swing.JMenuItem jmSair;
    private javax.swing.JMenuItem jmVoltar;
    // End of variables declaration//GEN-END:variables

    @Override
    public ArrayList<JMenu> createMenuItems() 
    {
        ArrayList<JMenu> retorno = new ArrayList<>();
        retorno.add(jmBalcony);
               
        return retorno;
    }
}
