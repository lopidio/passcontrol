package br.com.thecave.passcontrol.testes;

import br.com.thecave.passcontrol.db.bean.UserBean;
import br.com.thecave.passcontrol.db.dao.UserDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antonio Arleudo da Costa
 */
public class UserDAOTest 
{
    
    public UserDAOTest() 
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
    public void insertTeste()
    {
        UserBean bean = new UserBean();
        bean.setId(1);
        bean.setLogin("login");
        bean.setName("name");
        bean.setPassword("senha");
        bean.setType(1);
                
        assertTrue(UserDAO.insert(bean));
        
    }
    
}