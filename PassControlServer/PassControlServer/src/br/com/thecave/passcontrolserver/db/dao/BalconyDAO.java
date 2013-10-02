package br.com.thecave.passcontrolserver.db.dao;

import br.com.thecave.passcontrolserver.db.ConnectionDataBase;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
            String sql = "INSERT INTO TB_BALCONY (TX_NUMBER) " +
                         "VALUES ('" + bean.getNumber() + "' );";
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
            String sql = "UPDATE TB_BALCONY SET TX_NUMBER = '"+ bean.getNumber() + 
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
        BalconyBean bean = null;
        try
        {
        // pegar a conexão com o bancos
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM TB_BALCONY WHERE INT_ID=" + id +";";

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                bean = new BalconyBean();
                bean.setId(rs.getInt("INT_ID"));    
                bean.setNumber(rs.getString("TX_NUMBER"));
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
     * Metodo para recuperar um BalconyBean a partir de seu id.
     * @param id Id do registro que se quer recuperar
     * @return Bean com os dados ja preenchidos.
     */
    public static ArrayList<BalconyBean> selectAll()
    {
        ArrayList<BalconyBean> retorno = new ArrayList<>();
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
                BalconyBean bean = new BalconyBean();
                bean.setId(rs.getInt("INT_ID"));    
                bean.setNumber(rs.getString("TX_NUMBER"));
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

    public static BalconyBean selectFromNumber(String number) 
    {
        BalconyBean bean = null;
        try
        {
        // pegar a conexão com o bancos
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM TB_BALCONY WHERE TX_NUMBER='" + number +"';";

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                bean = new BalconyBean();
                bean.setId(rs.getInt("INT_ID"));    
                bean.setNumber(rs.getString("TX_NUMBER"));
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
