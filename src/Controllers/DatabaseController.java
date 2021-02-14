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
    
    public LinkedList<CompagniaAerea> getCompagnieAeree() {
    	CompagniaAereaDAO compagniaAereaDao = new CompagniaAereaDAO();
    	return compagniaAereaDao.get();
    }
    
    public LinkedList<Tratta> getTratteFromCompagnia(String nomeCompagnia) {
    	TrattaDAO trattaDao = new TrattaDAO();
    	LinkedList<Tratta> tratte = trattaDao.findTrattaByCompagnia(nomeCompagnia);
    	return tratte;
    }
    
    public LinkedList<Coda> getCodaByIdTratta(int idTratta) {
    	CodaDAO codaDao = new CodaDAO();
    	LinkedList<Coda> code = codaDao.findCodaByIdTratta(idTratta);
    	return code;
    }
    
    
}
