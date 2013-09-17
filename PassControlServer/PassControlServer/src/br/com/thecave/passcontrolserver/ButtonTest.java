package br.com.thecave.passcontrolserver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ButtonTest {

    private JFrame frame;
    private SoftJPanel softButton1;

    public void createAndShowGUI() {
        softButton1 = new SoftJPanel("Transparent Button");
        softButton1.setBackground(Color.GREEN);
        frame = new JFrame();
        frame.getContentPane().setLayout(new java.awt.GridLayout(2, 2, 10, 10));
        frame.add(softButton1);
        frame.setSize(700, 300);
        frame.setLocation(150, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        softButton1.fadeIn(0.03f);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ButtonTest().createAndShowGUI();
            }
        });
    }

    private static class SoftJPanel extends JPanel 
    {

        private static class AlphaChanger implements ActionListener
        {
            private float alpha;
            private float incrementer;
            SoftJPanel panelOwner;

            public AlphaChanger(float incrementer, SoftJPanel panelOwner) 
            {
                this.panelOwner = panelOwner;
                this.incrementer = incrementer;
                if (incrementer < 0)
                    alpha = 1;
                else
                    alpha = 0;
            }
            
            public float getAlpha() {
                return alpha;
            }

            public float getIncrementer() {
                return incrementer;
            }

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                alpha += incrementer;
                if (alpha > 1f || alpha < 0) 
                {
                    //Finaliza o valor do alha
                    alpha = alpha>1?1:0;
                    try 
                    {
                        finalize();
                    }
                    catch (Throwable ex) 
                    {
                        Logger.getLogger(ButtonTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                panelOwner.setAlpha(alpha);
            }
                
        }
        
        private Timer alphaChanger;
        private static final JButton lafDeterminer = new JButton();
        private static final long serialVersionUID = 1L;
        private boolean rectangularLAF;
        private float alpha = 1f;

        SoftJPanel() 
        {
        }

        SoftJPanel(String text) {
            this(text, null);
        }

        SoftJPanel(String text, Icon icon) {
            setOpaque(false);
        }

        public float getAlpha() {
            return alpha;
        }

        public void setAlpha(float alpha) {
            this.alpha = alpha;
            repaint();
        }
        
        public void fadeIn(float increment)
        {
            alpha = 0;
            alphaChanger = new Timer(30, new AlphaChanger(increment, this));
            alphaChanger.start();            
        }

        @Override
        public void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            if (rectangularLAF && isBackgroundSet()) {
                Color c = getBackground();
                g2.setColor(c);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            super.paintComponent(g2);
        }

        @Override
        public void updateUI() {
            super.updateUI();
            lafDeterminer.updateUI();
            rectangularLAF = lafDeterminer.isOpaque();
        }
    }
    }