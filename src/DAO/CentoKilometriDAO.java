package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Models.CentoKilometri;

public class CentoKilometriDAO extends JDBC {

	private String tableName = "cento_kilometri";
	
	public CentoKilometri store(CentoKilometri centoKilometri){
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

    public CentoKilometri first(){
        LinkedList<CentoKilometri> centoKilometriList = find();
        return centoKilometriList.get(0);
    }

    public CentoKilometri last(){
    	LinkedList<CentoKilometri> centoKilometriList = find();
        return centoKilometriList.get(centoKilometriList.size() - 1);
    }
}
