/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.messages.generic;

import br.com.thecave.passcontrolserver.util.ConfigurationFile;

/**
 *
 * @author guilherme
 */
public class ConfigurationFileAlterationMessage extends PassControlMessage
{
    ConfigurationFile configurationFile;

    public ConfigurationFileAlterationMessage(ConfigurationFile configurationFile)
    {
        super(MessageActors.ServerActor, MessageActors.AllActors);
        this.configurationFile = configurationFile;
    }

    public ConfigurationFile getConfigurationFile() 
    {
        return configurationFile;
    }

    public void setConfigurationFile(ConfigurationFile configurationFile) {
        this.configurationFile = configurationFile;
    }
    
}
