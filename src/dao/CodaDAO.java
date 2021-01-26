package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Models.Coda;

public class CodaDAO extends Jdbc{

	protected String tableName = "coda";
	
	public Coda store(Coda coda){
        String query = "INSERT INTO " + this.tableName + " VALUES  (?)";

        try {
            PreparedStatement statement = Jdbc.GetConnection().prepareStatement(query);
            statement.setString(1, coda.nomeCoda);
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return coda;
    }

    public List<Coda> find(){
        String query = "SELECT * FROM " + this.tableName;
        List<Coda> codaList = new LinkedList<Coda>();
        try {
            PreparedStatement statement = Jdbc.GetConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Coda coda = new Coda();
                coda.nomeCoda = resultSet.getString("nome_coda");
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
        List<Coda> codaList = find();
        return codaList.get(0);
    }

    public Coda last(){
        List<Coda> codaList = find();
        return codaList.get(codaList.size() - 1);
    }
}
