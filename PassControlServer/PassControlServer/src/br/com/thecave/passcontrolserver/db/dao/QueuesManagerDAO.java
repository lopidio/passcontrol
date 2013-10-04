package br.com.thecave.passcontrolserver.db.dao;

import br.com.thecave.passcontrolserver.db.ConnectionDataBase;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import br.com.thecave.passcontrolserver.util.QueueElementHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Antonio Arleudo da Costa
 */
public class QueuesManagerDAO 
{
    public final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddkkmmss");

    /**
     * Metodo para persistir um QueuesManagerBean na tabela Tb_QUEUES_MANAGER
     * @param bean Objeto QueuesManagerBean a ser inserido
     * @return  true se o metodo executar corretamente, false caso contrario
     * @see QueuesManagerBean
     */
    public static boolean insert(QueuesManagerBean bean)
    {
        try
        {
            // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return false;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "INSERT INTO TB_QUEUES_MANAGER (INT_ID_SERVICE," + 
                                                        "INT_ID_BALCONY," + 
                                                        "INT_ID_USER_CHECKIN," + 
                                                        "INT_ID_USER_CHECKOUT," +
                                                        "INT_ID_CLIENT," +
                                                        "DT_CHECKIN," + 
                                                        "DT_CHECKOUT," + 
                                                        "TX_PASS_NUMBER) " +
                                                        "VALUES (" + 
                                                        bean.getIdService() + ", " + 
                                                        bean.getIdBalcony() + ", " +
                                                        bean.getIdUserCheckin() + ", " +
                                                        bean.getIdUserCheckout() + ", " +
                                                        bean.getIdClient() + ", '" +
                                                        bean.getCheckin() + "', '" +
                                                        bean.getCheckout() + "', " +  
                                                        "'" + bean.getPassNumber()+ "' );";
            stmt.executeUpdate(sql);           

            stmt.close();
            conn.commit();
            conn.close();
            return true;
        } 
        catch ( Exception e ) 
        {
          e.printStackTrace();
          ConnectionDataBase.getInstance().closeConnection();
          return false;
        }
    }
    /**
     * Metodo para atualizar um registro utilizando um beam atualizado
     * @param bean Bean atual.
     * @return true se executado com sucesso, false, caso contrario.
     */
    public static boolean update(QueuesManagerBean bean)
    {
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return false;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "UPDATE TB_QUEUES_MANAGER SET INT_ID_SERVICE = "+ bean.getIdService() + 
                         ",INT_ID_BALCONY = " + bean.getIdBalcony() + 
                         ",INT_ID_USER_CHECKIN = "+ bean.getIdUserCheckin() + 
                         ",INT_ID_USER_CHECKOUT = " + bean.getIdUserCheckout() + 
                         ",INT_ID_CLIENT = '"+ bean.getIdClient() +
                         "',DT_CHECKIN = '" + bean.getCheckin() +
                         "',DT_CHECKOUT = '" + bean.getCheckout() +
                         "',TX_PASS_NUMBER = '" + bean.getPassNumber() +
                         "' WHERE INT_ID=" + bean.getId() + ";";

            stmt.executeUpdate(sql);
            conn.commit();          
            stmt.close();
            conn.close();
            return true;
        }
        catch ( Exception e ) 
        {
          //TODO: logar erro
            e.printStackTrace();
          ConnectionDataBase.getInstance().closeConnection();
          return false;
        }              
    }
    /**
     * Metodo para deleção de um registro na tabela TB_QUEUES_MANAGER com base no QueuesManagerBean, utilizando o seu id
     * @param bean QueuesManagerBean a ser deletado.
     * @see QueuesManagerBean
     * @return True se deleção ocorrida com sucesso. false, caso contrario.
     */
    public static boolean delete(QueuesManagerBean bean)
    {
     try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return false;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "DELETE FROM  TB_QUEUES_MANAGER WHERE INT_ID=" + bean.getId() + ";";

            stmt.executeUpdate(sql);
            conn.commit();          
            stmt.close();
            conn.close();
            return true;
        }
        catch ( Exception e ) 
        {
            //TODO: logar erro
          ConnectionDataBase.getInstance().closeConnection();
          return false;
        }    
    }

    /**
     * Metodo para recuperar um QueuesManagerBean a partir de seu id.
     * @param id Id do registro que se quer recuperar
     * @return Bean com os dados ja preenchidos.
     */
    public static QueuesManagerBean selectFromId(int id)
    {
        QueuesManagerBean bean = null;
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM TB_QUEUES_MANAGER WHERE INT_ID="+id+";";

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                bean = new QueuesManagerBean();
                bean.setId(rs.getInt("INT_ID"));
                bean.setIdService(rs.getInt("INT_ID_SERVICE"));
                bean.setIdBalcony(rs.getInt("INT_ID_BALCONY"));
                bean.setIdUserCheckin(rs.getInt("INT_ID_USER_CHECKIN"));
                bean.setIdUserCheckout(rs.getInt("INT_ID_USER_CHECKOUT"));
                bean.setIdClient(rs.getInt("INT_ID_CLIENT"));
                bean.setCheckin(rs.getString("DT_CHECKIN"));
                bean.setPassNumber(rs.getString("TX_PASS_NUMBER"));
                bean.setCheckout(rs.getString("DT_CHECKOUT"));
            }
            
            stmt.close();
            conn.close();
            return bean;
        }
        catch ( Exception e ) 
        {
            //TODO: logar erro
          ConnectionDataBase.getInstance().closeConnection();
          return null;
        }  
    }    
    
    /**
     * Método para recuperar todos os clientes passíveis de atendimento por um certo guichê ordenados por ordem de chegada
     * @param id Id do registro que se quer recuperar
     * @return Bean com os dados ja preenchidos.
     */
    public static HashMap<Integer, ArrayList<QueuesManagerBean>> selectAvaliableClientsOfBalcony(BalconyBean balconyBean)
    {
        //Uma lista para cada prioridade
        HashMap<Integer, ArrayList<QueuesManagerBean>> retorno = new HashMap<>(5);
        ArrayList<ArrayList<QueuesManagerBean>> queuesManagerBeansList = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            queuesManagerBeansList.add(new ArrayList<QueuesManagerBean>());
        }
        
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT TQM.INT_ID, TQM.INT_ID_SERVICE, TQM.INT_ID_BALCONY ,TQM.INT_ID_USER_CHECKIN ,TQM.INT_ID_USER_CHECKOUT ,TQM.INT_ID_CLIENT ,TQM.DT_CHECKIN,TQM.TX_PASS_NUMBER,TQM.DT_CHECKOUT, INT_PRIORITY "
                            + " FROM TB_QUEUES_MANAGER AS TQM, " +
                            "(SELECT * FROM TB_BALCONY_TYPES_SERVICE WHERE INT_ID_BALCONY = "+balconyBean.getId()+") as TBTS, " +
                            " (SELECT * FROM TB_SERVICE) as TS "+
                            "WHERE TBTS.INT_ID_SERVICE = TS.INT_ID AND TQM.INT_ID_SERVICE = TBTS.INT_ID_SERVICE AND TQM.INT_ID_BALCONY <> " + QueueElementHandler.QUEUE_ELEMENT_SKIPPED_BALCONY_ID
                    + " AND TQM.DT_CHECKOUT = 'null' ORDER BY TQM.DT_CHECKIN;";
     
            
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                QueuesManagerBean bean = new QueuesManagerBean();
                bean.setId(rs.getInt("TQM.INT_ID"));
                bean.setIdService(rs.getInt("TQM.INT_ID_SERVICE"));
                bean.setIdBalcony(rs.getInt("TQM.INT_ID_BALCONY"));
                bean.setIdUserCheckin(rs.getInt("TQM.INT_ID_USER_CHECKIN"));
                bean.setIdUserCheckout(rs.getInt("TQM.INT_ID_USER_CHECKOUT"));
                bean.setIdClient(rs.getInt("TQM.INT_ID_CLIENT"));
                bean.setCheckin(rs.getString("TQM.DT_CHECKIN"));
                bean.setPassNumber(rs.getString("TQM.TX_PASS_NUMBER"));                
                bean.setCheckout(rs.getString("TQM.DT_CHECKOUT"));
                int priority = rs.getInt("INT_PRIORITY");
                queuesManagerBeansList.get(priority - 1).add(bean); //Pq a prioridade no banco varia de 1 - 5
            }
            
            //Adiciona no retorno
            for (int i = 0; i < 5; i++) 
            {
                retorno.put(i, queuesManagerBeansList.get(i));
            }
        
            stmt.close();
            conn.close();
            return retorno;
        }
        catch ( Exception e ) 
        {
            e.printStackTrace();
            //TODO: logar erro
          ConnectionDataBase.getInstance().closeConnection();
          return null;
        }  
    }    
    
    /**
     * Método para se existe elementos de um determinado serviço que não foram atendidos ainda
     * @param id Id do registro que se quer recuperar
     * @return Bean com os dados ja preenchidos.
     */
    public static boolean selectAvaliableTuplesFromService(ServiceBean serviceBean)
    {
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return false;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM TB_QUEUES_MANAGER WHERE INT_ID_BALCONY = 0 AND INT_ID_SERVICE = "+serviceBean.getId()+" LIMIT 1;";

            ResultSet rs = stmt.executeQuery(sql);
            
            boolean retorno = false;
            if(rs.next())
            {
                retorno = true;
            }
            
            stmt.close();
            conn.close();
            return retorno;
        }
        catch ( Exception e ) 
        {
            //TODO: logar erro
          ConnectionDataBase.getInstance().closeConnection();
          return false;
        }  
    }    

    public static int getCountClientsOfService(int serviceId)
    {
        int retorno = 0;
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return retorno;
            
            Statement stmt;
            conn.setAutoCommit(false);
            
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) as COUNT FROM TB_QUEUES_MANAGER WHERE INT_ID_SERVICE = "+serviceId+";";

            ResultSet rs = stmt.executeQuery(sql);
            
            
            if(rs.next())
            {
                retorno = rs.getInt("COUNT");
            }
            
            stmt.close();
            conn.close();
            return retorno;
        }
        catch ( Exception e ) 
        {
            //TODO: logar erro
          ConnectionDataBase.getInstance().closeConnection();
          return 0;
        }  
        
    }

    public static ArrayList<QueuesManagerBean> selectLastCalledClientsFromEachServices() {
        ArrayList<QueuesManagerBean> retorno = new ArrayList<>();
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);
            
