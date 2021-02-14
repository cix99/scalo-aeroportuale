package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Models.Coda;

public class CodaDAO extends JDBC {

	private String tableName = "coda";
	
	public Coda store(Coda coda){
        String query = "INSERT INTO " + tableName + " VALUES  (?)";

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, coda.getNomeCoda());
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return coda;
    }

    public Coda findByName(int idCoda, int idTratta){
        String query = "SELECT * FROM " + tableName + " WHERE id = ? AND id_tratta = ?";
        Coda coda = new Coda();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setInt(1, idCoda);
            statement.setInt(2, idTratta);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                coda.setNomeCoda(resultSet.getString("nome_coda"));
                break;
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }

        return coda;
    }

    public LinkedList<Coda> find(){
        String query = "SELECT * FROM " + tableName;
        LinkedList<Coda> codaList = new LinkedList<Coda>();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Coda coda = new Coda();
                coda.setNomeCoda(resultSet.getString("nome_coda"));
                codaList.add(coda);
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }

        return codaList;
    }
    
    public LinkedList<Coda> findCodaByIdTratta(int idTratta){
        String query = "SELECT * FROM " + tableName + " WHERE id_tratta = ?";
        LinkedList<Coda> codaList = new LinkedList<Coda>();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setInt(1, idTratta);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Coda coda = new Coda();
                coda.setNomeCoda(resultSet.getString("nome_coda"));
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
}
