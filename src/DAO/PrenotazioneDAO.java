package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.UUID;

import Models.CentoKilometri;
import Models.Coda;
import Models.CompagniaAerea;
import Models.Prenotazione;

public class PrenotazioneDAO extends JDBC {

	private String tableName = "prenotazione";
	
	public Prenotazione store(Prenotazione prenotazione){
        String query = "INSERT INTO " + tableName + " (id, id_tratta, nome_passeggero, cognome_passeggero, coda, cento_kilometri, compagnia_aerea) VALUES  (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            UUID uuid = UUID.randomUUID();
            statement.setString(1, uuid.toString());
            statement.setInt(2, prenotazione.idTratta);
            statement.setString(3, prenotazione.nomePasseggero);
            statement.setObject(4, prenotazione.cognomePasseggero);
            statement.setObject(5, prenotazione.coda);
            statement.setObject(6, prenotazione.centoKilometri);
            statement.setString(7, prenotazione.compagniaAerea.nomeCompagnia);
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }

        return prenotazione;
    }

    public LinkedList<Prenotazione> find(){
        String query = "SELECT * FROM " + tableName;
        LinkedList<Prenotazione> PrenotaioneList = new LinkedList<Prenotazione>();
        CodaDAO codaDAO = new CodaDAO();
        CentoKilometriDAO centoKilometriDAO = new CentoKilometriDAO();
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.id = resultSet.getString("id");
                prenotazione.idTratta = resultSet.getInt("id_tratta");
                prenotazione.nomePasseggero = resultSet.getString("nome_passeggero");
                prenotazione.cognomePasseggero = resultSet.getString("cognome_passeggero");
                prenotazione.coda = (Coda) codaDAO.findByName(resultSet.getString("coda"));
                prenotazione.centoKilometri = (CentoKilometri) centoKilometriDAO.findByCode(resultSet.getString("cento_kilometri"));
                prenotazione.compagniaAerea = (CompagniaAerea) compagniaAereaDAO.findByName(resultSet.getString("compagnia_aerea"));
                PrenotaioneList.add(prenotazione);
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return PrenotaioneList;
    }

    public Prenotazione first(){
    	LinkedList<Prenotazione> PrenotaioneList = find();
        return PrenotaioneList.get(0);
    }

    public Prenotazione last(){
    	LinkedList<Prenotazione> PrenotaioneList = find();
        return PrenotaioneList.get(PrenotaioneList.size() - 1);
    }

}
