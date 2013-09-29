package br.com.thecave.passcontrolserver.util;

import java.io.Serializable;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 * Classe para leitura e escrita do xml de configuração a ser adotado pelo
 * sistema esse xml contem dados de cada um dos modulos do sistema desde ip a
 * informações mais específicas de comportamento.
 *
 * @author Antonio Arleudo da Costa
 */
public class ConfigurationFile implements Serializable
{
    //==========================================================================
    // Atributos a serem mapeados
    //==========================================================================

    private boolean gerenciamentoAutomatico;
    private int slideShowSpeed;
    private ImageIcon mainImage;
    private String ipServer;
    private int portServer;
    private HashMap<String, ImageIcon> imgsSlide;

    /**
     * Construtor privado da classe
     */
    public ConfigurationFile()
    {
        gerenciamentoAutomatico = true;
        slideShowSpeed = 5000;
        ipServer = "127.0.0.1";
        portServer = 23073;
        imgsSlide = new HashMap<>();
        mainImage = new ImageIcon(getClass().getResource("/resources/splash.png"));
    }

    public boolean isGerenciamentoAutomatico()
    {
        return gerenciamentoAutomatico;
    }

    public void setGerenciamentoAutomatico( boolean gerenciamentoAutomatico )
    {
        this.gerenciamentoAutomatico = gerenciamentoAutomatico;
    }

    /**
     * @return the slideShowSpeed
     */
    public int getSlideShowSpeed()
    {
        return slideShowSpeed;
    }

    /**
     * @param slideShowSpeed the slideShowSpeed to set
     */
    public void setSlideShowSpeed( int slideShowSpeed )
    {
        this.slideShowSpeed = slideShowSpeed;
    }

    /**
     * @return the ipServer
     */
    public String getIpServer()
    {
        return ipServer;
    }

    /**
     * @param ipServer the ipServer to set
     */
    public void setIpServer( String ipServer )
    {
        this.ipServer = ipServer;
    }

    /**
     * @return the portServer
     */
    public int getPortServer()
    {
        return portServer;
    }

    /**
     * @param portServer the portServer to set
     */
    public void setPortServer( int portServer )
    {
        this.portServer = portServer;
    }

    public HashMap<String, ImageIcon> getImgsSlide()
    {
        return imgsSlide;
    }

    public ImageIcon getMainImage() {
        return mainImage;
    }

    public void setMainImage(ImageIcon mainImage) {
        this.mainImage = mainImage;
    }
    
    
}
