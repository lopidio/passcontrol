/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.component.util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author lopidio
 */
public class AnimationUtil 
{
    private AnimationUtilObserver observer;
    private int runTime = 2000;
    private JPanel panel;
    private Rectangle from;
    private Rectangle to;
    Timer timer ;
    private long startTime;

    public AnimationUtil(JPanel panel, Rectangle from, Rectangle to) {
        this.panel = panel;
        this.from = from;
        this.to = to;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }
    
    public void start() {
        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long duration = System.currentTimeMillis() - startTime;
                double progress = (double)duration / (double)runTime;
                if (progress > 1f) {
                    progress = 1f;
                    if (timer != null)
                    {
                        if (observer != null)
                            observer.onAnimationEnd();
                        timer.stop();
                    }
                }
                Rectangle target = calculateProgress(from, to, progress);
                panel.setBounds(target);
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        startTime = System.currentTimeMillis();
        timer.start();
    }


    public static Rectangle calculateProgress(Rectangle startBounds, Rectangle targetBounds, double progress) {

        Rectangle bounds = new Rectangle();

        if (startBounds != null && targetBounds != null) {

            bounds.setLocation(calculateProgress(startBounds.getLocation(), targetBounds.getLocation(), progress));
            bounds.setSize(calculateProgress(startBounds.getSize(), targetBounds.getSize(), progress));

        }

        return bounds;

    }

    public static Point calculateProgress(Point startPoint, Point targetPoint, double progress) {

        Point point = new Point();

        if (startPoint != null && targetPoint != null) {

            point.x = calculateProgress(startPoint.x, targetPoint.x, progress);
            point.y = calculateProgress(startPoint.y, targetPoint.y, progress);

        }

        return point;

    }

    public static int calculateProgress(int startValue, int endValue, double fraction) {

        int value = 0;
        int distance = endValue - startValue;
        value = (int)Math.round((double)distance * fraction);
        value += startValue;

        return value;

    }

    public static Dimension calculateProgress(Dimension startSize, Dimension targetSize, double progress) {

        Dimension size = new Dimension();

        if (startSize != null && targetSize != null) {

            size.width = calculateProgress(startSize.width, targetSize.width, progress);
            size.height = calculateProgress(startSize.height, targetSize.height, progress);

        }

        return size;
    }    

    public void stop() 
    {
        if (timer != null)
        {
            timer.stop();
        }
    }

    public void setObserver(AnimationUtilObserver observer) {
        this.observer = observer;
    }
    
}
