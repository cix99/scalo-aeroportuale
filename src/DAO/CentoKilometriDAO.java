package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Models.CentoKilometri;
import Models.CompagniaAerea;

public class CentoKilometriDAO extends JDBC {

	protected String tableName = "cento_kilometri";
	
	public CentoKilometri store(CentoKilometri centoKilometri){
        String query = "INSERT INTO " + this.tableName + " (codice_compagnia, compagnia_aerea, punti) VALUES  (?, ?, ?)";

        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, centoKilometri.codiceCompagnia);
            statement.setString(2, centoKilometri.compagniaAerea.nomeCompagnia);
            statement.setInt(3, centoKilometri.punti);
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return centoKilometri;
    }

    public CentoKilometri findByCode(String codiceCompagnia){
        String query = "SELECT * FROM " + this.tableName + " WHERE codice_compagnia = ?";
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        CentoKilometri centoKilometri = new CentoKilometri();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, codiceCompagnia);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                centoKilometri.id = resultSet.getInt("id");
                centoKilometri.codiceCompagnia = resultSet.getString("codice_compagnia");
                centoKilometri.compagniaAerea = (CompagniaAerea) compagniaAereaDAO.findByName(resultSet.getString("compagnia_aerea"));
                centoKilometri.punti = resultSet.getInt("punti");
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return centoKilometri;
    }

    public List<CentoKilometri> find(){
        String query = "SELECT * FROM " + this.tableName;
        List<CentoKilometri> centoKilometriList = new LinkedList<CentoKilometri>();
        CompagniaAereaDAO compagniaAereaDAO = new CompagniaAereaDAO();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CentoKilometri centoKilometri = new CentoKilometri();
                centoKilometri.id = resultSet.getInt("id");
                centoKilometri.codiceCompagnia = resultSet.getString("codice_compagnia");
                centoKilometri.compagniaAerea = (CompagniaAerea) compagniaAereaDAO.findByName(resultSet.getString("compagnia_aerea"));
                centoKilometri.punti = resultSet.getInt("punti");
                centoKilometriList.add(centoKilometri);
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return centoKilometriList;
    }

    public CentoKilometri first(){
        List<CentoKilometri> centoKilometriList = find();
        return centoKilometriList.get(0);
    }

    public CentoKilometri last(){
        List<CentoKilometri> centoKilometriList = find();
        return centoKilometriList.get(centoKilometriList.size() - 1);
    }
}
