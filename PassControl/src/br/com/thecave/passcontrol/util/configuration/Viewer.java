package br.com.thecave.passcontrol.util.configuration;

public class Viewer
{
    private String mainPage;
    private String ipViewer;
    private Slide slide;

    /**
     * @return the mainPage
     */
    public String getMainPage() 
    {
        return mainPage;
    }

    /**
     * @param mainPage the mainPage to set
     */
    public void setMainPage(String mainPage) 
    {
        this.mainPage = mainPage;
    }

    /**
     * @return the ipViewer
     */
    public String getIpViewer() 
    {
        return ipViewer;
    }

    /**
     * @param ipViewer the ipViewer to set
     */
    public void setIpViewer(String ipViewer) 
    {
        this.ipViewer = ipViewer;
    }

    /**
     * @return the slide
     */
    public Slide getSlide() 
    {
        return slide;
    }

    /**
     * @param slide the slide to set
     */
    public void setSlide(Slide slide) 
    {
        this.slide = slide;
    }
}
