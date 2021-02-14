package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.UUID;

import Models.Prenotazione;

public class PrenotazioneDAO extends JDBC {

	private String tableName = "prenotazione";
	
	public Prenotazione store(Prenotazione prenotazione){
        String query = "INSERT INTO " + tableName + " (id, id_tratta, codice_prenotazione, nome_passeggero, cognome_passeggero, "
        								+ "coda, cento_kilometri, compagnia_aerea, imbarcato) VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            UUID uuid = UUID.randomUUID();
            statement.setString(1, uuid.toString());
            statement.setInt(2, prenotazione.getIdTratta());
            statement.setString(3,prenotazione.getCodicePrenotazione());
            statement.setString(4, prenotazione.getNomePasseggero());
            statement.setString(5, prenotazione.getCognomePasseggero());
            statement.setObject(6, prenotazione.getCoda());
            statement.setObject(7, prenotazione.getCentoKilometri());
            statement.setString(8, prenotazione.getCompagniaAerea().getNomeCompagnia());
            statement.setBoolean(9, prenotazione.getImbarcato());
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
                prenotazione.setId(resultSet.getString("id"));
                prenotazione.setIdTratta(resultSet.getInt("id_tratta"));
                prenotazione.setCodicePrenotazione(resultSet.getString("codice_prenotazione"));
                prenotazione.setNomePasseggero(resultSet.getString("nome_passeggero"));
                prenotazione.setCognomePasseggero(resultSet.getString("cognome_passeggero"));
                prenotazione.setCoda(codaDAO.findByName(resultSet.getInt("coda"), resultSet.getInt("id_tratta")));
                prenotazione.setCentoKilometri(centoKilometriDAO.findByCode(resultSet.getString("cento_kilometri")));
                prenotazione.setCompagniaAerea(compagniaAereaDAO.findByName(resultSet.getString("compagnia_aerea")));
                prenotazione.setImbarcato(resultSet.getBoolean("imbarcato"));
                PrenotaioneList.add(prenotazione);
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return PrenotaioneList;
    }
    
    public LinkedList<Prenotazione> findByTrattaId(int trattaId){
        String query = "SELECT * FROM " + tableName + " WHERE id_tratta = ? ORDER BY id ASC"; //Possiamo modificare ORDER BY in seguito
        LinkedList<Prenotazione> PrenotazioneList = new LinkedList<Prenotazione>();
        CodaDAO codaDAO = new CodaDAO();
        CentoKilometriDAO centoKilometriDAO = new CentoKilometriDAO();
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setInt(1, trattaId);
            ResultSet resultSet = statement.executeQuery();  
        	 while (resultSet.next()) {
                 Prenotazione prenotazione = new Prenotazione();
                 prenotazione.setId(resultSet.getString("id"));
                 prenotazione.setIdTratta(resultSet.getInt("id_tratta"));
                 prenotazione.setCodicePrenotazione(resultSet.getString("codice_prenotazione"));
                 prenotazione.setNomePasseggero(resultSet.getString("nome_passeggero"));
                 prenotazione.setCognomePasseggero(resultSet.getString("cognome_passeggero"));
                 prenotazione.setCoda(codaDAO.findByName(resultSet.getInt("coda"), resultSet.getInt("id_tratta")));  //da sistemare
                 prenotazione.setCentoKilometri(centoKilometriDAO.findByCode(resultSet.getString("cento_kilometri")));
                 prenotazione.setCompagniaAerea(compagniaAereaDAO.findByName(resultSet.getString("compagnia_aerea")));
                 prenotazione.setImbarcato(resultSet.getBoolean("imbarcato"));
                 PrenotazioneList.add(prenotazione);
             }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return PrenotazioneList;
    }
    
    public void updateImbarcato (boolean value, String id) {
    	String query = "UPDATE " + tableName + " SET imbarcato = ? WHERE id = ?";
		try {
			PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
			statement.setBoolean(1, value); 
			statement.setString(2, id);
			statement.executeUpdate();
			statement.close();
		}catch(SQLException e){
			System.out.println(e);
		}
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
