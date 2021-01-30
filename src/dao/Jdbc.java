package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {

    static Connection connection = null;
    
    public static Connection GetConnection() {
    	if (connection != null)
            return connection;

    	String database = "jdbc:postgresql://34.91.62.170:5432/scalo_aeroportuale";
    	String username = "postgres";
    	String password = "f3bJoHeGFwzkAcPg";
    	return getConnection (database, username, password);
    }
    
    private static Connection getConnection (String database, String username, String password) {
    	try {
            connection = DriverManager.getConnection(
                    database,
                    username,
                    password
            );
        } catch (SQLException e){
            System.out.println("SQL Exception:\n " + e);
        }
    	return connection;
    }
}