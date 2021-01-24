package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

enum Stato {
    IN_ATTESA,
    IN_CORSO,
    CONCLUSO
}

public class Tratta extends Model {

    protected String table_name = "tratta";

    public Integer id;
    public String destinazione;
    public String compagnia_aerea;
    public LocalDateTime ora_inizio_imbarco;
    public LocalDateTime ora_fine_imbarco_stimato;
    public LocalDateTime ora_fine_imbarco_effettivo;
    public String gate;
    public Stato stato_imbarco;
    public Boolean ritardo;

    public Tratta store(){
        String query = "INSERT INTO " + this.table_name + " (destinazione, compagnia_aerea, ora_inizio_imbarco, ora_fine_imbarco_stimato, ora_fine_imbarco_effettivo, gate) VALUES  (?, ?, ?, ?, ?, ?)";

        CompagniaAerea compagniaAerea = new CompagniaAerea().first(this.compagnia_aerea);
        if(compagniaAerea.nome_compagnia == null){
            compagniaAerea.nome_compagnia = this.compagnia_aerea;
            compagniaAerea.store();
        }

        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, this.destinazione);
            statement.setString(2, this.compagnia_aerea);
            statement.setObject(3, this.ora_inizio_imbarco);
            statement.setObject(4, this.ora_fine_imbarco_stimato);
            statement.setObject(5, this.ora_fine_imbarco_effettivo);
            statement.setString(6, this.gate);
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
            return this;
        }

        return this;
    }

    public List<Tratta> find(){
        String query = "SELECT * FROM " + this.table_name;
        List<Tratta> list = new LinkedList();
        try {

            // create the preparedstatement and add the criteria
            PreparedStatement statement = this.connection.prepareStatement(query);

            // process the results
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tratta tratta = new Tratta();
                tratta.destinazione = resultSet.getString("destinazione");
                tratta.compagnia_aerea = resultSet.getString("compagnia_aerea");
                tratta.ora_inizio_imbarco = resultSet.getTimestamp("ora_inizio_imbarco").toLocalDateTime();
                tratta.ora_fine_imbarco_stimato = resultSet.getTimestamp("ora_fine_imbarco_stimato").toLocalDateTime();
                tratta.ora_fine_imbarco_effettivo = resultSet.getTimestamp("ora_fine_imbarco_effettivo").toLocalDateTime();
                tratta.stato_imbarco = tratta.stato_imbarco.valueOf(resultSet.getString("stato_imbarco"));
                tratta.ritardo = resultSet.getBoolean("ritardo");
                tratta.gate = resultSet.getString("gate");
                list.add(tratta);
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }

        return list;
    }

    public Tratta first(){
        List<Tratta> list = this.find();
        return list.get(0);
    }

    public Tratta last(){
        List<Tratta> list = this.find();
        return list.get(list.size() - 1);
    }

}
