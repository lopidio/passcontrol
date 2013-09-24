package br.com.thecave.passcontrol.component.util;


import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestAnimatedPane {

    public static void main(String[] args) {
        new TestAnimatedPane();
    }

    public TestAnimatedPane() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
                
                Point position = new Point(500, 500);
                Rectangle from = new Rectangle(position.x, position.y, 300, 300);
                Rectangle to = new Rectangle(position.x, position.y, 10, 10);
                

                JFrame frame = new JFrame("Test");
                frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                
                
                JPanel conteinerPane = new JPanel();
//                conteinerPane.setBounds(from);                
                
                Box box = Box.createVerticalBox();
                box.setAlignmentX(LEFT_ALIGNMENT);   
//                conteinerPane.setLayout(new FlowLayout());
//                conteinerPane.add(box);                  
                
                
//                conteinerPane.setLayout(new BoxLayout(conteinerPane, BoxLayout.Y_AXIS));
//                conteinerPane.setLayout(new FlowLayout());
                JPanel animatedPane = new JPanel();                
                animatedPane.setBackground(Color.RED);
                conteinerPane.add(animatedPane);
//                box.add(new JLabel("label"));
                
                //Animation region
                AnimationUtil animate = new AnimationUtil(animatedPane, from, to);
                animate.setRunTime(5000);
                animate.start();
                //End of animation region
                                
                frame.add(conteinerPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    
}