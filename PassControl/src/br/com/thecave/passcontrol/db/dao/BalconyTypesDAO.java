package br.com.thecave.passcontrol.db.dao;

import br.com.thecave.passcontrol.db.ConnectionDataBase;
import br.com.thecave.passcontrol.db.bean.BalconyTypesBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Classe para persistencia na tabela TB_BALCONY_TYPES utilizando a classe BalconyTypesBean
 * @see BalconyTypesBean
 * @author Glaylson Alves
 */
public class BalconyTypesDAO 
{
    /**
     * Metodo para persistir um BalconyTypesBean na tabela TB_BALCONY_TYPES
     * @param bean Objeto BalconyTypesBean a ser inserido
     * @return  true se o metodo executar corretamente, false caso contrario
     * @see BalconyTypesBean
     */
    public static boolean insert(BalconyTypesBean bean)
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
            String sql = "INSERT INTO TB_BALCONY_TYPES (TX_TYPE) " +
                         "VALUES ('" + bean.getType() +"' );";
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
    public static boolean update(BalconyTypesBean bean)
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
            String sql = "UPDATE TB_BALCONY_TYPES SET TX_TYPE = '"+ bean.getType() + 
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
          ConnectionDataBase.getInstance().closeConnection();
          return false;
        }              
    }
    /**
     * Metodo para deleção de um registro na tabela TB_BALCONY_TYPES com base no BalconyTypesBean, utilizando o seu id
     * @param bean BalconyTypesBean a ser deletado.
     * @see BalconyTypesBean
     * @return True se deleção ocorrida com sucesso. false, caso contrario.
     */
    public static boolean delete(BalconyTypesBean bean)
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
            String sql = "DELETE FROM  TB_BALCONY_TYPES WHERE INT_ID=" + bean.getId() + ";";

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
     * Metodo para recuperar um BalconyTypesBean a partir de seu id.
     * @param id Id do registro que se quer recuperar
     * @return Bean com os dados ja preenchidos.
     */
    public static BalconyTypesBean selectFromId(int id)
    {
        BalconyTypesBean bean = new BalconyTypesBean();
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM TB_BALCONY_TYPES;";

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                bean.setId(rs.getInt("INT_ID"));
                bean.setType(rs.getString("TX_TYPE"));
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
