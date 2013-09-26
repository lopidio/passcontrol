package br.com.thecave.passcontrol.screens;

import br.com.thecave.passcontrol.controller.ViewerController;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Arleudo
 */
public class ViewerScreen extends PassControlPanel
{

    ViewerController controller = null;

    /**
     * Creates new form AdminScreen
     */
    public ViewerScreen()
    {
        super("Visualizador", new ViewerController());
        this.controller = (ViewerController) getPanelController();
        initComponents();
        jpQueueInfoBig.setVisible(false);
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

        jpQueueInfoBig = new javax.swing.JPanel();
        lblSlide = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        jpQueueInfoBig.setBackground(new java.awt.Color(255, 255, 255));
        jpQueueInfoBig.setMaximumSize(new java.awt.Dimension(350, 210));
        jpQueueInfoBig.setMinimumSize(new java.awt.Dimension(350, 210));
        jpQueueInfoBig.setPreferredSize(new java.awt.Dimension(350, 210));
        add(jpQueueInfoBig);
        jpQueueInfoBig.setBounds(490, 190, 350, 210);

        lblSlide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSlide.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(lblSlide);
        lblSlide.setBounds(0, 0, 1370, 800);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jpQueueInfoBig;
    private javax.swing.JLabel lblSlide;
    // End of variables declaration//GEN-END:variables

    public void setPresentationImage( Image img )
    {
        lblSlide.setIcon(new ImageIcon(img));
    }
}
