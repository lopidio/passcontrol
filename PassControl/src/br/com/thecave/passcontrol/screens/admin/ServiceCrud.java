/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.screens.admin;

import br.com.thecave.passcontrol.controller.ServiceCrudController;
import br.com.thecave.passcontrol.screens.PassControlPanel;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenu;

/**
 *
 * @author Arleudo
 */
public class ServiceCrud extends PassControlPanel 
{
    ServiceCrudController controller = null;
    /**
     * Creates new form AdminScreen
     */
    public ServiceCrud() 
    {
        super("Cadastro de Serviços", new ServiceCrudController());
        this.controller = (ServiceCrudController) getPanelController();
        initComponents();
        controller.loadServices();
        defineCBNames();
        sincronizeCombos();
        btCancelar.setEnabled(false);
        btSalvar.setEnabled(false);
        cbPrioridade.setEnabled(false);
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

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btEditar = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cbPrioridade = new javax.swing.JComboBox();
        cbName = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Nome do Serviço");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btEditar.setBackground(new java.awt.Color(0, 153, 191));
        btEditar.setText("Editar");

        btNovo.setBackground(new java.awt.Color(0, 153, 191));
        btNovo.setText("Novo");
        btNovo.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btNovoActionPerformed(evt);
            }
        });

        btDeletar.setBackground(new java.awt.Color(0, 153, 191));
        btDeletar.setText("Deletar");

        btSalvar.setBackground(new java.awt.Color(0, 153, 191));
        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btSalvarActionPerformed(evt);
            }
        });

        btCancelar.setBackground(new java.awt.Color(0, 153, 191));
        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Prioridade");

        cbPrioridade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mínima", "Baixa", "Média", "Alta", "Máxima" }));

        cbName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbName.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                cbNameItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(942, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(cbPrioridade, 0, 188, Short.MAX_VALUE)
                    .addComponent(cbName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbPrioridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btNovoActionPerformed
    {//GEN-HEADEREND:event_btNovoActionPerformed
        btNovo.setEnabled(false);
        btSalvar.setEnabled(true);
        btCancelar.setEnabled(true);
        cbName.setEditable(true);
        cbPrioridade.setEnabled(true);
        btEditar.setEnabled(false);
        btDeletar.setEnabled(false);
        
        cbName.setModel(new DefaultComboBoxModel());
    }//GEN-LAST:event_btNovoActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btCancelarActionPerformed
    {//GEN-HEADEREND:event_btCancelarActionPerformed
        defineCBNames();        
    }//GEN-LAST:event_btCancelarActionPerformed

    private void cbNameItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_cbNameItemStateChanged
    {//GEN-HEADEREND:event_cbNameItemStateChanged
        sincronizeCombos();
    }//GEN-LAST:event_cbNameItemStateChanged

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btSalvarActionPerformed
    {//GEN-HEADEREND:event_btSalvarActionPerformed
        controller.saveService(cbName.getSelectedItem().toString(), cbPrioridade.getSelectedIndex() +1 );
    }//GEN-LAST:event_btSalvarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btDeletar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox cbName;
    private javax.swing.JComboBox cbPrioridade;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public ArrayList<JMenu> createMenuItems() 
    {
        ArrayList<JMenu> ret = new ArrayList<JMenu>();
        return ret;
    }

    private void defineCBNames()
    {
        controller.defineCBNames(cbName);        
    }
    
    private void defineCBPriorites(int value)
    {
        cbPrioridade.setSelectedIndex(value - 1);
    }

    private void sincronizeCombos()
    {
        ServiceBean bean = extractBeanFromCombo();
        if(bean != null)
        defineCBPriorites(bean.getPriority());
    }

    private ServiceBean extractBeanFromCombo()
    {
        return controller.extractBeanFromCombo(cbName);
    }
}
