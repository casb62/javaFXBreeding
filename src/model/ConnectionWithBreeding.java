package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConnectionWithBreeding {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/breeding";
    public Connection connection = null;//manage the connection
    public Statement statement = null;//query instruction
    public ResultSet resultSet = null;//manage results
        
    public void connect(){
        try {
            connection = DriverManager.getConnection(DATABASE_URL, "postgres", "breeding");
                
        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados:\n" + sqlException.getMessage());
        }
    }
    
    public void disconnect(){
        try {
            connection.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão com o banco de dados:\n" + exception.getMessage());
        }
    }
    
    public void executeQuery(String sql){
        try {
            statement = connection.createStatement(resultSet.TYPE_SCROLL_INSENSITIVE, resultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco de dados:\n" + ex.getMessage());
        }
    }
}