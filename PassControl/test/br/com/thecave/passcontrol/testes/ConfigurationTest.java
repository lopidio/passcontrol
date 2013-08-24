/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrol.testes;

import br.com.thecave.passcontrol.util.configuration.Configuration;
import br.com.thecave.passcontrol.util.configuration.Slide;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anton_000
 */
public class ConfigurationTest {
    
    public ConfigurationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void toXmlTest()
    {
        Configuration c = Configuration.getInstance();
        c.getAdmin().setIpAdmin("ip1dmin");
        c.getBalcony().setIpBalcony("ipbalcony");
        c.getBalcony().setLastBalconyNumber(1);
        c.getBalcony().setLastBalconyType(1);
        c.getBalcony().setMainImage("imagebalcony");
        c.getQueuePop().setIpQueuePop("ipqueuepop");
        c.getQueuePop().setMainImage("imagequeuepop");
        c.getQueuePush().setIpQueuePush("ipqueuepush");
        c.getQueuePush().setMainImage("imagequeuepush");
        c.getServer().setIpServer("ipserver");
        c.getServer().setServerPort("portserver");
        c.getViewer().setIpViewer("ipviewew");
        c.getViewer().setMainPage("imageviewer");
        
        Slide s = new Slide();
        ArrayList<String> lista = new ArrayList<String>();
        lista.add("imagem1");
        lista.add("imagem 2");
        lista.add("imagem 3");
        
        s.setImgs(lista);
        c.getViewer().setSlide(s);
        
        Configuration.getInstance().toXml();        
    }
}