package br.com.thecave.passcontrol.testes;

import br.com.thecave.passcontrol.util.configuration.ConfigurationFile;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author anton_000
 */
public class ConfigurationFileTest
{
    
    public ConfigurationFileTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void toXml() 
    {
        ConfigurationFile file = ConfigurationFile.getInstance();
        file.setGerenciamento(true);
        file.setIpServer("192.168.0.1");
        file.setPortServer("123456");
        file.setSlideShowSpeed(123);
        
        ArrayList<String> imgs = new ArrayList<String>();
        imgs.add("primeira");
        imgs.add("segunda");
        imgs.add("terceira");
        file.setImgsSlide(imgs);
        file.toXml();
    }
}