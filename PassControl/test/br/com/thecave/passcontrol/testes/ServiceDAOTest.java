package br.com.thecave.passcontrol.testes;

import br.com.thecave.passcontrol.db.bean.ServiceBean;
import br.com.thecave.passcontrol.db.dao.ServiceDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de teste do UserDAO
 * @author Glaylson Alves 
 */
public class ServiceDAOTest {
    
    public ServiceDAOTest() {
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
    public void ServiceDAOInsetTest() 
    {
//        ServiceBean bean = new ServiceBean();
//        bean.setName("test");
//        bean.setPriority(1);
//        
//        assertTrue( ServiceDAO.insert(bean) );
    }
    
    @Test
    public void ServiceDAOUpdateTest() 
    {
//        ServiceBean bean = new ServiceBean();
//        bean.setName("deuCerto");
//        bean.setPriority(10);
//        bean.setId(1);
//        
//        assertTrue( ServiceDAO.update(bean) );
    }
    
    @Test
    public void ServiceDAODeleteTest() 
    {
//        ServiceBean bean = new ServiceBean();
//        bean.setId(4);
//        
//        assertTrue( ServiceDAO.update(bean) );
    }
    
    @Test
    public void ServiceDAOSelectTest() 
    {
       ServiceBean bean = ServiceDAO.selectFromId(4);
       assertNotNull(bean);
    }

    
}