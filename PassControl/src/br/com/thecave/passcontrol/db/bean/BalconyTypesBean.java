package br.com.thecave.passcontrol.db.bean;

/**
 * Classe que irá guardar os dados da tabela TB_BALCONY_TYPES no banco "queue_manager.db"
 * @author Glaylson Alves
 */
public class BalconyTypesBean extends TableBean
{
    /**
     * Guarda o valor da variavel [INT_ID] da tabela TB_BALCONY_TYPES
     */
    private int id;
    /**
     * Guarda o valor da variavel [TX_TYPE] da tabela TB_BALCONY_TYPES
     */
    private String type;

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
     * @return the type
     */
    public String getType() 
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) 
    {
        this.type = type;
    }
}
