package br.com.thecave.passcontrolserver.util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe para leitura e escrita do xml de configuração a ser adotado pelo sistema
 * esse xml contem dados de cada um dos modulos do sistema
 * desde ip a informações mais específicas de comportamento.
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
    private String ipServer;
    private String portServer;
    private ArrayList<String> imgsSlide;
    
    /**
     * Construtor privado da classe 
     */
    public ConfigurationFile()
    {
        imgsSlide = new ArrayList<String>();
    }

    public boolean isGerenciamentoAutomatico() {
        return gerenciamentoAutomatico;
    }

    public void setGerenciamentoAutomatico(boolean gerenciamentoAutomatico) {
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
    public String getPortServer()
    {
        return portServer;
    }

    /**
     * @param portServer the portServer to set
     */
    public void setPortServer( String portServer )
    {
        this.portServer = portServer;
    }

    /**
     * @return the imgsSlide
     */
    public ArrayList<String> getImgsSlide()
    {
        return imgsSlide;
    }

    /**
     * @param imgsSlide the imgsSlide to set
     */
    public void setImgsSlide( ArrayList<String> imgsSlide )
    {
        this.imgsSlide = imgsSlide;
    }
}

