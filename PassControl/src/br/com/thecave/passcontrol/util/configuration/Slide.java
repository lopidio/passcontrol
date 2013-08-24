package br.com.thecave.passcontrol.util.configuration;

import java.util.ArrayList;

public class Slide
{
    private int time;
    private ArrayList<String> imgs;

    public Slide()
    {
        imgs = new ArrayList<String>();
    }

    /**
     * @return the time
     */
    public int getTime() 
    {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(int time) 
    {
        this.time = time;
    }

    /**
     * @return the imgs
     */
    public ArrayList<String> getImgs() 
    {
        return imgs;
    }

    /**
     * @param imgs the imgs to set
     */
    public void setImgs(ArrayList<String> imgs) 
    {
        this.imgs = imgs;
    }
}
