package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

import Models.Coda;
import Models.Stato;
import Models.Tratta;

public class TrattaDAO extends JDBC {

	private String tableName = "tratta";
	
	public int store(Tratta tratta){
        String query = "INSERT INTO " + tableName + " (destinazione, compagnia_aerea, ora_inizio_imbarco_stimato, ora_fine_imbarco_stimato, max_prenotazioni, gate) VALUES  (?, ?, ?, ?, ?, ?) RETURNING (id)";
        int id = 0;
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, tratta.getDestinazione());
            statement.setString(2, tratta.getCompagniaAerea().getNomeCompagnia());
            statement.setObject(3, tratta.getOraInizioImbarcoStimato());
            //statement.setObject(4, tratta.getOraInizioImbarcoEffettivo());
            statement.setObject(4, tratta.getOraFineImbarcoStimato());
            statement.setInt(5, tratta.getMaxPrenotazioni());
            //statement.setObject(6, tratta.getOraFineImbarcoEffettivo());
            statement.setString(6, tratta.getGate().getNomeGate());
            statement.execute();
            ResultSet lastInsert = statement.getResultSet();
            if (lastInsert.next())
            	id = lastInsert.getInt(1);
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return id;
    }

    public LinkedList<Tratta> find(){
        String query = "SELECT * FROM " + tableName + " ORDER BY id";
        LinkedList<Tratta> TrattaList = new LinkedList<Tratta>();
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        GateDAO gateDAO = new GateDAO();

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tratta tratta = new Tratta();
                tratta.setId(resultSet.getInt("id"));
                tratta.setDestinazione(resultSet.getString("destinazione"));
                tratta.setCompagniaAerea(compagniaAereaDAO.findByName(resultSet.getString("compagnia_aerea")));
                tratta.setOraInizioImbarcoStimato(resultSet.getTimestamp("ora_inizio_imbarco_stimato").toLocalDateTime());
                if (resultSet.getTimestamp("ora_inizio_imbarco_effettivo") != null)
                	tratta.setOraInizioImbarcoEffettivo(resultSet.getTimestamp("ora_inizio_imbarco_effettivo").toLocalDateTime());
                if (resultSet.getTimestamp("ora_fine_imbarco_stimato") != null)
                	tratta.setOraFineImbarcoStimato(resultSet.getTimestamp("ora_fine_imbarco_stimato").toLocalDateTime());
                if (resultSet.getTimestamp("ora_fine_imbarco_effettivo") != null)
                	tratta.setOraFineImbarcoEffettivo(resultSet.getTimestamp("ora_fine_imbarco_effettivo").toLocalDateTime());
                tratta.setStatoImbarco(Stato.valueOf(resultSet.getString("stato_imbarco")));
                tratta.setRitardo(resultSet.getBoolean("ritardo"));
                tratta.setGate(gateDAO.findByName(resultSet.getString("gate")));
                tratta.setMaxPrenotazioni(resultSet.getInt("max_prenotazioni"));
                TrattaList.add(tratta);
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return TrattaList;
    }
    
    public LinkedList<Tratta> findTrattaByGate(String nomeGate){
        String query = "SELECT * FROM " + tableName + " WHERE gate = ? AND stato_imbarco <> ? ORDER BY ora_inizio_imbarco_stimato ASC";
        LinkedList<Tratta> TrattaList = new LinkedList<Tratta>();
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        GateDAO gateDAO = new GateDAO();

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, nomeGate);
            statement.setObject(2, Stato.CONCLUSO, java.sql.Types.OTHER);
            ResultSet resultSet = statement.executeQuery();
        	while (resultSet.next()) {
                Tratta tratta = new Tratta();
                tratta.setId(resultSet.getInt("id"));
                tratta.setDestinazione(resultSet.getString("destinazione"));
                tratta.setCompagniaAerea(compagniaAereaDAO.findByName(resultSet.getString("compagnia_aerea")));
                tratta.setOraInizioImbarcoStimato(resultSet.getTimestamp("ora_inizio_imbarco_stimato").toLocalDateTime());
                if (resultSet.getTimestamp("ora_inizio_imbarco_effettivo") != null)
                	tratta.setOraInizioImbarcoEffettivo(resultSet.getTimestamp("ora_inizio_imbarco_effettivo").toLocalDateTime());
                tratta.setOraFineImbarcoStimato(resultSet.getTimestamp("ora_fine_imbarco_stimato").toLocalDateTime());
                if (resultSet.getTimestamp("ora_fine_imbarco_effettivo") != null)
                	tratta.setOraFineImbarcoEffettivo(resultSet.getTimestamp("ora_fine_imbarco_effettivo").toLocalDateTime());
                tratta.setStatoImbarco(Stato.valueOf(resultSet.getString("stato_imbarco")));
                tratta.setRitardo(resultSet.getBoolean("ritardo"));
                tratta.setGate(gateDAO.findByName(resultSet.getString("gate")));
                tratta.setMaxPrenotazioni(resultSet.getInt("max_prenotazioni"));
                TrattaList.add(tratta);
            }            
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return TrattaList;
    }
    
    
    public LinkedList<Tratta> findTrattaByCompagnia(String nomeCompagnia){
        String query = "SELECT * FROM " + tableName + " WHERE compagnia_aerea = ?";
        LinkedList<Tratta> TrattaList = new LinkedList<Tratta>();
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        GateDAO gateDAO = new GateDAO();

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, nomeCompagnia);
            ResultSet resultSet = statement.executeQuery();
        	while (resultSet.next()) {
                Tratta tratta = new Tratta();
                tratta.setId(resultSet.getInt("id"));
                tratta.setDestinazione(resultSet.getString("destinazione"));
                tratta.setCompagniaAerea(compagniaAereaDAO.findByName(resultSet.getString("compagnia_aerea")));
                tratta.setOraInizioImbarcoStimato(resultSet.getTimestamp("ora_inizio_imbarco_stimato").toLocalDateTime());
                if (resultSet.getTimestamp("ora_inizio_imbarco_effettivo") != null)
                	tratta.setOraInizioImbarcoEffettivo(resultSet.getTimestamp("ora_inizio_imbarco_effettivo").toLocalDateTime());
                tratta.setOraFineImbarcoStimato(resultSet.getTimestamp("ora_fine_imbarco_stimato").toLocalDateTime());
                if (resultSet.getTimestamp("ora_fine_imbarco_effettivo") != null)
                	tratta.setOraFineImbarcoEffettivo(resultSet.getTimestamp("ora_fine_imbarco_effettivo").toLocalDateTime());
                tratta.setStatoImbarco(Stato.valueOf(resultSet.getString("stato_imbarco")));
                tratta.setRitardo(resultSet.getBoolean("ritardo"));
                tratta.setGate(gateDAO.findByName(resultSet.getString("gate")));
                tratta.setMaxPrenotazioni(resultSet.getInt("max_prenotazioni"));
                TrattaList.add(tratta);
            }            
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return TrattaList;
    }
    
    public LinkedList<Tratta> findTrattaByCompagnia(String nomeCompagnia, Stato stato){
        String query = "SELECT * FROM " + tableName + " WHERE compagnia_aerea = ? AND stato_imbarco = ?";
        LinkedList<Tratta> TrattaList = new LinkedList<Tratta>();
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        GateDAO gateDAO = new GateDAO();

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, nomeCompagnia);
            statement.setObject(2, stato, java.sql.Types.OTHER);
            ResultSet resultSet = statement.executeQuery();
        	while (resultSet.next()) {
                Tratta tratta = new Tratta();
                tratta.setId(resultSet.getInt("id"));
                tratta.setDestinazione(resultSet.getString("destinazione"));
                tratta.setCompagniaAerea(compagniaAereaDAO.findByName(resultSet.getString("compagnia_aerea")));
                tratta.setOraInizioImbarcoStimato(resultSet.getTimestamp("ora_inizio_imbarco_stimato").toLocalDateTime());
                if (resultSet.getTimestamp("ora_inizio_imbarco_effettivo") != null)
                	tratta.setOraInizioImbarcoEffettivo(resultSet.getTimestamp("ora_inizio_imbarco_effettivo").toLocalDateTime());
                tratta.setOraFineImbarcoStimato(resultSet.getTimestamp("ora_fine_imbarco_stimato").toLocalDateTime());
                if (resultSet.getTimestamp("ora_fine_imbarco_effettivo") != null)
                	tratta.setOraFineImbarcoEffettivo(resultSet.getTimestamp("ora_fine_imbarco_effettivo").toLocalDateTime());
                tratta.setStatoImbarco(Stato.valueOf(resultSet.getString("stato_imbarco")));
                tratta.setRitardo(resultSet.getBoolean("ritardo"));
                tratta.setGate(gateDAO.findByName(resultSet.getString("gate")));
                tratta.setMaxPrenotazioni(resultSet.getInt("max_prenotazioni"));
                TrattaList.add(tratta);
            }            
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return TrattaList;
    }
    
	public boolean delete(int idTratta) {
		String query = "DELETE FROM " + tableName + " WHERE id = ?";

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setInt(1, idTratta);
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
	}

    public Tratta first(){
    	LinkedList<Tratta> TrattaList = find();
        return TrattaList.get(0);
    }

    public Tratta last(){
    	LinkedList<Tratta> TrattaList = find();
        return TrattaList.get(TrattaList.size() - 1);
    }

	public boolean updateInizioImbarco(Tratta tratta) {
		String query = "UPDATE " + tableName + " SET ora_inizio_imbarco_effettivo = ?, stato_imbarco = ? WHERE id = ?";
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setObject(1, tratta.getOraInizioImbarcoEffettivo());
            statement.setObject(2, tratta.getStatoImbarco(), java.sql.Types.OTHER);
            statement.setInt(3, tratta.getId());
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
	}

	public boolean updateFineImbarco(Tratta tratta) {
		String query = "UPDATE " + tableName + " SET ora_fine_imbarco_effettivo = ?, stato_imbarco = ? WHERE id = ?";
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setObject(1, tratta.getOraFineImbarcoEffettivo());
            statement.setObject(2, tratta.getStatoImbarco(), java.sql.Types.OTHER);
            statement.setInt(3, tratta.getId());
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
	}
	
	public boolean update(String gate, LocalDateTime dataInizio, LocalDateTime dataFine, int max, int idTratta) {
		String query = "UPDATE " + tableName + " SET gate = ?, ora_inizio_imbarco_stimato = ?, ora_fine_imbarco_stimato = ?, max_prenotazioni = ? WHERE id = ?";
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, gate);
            statement.setObject(2, dataInizio);
            statement.setObject(3, dataFine);
            statement.setInt(4, max);
            statement.setInt(5, idTratta);
            statement.executeUpdate();
            statement.close();            
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
	}
}
