package br.com.thecave.passcontrol.component.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ButtonTest {

    private JFrame frame;
    private FaderJPanel softButton1;

    public void createAndShowGUI() {
        softButton1 = new FaderJPanel();
        softButton1.setBackground(Color.GREEN);
        frame = new JFrame();
        frame.getContentPane().setLayout(new java.awt.GridLayout(2, 2, 10, 10));
        frame.add(softButton1);
        frame.setSize(700, 300);
        frame.setLocation(150, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        softButton1.fadeIn(3); //seconds

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ButtonTest().createAndShowGUI();
            }
        });
    }

    private static class FaderJPanel extends JPanel 
    {

        private static class FaderIn extends Fader
        {
            public FaderIn(float seconds, FaderJPanel panelOwner) 
            {
                super(seconds, panelOwner, true);
            }

        }
        private static class FaderOut extends Fader
        {
            public FaderOut(float seconds, FaderJPanel panelOwner) 
            {
                super(seconds, panelOwner, false);
            }
        }        
        
        private abstract static class Fader implements ActionListener
        {
            private float alpha;
            private boolean faderIn; //false quando é fader out
            private float durationMilliseconds;
            private long startTime;;
            Timer alphaChanger;            
            FaderJPanel panelOwner;

            public Fader(float seconds, FaderJPanel panelOwner, boolean faderIn ) 
            {
                alphaChanger = new Timer(0, this);
                
                this.panelOwner = panelOwner;
                this.durationMilliseconds = seconds*1000;
                this.faderIn = faderIn;
                if (faderIn)
                    alpha = 0;
                else
                    alpha = 1f;
                startTime = System.currentTimeMillis();
                alphaChanger.start();                  
            }
            
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                float millisSinceBeginning = System.currentTimeMillis() - startTime;
                alpha = (float)millisSinceBeginning/durationMilliseconds;
                
                if (isFaderOut())
                    alpha = 1 - alpha;
                System.out.println("AlphaAP: " + alpha);
                //Condição de término
                if (alpha > 1.0f || alpha < 0) 
                {
                    //Finaliza o valor do alpha
                    alpha = alpha > 1 ? 1 : 0;
                    try 
                    {
                        alphaChanger.stop();
                    }
                    catch (Throwable ex) 
                    {
                        Logger.getLogger(ButtonTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally
                    {
                        panelOwner.setAlpha(alpha);                                                
                    }
                }
                panelOwner.setAlpha(alpha);
            }         
            public float getAlpha() 
            {
                return alpha;
            }
            public boolean isFaderIn()
            {
                return faderIn;
            }
            public boolean isFaderOut()
            {
                return !faderIn;
            }            
                
        }
        
        private float alpha = 1f;

        public float getAlpha() 
        {
            return alpha;
        }

        public void setAlpha(float alpha)
        {
            this.alpha = alpha;
            repaint();
        }
        
        //seconds
        public void fadeIn(float seconds) 
        {
            new FaderIn(seconds, this);  
        }

        public void fadeOut(float seconds) 
        {
            new FaderOut(seconds, this);  
        }

        @Override
        public void paintComponent(java.awt.Graphics graphics)
        {
            java.awt.Graphics2D graphics2D = (java.awt.Graphics2D) graphics;
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)alpha));
            super.paintComponent(graphics2D);
            System.out.println("AlphaFJP: " + alpha);            
        }

    }
}