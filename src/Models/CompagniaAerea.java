package Models;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class CompagniaAerea extends Model {

    protected String table_name = "compagnia_aerea";

    public String nome_compagnia;

    public CompagniaAerea store(){
        String query = "INSERT INTO " + this.table_name + " VALUES (?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, this.nome_compagnia);
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
        return this;
    }

    public List<CompagniaAerea> find(String nome){
        String query = "SELECT * FROM " + this.table_name + " WHERE nome_compagnia = ?";
        List<CompagniaAerea> compagnie_aeree = new LinkedList();
        try {

            // create the preparedstatement and add the criteria
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, nome);

            // process the results
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CompagniaAerea compagniaAerea = new CompagniaAerea();
                compagniaAerea.nome_compagnia = resultSet.getString("nome_compagnia");
                compagnie_aeree.add(compagniaAerea);
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }

        return compagnie_aeree;
    }

    public CompagniaAerea first(String nome){
        List<CompagniaAerea> compagnie_aeree = this.find(nome);
        return compagnie_aeree.get(0);
    }

    public CompagniaAerea last(String nome){
        List<CompagniaAerea> compagnie_aeree = this.find(nome);
        return compagnie_aeree.get(compagnie_aeree.size() - 1);
    }

}
