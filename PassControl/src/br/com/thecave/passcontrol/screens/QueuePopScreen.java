/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controler.QueuePopController;
import java.util.ArrayList;
import javax.swing.JMenu;

/**
 *
 * @author Arleudo
 */
public class QueuePopScreen extends PassControlPanel 
{
    QueuePopController controller = null;
    
    /**
     * Creates new form AdminScreen
     */
    public QueuePopScreen() 
    {
        super("Controle de Fila", new QueuePopController());
        this.controller = (QueuePopController) getPanelController();
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

        jlImage = new javax.swing.JLabel();

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
    // End of variables declaration//GEN-END:variables

}
