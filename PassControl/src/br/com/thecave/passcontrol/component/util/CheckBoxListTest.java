/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.component.util;

import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import java.awt.Component;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JCheckBox;

/**
 *
 * @author lopidio
 */
public class CheckBoxListTest extends javax.swing.JFrame {

    /**
     * Creates new form CheckBoxListTest
     */
    public CheckBoxListTest()
    {
        initComponents();    
        
        ArrayList<ServiceBean> servicos = new ArrayList<>();
        for (int i = 0; i < 24; ++i)
        {
            ServiceBean serviceBean = new ServiceBean();
            serviceBean.setId(i);
            serviceBean.setName("Checkbox " + i);
            servicos.add(serviceBean);
        }
        
        
        //AQUI TÁ O SEGREDO!!
        Box panel1 = Box.createVerticalBox();
        panel1.setAlignmentX(LEFT_ALIGNMENT);        
        Box panel2 = Box.createVerticalBox();
        panel2.setAlignmentX(RIGHT_ALIGNMENT);        
        jPanel.setLayout(new FlowLayout());
        jPanel.add(panel1);
        jPanel.add(Box.createHorizontalStrut(20));
        jPanel.add(panel2);
        
        ArrayList<JCheckBox> checkBoxs = createCheckBoxFromServicesList(servicos);
        for (int i = 0; i < servicos.size(); ++i)
        {
            JCheckBox box = checkBoxs.get(i);
            if (i%2 == 0)
            {
                panel1.add(box);                
            }
            else
            {
                panel2.add(box);                                
            }
        }
        //MORREU!
    }
    
    private ArrayList<JCheckBox> createCheckBoxFromServicesList(ArrayList<ServiceBean> servicesBean)
    {
        ArrayList<JCheckBox> retorno = new ArrayList<>(servicesBean.size());
        for (ServiceBean service : servicesBean) 
        {
            JCheckBox novoCheckBox = new JCheckBox(service.getName());
            retorno.add(novoCheckBox);
        }
        return retorno;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane = new javax.swing.JScrollPane();
        jPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel.setLayout(new javax.swing.BoxLayout(jPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane.setViewportView(jPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addGap(77, 77, 77))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CheckBoxListTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckBoxListTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckBoxListTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckBoxListTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckBoxListTest().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane;
    // End of variables declaration//GEN-END:variables
}
