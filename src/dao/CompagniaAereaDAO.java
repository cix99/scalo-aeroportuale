package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Models.CompagniaAerea;

public class CompagniaAereaDAO {

	protected static String tableName = "compagnia_aerea";
	
	public CompagniaAerea store(CompagniaAerea compagniaAerea){
        String query = "INSERT INTO " + this.tableName + " VALUES (?)";
        try {
            PreparedStatement statement = Jdbc.GetConnection().prepareStatement(query);
            statement.setString(1, compagniaAerea.nomeCompagnia);
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return compagniaAerea;
    }

    public static CompagniaAerea findByName(String nome){
        String query = "SELECT * FROM " + tableName + " WHERE nome_compagnia = ?";
        CompagniaAerea compagniaAerea = new CompagniaAerea();
        try {
            PreparedStatement statement = Jdbc.GetConnection().prepareStatement(query);
            statement.setString(1, nome);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                compagniaAerea.nomeCompagnia = resultSet.getString("nome_compagnia");
                break;
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }
        return compagniaAerea;
    }

    public List<CompagniaAerea> get(){
        String query = "SELECT * FROM " + this.tableName;
        List<CompagniaAerea> compagniaAereaList = new LinkedList<CompagniaAerea>();
        try {
            PreparedStatement statement = Jdbc.GetConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CompagniaAerea compagniaAerea = new CompagniaAerea();
                compagniaAerea.nomeCompagnia = resultSet.getString("nome_compagnia");
                compagniaAereaList.add(compagniaAerea);
            }
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            System.out.println(e);
        }
        return compagniaAereaList;
    }

    public CompagniaAerea first(String nome){
        List<CompagniaAerea> compagniaAereaList = get();
        return compagniaAereaList.get(0);
    }

    public CompagniaAerea last(String nome){
        List<CompagniaAerea> compagniaAereaList = get();
        return compagniaAereaList.get(compagniaAereaList.size() - 1);
    }

}
