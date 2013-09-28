package br.com.thecave.passcontrol.component.util;

import br.com.thecave.passcontrol.utils.PassControlFont;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author lopidio
 */
public class QueueElementInfo extends JPanel
{
    public static final Dimension SIZE = new QueueElementInfo(null, null, null, null).getPreferredSize();


    private String clientName;
    private String queueName;
    private String balconyName;
    private String userPass;
    

    public QueueElementInfo( String clientName, String queueName, String userPass, String balconyName )
    {

        initComponents();
        this.clientName = clientName;
        this.queueName = queueName;
        this.balconyName = balconyName;
        this.userPass = userPass;

        txtFila.setText(queueName);
        txtNome.setText(clientName);
        txtSenha.setText(userPass);
        txtGuiche.setText(balconyName);

    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
        txtNome.setText(clientName);
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
        txtFila.setText(queueName);
    }

    public String getBalconyName() {
        return balconyName;
        
    }

    public void setBalconyName(String balconyName) {
        this.balconyName = balconyName;
        txtGuiche.setText(balconyName);        
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
        txtSenha.setText(userPass);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        lblGuiche = new javax.swing.JLabel();
        lblFila = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JLabel();
        txtFila = new javax.swing.JLabel();
        txtGuiche = new javax.swing.JLabel();
        lblSenha1 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 232, 29));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        setForeground(new java.awt.Color(255, 100, 60));
        setMaximumSize(new java.awt.Dimension(250, 150));
        setMinimumSize(new java.awt.Dimension(250, 150));
        setPreferredSize(new java.awt.Dimension(250, 95));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setToolTipText("");
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 210, 14));

        lblGuiche.setFont(new PassControlFont().getSizedFont(8));
        lblGuiche.setText("Guichê:");
        add(lblGuiche, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 50, -1));

        lblFila.setFont(new PassControlFont().getSizedFont(8));
        lblFila.setText("Fila:");
        add(lblFila, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        lblNome.setFont(new PassControlFont().getSizedFont(8));
        lblNome.setText("Nome do cliente:");
        add(lblNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        txtNome.setFont(new PassControlFont().getSizedFont(18));
        txtNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNome.setText("Leudinho");
        add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 190, -1));

        txtFila.setFont(new PassControlFont().getSizedFont(18));
        txtFila.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFila.setText("Prioritária");
        add(txtFila, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 190, -1));

        txtGuiche.setFont(new PassControlFont().getSizedFont(24));
        txtGuiche.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtGuiche.setText("B13");
        add(txtGuiche, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 110, 30));

        lblSenha1.setFont(new PassControlFont().getSizedFont(8));
        lblSenha1.setText("Senha:");
        add(lblSenha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtSenha.setFont(new PassControlFont().getSizedFont(24));
        txtSenha.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtSenha.setText("43");
        add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, 30));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblFila;
    private javax.swing.JLabel lblGuiche;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha1;
    private javax.swing.JLabel txtFila;
    private javax.swing.JLabel txtGuiche;
    private javax.swing.JLabel txtNome;
    private javax.swing.JLabel txtSenha;
    // End of variables declaration//GEN-END:variables
}
