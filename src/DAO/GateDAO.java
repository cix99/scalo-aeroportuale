package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Models.Gate;

public class GateDAO extends JDBC {

    private String tableName = "gate";

    public boolean store(Gate gate){
        String query = "INSERT INTO " + tableName + " VALUES (?)";
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, gate.getNomeGate());
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    public boolean delete (String nomeGate) {
    	String query = "DELETE FROM " + tableName + " WHERE nome_gate = (?)";
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, nomeGate);
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
            return false;
        }

        return true;
    }

    public Gate findByName(String nome){
        String query = "SELECT * FROM " + tableName + " WHERE nome_gate = ?";
        Gate gate = new Gate();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            statement.setString(1, nome);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                gate.setNomeGate(resultSet.getString("nome_gate"));
                break;
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return gate;
    }

    public LinkedList<Gate> get(){
        String query = "SELECT * FROM gate";
        LinkedList<Gate> gateList = new LinkedList<Gate>();
        try {
            PreparedStatement statement = JDBC.GetConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Gate gate = new Gate();
                gate.setNomeGate(resultSet.getString("nome_gate"));
                gateList.add(gate);
            }
            resultSet.close();
            statement.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return gateList;
    }

    public Gate first(){
    	LinkedList<Gate> gateList = get();
        return gateList.get(0);
    }

    public Gate last(){
    	LinkedList<Gate> gateList = get();
        return gateList.get(gateList.size() - 1);
    }
}
