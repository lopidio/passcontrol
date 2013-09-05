package br.com.thecave.passcontrolserver.db.dao;

import br.com.thecave.passcontrolserver.db.ConnectionDataBase;
import br.com.thecave.passcontrolserver.db.bean.UserBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe para persistencia na tabela TB_USER utilizando a classe UserBean
 * @see UserBean
 * @author Antonio Arleudo da Costa
 */
public class UserDAO 
{
    /**
     * Metodo para persistir um UserBean na tabela Tb_USER
     * @param bean Objeto UserBean a ser inserido
     * @return  true se o metodo executar corretamente, false caso contrario
     * @see UserBean
     */
    public static boolean insert(UserBean bean)
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
            String sql = "INSERT INTO TB_USER (TX_NAME,INT_TYPE,TX_LOGIN,TX_PASSWORD) " +
                         "VALUES ('" + bean.getName() + "', " + 
                         bean.getType() + ", " + 
                         "'" + bean.getLogin() + "', " + 
                         "'" + bean.getPassword() + "' );";
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
    public static boolean update(UserBean bean)
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
            String sql = "UPDATE TB_USER SET TX_NAME = '"+ bean.getName() + 
                         "',INT_TYPE = " + bean.getType() + 
                         ",TX_LOGIN = '"+ bean.getLogin() + 
                         "',TX_PASSWORD = '" + bean.getPassword() + 
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
     * Metodo para deleção de um registro na tabela TB_USER com base no UserBean, utilizando o seu id
     * @param bean UserBean a ser deletado.
     * @see UserBean
     * @return True se deleção ocorrida com sucesso. false, caso contrario.
     */
    public static boolean delete(UserBean bean)
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
     * Metodo para recuperar um UserBean a partir de seu id.
     * @param id Id do registro que se quer recuperar
     * @return Bean com os dados ja preenchidos.
     */
    public static UserBean selectFromId(int id)
    {
        UserBean bean = null;
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
                bean = new UserBean();
                bean.setId(rs.getInt("INT_ID"));
                bean.setName(rs.getString("TX_NAME"));
                bean.setType(rs.getInt("INT_TYPE"));
                bean.setLogin(rs.getString("TX_LOGIN"));
                bean.setPassword(rs.getString("TX_PASSWORD"));
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
     * Metodo para recuperar um UserBean a partir de seu id.
     * @param name Nome do registro que se quer recuperar
     * @return Bean com os dados ja preenchidos.
     */
    public static UserBean selectFromName(String name)
    {
        UserBean bean = null;
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM TB_USER WHERE TX_NAME='" + name + "';";

            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next())
            {
                bean = new UserBean();
                bean.setId(rs.getInt("INT_ID"));
                bean.setName(rs.getString("TX_NAME"));
                bean.setType(rs.getInt("INT_TYPE"));
                bean.setLogin(rs.getString("TX_LOGIN"));
                bean.setPassword(rs.getString("TX_PASSWORD"));
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
     * Metodo para recuperar um UserBean a partir de seu id.
     * @return Lista de beans (vazia, caso não haja nada).
     */
    public static ArrayList<UserBean> selectAll()
    {
        ArrayList<UserBean> beanList = new ArrayList<>();
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
            
            if(rs.next())
            {
                UserBean bean = new UserBean();
                bean.setId(rs.getInt("INT_ID"));
                bean.setName(rs.getString("TX_NAME"));
                bean.setType(rs.getInt("INT_TYPE"));
                bean.setLogin(rs.getString("TX_LOGIN"));
                bean.setPassword(rs.getString("TX_PASSWORD"));
                beanList.add(bean);
            }
            
            stmt.close();
            conn.close();
            return beanList;
        }
        catch ( Exception e ) 
        {
            //TODO: logar erro
          ConnectionDataBase.getInstance().closeConnection();
          return null;
        }            
    }    
}
