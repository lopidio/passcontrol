package br.com.thecave.passcontrol.util.configuration;

import br.com.thecave.passcontrolserver.util.FileUtils;
import com.thoughtworks.xstream.XStream;

/**
 * Classe para leitura e escrita do xml de configuração a ser adotado pelo sistema
 * esse xml contem dados de cada um dos modulos do sistema
 * desde ip a informações mais específicas de comportamento.
 * 
 * @author Antonio Arleudo da Costa
 */
public class Configuration 
{
    //==========================================================================
    // Atributos a serem mapeados
    //==========================================================================
    private Server server;
    private Admin admin;
    private Viewer viewer;
    private QueuePop queuePop;
    private QueuePush queuePush;
    private Balcony balcony;   
    
    private static Configuration instance;
    
    /**
     * Construtor privado da classe 
     */
    private Configuration()
    {
        server = new Server();
        admin = new Admin();
        viewer = new Viewer();
        queuePop = new QueuePop();
        queuePush = new QueuePush();
        balcony = new Balcony();
        
    }
    
    /**
     * Metodo para recuperar a instância da classe de configuração
     * @return 
     */
    public static Configuration getInstance()
    {
        if(instance == null)
            instance = new Configuration();
        return instance;
    }

    /**
     * @return the server
     */
    public Server getServer() 
    {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(Server server) 
    {
        this.server = server;
    }

    /**
     * @return the admin
     */
    public Admin getAdmin() 
    {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(Admin admin) 
    {
        this.admin = admin;
    }

    /**
     * @return the viewer
     */
    public Viewer getViewer() 
    {
        return viewer;
    }

    /**
     * @param viewer the viewer to set
     */
    public void setViewer(Viewer viewer) 
    {
        this.viewer = viewer;
    }

    /**
     * @return the queuePop
     */
    public QueuePop getQueuePop() 
    {
        return queuePop;
    }

    /**
     * @param queuePop the queuePop to set
     */
    public void setQueuePop(QueuePop queuePop) 
    {
        this.queuePop = queuePop;
    }

    /**
     * @return the queuePush
     */
    public QueuePush getQueuePush() 
    {
        return queuePush;
    }

    /**
     * @param queuePush the queuePush to set
     */
    public void setQueuePush(QueuePush queuePush) 
    {
        this.queuePush = queuePush;
    }

    /**
     * @return the balcony
     */
    public Balcony getBalcony() 
    {
        return balcony;
    }

    /**
     * @param balcony the balcony to set
     */
    public void setBalcony(Balcony balcony) 
    {
        this.balcony = balcony;
    } 
    
    public void toXml()
    {
        XStream xstream = new XStream();
        xstream.alias("config", Configuration.class);        
        String xml = xstream.toXML(this);
        FileUtils.saveFile("config/config_pass_control.xml", xml);
    }
}

