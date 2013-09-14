/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.screens.admin;

import br.com.thecave.passcontrol.controller.BalconyCrudController;
import br.com.thecave.passcontrol.screens.PassControlPanel;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import java.util.ArrayList;
import javax.swing.JMenu;

/**
 *
 * @author Arleudo
 */
public class BalconyCrud extends PassControlPanel 
{
    BalconyCrudController controller = null;
    /**
     * Creates new form AdminScreen
     */
    public BalconyCrud() 
    {
        super("Cadastro de Guichê", new BalconyCrudController());
        this.controller = (BalconyCrudController) getPanelController();
        initComponents();
        controller.loadBalconys();
        defineCBNames();        
    }
    
    private void defineCBNames()
    {
        controller.defineCBNames(cbBalconyName);        
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

        cbBalconyName = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btSalvar = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        cbBalconyName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        jLabel3.setText("Nome do Guichê");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btSalvar.setBackground(new java.awt.Color(0, 153, 191));
        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btSalvarActionPerformed(evt);
            }
        });

        btNovo.setBackground(new java.awt.Color(0, 153, 191));
        btNovo.setText("Novo");

        btDeletar.setBackground(new java.awt.Color(0, 153, 191));
        btDeletar.setText("Deletar");
        btDeletar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btDeletarActionPerformed(evt);
            }
        });

        btCancelar.setBackground(new java.awt.Color(0, 153, 191));
        btCancelar.setText("Cancelar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(925, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cbBalconyName, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbBalconyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(374, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btSalvarActionPerformed
    {//GEN-HEADEREND:event_btSalvarActionPerformed
        BalconyBean balconyBean = new BalconyBean();
        ArrayList<ServiceBean> typesServiceBean = new ArrayList<ServiceBean>();
        
        ServiceBean bean =  new ServiceBean();
        bean.setName("meu serviço");
        bean.setPriority(3);
        typesServiceBean.add(bean);
        
        balconyBean.setNumber(cbBalconyName.getSelectedItem().toString());
        controller.loadBalconys();
        
        balconyBean = extractBeanFromCombo();
        
        controller.saveBalcony(balconyBean, typesServiceBean);
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btDeletarActionPerformed
    {//GEN-HEADEREND:event_btDeletarActionPerformed
        BalconyBean bean = extractBeanFromCombo();
        controller.deleteBalcony(bean);
    }//GEN-LAST:event_btDeletarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btDeletar;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox cbBalconyName;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public ArrayList<JMenu> createMenuItems() 
    {
        ArrayList<JMenu> ret = new ArrayList<>();
        return ret;
    }
    private BalconyBean extractBeanFromCombo()
    {
        return controller.extractBeanFromName(cbBalconyName.getSelectedItem().toString());
    }

}
