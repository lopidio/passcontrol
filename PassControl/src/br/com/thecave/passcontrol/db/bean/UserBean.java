<<<<<<< HEAD
package br.com.thecave.passcontrol.db.bean;

/**
 * Classe para encapsular os dados da tabela [TB_USER]
 * 
 * @author Antonio Arleudo da Costa
 */
public class UserBean 
{
    /**
     * Guarda o valor da variavel [INT_ID] da tabela TB_USER
     */
    private int id;
     /**
     * Guarda o valor da variavel [TX_NAME] da tabela TB_USER
     */
    private String name;
=======

package br.com.thecave.passcontrol.db.bean;
/**
 * Classe que irá guardar os dados da tabela TB_USER no banco "queue_manager.db"
 * @author Glaylson Alves
 */
public class UserBean extends TableBean{
     /**
     * Guarda o valor da variavel [INT_ID] da tabela TB_USER
     */
    private int id;
>>>>>>> refs/remotes/origin/master
    /**
     * Guarda o valor da variavel [INT_TYPE] da tabela TB_USER
     */
    private int type;
    /**
<<<<<<< HEAD
=======
     * Guarda o valor da variavel [TX_NAME] da tabela TB_USER
     */
    private String name;
    /**
>>>>>>> refs/remotes/origin/master
     * Guarda o valor da variavel [TX_LOGIN] da tabela TB_USER
     */
    private String login;
    /**
     * Guarda o valor da variavel [TX_PASSWORD] da tabela TB_USER
     */
    private String password;

    /**
     * @return the id
     */
    public int getId() 
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) 
    {
        this.id = id;
    }

    /**
<<<<<<< HEAD
     * @return the name
     */
    public String getName() 
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * @return the type
     */
    public int getType() 
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) 
    {
        this.type = type;
=======
     * @return the type
     */
    public int getType() 
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) 
    {
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() 
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) 
    {
        this.name = name;
>>>>>>> refs/remotes/origin/master
    }

    /**
     * @return the login
     */
    public String getLogin() 
    {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) 
    {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() 
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) 
    {
        this.password = password;
    }
}
