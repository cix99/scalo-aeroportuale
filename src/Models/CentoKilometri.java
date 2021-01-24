package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class CentoKilometri extends Model {

    public Integer id;
    public String codice_compagnia;
    public String compagnia_aerea;
    public Integer punti;

    public CentoKilometri store(){
        String query = "INSERT INTO " + this.table_name + " (codice_compagnia, compagnia_aerea, punti) VALUES  (?, ?, ?)";

        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, this.codice_compagnia);
            statement.setString(2, this.compagnia_aerea);
            statement.setInt(3, this.punti);
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
            return this;
        }

        return this;
    }

    public List<CentoKilometri> find(){
        String query = "SELECT * FROM " + this.table_name;
        List<CentoKilometri> list = new LinkedList();
        try {

            // create the preparedstatement and add the criteria
            PreparedStatement statement = this.connection.prepareStatement(query);

            // process the results
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CentoKilometri centoKilometri = new CentoKilometri();
                centoKilometri.id = resultSet.getInt("id");
                centoKilometri.codice_compagnia = resultSet.getString("codice_compagnia");
                centoKilometri.compagnia_aerea = resultSet.getString("compagnia_aerea");
                centoKilometri.punti = resultSet.getInt("punti");
                list.add(centoKilometri);
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }

        return list;
    }

    public CentoKilometri first(){
        List<CentoKilometri> list = this.find();
        return list.get(0);
    }

    public CentoKilometri last(){
        List<CentoKilometri> list = this.find();
        return list.get(list.size() - 1);
    }
}
