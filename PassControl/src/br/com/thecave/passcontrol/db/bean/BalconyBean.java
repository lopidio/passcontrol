package br.com.thecave.passcontrol.db.bean;
/**
 * Classe que irá guardar os dados da tabela TB_BALCONY no banco "queue_manager.db"
 * @author Glaylson Alves
 */
public class BalconyBean extends TableBean
{
    /**
     * Guarda o valor da variavel [INT_ID] da tabela TB_BALCONY
     */
    private int id;
    /**
     * Guarda o valor da variavel [INT_NUMBER] da tabela TB_BALCONY
     */
    private int number;
    /**
     * Guarda o valor da variavel [INT_ID_BALCONY_TYPE] da tabela TB_BALCONY
     */
    private int idBalconyType;
    
    
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
     * @return the number
     */
    public int getNumber() 
    {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) 
    {
        this.number = number;
    }

    /**
     * @return the idBalconyType
     */
    public int getIdBalconyType() {
        return idBalconyType;
    }

    /**
     * @param idBalconyType the idBalconyType to set
     */
    public void setIdBalconyType(int idBalconyType) {
        this.idBalconyType = idBalconyType;
    }
  
}