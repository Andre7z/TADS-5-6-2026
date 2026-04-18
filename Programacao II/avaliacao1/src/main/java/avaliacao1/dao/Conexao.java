package avaliacao1.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/vendas_prog2_andre";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "2006";

    private static Connection connection;

    public static Connection getConnection() throws SQLException{
        if (connection == null || connection.isClosed()){
            connection = DriverManager.getConnection(URL,USUARIO, SENHA);
        }
        return connection;
    }
    public static void fecharConexao(){
        if (connection != null){
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
        
    }
