package br.com.thecave.passcontrolserver.db.dao;

import br.com.thecave.passcontrolserver.db.ConnectionDataBase;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.QueuesManagerBean;
import br.com.thecave.passcontrolserver.db.bean.ServiceBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

            //stmt.executeUpdate(sql);
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
    
    public static void main(String[] args)
    {
//        QueuesManagerBean bean = new QueuesManagerBean();
//        bean.setIdBalcony(1);
//        bean.setIdClient(0);
//        bean.setIdService(1);
//        bean.setPassNumber("asd");
//        bean.setCheckin(dateFormat.format(new Date()));
//        QueuesManagerDAO.insert(bean);
        System.out.println(QueuesManagerDAO.getCountOfClientsOfServiceToday(1));
    }
    
    /**
     * Método para recuperar todos os elementos passíveis de atendimento por um certo guichê ordenados por ordem de chegada
     * @param id Id do registro que se quer recuperar
     * @return Bean com os dados ja preenchidos.
     */
    public static ArrayList<QueuesManagerBean> selectAvaliableTuplesFromBalcony(BalconyBean balconyBean)
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
            String sql = "SELECT * FROM TB_QUEUES_MANAGER WHERE INT_ID_BALCONY = 0 AND INT_ID_SERVICE = "
                    + "(SELECT INT_ID_SERVICE FROM TB_BALCONY_TYPES_SERVICE WHERE INT_ID_BALCONY = "+balconyBean.getId()+") ORDER BY DT_CHECKIN";

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

    public static int getCountOfClientsOfServiceToday(int serviceId)
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
            
            SimpleDateFormat todayFormatter = new SimpleDateFormat("dd");
            String today = "______" + todayFormatter.format(new Date()) + "______"; //yyyymm dd hhmmss

            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) as COUNT FROM TB_QUEUES_MANAGER WHERE INT_ID_SERVICE = "+serviceId+" AND DT_CHECKIN LIKE '" +  today + "';";

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
}
