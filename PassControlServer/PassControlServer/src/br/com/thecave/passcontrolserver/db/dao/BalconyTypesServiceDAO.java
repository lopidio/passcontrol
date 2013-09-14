package br.com.thecave.passcontrolserver.db.dao;

import br.com.thecave.passcontrolserver.db.ConnectionDataBase;
import br.com.thecave.passcontrolserver.db.bean.BalconyBean;
import br.com.thecave.passcontrolserver.db.bean.BalconyTypesServiceBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * Classe para persistencia na tabela TB_BALCONY_TYPES_SERVICE utilizando a classe BalconyTypesServiceBean
 * @see BalconyTypesServiceBean
 * @author Glaylson Alves
 */
public class BalconyTypesServiceDAO {
    /**
     * Metodo para persistir um BalconyTypesServiceBean na tabela TB_BALCONY_TYPES_SERVICE
     * @param bean Objeto BalconyTypesServiceBean a ser inserido
     * @return  true se o metodo executar corretamente, false caso contrario
     * @see BalconyTypesServiceBean
     */
    public static boolean insert(BalconyTypesServiceBean bean)
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
            String sql = "INSERT INTO TB_BALCONY_TYPES_SERVICE (INT_ID_BALCONY,INT_ID_SERVICE) " +
                         "VALUES (" + bean.getIdBalcony() + ","+ bean.getIdService()+" );";
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
    public static boolean update(BalconyTypesServiceBean bean)
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
            String sql = "UPDATE TB_BALCONY_TYPES_SERVICE SET INT_ID_BALCONY = "+ bean.getIdBalcony() + 
                         ",INT_ID_SERVICE = " + bean.getIdService() +
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
     * Metodo para deleção de um registro na tabela TB_BALCONY_TYPES_SERVICE com base no BalconyTypesServiceBean, utilizando o seu id
     * @param bean BalconyTypesServiceBean a ser deletado.
     * @see BalconyTypesServiceBean
     * @return True se deleção ocorrida com sucesso. false, caso contrario.
     */
    public static boolean delete(BalconyTypesServiceBean bean)
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
            String sql = "DELETE FROM  TB_BALCONY_TYPES_SERVICE WHERE INT_ID=" + bean.getId() + ";";

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
     * Metodo para recuperar um BalconyTypesServiceBean a partir de seu id.
     * @param id Id do registro que se quer recuperar
     * @return Bean com os dados ja preenchidos.
     */
    public static BalconyTypesServiceBean selectFromId(int id)
    {
        BalconyTypesServiceBean bean = null;
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM TB_BALCONY_TYPES_SERVICE WHERE INT_ID="+id+";";

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                bean = new BalconyTypesServiceBean();
                bean.setId(rs.getInt("INT_ID"));
                bean.setIdBalcony(rs.getInt("INT_ID_BALCONY"));
                bean.setIdService(rs.getInt("INT_ID_SERVICE"));

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
     * Método para recuperar os tipos que determinado guichê atende
     * @param balconyBean bean que se deseja saber os serviços
     * @return vetor de ids do Service
     */
    public static ArrayList<Integer> selectServicesIdFromBalconyId(BalconyBean balconyBean)
    {
        ArrayList<Integer> retorno = new ArrayList<>();
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT INT_ID_SERVICE FROM TB_BALCONY_TYPES_SERVICE WHERE INT_ID_BALCONY="+balconyBean.getId()+";";

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                retorno.add(rs.getInt("INT_ID_SERVICE"));
            }
            
            stmt.close();
            conn.close();
            return retorno;
        }
        catch ( Exception e ) 
        {
            //TODO: logar erro
          ConnectionDataBase.getInstance().closeConnection();
          return retorno;
        }            
    }
}
