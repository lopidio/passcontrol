package br.com.thecave.passcontrol.db.bean;

/**
 * Classe que irá guardar os dados da tabela TB_CLIENT no banco "queue_manager.db"
 * @author Glaylson Alves
 */
public class ClientBean extends TableBean
{
    /**
     * Guarda o valor da variavel [INT_ID] da tabela TB_CLIENT
     */
    private int id;
    /**
     * Guarda o valor da variavel [TX_REGISTER] da tabela TB_CLIENT
     */
    private String register;
    /**
     * Guarda o valor da variavel [TX_NAME] da tabela TB_CLIENT
     */
    private String name;
    /**
     * Guarda o valor da variavel [TX_TELEFONE] da tabela TB_CLIENT
     */
    private String telefone;

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
     * @return the register
     */
    public String getRegister() 
    {
        return register;
    }

    /**
     * @param register the register to set
     */
    public void setRegister(String register) 
    {
        this.register = register;
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
    }

    /**
     * @return the telefone
     */
    public String getTelefone() 
    {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) 
    {
        this.telefone = telefone;
    }
  
}
