package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.component.util.QueueElementInfoBig;
import br.com.thecave.passcontrol.controller.BalconyController;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.messages.balcony.BalconyShowClientMessage;

/**
 *
 * @author Arleudo
 */
public class BalconyScreen extends PassControlPanel
{

    BalconyController controller = null;

    /**
     * Creates new form AdminScreen
     */
    public BalconyScreen()
    {
        super("Guichê", new BalconyController());
        controller = (BalconyController) getPanelController();
        initComponents();
    }
    
    public void enableButtons(boolean enable)
    {
        btChamar.setEnabled(enable);
        btEncerrar.setEnabled(enable);
        btEsperar.setEnabled(enable);
        btRepetir.setEnabled(enable);        
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

        jlBalcony = new javax.swing.JLabel();
        jpBarraLateral = new javax.swing.JPanel();
        btChamar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btRepetir = new javax.swing.JButton();
        btEncerrar = new javax.swing.JButton();
        btEsperar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jlBalcony.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/balcony_button.png"))); // NOI18N

        jpBarraLateral.setPreferredSize(new java.awt.Dimension(6, 0));

        javax.swing.GroupLayout jpBarraLateralLayout = new javax.swing.GroupLayout(jpBarraLateral);
        jpBarraLateral.setLayout(jpBarraLateralLayout);
        jpBarraLateralLayout.setHorizontalGroup(
            jpBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );
        jpBarraLateralLayout.setVerticalGroup(
            jpBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );

        btChamar.setBackground(new java.awt.Color(45, 123, 142));
        btChamar.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        btChamar.setText("Chamar");
        btChamar.setEnabled(false);
        btChamar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btChamarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(150, 150, 150));
        jLabel1.setFont(new java.awt.Font("Square721 BT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(200, 200, 200));
        jLabel1.setText("CONTROLADOR DE GUICHÊ");

        btRepetir.setBackground(new java.awt.Color(45, 123, 142));
        btRepetir.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        btRepetir.setText("Repetir");
        btRepetir.setEnabled(false);
        btRepetir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btRepetirActionPerformed(evt);
            }
        });

        btEncerrar.setBackground(new java.awt.Color(45, 123, 142));
        btEncerrar.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        btEncerrar.setText("Encerrar");
        btEncerrar.setEnabled(false);
        btEncerrar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btEncerrarActionPerformed(evt);
            }
        });

        btEsperar.setBackground(new java.awt.Color(45, 123, 142));
        btEsperar.setFont(new java.awt.Font("Square721 BT", 0, 14)); // NOI18N
        btEsperar.setText("Esperar");
        btEsperar.setEnabled(false);
        btEsperar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btEsperarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlBalcony)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpBarraLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btChamar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btRepetir, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btEsperar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(708, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpBarraLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlBalcony)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btChamar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btRepetir, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btEncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btEsperar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(499, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btChamarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btChamarActionPerformed
    {//GEN-HEADEREND:event_btChamarActionPerformed
    }//GEN-LAST:event_btChamarActionPerformed

    private void btRepetirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btRepetirActionPerformed
    {//GEN-HEADEREND:event_btRepetirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btRepetirActionPerformed

    private void btEncerrarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btEncerrarActionPerformed
    {//GEN-HEADEREND:event_btEncerrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btEncerrarActionPerformed

    private void btEsperarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btEsperarActionPerformed
    {//GEN-HEADEREND:event_btEsperarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btEsperarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btChamar;
    private javax.swing.JButton btEncerrar;
    private javax.swing.JButton btEsperar;
    private javax.swing.JButton btRepetir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jlBalcony;
    private javax.swing.JPanel jpBarraLateral;
    // End of variables declaration//GEN-END:variables

    public void initialize( BalconyBean balconyBean )
    {
        controller.setBalconyBean(balconyBean);
    }

    public void showPanelQueueInfo( BalconyShowClientMessage response )
    {
        QueueElementInfoBig queueElementInfoBig = new QueueElementInfoBig(response.getClientName(), response.getServiceType(), response.getQueuesManagerBean().getPassNumber());
        //jpSenha.add(queueElementInfoBig);
        //jpSenha.setVisible(true);
    }
}
