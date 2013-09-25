package br.com.thecave.passcontrol.util.configuration;

import br.com.thecave.passcontrolserver.util.FileUtils;
import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;

/**
 * Classe para leitura e escrita do xml de configuração a ser adotado pelo sistema
 * esse xml contem dados de cada um dos modulos do sistema
 * desde ip a informações mais específicas de comportamento.
 * 
 * @author Antonio Arleudo da Costa
 */
public class ConfigurationFile 
{
    //==========================================================================
    // Atributos a serem mapeados
    //==========================================================================
    private boolean gerenciamento;
    private int slideShowSpeed;
    private String ipServer;
    private String portServer;
    private ArrayList<String> imgsSlide;
    
    private static ConfigurationFile instance;
    
    /**
     * Construtor privado da classe 
     */
    private ConfigurationFile()
    {
        imgsSlide = new ArrayList<String>();
    }
    
    /**
     * Metodo para recuperar a instância da classe de configuração
     * @return 
     */
    public static ConfigurationFile getInstance()
    {
        if(instance == null)
            instance = new ConfigurationFile();
        return instance;
    }
    public void toXml()
    {
        XStream xstream = new XStream();
        xstream.alias("config", ConfigurationFile.class);        
        String xml = xstream.toXML(this);
        FileUtils.saveFile("config/config_pass_control.xml", xml);
    }

    /**
     * @return the gerenciamento
     */
    public boolean getGerenciamento()
    {
        return gerenciamento;
    }

    /**
     * @param gerenciamento the gerenciamento to set
     */
    public void setGerenciamento( boolean gerenciamento )
    {
        this.gerenciamento = gerenciamento;
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

