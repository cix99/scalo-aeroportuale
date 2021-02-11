package Controllers;

import java.util.LinkedList;

import DAO.*;
import Models.*;

public class DatabaseController {
		    
    public void Function() {
    	
    }
    
    public LinkedList<Tratta> getTrattaInfoFromGate(String nomeGate) {
    	TrattaDAO trattaDao = new TrattaDAO();
    	LinkedList<Tratta> tratte = trattaDao.findTrattaByGate(nomeGate);
  
    	return tratte;
    }
    
    public LinkedList<Prenotazione> getPrenotatiFromTratta (int trattaId) {
    	PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO();
    	LinkedList<Prenotazione> prenotazioni = prenotazioneDao.findByTrattaId(trattaId);
    	return prenotazioni;
    }
    
    public void updateImbarcatoInDatabase(boolean value, String id) {
    	PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO();
    	prenotazioneDao.updateImbarcato(value, id);
    }
    
}
