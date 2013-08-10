package br.com.thecave.passcontrol.db.dao;

import br.com.thecave.passcontrol.db.ConnectionDataBase;
import br.com.thecave.passcontrol.db.bean.QueuesManagerBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Antonio Arleudo da Costa
 */
public class QueuesManagerDAO 
{
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
                                                        bean.getIdClient() + ", " +
                                                        bean.getCheckin() + ", " +
                                                        bean.getCheckout() + ", " +                     
                                                        "'" + bean.getPassNumber()+ "' );";
            stmt.executeUpdate(sql);           

            stmt.close();
            conn.commit();
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
//            String sql = "UPDATE TB_USER SET TX_NAME = '"+ bean.getName() + 
//                         "',INT_TYPE = " + bean.getType() + 
//                         ",TX_LOGIN = '"+ bean.getLogin() + 
//                         "',TX_PASSWORD = '" + bean.getPassword() + 
//                         "' WHERE INT_ID=" + bean.getId() + ";";

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
     * Metodo para deleção de um registro na tabela TB_USER com base no QueuesManagerBean, utilizando o seu id
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
            String sql = "DELETE FROM  TB_USER WHERE INT_ID=" + bean.getId() + ";";

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
    public QueuesManagerBean selectFromId(int id)
    {
        QueuesManagerBean bean = new QueuesManagerBean();
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM TB_USER;";

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
//                bean.setId(rs.getInt("INT_ID"));
//                bean.setName(rs.getString("TX_NAME"));
//                bean.setType(rs.getInt("INT_TYPE"));
//                bean.setLogin(rs.getString("TX_LOGIN"));
//                bean.setPassword(rs.getString("TX_PASSWORD"));
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
}
