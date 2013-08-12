package br.com.thecave.passcontrol.db.bean;
/**
 * Classe que irá guardar os dados da tabela TB_BALCONY_TYPES_SERVICE no banco "queue_manager.db"
 * @author Glaylson Alves
 */
public class BalconyTypesServiceBean extends TableBean
{
    /**
     * Guarda o valor da variavel [INT_ID] da tabela TB_BALCONY_TYPES_SERVICE
     */
    private int id;
    /**
     * Guarda o valor da variavel [TX_ID_BALCONY] da tabela TB_BALCONY_TYPES_SERVICE
     */
    private int idBalcony;
   /**
     * Guarda o valor da variavel [TX_ID_SERVICE] da tabela TB_BALCONY_TYPES_SERVICE
     */
    private int idService;

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
     * @return the idBalcony
     */
    public int getIdBalcony() 
    {
        return idBalcony;
    }

    /**
     * @param idBalcony the idBalcony to set
     */
    public void setIdBalcony(int idBalcony) 
    {
        this.idBalcony = idBalcony;
    }

    /**
     * @return the idService
     */
    public int getIdService() 
    {
        return idService;
    }

    /**
     * @param idService the idService to set
     */
    public void setIdService(int idService) 
    {
        this.idService = idService;
    }
}
