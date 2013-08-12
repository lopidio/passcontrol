package br.com.thecave.passcontrol.db.dao;

import br.com.thecave.passcontrol.db.ConnectionDataBase;
import br.com.thecave.passcontrol.db.bean.BalconyBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * Classe para persistencia na tabela TB_BALCONY utilizando a classe BalconyBean
 * @see BalconyBean
 * @author Glaylson Alves
 */
public class BalconyDAO {
    /**
     * Metodo para persistir um BalconyBean na tabela TB_BALCONY
     * @param bean Objeto BalconyBean a ser inserido
     * @return  true se o metodo executar corretamente, false caso contrario
     * @see BalconyBean
     */
    public static boolean insert(BalconyBean bean)
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
            String sql = "INSERT INTO TB_BALCONY (INT_NUMBER,INT_ID_BALCONY_TYPE) " +
                         "VALUES (" + bean.getNumber() +" , " + bean.getIdBalconyType()+ " );";
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
    public static boolean update(BalconyBean bean)
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
            String sql = "UPDATE TB_BALCONY SET INT_NUMBER = "+ bean.getNumber() + 
                    ",INT_ID_BALCONY_TYPE = " + bean.getIdBalconyType() + 
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
     * Metodo para deleção de um registro na tabela TB_BALCONY com base no BalconyBean, utilizando o seu id
     * @param bean BalconyBean a ser deletado.
     * @see BalconyBean
     * @return True se deleção ocorrida com sucesso. false, caso contrario.
     */
    public static boolean delete(BalconyBean bean)
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
            String sql = "DELETE FROM TB_BALCONY WHERE INT_ID=" + bean.getId() + ";";
            
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
     * Metodo para recuperar um BalconyBean a partir de seu id.
     * @param id Id do registro que se quer recuperar
     * @return Bean com os dados ja preenchidos.
     */
    public static BalconyBean selectFromId(int id)
    {
        BalconyBean bean = new BalconyBean();
        try
        {
        // pegar a conexão com o bancos
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM TB_BALCONY;";

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                bean.setId(rs.getInt("INT_ID"));    
                bean.setNumber(rs.getInt("INT_NUMBER"));
                bean.setIdBalconyType(rs.getInt("INT_ID_BALCONY_TYPE"));

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
