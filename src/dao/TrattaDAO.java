package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Models.Stato;
//import dao.CompagniaAereaDAO;
import Models.Tratta;

public class TrattaDAO extends Jdbc {

	protected String tableName = "tratta";
	
	public Tratta store(Tratta tratta){
        String query = "INSERT INTO " + this.tableName + " (destinazione, compagnia_aerea, ora_inizio_imbarco, ora_fine_imbarco_stimato, ora_fine_imbarco_effettivo, gate) VALUES  (?, ?, ?, ?, ?, ?)";

//        CompagniaAerea compagniaAerea = new CompagniaAerea().first(tratta.compagniaAerea);
//        if(compagniaAerea.nomeCompagnia == null){
//            compagniaAerea.nomeCompagnia = tratta.compagniaAerea;
//            compagniaAerea.store();
//        }

        try {
            PreparedStatement statement = Jdbc.GetConnection().prepareStatement(query);
            statement.setString(1, tratta.destinazione);
            statement.setString(2, tratta.compagniaAerea);
            statement.setObject(3, tratta.oraInizioImbarco);
            statement.setObject(4, tratta.oraFineImbarcoStimato);
            statement.setObject(5, tratta.oraFineImbarcoEffettivo);
            statement.setString(6, tratta.gate);
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }

        return tratta;
    }

    public List<Tratta> find(){
        String query = "SELECT * FROM " + this.tableName;
        List<Tratta> TrattaList = new LinkedList<Tratta>();
        try {
            PreparedStatement statement = Jdbc.GetConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tratta tratta = new Tratta();
                tratta.destinazione = resultSet.getString("destinazione");
                tratta.compagniaAerea = resultSet.getString("compagnia_aerea");
                tratta.oraInizioImbarco = resultSet.getTimestamp("ora_inizio_imbarco").toLocalDateTime();
                tratta.oraFineImbarcoStimato = resultSet.getTimestamp("ora_fine_imbarco_stimato").toLocalDateTime();
                tratta.oraFineImbarcoEffettivo = resultSet.getTimestamp("ora_fine_imbarco_effettivo").toLocalDateTime();
                tratta.statoImbarco = Stato.valueOf(resultSet.getString("stato_imbarco"));
                tratta.ritardo = resultSet.getBoolean("ritardo");
                tratta.gate = resultSet.getString("gate");
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
        List<Tratta> TrattaList = find();
        return TrattaList.get(0);
    }

    public Tratta last(){
        List<Tratta> TrattaList = find();
        return TrattaList.get(TrattaList.size() - 1);
    }
}
