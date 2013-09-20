package br.com.thecave.passcontrol.component.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class FaderJPanel extends JPanel
{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() 
            {
                JFrame frame = new JFrame();
                FaderJPanel faderPanel = new FaderJPanel();
                faderPanel.setBackground(Color.GREEN);
                frame.getContentPane().setLayout(new java.awt.GridLayout(2, 2, 10, 10));
                frame.add(faderPanel);
                frame.setSize(700, 300);
                frame.setLocation(150, 100);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

//                new FaderIn(3000, faderPanel);  
//                new FaderOut(3, faderPanel);  

            }
        });
    }        

    public FaderJPanel() 
    {
//        setOpaque(false);
    }

    public static class FaderIn extends Fader
    {
        public FaderIn(float seconds, FaderJPanel panelOwner) 
        {
            super(seconds, panelOwner, true);
        }

    }
    public static class FaderOut extends Fader
    {
        public FaderOut(float seconds, FaderJPanel panelOwner) 
        {
            super(seconds, panelOwner, false);
        }
    }        

    private abstract static class Fader implements ActionListener
    {
        private boolean faderIn; //false quando é fader out
        private float durationMilliseconds;
        private long startTime;;
        Timer alphaChanger;            
        FaderJPanel panelOwner;

        public Fader(float milliseconds, final FaderJPanel panelOwner, boolean faderIn ) 
        {
            alphaChanger = new Timer(50, this);

            this.panelOwner = panelOwner;
            this.durationMilliseconds = milliseconds;
            this.faderIn = faderIn;
            float alpha = 1;
            if (faderIn)
                alpha = 0;
            startTime = System.currentTimeMillis();
            panelOwner.setAlpha(alpha);
            alphaChanger.start();        
            
            Timer uiChanger = new Timer(3500, new ActionListener() 
                {

                    private LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();
                    private int index = 1;

                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        try {
                            UIManager.setLookAndFeel(laf[index].getClassName());
                            SwingUtilities.updateComponentTreeUI(panelOwner);
                        } catch (Exception exc) {
                            exc.printStackTrace();
                        }
                        index = (index + 1) % laf.length;
                    }            
                });
            uiChanger.start();
            
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            final float maxValue = 1f;
            float millisSinceBeginning = System.currentTimeMillis() - startTime;
            float alpha = (float)maxValue*millisSinceBeginning/(float)durationMilliseconds;

            if (isFaderOut())
                alpha = maxValue - alpha;
            //Condição de término
            if (alpha >= maxValue || alpha <= 0) 
            {
                //Finaliza o valor do alpha
                alpha = alpha > maxValue ? maxValue : 0;
                try 
                {
                    alphaChanger.stop();
                    if (isFaderIn())
                        new FaderOut(durationMilliseconds, panelOwner);
                    else
                        new FaderIn(durationMilliseconds, panelOwner);
                    return;
                }
                catch (Throwable ex){}
            }
            panelOwner.setAlpha(alpha);
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
    
//    @Override
//    public void paintComponent(java.awt.Graphics g) 
//    {
//        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
//        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
//        System.out.println("Alpha:" + alpha);
//        super.paintComponent(g2);
//    }

    @Override
    protected void paintComponent(Graphics g) {
        if (g instanceof Graphics2D) {
            final int R = 240;
            final int G = 240;
            final int B = 240;

            Paint p =
                new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0),
                    0.0f, getHeight(), new Color(R, G, B, 255), true);
            Graphics2D g2d = (Graphics2D)g;
                    System.out.println("Alpha:" + alpha);

            g2d.setPaint(p);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }    

}
