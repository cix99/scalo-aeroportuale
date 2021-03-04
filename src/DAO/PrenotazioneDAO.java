package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.UUID;

import Models.Prenotazione;

public class PrenotazioneDAO extends JDBC {

	private String tableName = "prenotazione";
	
	public boolean store(Prenotazione prenotazione){
        String query = "INSERT INTO " + tableName + " (id, id_tratta, codice_prenotazione, nome_passeggero, cognome_passeggero, "
        								+ "coda, cento_kilometri, compagnia_aerea, imbarcato) VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            UUID uuid = UUID.randomUUID();
            statement.setString(1, uuid.toString());
            statement.setInt(2, prenotazione.getIdTratta());
            statement.setString(3, prenotazione.getCodicePrenotazione());
            statement.setString(4, prenotazione.getNomePasseggero());
            statement.setString(5, prenotazione.getCognomePasseggero());
            statement.setInt(6, prenotazione.getCoda().getId());
            statement.setInt(7, prenotazione.getCentoKilometri().getId());
            statement.setString(8, prenotazione.getCompagniaAerea().getNomeCompagnia());
            statement.setBoolean(9, prenotazione.getImbarcato());
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
    }
	
	public boolean storeWithoutCK(Prenotazione prenotazione){
        String query = "INSERT INTO " + tableName + " (id, id_tratta, codice_prenotazione, nome_passeggero, cognome_passeggero, "
        								+ "coda, compagnia_aerea, imbarcato) VALUES  (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            UUID uuid = UUID.randomUUID();
            statement.setString(1, uuid.toString());
            statement.setInt(2, prenotazione.getIdTratta());
            statement.setString(3, prenotazione.getCodicePrenotazione());
            statement.setString(4, prenotazione.getNomePasseggero());
            statement.setString(5, prenotazione.getCognomePasseggero());
            statement.setInt(6, prenotazione.getCoda().getId());
            statement.setString(7, prenotazione.getCompagniaAerea().getNomeCompagnia());
            statement.setBoolean(8, prenotazione.getImbarcato());
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
    }

	public LinkedList<Prenotazione> findByTrattaId(int trattaId){
        String query = "SELECT * FROM " + tableName + " LEFT JOIN coda ON coda.id = " + tableName + ".coda WHERE coda.id_tratta = ? ORDER BY coda.priority DESC"; 
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
                 prenotazione.setCoda(codaDAO.findById(resultSet.getInt("coda")));  //da sistemare
                 prenotazione.setCentoKilometri(centoKilometriDAO.findById(resultSet.getInt("cento_kilometri")));
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
    
    public LinkedList<Prenotazione> findByCentoKilometriId(int idCentoKilometri){
        String query = "SELECT * FROM " + tableName + " WHERE cento_kilometri = ?";
        LinkedList<Prenotazione> PrenotazioneList = new LinkedList<Prenotazione>();
        CodaDAO codaDAO = new CodaDAO();
        CentoKilometriDAO centoKilometriDAO = new CentoKilometriDAO();
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setInt(1, idCentoKilometri);
            ResultSet resultSet = statement.executeQuery();  
        	 while (resultSet.next()) {
                 Prenotazione prenotazione = new Prenotazione();
                 prenotazione.setId(resultSet.getString("id"));
                 prenotazione.setIdTratta(resultSet.getInt("id_tratta"));
                 prenotazione.setCodicePrenotazione(resultSet.getString("codice_prenotazione"));
                 prenotazione.setNomePasseggero(resultSet.getString("nome_passeggero"));
                 prenotazione.setCognomePasseggero(resultSet.getString("cognome_passeggero"));
                 prenotazione.setCoda(codaDAO.findById(resultSet.getInt("coda")));  //da sistemare
                 prenotazione.setCentoKilometri(centoKilometriDAO.findById(resultSet.getInt("cento_kilometri")));
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
                prenotazione.setCoda(codaDAO.findById(resultSet.getInt("coda")));
                prenotazione.setCentoKilometri(centoKilometriDAO.findById(resultSet.getInt("cento_kilometri")));
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
    
	public boolean isPrenotazionePossible(int idTratta) {
		String query = "SELECT COUNT (*) AS num FROM " + tableName + " WHERE id_tratta = ?";
		int count = 0;	     

        TrattaDAO trattaDao = new TrattaDAO();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setInt(1, idTratta);
            ResultSet resultSet = statement.executeQuery();  
        	while (resultSet.next()) {
        		count = resultSet.getInt("num");
            }
            resultSet.close();
            statement.close();
        } catch(SQLException e) {
            System.out.println(e);
        }
        if (count >= trattaDao.findById(idTratta).getMaxPrenotazioni())
        	return false;
        return true;
	}

	public LinkedList<Prenotazione> findForPunti(int idTratta, String nomeCompagnia){
        String query = "SELECT * FROM " + tableName + " WHERE id_tratta = ? AND compagnia_aerea = ? AND imbarcato = ?";
        LinkedList<Prenotazione> PrenotazioneList = new LinkedList<Prenotazione>();
        CodaDAO codaDAO = new CodaDAO();
        CentoKilometriDAO centoKilometriDAO = new CentoKilometriDAO();
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setInt(1, idTratta);
            statement.setString(2, nomeCompagnia);
            statement.setBoolean(3, true);
            ResultSet resultSet = statement.executeQuery();  
        	 while (resultSet.next()) {
                 Prenotazione prenotazione = new Prenotazione();
                 prenotazione.setId(resultSet.getString("id"));
                 prenotazione.setIdTratta(resultSet.getInt("id_tratta"));
                 prenotazione.setCodicePrenotazione(resultSet.getString("codice_prenotazione"));
                 prenotazione.setNomePasseggero(resultSet.getString("nome_passeggero"));
                 prenotazione.setCognomePasseggero(resultSet.getString("cognome_passeggero"));
                 prenotazione.setCoda(codaDAO.findById(resultSet.getInt("coda")));  //da sistemare
                 prenotazione.setCentoKilometri(centoKilometriDAO.findById(resultSet.getInt("cento_kilometri")));
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
    
	public boolean delete(String idPrenotazione) {
		String query = "DELETE FROM " + tableName + " WHERE id = ?";

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, idPrenotazione);
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
	}
	
	public boolean update(int coda, String id) {
		String query = "UPDATE " + tableName + " SET coda = ? WHERE id = ?";
		try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setInt(1, coda);
            statement.setString(2, id);
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
	}
    
    public boolean updateImbarcato (boolean value, String id) {
    	String query = "UPDATE " + tableName + " SET imbarcato = ? WHERE id = ?";
		try {
			PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
			statement.setBoolean(1, value); 
			statement.setString(2, id);
			statement.executeUpdate();
			statement.close();
		}catch(SQLException e){
			System.out.println(e);
			return false;
		}
		return true;
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
