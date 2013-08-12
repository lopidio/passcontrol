package br.com.thecave.passcontrol.testes;

import br.com.thecave.passcontrol.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrol.db.dao.QueuesManagerDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.xml.crypto.Data;

/**
 * Classe de teste do UserDAO
 * @author Glaylson Alves 
 */
public class QueuesManagerDAOTest {
    
    public QueuesManagerDAOTest() {
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
    public void QueuesManagerDAOInsertTest() 
    {
        QueuesManagerBean bean = new QueuesManagerBean();
        bean.setIdService(1);
        bean.setIdBalcony(1);
        bean.setIdUserCheckin(1);
        bean.setIdUserCheckout(1);
        bean.setIdClient(1);
        bean.setCheckin(null);
        bean.setCheckout(null);
        bean.setPassNumber("1");
        
        assertTrue( QueuesManagerDAO.insert(bean) );
    }
    
    
}