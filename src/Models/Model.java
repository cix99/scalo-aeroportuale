package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Model {

    protected Connection connection;
    protected String table_name;

    {
        try {
            this.connection = DriverManager.getConnection(
                "jdbc:postgresql://34.91.62.170:5432/scalo_aeroportuale",
                "postgres", "f3bJoHeGFwzkAcPg");
        } catch (SQLException e){
            System.out.println("SQL Exception:\n " + e);
        }
    }
}