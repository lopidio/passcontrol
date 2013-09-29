package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controller.QueuePopController;

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
        jpBarraLateral = new javax.swing.JPanel();
        jOutterScrollPane = new javax.swing.JScrollPane();
        jpScrollablePane = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/queue_pop_button.png"))); // NOI18N
        jlImage.setToolTipText("");
        add(jlImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 250, 150));

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

        add(jpBarraLateral, new org.netbeans.lib.awtextra.AbsoluteConstraints(267, 11, -1, 290));

        jOutterScrollPane.setViewportView(jpScrollablePane);

        add(jOutterScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 600, 290));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jOutterScrollPane;
    private javax.swing.JLabel jlImage;
    private javax.swing.JPanel jpBarraLateral;
    private javax.swing.JPanel jpScrollablePane;
    // End of variables declaration//GEN-END:variables
}
