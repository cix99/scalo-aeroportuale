package Models;

import java.sql.PreparedStatement;
import java.lang.String;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Gate extends Model {

    protected String table_name = "gate";

    public String nome_gate;

    public Gate store(){
        String query = "INSERT INTO " + this.table_name + " VALUES  (?)";

        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, this.nome_gate);
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
            return this;
        }

        return this;
    }

    public List<Gate> find(){
        String query = "SELECT * FROM " + this.table_name;
        List<Gate> list = new LinkedList();
        try {

            // create the preparedstatement and add the criteria
            PreparedStatement statement = this.connection.prepareStatement(query);

            // process the results
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Gate gate = new Gate();
                gate.nome_gate = resultSet.getString("nome_gate");
                list.add(gate);
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }

        return list;
    }

    public Gate first(){
        List<Gate> list = this.find();
        return list.get(0);
    }

    public Gate last(){
        List<Gate> list = this.find();
        return list.get(list.size() - 1);
    }

}
