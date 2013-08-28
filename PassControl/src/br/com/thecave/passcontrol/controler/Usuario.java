package br.com.thecave.passcontrol.controler;

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
    
    public String getName()
    {
        return this.name;
    }
    
    public static Usuario getInstance()
    {
        if(instance == null)
            instance = new Usuario();
        return instance;
    }
    
    public void init(String name, String pass)
    {
        getInstance().name = name;
        getInstance().pass = pass;
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
        // TODO: invocar metodo send da thread de comunicação
        return true;
    }
}
