package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Coda extends Model {

    protected String table_name = "coda";

    public String nome_coda;

    public Coda store(){
        String query = "INSERT INTO " + this.table_name + " VALUES  (?)";

        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, this.nome_coda);
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
            return this;
        }

        return this;
    }

    public List<Coda> find(){
        String query = "SELECT * FROM " + this.table_name;
        List<Coda> list = new LinkedList();
        try {

            // create the preparedstatement and add the criteria
            PreparedStatement statement = this.connection.prepareStatement(query);

            // process the results
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Coda coda = new Coda();
                coda.nome_coda = resultSet.getString("nome_coda");
                list.add(coda);
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }

        return list;
    }

    public Coda first(){
        List<Coda> list = this.find();
        return list.get(0);
    }

    public Coda last(){
        List<Coda> list = this.find();
        return list.get(list.size() - 1);
    }

}
