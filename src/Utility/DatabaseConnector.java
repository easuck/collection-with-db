package Utility;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private final String url = "jdbc:postgresql://localhost/collection_db";
    private final String username = "postgres";
    private final String password = "123";

    public Connection connect(){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);

            if(connection != null){
                System.out.println("connection has been set");
            }
        }
        catch(SQLException e){
            System.out.println("couldn't make a connection");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
