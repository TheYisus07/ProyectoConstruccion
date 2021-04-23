package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio de Jesús Dominguez García
 */
public class Conection {
    private Connection connection;
    private final String DB = "jdbc:mysql://localhost:3306/SGP-CA";
    private final String USERNAME = "Admin";
    private final String PASSWORD = "RJ92a1bDL4l";

    public Connection getConnection() throws SQLException{
        connect();
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void connect() throws SQLException{
        
        connection = DriverManager.getConnection(DB, USERNAME, PASSWORD);
            
    }

    public void disconnect() {
        if(connection!=null){
            try{
                if(!connection.isClosed()){
                    connection.close();
                }
            }catch (SQLException ex) {
                Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}