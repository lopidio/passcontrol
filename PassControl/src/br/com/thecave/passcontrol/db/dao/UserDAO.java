package br.com.thecave.passcontrol.db.dao;

import br.com.thecave.passcontrol.db.ConnectionDataBase;
import br.com.thecave.passcontrol.db.bean.UserBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
            
            System.out.println("Excessão capturada: " + e.getMessage());
            ConnectionDataBase.getInstance().closeConnection();
        }
        return false;
    }
}
