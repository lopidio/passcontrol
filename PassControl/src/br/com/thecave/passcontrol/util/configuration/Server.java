package br.com.thecave.passcontrol.util.configuration;

public class Server
{
    private String ipServer;
    private String serverPort;        

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
    public void setIpServer(String ipServer) 
    {
        this.ipServer = ipServer;
    }

    /**
     * @return the serverPort
     */
    public String getServerPort() 
    {
        return serverPort;
    }

    /**
     * @param serverPort the serverPort to set
     */
    public void setServerPort(String serverPort) 
    {
        this.serverPort = serverPort;
    }
}  
