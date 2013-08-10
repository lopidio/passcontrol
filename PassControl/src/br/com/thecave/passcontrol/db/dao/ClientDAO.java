package br.com.thecave.passcontrol.db.dao;
import br.com.thecave.passcontrol.db.ConnectionDataBase;
import br.com.thecave.passcontrol.db.bean.ClientBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Classe para persistencia na tabela TB_CLIENT utilizando a classe ClientBean
 * @see ClientBean
 * @author Glaylson Alves
 */
public class ClientDAO {
    /**
     * Metodo para persistir um ClientBean na tabela Tb_CLIENT
     * @param bean Objeto ClientBean a ser inserido
     * @return  true se o metodo executar corretamente, false caso contrario
     * @see ClientBean
     */
    public static boolean insert(ClientBean bean)
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
            String sql = "INSERT INTO TB_CLIENT (TX_REGISTER,TX_NAME,TX_TELEFONE) " +
                         "VALUES ('" + bean.getName() + "', " + 
                         bean.getRegister() + ", " + 
                         "'" + bean.getTelefone() + "' );";
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
    public static boolean update(ClientBean bean)
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
            String sql = "UPDATE TB_CLIENT SET TX_REGISTER = '"+ bean.getRegister() + 
                         "',TX_NAME = " + bean.getName() + 
                         ",TX_TELEFONE = '"+ bean.getTelefone() +
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
    public static boolean delete(ClientBean bean)
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
            String sql = "DELETE FROM  TB_CLIENT WHERE INT_ID=" + bean.getId() + ";";

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
    public ClientBean selectFromId(int id)
    {
        ClientBean bean = new ClientBean();
        try
        {
        // pegar a conexão com o banco
            Connection conn = ConnectionDataBase.getInstance().getConnection();
            if(conn == null)
                return null;
            
            Statement stmt;
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM TB_CLIENT;";

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                bean.setId(rs.getInt("INT_ID"));
                bean.setRegister(rs.getString("TX_REGISTER"));
                bean.setName(rs.getString("INT_NAME"));
                bean.setTelefone(rs.getString("TX_TELEFONE"));
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
