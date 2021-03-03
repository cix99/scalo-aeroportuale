package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
    
    public Tratta findById(int idTratta) {
    	String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        Tratta tratta = new Tratta();
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        GateDAO gateDAO = new GateDAO();

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setInt(1, idTratta);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
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
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return tratta;
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

	public LinkedList<Tratta> isGateAvailableAfter(String gate, LocalDateTime dataInizio) {
		String query = "SELECT * FROM " + tableName + " WHERE gate = ? AND stato_imbarco <> ? AND ora_inizio_imbarco_stimato < ? ORDER BY ora_inizio_imbarco_stimato DESC";
	    LinkedList<Tratta> trattaList = new LinkedList<Tratta>();
	    CompagniaAereaDAO compagniaAereaDao = new CompagniaAereaDAO();
	    GateDAO gateDao = new GateDAO();
	    try {
	    	PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
	    	statement.setString(1, gate);
	    	statement.setObject(2, Stato.CONCLUSO, java.sql.Types.OTHER);
	    	statement.setObject(3, dataInizio);
	    	ResultSet resultSet = statement.executeQuery();
	    	while (resultSet.next()) {
	    		Tratta tratta = new Tratta();
                tratta.setId(resultSet.getInt("id"));
                tratta.setDestinazione(resultSet.getString("destinazione"));
                tratta.setCompagniaAerea(compagniaAereaDao.findByName(resultSet.getString("compagnia_aerea")));
                tratta.setOraInizioImbarcoStimato(resultSet.getTimestamp("ora_inizio_imbarco_stimato").toLocalDateTime());
                if (resultSet.getTimestamp("ora_inizio_imbarco_effettivo") != null)
                	tratta.setOraInizioImbarcoEffettivo(resultSet.getTimestamp("ora_inizio_imbarco_effettivo").toLocalDateTime());
                tratta.setOraFineImbarcoStimato(resultSet.getTimestamp("ora_fine_imbarco_stimato").toLocalDateTime());
                if (resultSet.getTimestamp("ora_fine_imbarco_effettivo") != null)
                	tratta.setOraFineImbarcoEffettivo(resultSet.getTimestamp("ora_fine_imbarco_effettivo").toLocalDateTime());
                tratta.setStatoImbarco(Stato.valueOf(resultSet.getString("stato_imbarco")));
                tratta.setRitardo(resultSet.getBoolean("ritardo"));
                tratta.setGate(gateDao.findByName(resultSet.getString("gate")));
                tratta.setMaxPrenotazioni(resultSet.getInt("max_prenotazioni"));
                trattaList.add(tratta);
	    	}
	    	resultSet.close();
	    	statement.close();
	    } catch(SQLException e) {
	    	System.out.println(e);
	    }
	    return trattaList;
	}
	
	public LinkedList<Tratta> isGateAvailableBefore(String gate, LocalDateTime dataInizio) {
		String query = "SELECT * FROM " + tableName + " WHERE gate = ? AND stato_imbarco <> ? AND ora_inizio_imbarco_stimato > ? ORDER BY ora_inizio_imbarco_stimato ASC";
	    LinkedList<Tratta> trattaList = new LinkedList<Tratta>();
	    CompagniaAereaDAO compagniaAereaDao = new CompagniaAereaDAO();
	    GateDAO gateDao = new GateDAO();
	    try {
	    	PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
	    	statement.setString(1, gate);
	    	statement.setObject(2, Stato.CONCLUSO, java.sql.Types.OTHER);
	    	statement.setObject(3, dataInizio);
	    	ResultSet resultSet = statement.executeQuery();
	    	while (resultSet.next()) {
	    		Tratta tratta = new Tratta();
                tratta.setId(resultSet.getInt("id"));
                tratta.setDestinazione(resultSet.getString("destinazione"));
                tratta.setCompagniaAerea(compagniaAereaDao.findByName(resultSet.getString("compagnia_aerea")));
                tratta.setOraInizioImbarcoStimato(resultSet.getTimestamp("ora_inizio_imbarco_stimato").toLocalDateTime());
                if (resultSet.getTimestamp("ora_inizio_imbarco_effettivo") != null)
                	tratta.setOraInizioImbarcoEffettivo(resultSet.getTimestamp("ora_inizio_imbarco_effettivo").toLocalDateTime());
                tratta.setOraFineImbarcoStimato(resultSet.getTimestamp("ora_fine_imbarco_stimato").toLocalDateTime());
                if (resultSet.getTimestamp("ora_fine_imbarco_effettivo") != null)
                	tratta.setOraFineImbarcoEffettivo(resultSet.getTimestamp("ora_fine_imbarco_effettivo").toLocalDateTime());
                tratta.setStatoImbarco(Stato.valueOf(resultSet.getString("stato_imbarco")));
                tratta.setRitardo(resultSet.getBoolean("ritardo"));
                tratta.setGate(gateDao.findByName(resultSet.getString("gate")));
                tratta.setMaxPrenotazioni(resultSet.getInt("max_prenotazioni"));
                trattaList.add(tratta);
	    	}
	    	resultSet.close();
	    	statement.close();
	    } catch(SQLException e) {
	    	System.out.println(e);
	    }
	    return trattaList;
	}

	/*
	SELECT
        tratta.gate,

        SUM(giornaliero.voli) as voli_giornalieri,
        SUM(giornaliero.utilizzo_stimato) as s_giornaliero,
        SUM(giornaliero.utilizzo_effettivo) as e_giornaliero,

        SUM(settimanale.voli) as voli_settimanali,
        SUM(settimanale.utilizzo_stimato) as s_settimanale,
        SUM(settimanale.utilizzo_effettivo) as e_settimanale,

        SUM(mensile.voli) as voli_mensili,
        SUM(mensile.utilizzo_stimato) as s_mensile,
        SUM(mensile.utilizzo_effettivo) as e_mensile

    FROM scalo_aeroportuale.tratta

    LEFT JOIN (
        SELECT
            gate,
            1 as voli,
            SUM(
                DATE_PART('day', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato) * 24 * 60 +
                DATE_PART('hour', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato) * 60 +
                DATE_PART('minute', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato)
            ) AS utilizzo_stimato,
            SUM(
                DATE_PART('day', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) * 24 * 60 +
                DATE_PART('hour', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) * 60 +
                DATE_PART('minute', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo)
            ) AS utilizzo_effettivo
        FROM scalo_aeroportuale.tratta
        WHERE ora_inizio_imbarco_stimato BETWEEN (now() - interval '1 day') AND (now())
        GROUP BY gate
    ) AS giornaliero ON giornaliero.gate = tratta.gate


    LEFT JOIN (
        SELECT
            gate,
            1 as voli,
            SUM(
                DATE_PART('day', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato) * 24 * 60 +
                DATE_PART('hour', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato) * 60 +
                DATE_PART('minute', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato)
            ) AS utilizzo_stimato,
            SUM(
                DATE_PART('day', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) * 24 * 60 +
                DATE_PART('Hour', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) * 60 +
                DATE_PART('minute', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo)
            ) AS utilizzo_effettivo
        FROM scalo_aeroportuale.tratta
        WHERE ora_inizio_imbarco_stimato BETWEEN (now() - interval '1 week') AND (now())
        GROUP BY gate
    ) AS settimanale ON settimanale.gate = tratta.gate

    LEFT JOIN (
        SELECT
            gate,
            1 as voli,
            SUM(
                DATE_PART('day', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato) * 24 * 60 +
                DATE_PART('hour', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato) * 60 +
                DATE_PART('minute', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato)
            ) AS utilizzo_stimato,
            SUM(
                DATE_PART('day', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) * 24 * 60 +
                DATE_PART('hour', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) * 60 +
                DATE_PART('minute', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo)
            ) AS utilizzo_effettivo
        FROM scalo_aeroportuale.tratta
        WHERE ora_inizio_imbarco_stimato BETWEEN (now() - interval '1 month') AND (now())
        GROUP BY gate
    ) AS mensile ON mensile.gate = tratta.gate

    GROUP BY tratta.gate
	 */
	public Map<String, Map<String, Integer>> statistiche() {
	    String query = "SELECT tratta.gate, SUM(giornaliero.voli) as voli_giornalieri, SUM(giornaliero.utilizzo_stimato) as s_giornaliero, SUM(giornaliero.utilizzo_effettivo) as e_giornaliero, SUM(settimanale.voli) as voli_settimanali, SUM(settimanale.utilizzo_stimato) as s_settimanale, SUM(settimanale.utilizzo_effettivo) as e_settimanale, SUM(mensile.voli) as voli_mensili, SUM(mensile.utilizzo_stimato) as s_mensile, SUM(mensile.utilizzo_effettivo) as e_mensile FROM scalo_aeroportuale.tratta LEFT JOIN( SELECT gate, 1 as voli, SUM( DATE_PART('day', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato) * 24 * 60 + DATE_PART('hour', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato) * 60 + DATE_PART('minute', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato)) AS utilizzo_stimato, SUM( DATE_PART('day', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) * 24 * 60 + DATE_PART('hour', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) * 60 + DATE_PART('minute', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) ) AS utilizzo_effettivo FROM scalo_aeroportuale.tratta WHERE ora_inizio_imbarco_stimato BETWEEN (now() - interval '1 day') AND (now()) GROUP BY gate ) AS giornaliero ON giornaliero.gate = tratta.gate LEFT JOIN ( SELECT gate, 1 as voli, SUM( DATE_PART('day', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato) * 24 * 60 + DATE_PART('hour', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato) * 60 + DATE_PART('minute', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato) ) AS utilizzo_stimato, SUM( DATE_PART('day', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) * 24 * 60 + DATE_PART('Hour', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) * 60 + DATE_PART('minute', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) ) AS utilizzo_effettivo FROM scalo_aeroportuale.tratta WHERE ora_inizio_imbarco_stimato BETWEEN (now() - interval '1 week') AND (now()) GROUP BY gate ) AS settimanale ON settimanale.gate = tratta.gate LEFT JOIN ( SELECT gate, 1 as voli, SUM( DATE_PART('day', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato) * 24 * 60 + DATE_PART('hour', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato) * 60 + DATE_PART('minute', tratta.ora_fine_imbarco_stimato - tratta.ora_inizio_imbarco_stimato) ) AS utilizzo_stimato, SUM( DATE_PART('day', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) * 24 * 60 + DATE_PART('hour', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) * 60 + DATE_PART('minute', tratta.ora_fine_imbarco_effettivo - tratta.ora_inizio_imbarco_effettivo) ) AS utilizzo_effettivo FROM scalo_aeroportuale.tratta WHERE ora_inizio_imbarco_stimato BETWEEN (now() - interval '1 month') AND (now()) GROUP BY gate ) AS mensile ON mensile.gate = tratta.gate GROUP BY tratta.gate";

        Map<String, Map<String, Integer>> statisticheGate = new HashMap<String, Map<String, Integer>>();

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Crea la mappa statistiche del singolo gate
                Map<String, Integer> statisticheMap = new HashMap<String, Integer>();
                statisticheMap.put("voli_giornalieri" , resultSet.getInt("voli_giornalieri"));
                statisticheMap.put("s_giornaliero" , resultSet.getInt("s_giornaliero"));
                statisticheMap.put("e_giornaliero" , resultSet.getInt("e_giornaliero"));
                statisticheMap.put("voli_settimanali" , resultSet.getInt("voli_settimanali"));
                statisticheMap.put("s_settimanale" , resultSet.getInt("s_settimanale"));
                statisticheMap.put("e_settiamanale" , resultSet.getInt("e_settimanale"));
                statisticheMap.put("voli_mensili" , resultSet.getInt("voli_mensili"));
                statisticheMap.put("s_mensile" , resultSet.getInt("s_mensile"));
                statisticheMap.put("e_mensile" , resultSet.getInt("e_mensile"));

                // Aggiungi il gate con le relative statistiche nella mappa generale
                statisticheGate.put(resultSet.getString("gate"), statisticheMap);
            }
            resultSet.close();
            statement.close();
        } catch(SQLException e) {
            System.out.println(e);
        }
        return statisticheGate;
    }

}
