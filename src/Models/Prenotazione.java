package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Prenotazione extends Model {

    protected String table_name = "prenotazione";

    public String id;
    public Integer id_tratta;
    public String nome_passeggero;
    public String cognome_passeggero;
    public String coda;
    public String cento_kilometri;
    public String compagnia_aerea;

    public Prenotazione store(){
        String query = "INSERT INTO " + this.table_name + " (id, id_tratta, nome_passeggero, cognome_passeggero, coda, cento_kilometri, compagnia_aerea) VALUES  (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            UUID uuid = UUID.randomUUID();
            statement.setString(1, uuid.toString());
            statement.setInt(2, this.id_tratta);
            statement.setString(3, this.nome_passeggero);
            statement.setObject(4, this.cognome_passeggero);
            statement.setObject(5, this.coda);
            statement.setObject(6, this.cento_kilometri);
            statement.setString(7, this.compagnia_aerea);
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
            return this;
        }

        return this;
    }

    public List<Prenotazione> find(){
        String query = "SELECT * FROM " + this.table_name;
        List<Prenotazione> list = new LinkedList();
        try {

            // create the preparedstatement and add the criteria
            PreparedStatement statement = this.connection.prepareStatement(query);

            // process the results
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.id = resultSet.getString("id");
                prenotazione.id_tratta = resultSet.getInt("id_tratta");
                prenotazione.nome_passeggero = resultSet.getString("nome_passeggero");
                prenotazione.cognome_passeggero = resultSet.getString("cognome_passeggero");
                prenotazione.coda = resultSet.getString("coda");
                prenotazione.cento_kilometri = resultSet.getString("cento_kilometri");
                prenotazione.compagnia_aerea = resultSet.getString("compagnia_aerea");
                list.add(prenotazione);
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }

        return list;
    }

    public Prenotazione first(){
        List<Prenotazione> list = this.find();
        return list.get(0);
    }

    public Prenotazione last(){
        List<Prenotazione> list = this.find();
        return list.get(list.size() - 1);
    }

}
