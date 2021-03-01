package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Models.Coda;

public class CodaDAO extends JDBC {

	private String tableName = "coda";
	
	public boolean store(Coda coda){
        String query = "INSERT INTO " + tableName + " (id_tratta, nome_coda, priority) VALUES  (?, ?, ?)";

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setInt(1, coda.getIdTratta());
            statement.setString(2, coda.getNomeCoda());
            statement.setInt(3, coda.getPriority());
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public Coda findById(int idCoda){
        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        Coda coda = new Coda();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setInt(1, idCoda);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	coda.setId(resultSet.getInt("id"));
            	coda.setIdTratta(resultSet.getInt("id_tratta"));
                coda.setNomeCoda(resultSet.getString("nome_coda"));
                if (resultSet.getTimestamp("inizio_imbarco_coda") != null)
                	coda.setInizioImbarcoCoda(resultSet.getTimestamp("inizio_imbarco_coda").toLocalDateTime());
                if (resultSet.getTimestamp("fine_imbarco_coda") != null)
                	coda.setFineImbarcoCoda(resultSet.getTimestamp("fine_imbarco_coda").toLocalDateTime());
                coda.setPriority(resultSet.getInt("priority"));
               break;
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }

        return coda;
    }
    
    public Coda findByNameAndTratta(String nomeCoda, int idTratta){
        String query = "SELECT * FROM " + tableName + " WHERE nome_coda = ? AND id_tratta = ?";
        Coda coda = new Coda();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, nomeCoda);
            statement.setInt(2, idTratta);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	coda.setId(resultSet.getInt("id"));
            	coda.setIdTratta(resultSet.getInt("id_tratta"));
                coda.setNomeCoda(resultSet.getString("nome_coda"));
                if (resultSet.getTimestamp("inizio_imbarco_coda") != null)
                	coda.setInizioImbarcoCoda(resultSet.getTimestamp("inizio_imbarco_coda").toLocalDateTime());
                if (resultSet.getTimestamp("fine_imbarco_coda") != null)
                	coda.setFineImbarcoCoda(resultSet.getTimestamp("fine_imbarco_coda").toLocalDateTime());
                coda.setPriority(resultSet.getInt("priority"));
                break;
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }

        return coda;
    }
    
    public LinkedList<Coda> findCodaByIdTratta(int idTratta){
        String query = "SELECT * FROM " + tableName + " WHERE id_tratta = ? ORDER BY priority DESC";
        LinkedList<Coda> codaList = new LinkedList<Coda>();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setInt(1, idTratta);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Coda coda = new Coda();
                coda.setId(resultSet.getInt("id"));
            	coda.setIdTratta(resultSet.getInt("id_tratta"));
                coda.setNomeCoda(resultSet.getString("nome_coda"));
                if (resultSet.getTimestamp("inizio_imbarco_coda") != null)
                	coda.setInizioImbarcoCoda(resultSet.getTimestamp("inizio_imbarco_coda").toLocalDateTime());
                if (resultSet.getTimestamp("fine_imbarco_coda") != null)
                	coda.setFineImbarcoCoda(resultSet.getTimestamp("fine_imbarco_coda").toLocalDateTime());
                coda.setPriority(resultSet.getInt("priority"));
                codaList.add(coda);
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }

        return codaList;
    }
    
    public LinkedList<Coda> find(){
        String query = "SELECT * FROM " + tableName;
        LinkedList<Coda> codaList = new LinkedList<Coda>();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Coda coda = new Coda();
                coda.setId(resultSet.getInt("id"));
            	coda.setIdTratta(resultSet.getInt("id_tratta"));
                coda.setNomeCoda(resultSet.getString("nome_coda"));
                if (resultSet.getTimestamp("inizio_imbarco_coda") != null)
                	coda.setInizioImbarcoCoda(resultSet.getTimestamp("inizio_imbarco_coda").toLocalDateTime());
                if (resultSet.getTimestamp("fine_imbarco_coda") != null)
                	coda.setFineImbarcoCoda(resultSet.getTimestamp("fine_imbarco_coda").toLocalDateTime());
                coda.setPriority(resultSet.getInt("priority"));
                codaList.add(coda);
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }

        return codaList;
    }

    public Coda first(){
    	LinkedList<Coda> codaList = find();
        return codaList.get(0);
    }

    public Coda last(){
    	LinkedList<Coda> codaList = find();
        return codaList.get(codaList.size() - 1);
    }

	public boolean updateInizioImbarco(Coda coda) {
		String query = "UPDATE " + tableName + " SET inizio_imbarco_coda = ? WHERE id = ?";
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setObject(1, coda.getInizioImbarcoCoda());
            statement.setInt(2, coda.getId());
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
	}

	public boolean updateFineImbarco(Coda coda) {
		String query = "UPDATE " + tableName + " SET fine_imbarco_coda = ? WHERE id = ?";
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setObject(1, coda.getFineImbarcoCoda());
            statement.setInt(2, coda.getId());
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
	}

	public boolean update(Coda coda) {
		String query = "UPDATE " + tableName + " SET priority = ? WHERE id = ?";
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setObject(1, coda.getPriority());
            statement.setInt(2, findByNameAndTratta(coda.getNomeCoda(), coda.getIdTratta()).getId());
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
	}
}
