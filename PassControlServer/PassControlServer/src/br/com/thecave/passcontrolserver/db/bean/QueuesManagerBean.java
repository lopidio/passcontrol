package br.com.thecave.passcontrolserver.db.bean;

import java.util.Date;

/**
 * Classe que irá guardar os dados da tabela TB_QUEUES_MANAGER no banco "queue_manager.db"
 * @author Glaylson Alves
 */
public class QueuesManagerBean extends TableBean 
{
    /**
     * Guarda o valor da variavel [INT_ID] da tabela TB_QUEUES_MANAGER
     */
    private int id;
    /**
     * Guarda o valor da variavel [INT_ID_SERVICE] da tabela TB_QUEUES_MANAGER
     */
    private int idService;
    /**
     * Guarda o valor da variavel [INT_ID_BALCONY] da tabela TB_QUEUES_MANAGER
     */
    private int idBalcony;
    /**
     * Guarda o valor da variavel [INT_ID_USER_CHECKIN] da tabela TB_QUEUES_MANAGER
     */
    private int idUserCheckin;
    /**
     * Guarda o valor da variavel [INT_ID_CHECKOUT] da tabela TB_QUEUES_MANAGER
     */
    private int idUserCheckout;
    /**
     * Guarda o valor da variavel [INT_ID_CLIENT] da tabela TB_QUEUES_MANAGER
     */
    private int idClient;
    /**
     * Guarda o valor da variavel [DT_CHECKIN] da tabela TB_QUEUES_MANAGER
     */
    private String checkin;
    /**
     * Guarda o valor da variavel [DT_CHECKOUT] da tabela TB_QUEUES_MANAGER
     */
    private String checkout;
    /**
     * Guarda o valor da variavel [TX_PASS_NUMBER] da tabela TB_QUEUES_MANAGER
     */
    private String passNumber;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idService
     */
    public int getIdService() {
        return idService;
    }

    /**
     * @param idService the idService to set
     */
    public void setIdService(int idService) {
        this.idService = idService;
    }

    /**
     * @return the idBalcony
     */
    public int getIdBalcony() {
        return idBalcony;
    }

    /**
     * @param idBalcony the idBalcony to set
     */
    public void setIdBalcony(int idBalcony) {
        this.idBalcony = idBalcony;
    }

    /**
     * @return the idUserCheckin
     */
    public int getIdUserCheckin() {
        return idUserCheckin;
    }

    /**
     * @param idUserCheckin the idUserCheckin to set
     */
    public void setIdUserCheckin(int idUserCheckin) {
        this.idUserCheckin = idUserCheckin;
    }

    /**
     * @return the idUserCheckout
     */
    public int getIdUserCheckout() {
        return idUserCheckout;
    }

    /**
     * @param idUserCheckout the idUserCheckout to set
     */
    public void setIdUserCheckout(int idUserCheckout) {
        this.idUserCheckout = idUserCheckout;
    }

    /**
     * @return the idClient
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * @param idClient the idClient to set
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * @return the checkin
     */
    public String getCheckin() {
        return checkin;
    }

    /**
     * @param checkin the checkin to set
     */
    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    /**
     * @return the checkout
     */
    public String getCheckout() {
        return checkout;
    }

    /**
     * @param checkout the checkout to set
     */
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    /**
     * @return the passNumber
     */
    public String getPassNumber() {
        return passNumber;
    }

    /**
     * @param passNumber the passNumber to set
     */
    public void setPassNumber(String passNumber) {
        this.passNumber = passNumber;
    }

}
