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
 * Classe de teste do UserDAO
 * @author Antonio Arleudo
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
    public void UserDaoInsertTest()
    {
        UserBean bean = new UserBean();
        bean.setName("name");
        bean.setType(1);
        bean.setLogin("login");
        bean.setPassword("senha");
        
        assertTrue(UserDAO.insert(bean));        
    }
    @Test
    public void UserDaoUpdateTest()
    {
//        UserBean bean = new UserBean();
//        bean.setId(1);
//        
//        assertTrue(UserDAO.update(bean));        
    }
}