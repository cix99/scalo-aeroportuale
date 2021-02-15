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
    
    public LinkedList<Tratta> getTratte () {
    	TrattaDAO trattaDao = new TrattaDAO();
    	return trattaDao.find();
    }
    
    public LinkedList<Prenotazione> getPrenotazioni() {
    	PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO();
    	return prenotazioneDao.find();
    }
    
    public LinkedList<CompagniaAerea> getCompagnie() {
    	CompagniaAereaDAO compagniaAereaDao = new CompagniaAereaDAO();
    	return compagniaAereaDao.get();
    }
    
    public LinkedList<Gate> getGates() {
    	GateDAO gateDao = new GateDAO();
    	return gateDao.get();
    }
    
    public boolean salvaNuovoGate (String nomeGate) {
    	GateDAO gateDao = new GateDAO();
    	Gate gate = new Gate(nomeGate);
    	if (gateDao.store(gate))
    		return true;
    	return false;
    }
    
    public boolean salvaNuovoCompagniaAerea (String nomeCompagnia) {
    	CompagniaAereaDAO compagniaAereaDao = new CompagniaAereaDAO();
    	CompagniaAerea compagniaAerea = new CompagniaAerea(nomeCompagnia);
    	if (compagniaAereaDao.store(compagniaAerea))
    		return true;
    	return false;
    }
    
    public boolean salvaNuovoCentoKilometri(String codice, String nomeCompagnia, String punti) {
    	CentoKilometriDAO centoKilometriDao = new CentoKilometriDAO();
    	int puntiValore;
    	try {
    	   puntiValore = Integer.parseInt(punti);
    	}
    	catch (NumberFormatException e)
    	{
    	   puntiValore = 0;
    	}
    	CompagniaAerea compagniaAerea = new CompagniaAerea(nomeCompagnia);
    	CentoKilometri centoKilometri = new CentoKilometri(codice, compagniaAerea, puntiValore);
    	if (centoKilometriDao.store(centoKilometri))
    		return true;
    	return false;
    }
    
    
}
