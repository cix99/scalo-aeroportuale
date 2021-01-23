package Models;

import java.sql.PreparedStatement;
import java.lang.String;
import java.sql.SQLException;

public class Gate extends Model {

    protected String table_name = "gate";

    public String nome_gate;

    public boolean store(){
        String query = "INSERT INTO " + this.table_name + " (?)";
        Boolean store = false;
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, this.nome_gate);
            store = statement.executeQuery();
        }catch(SQLException e){
            System.out.println(e);
        }
        //return true;
        return store;
    }

}
