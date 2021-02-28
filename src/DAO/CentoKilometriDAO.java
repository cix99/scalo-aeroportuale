package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Models.CentoKilometri;

public class CentoKilometriDAO extends JDBC {

	private String tableName = "cento_kilometri";
	
	public boolean store(CentoKilometri centoKilometri){
        String query = "INSERT INTO " + tableName + " (codice_compagnia, compagnia_aerea, punti) VALUES  (?, ?, ?)";

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, centoKilometri.getCodiceCompagnia());
            statement.setString(2, centoKilometri.getCompagniaAerea().getNomeCompagnia());
            statement.setInt(3, centoKilometri.getPunti());
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    public CentoKilometri findById(int id){
        String query = "SELECT * FROM " + tableName + " WHERE id = ?";
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        CentoKilometri centoKilometri = new CentoKilometri();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                centoKilometri.setId(resultSet.getInt("id"));
                centoKilometri.setCodiceCompagnia(resultSet.getString("codice_compagnia"));
                centoKilometri.setCompagniaAerea(compagniaAereaDAO.findByName(resultSet.getString("compagnia_aerea")));
                centoKilometri.setPunti(resultSet.getInt("punti"));
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return centoKilometri;
    }
    
    public CentoKilometri findByCode(String codiceCompagnia){
        String query = "SELECT * FROM " + tableName + " WHERE codice_compagnia = ?";
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        CentoKilometri centoKilometri = new CentoKilometri();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, codiceCompagnia);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                centoKilometri.setId(resultSet.getInt("id"));
                centoKilometri.setCodiceCompagnia(resultSet.getString("codice_compagnia"));
                centoKilometri.setCompagniaAerea(compagniaAereaDAO.findByName(resultSet.getString("compagnia_aerea")));
                centoKilometri.setPunti(resultSet.getInt("punti"));
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return centoKilometri;
    }
    
    public CentoKilometri findByCodeAndCompany(String codiceCompagnia, String nomeCompagnia){
        String query = "SELECT * FROM " + tableName + " WHERE codice_compagnia = ? AND compagnia_aerea = ?";
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        CentoKilometri centoKilometri = new CentoKilometri();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, codiceCompagnia);
            statement.setString(2, nomeCompagnia);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                centoKilometri.setId(resultSet.getInt("id"));
                centoKilometri.setCodiceCompagnia(resultSet.getString("codice_compagnia"));
                centoKilometri.setCompagniaAerea(compagniaAereaDAO.findByName(resultSet.getString("compagnia_aerea")));
                centoKilometri.setPunti(resultSet.getInt("punti"));
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return centoKilometri;
    }

    public LinkedList<CentoKilometri> find(){
        String query = "SELECT * FROM " + tableName;
        LinkedList<CentoKilometri> centoKilometriList = new LinkedList<CentoKilometri>();
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CentoKilometri centoKilometri = new CentoKilometri();
                centoKilometri.setId(resultSet.getInt("id"));
                centoKilometri.setCodiceCompagnia(resultSet.getString("codice_compagnia"));
                centoKilometri.setCompagniaAerea(compagniaAereaDAO.findByName(resultSet.getString("compagnia_aerea")));
                centoKilometri.setPunti(resultSet.getInt("punti"));
                centoKilometriList.add(centoKilometri);
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return centoKilometriList;
    }
    
	public boolean delete(int idCentoKilometri) {
		String query = "DELETE FROM " + tableName + " WHERE id = ?";

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setInt(1, idCentoKilometri);
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
	}

    public CentoKilometri first(){
        LinkedList<CentoKilometri> centoKilometriList = find();
        return centoKilometriList.get(0);
    }

    public CentoKilometri last(){
    	LinkedList<CentoKilometri> centoKilometriList = find();
        return centoKilometriList.get(centoKilometriList.size() - 1);
    }

	public boolean update(String codice, String nomeCompagnia, int punti, int id) {
		String query = "UPDATE " + tableName + " SET codice_compagnia = ?, compagnia_aerea = ?, punti = ? WHERE id = ?";
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, codice);
            statement.setString(2, nomeCompagnia);
            statement.setInt(3, punti);
            statement.setInt(4, id);
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
	}
    
}
