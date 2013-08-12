package br.com.thecave.passcontrol.db.dao;
import br.com.thecave.passcontrol.db.ConnectionDataBase;
import br.com.thecave.passcontrol.db.bean.ServiceBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * Classe para persistencia na tabela TB_SERVICE utilizando a classe ServiceBean
 * @see ServiceBean
 * @author Glaylson Alves
 */

public class ServiceDAO {
    /**
     * Metodo para persistir um ServiceBean na tabela TB_SERVICE
     * @param bean Objeto ServiceBean a ser inserido
     * @return  true se o metodo executar corretamente, false caso contrario
     * @see ServiceBean
     */
    public static boolean insert(ServiceBean bean)
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
            String sql = "INSERT INTO TB_SERVICE (TX_NAME,INT_PRIORITY) " +
                         "VALUES ('" + bean.getName() + "', " + 
                         bean.getPriority() + ");";
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
    public static boolean update(ServiceBean bean)
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
            String sql = "UPDATE TB_SERVICE SET TX_NAME = '"+ bean.getName() +
                    "',INT_PRIORITY = " + bean.getPriority() +
                    " WHERE INT_ID=" + bean.getId() + ";";           
            
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
     * Metodo para deleção de um registro na tabela TB_SERVICE com base no ServiceBean, utilizando o seu id
     * @param bean ServiceBean a ser deletado.
     * @see ServiceBean
     * @return True se deleção ocorrida com sucesso. false, caso contrario.
     */
    public static boolean delete(ServiceBean bean)
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
            String sql = "DELETE FROM  TB_SERVICE WHERE INT_ID=" + bean.getId() + ";";

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
     * Metodo para recuperar um ServiceBean a partir de seu id.
     * @param id Id do registro que se quer recuperar
     * @return Bean com os dados ja preenchidos.
     */
    public static ServiceBean selectFromId(int id)
    {
        ServiceBean bean = new ServiceBean();
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM TB_SERVICE;";

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                bean.setId(rs.getInt("INT_ID"));
                bean.setName(rs.getString("TX_NAME"));
                bean.setPriority(rs.getInt("INT_PRIORITY"));
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
