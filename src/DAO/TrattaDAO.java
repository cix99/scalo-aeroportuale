package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Models.Stato;
import Models.Tratta;

public class TrattaDAO extends JDBC {

	private String tableName = "tratta";
	
	public int store(Tratta tratta){
//        String query = "INSERT INTO " + tableName + " (destinazione, compagnia_aerea, ora_inizio_imbarco_stimato, ora_inizio_imbarco_effettivo, "
//        											+ "ora_fine_imbarco_stimato, ora_fine_imbarco_effettivo, gate) VALUES  (?, ?, ?, ?, ?, ?, ?)";
        String query = "INSERT INTO " + tableName + " (destinazione, compagnia_aerea, ora_inizio_imbarco_stimato, ora_fine_imbarco_stimato) VALUES  (?, ?, ?, ?) RETURNING (id)";
        int id = 0;
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, tratta.getDestinazione());
            statement.setString(2, tratta.getCompagniaAerea().getNomeCompagnia());
            statement.setObject(3, tratta.getOraInizioImbarcoStimato());
            //statement.setObject(4, tratta.getOraInizioImbarcoEffettivo());
            statement.setObject(4, tratta.getOraFineImbarcoStimato());
            //statement.setObject(6, tratta.getOraFineImbarcoEffettivo());
            //statement.setString(7, tratta.getGate().getNomeGate());
//            statement.executeUpdate();
            //idTratta = statement.getGeneratedKeys().getInt("id");
            statement.execute();
            ResultSet lastInsert = statement.getResultSet();
            lastInsert.next();
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
        String query = "SELECT * FROM " + tableName + " WHERE gate = ?";
        LinkedList<Tratta> TrattaList = new LinkedList<Tratta>();
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        GateDAO gateDAO = new GateDAO();

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, nomeGate);
            ResultSet resultSet = statement.executeQuery();
        	while (resultSet.next()) {
                Tratta tratta = new Tratta();
                tratta.setId(resultSet.getInt("id"));
                tratta.setDestinazione(resultSet.getString("destinazione"));
                tratta.setCompagniaAerea(compagniaAereaDAO.findByName(resultSet.getString("compagnia_aerea")));
                tratta.setOraInizioImbarcoStimato(resultSet.getTimestamp("ora_inizio_imbarco_stimato").toLocalDateTime());
                tratta.setOraInizioImbarcoEffettivo(resultSet.getTimestamp("ora_inizio_imbarco_effettivo").toLocalDateTime());
                tratta.setOraFineImbarcoStimato(resultSet.getTimestamp("ora_fine_imbarco_stimato").toLocalDateTime());
                tratta.setOraFineImbarcoEffettivo(resultSet.getTimestamp("ora_fine_imbarco_effettivo").toLocalDateTime());
                tratta.setStatoImbarco(Stato.valueOf(resultSet.getString("stato_imbarco")));
                tratta.setRitardo(resultSet.getBoolean("ritardo"));
                tratta.setGate(gateDAO.findByName(resultSet.getString("gate")));
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
                tratta.setOraInizioImbarcoEffettivo(resultSet.getTimestamp("ora_inizio_imbarco_effettivo").toLocalDateTime());
                tratta.setOraFineImbarcoStimato(resultSet.getTimestamp("ora_fine_imbarco_stimato").toLocalDateTime());
                tratta.setOraFineImbarcoEffettivo(resultSet.getTimestamp("ora_fine_imbarco_effettivo").toLocalDateTime());
                tratta.setStatoImbarco(Stato.valueOf(resultSet.getString("stato_imbarco")));
                tratta.setRitardo(resultSet.getBoolean("ritardo"));
                tratta.setGate(gateDAO.findByName(resultSet.getString("gate")));
                TrattaList.add(tratta);
            }            
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return TrattaList;
    }

    public Tratta first(){
    	LinkedList<Tratta> TrattaList = find();
        return TrattaList.get(0);
    }

    public Tratta last(){
    	LinkedList<Tratta> TrattaList = find();
        return TrattaList.get(TrattaList.size() - 1);
    }
}
