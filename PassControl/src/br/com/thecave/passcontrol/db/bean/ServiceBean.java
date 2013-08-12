package br.com.thecave.passcontrol.db.bean;
/**
 * Classe que irá guardar os dados da tabela TB_SERVICE no banco "queue_manager.db"
 * @author Glaylson Alves
 */
public class ServiceBean extends TableBean
{
    /**
     * Guarda o valor da variavel [INT_ID] da tabela TB_SERVICE
     */
    private int id;
    /**
     * Guarda o valor da variavel [TX_NAME] da tabela TB_SERVICE
     */
    private String name;
    /**
     * Guarda o valor da variavel [INT_PRIORITY] da tabela TB_SERVICE
     */
    private int priority;

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
     * @return the priority
     */
    public int getPriority() 
    {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) 
    {
        this.priority = priority;
    }   
}