//            SimpleDateFormat todayFormatter = new SimpleDateFormat("yyyyMMdd");
//            String today = todayFormatter.format(new Date()) + "______"; //yyyymmdd hhmmss //HOJE!! :D

            stmt = conn.createStatement();
            String sql = "SELECT MAX(DT_CHECKIN) as DT_CHECKIN, INT_ID, INT_ID_SERVICE , INT_ID_BALCONY, INT_ID_USER_CHECKIN , INT_ID_USER_CHECKOUT, INT_ID_CLIENT, DT_CHECKOUT ,TX_PASS_NUMBER FROM TB_QUEUES_MANAGER WHERE INT_ID_BALCONY != 0 GROUP BY INT_ID_SERVICE;";

            ResultSet rs = stmt.executeQuery(sql);
            
            
            if(rs.next())
            {
                QueuesManagerBean bean = new QueuesManagerBean();
                bean.setId(rs.getInt("INT_ID"));
                bean.setIdService(rs.getInt("INT_ID_SERVICE"));
                bean.setIdBalcony(rs.getInt("INT_ID_BALCONY"));
                bean.setIdUserCheckin(rs.getInt("INT_ID_USER_CHECKIN"));
                bean.setIdUserCheckout(rs.getInt("INT_ID_USER_CHECKOUT"));
                bean.setIdClient(rs.getInt("INT_ID_CLIENT"));
                bean.setCheckin(rs.getString("DT_CHECKIN"));
                bean.setPassNumber(rs.getString("TX_PASS_NUMBER"));                
                bean.setCheckout(rs.getString("DT_CHECKOUT"));
                retorno.add(bean);
            }
            
            stmt.close();
            conn.close();
            return retorno;
        }
        catch ( Exception e ) 
        {
            //TODO: logar erro
          ConnectionDataBase.getInstance().closeConnection();
          return null;
        }  
    }

    public static QueuesManagerBean selectLastInsertedBean() 
    {
        QueuesManagerBean bean = null;
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM TB_QUEUES_MANAGER ORDER BY INT_ID DESC LIMIT 1;";

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                bean = new QueuesManagerBean();
                bean.setId(rs.getInt("INT_ID"));
                bean.setIdService(rs.getInt("INT_ID_SERVICE"));
                bean.setIdBalcony(rs.getInt("INT_ID_BALCONY"));
                bean.setIdUserCheckin(rs.getInt("INT_ID_USER_CHECKIN"));
                bean.setIdUserCheckout(rs.getInt("INT_ID_USER_CHECKOUT"));
                bean.setIdClient(rs.getInt("INT_ID_CLIENT"));
                bean.setCheckin(rs.getString("DT_CHECKIN"));
                bean.setPassNumber(rs.getString("TX_PASS_NUMBER"));                
                bean.setCheckout(rs.getString("DT_CHECKOUT"));
            }
            
            stmt.close();
            conn.close();
            return bean;
        }
        catch ( Exception e ) 
        {
            //TODO: logar erro
          ConnectionDataBase.getInstance().closeConnection();
          return null;
        }  
    }

    public static ArrayList<QueuesManagerBean> selectAllClientsWaiting() 
    {
        ArrayList<QueuesManagerBean> retorno = new ArrayList<>();
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM TB_QUEUES_MANAGER WHERE DT_CHECKOUT = 'null';";

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                QueuesManagerBean bean = new QueuesManagerBean();
                bean.setId(rs.getInt("INT_ID"));
                bean.setIdService(rs.getInt("INT_ID_SERVICE"));
                bean.setIdBalcony(rs.getInt("INT_ID_BALCONY"));
                bean.setIdUserCheckin(rs.getInt("INT_ID_USER_CHECKIN"));
                bean.setIdUserCheckout(rs.getInt("INT_ID_USER_CHECKOUT"));
                bean.setIdClient(rs.getInt("INT_ID_CLIENT"));
                bean.setCheckin(rs.getString("DT_CHECKIN"));
                bean.setPassNumber(rs.getString("TX_PASS_NUMBER"));                
                bean.setCheckout(rs.getString("DT_CHECKOUT"));
                retorno.add(bean);
            }
            
            stmt.close();
            conn.close();
            return retorno;
        }
        catch ( Exception e ) 
        {
            //TODO: logar erro
          ConnectionDataBase.getInstance().closeConnection();
          return null;
        }  
    }

    public static QueuesManagerBean recoverSkippedClientFromBalcony(BalconyBean balconyBean) 
    {
        QueuesManagerBean retorno = null;
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT TQM.INT_ID, TQM.INT_ID_SERVICE, TQM.INT_ID_BALCONY ,TQM.INT_ID_USER_CHECKIN ,TQM.INT_ID_USER_CHECKOUT ,TQM.INT_ID_CLIENT ,TQM.DT_CHECKIN,TQM.TX_PASS_NUMBER,TQM.DT_CHECKOUT"
                            + " FROM TB_QUEUES_MANAGER AS TQM, " +
                            "(SELECT * FROM TB_BALCONY_TYPES_SERVICE WHERE INT_ID_BALCONY = "+balconyBean.getId()+") as TBTS " +
                            "WHERE TQM.INT_ID_SERVICE = TBTS.INT_ID_SERVICE AND TQM.INT_ID_BALCONY = " + QueueElementHandler.QUEUE_ELEMENT_SKIPPED_BALCONY_ID
                    + " AND TQM.DT_CHECKOUT = 'null' ORDER BY TQM.DT_CHECKIN LIMIT 1;";
     
            
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                retorno = new QueuesManagerBean();
                retorno.setId(rs.getInt("TQM.INT_ID"));
                retorno.setIdService(rs.getInt("TQM.INT_ID_SERVICE"));
                retorno.setIdBalcony(rs.getInt("TQM.INT_ID_BALCONY"));
                retorno.setIdUserCheckin(rs.getInt("TQM.INT_ID_USER_CHECKIN"));
                retorno.setIdUserCheckout(rs.getInt("TQM.INT_ID_USER_CHECKOUT"));
                retorno.setIdClient(rs.getInt("TQM.INT_ID_CLIENT"));
                retorno.setCheckin(rs.getString("TQM.DT_CHECKIN"));
                retorno.setPassNumber(rs.getString("TQM.TX_PASS_NUMBER"));                
                retorno.setCheckout(rs.getString("TQM.DT_CHECKOUT"));
            }
            
        
            stmt.close();
            conn.close();
            return retorno;
        }
        catch ( Exception e ) 
        {
            e.printStackTrace();
            //TODO: logar erro
          ConnectionDataBase.getInstance().closeConnection();
          return null;
        }  
        
    }
}
