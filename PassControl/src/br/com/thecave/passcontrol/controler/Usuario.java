package br.com.thecave.passcontrol.controler;

import br.com.thecave.passcontrol.db.bean.UserBean;
import br.com.thecave.passcontrol.db.dao.UserDAO;

/**
 *
 * @author Antonio Arleudo da Costa
 */
public class Usuario 
{
    private static Usuario instance;
    private String name;
    private String pass;
    
    private Usuario()
    {
    }
    
    public static Usuario getInstance()
    {
        if(instance == null)
            instance = new Usuario();
        return instance;
    }
    
    public void init(String name, String pass)
    {
        this.name = name;
        this.pass = pass;
    }
    
    /**
     * Metodo para verificar se o usuário é o Super
     * @param user String com o nome do usuário
     * @param pass String com a senha digitada
     * @return true se fo,r false se não
     */
    public boolean isSuperUser()
    {
        if(name.equalsIgnoreCase("admin"))
        {
            if(pass.equals("supasscontroladmin"))
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Verifica se a senha passada confere com a senha salva no banco de dados
     * @return true se a senha confere, false, caso contrário
     */
    public boolean checkPassword()
    {
        UserBean bean = UserDAO.selectFromName(name);
        if(bean.getName() == null)
            return false;
        if(bean.getPassword().equals(pass))
        {
            return true;
        }
        return false;
    }
}
