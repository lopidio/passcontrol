package br.com.thecave.passcontrol.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe seguindo o padrão singleton que terá a conexão com o banco de dados.
 * Essa conexão será aberta ao chamar o metodo getConnection e deverá ser fechada 
 * após sua sutilização.
 * @author Antonio Arleudo da Costa
 */
public class ConnectionDataBase 
{
    // Variavel que guarda a conexão com o banco de dados
    private static Connection c;
    // Variavel de instancia da classe
    private static ConnectionDataBase instance;
    
    /**
     * Construtor privado da classe para padrão singleton
     */
    private ConnectionDataBase()
    {
        c = null;
    }
    /**
     * Metodo para pegar a instancia única da classe.
     * @return instance.
     */
    public static ConnectionDataBase getInstance()
    {
        if(instance == null)
            instance = new ConnectionDataBase();
        return instance;
    }
    
    /**
     * Pega a conexão com o banco de dados
     * @return Connection
     */
    public Connection getConnection()
    {
        try 
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:queue_manager.db");
            return c;
        } 
        catch ( ClassNotFoundException e ) 
        {
            closeConnection();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        } catch (SQLException ex) 
        {
            closeConnection();
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            return null;
        }
    }
    /**
     * Encerra a conexão com o banco de dados, deve ser chamado sempre que a transação for realizada
     */
    public void closeConnection()
    {
        try 
        {
            c.close();
        } catch (SQLException ex) 
        {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        }
    }
}
