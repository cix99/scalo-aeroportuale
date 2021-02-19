package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Models.CompagniaAerea;

public class CompagniaAereaDAO {

	private String tableName = "compagnia_aerea";
	
	public boolean store(CompagniaAerea compagniaAerea){
        String query = "INSERT INTO " + tableName + " VALUES (?)";
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, compagniaAerea.getNomeCompagnia());
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public CompagniaAerea findByName(String nome){
        String query = "SELECT * FROM " + tableName + " WHERE nome_compagnia = (?)";
        CompagniaAerea compagniaAerea = new CompagniaAerea();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, nome);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                compagniaAerea.setNomeCompagnia(resultSet.getString("nome_compagnia"));
                break;
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }
        return compagniaAerea;
    }

    public LinkedList<CompagniaAerea> find(){
        String query = "SELECT * FROM " + tableName;
        LinkedList<CompagniaAerea> compagniaAereaList = new LinkedList<CompagniaAerea>();
        try {
            PreparedStatement statement = JDBC
            		.GetConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CompagniaAerea compagniaAerea = new CompagniaAerea();
                compagniaAerea.setNomeCompagnia(resultSet.getString("nome_compagnia"));
                compagniaAereaList.add(compagniaAerea);
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }
        return compagniaAereaList;
    }
    
	public boolean delete(String nomeCompagnia) {
		String query = "DELETE FROM " + tableName + " WHERE nome_compagnia = ?";

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, nomeCompagnia);
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
	}

    public CompagniaAerea first(String nome){
    	LinkedList<CompagniaAerea> compagniaAereaList = find();
        return compagniaAereaList.get(0);
    }

    public CompagniaAerea last(String nome){
    	LinkedList<CompagniaAerea> compagniaAereaList = find();
        return compagniaAereaList.get(compagniaAereaList.size() - 1);
    }

}
