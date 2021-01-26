package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import dao.Jdbc;

public class Prenotazione{

    public String id;
    public int idTratta;
    public String nomePasseggero;
    public String cognomePasseggero;
    public String coda;
    public String centoKilometri;
    public String compagniaAerea;

    public Prenotazione() {
	}
    
}
