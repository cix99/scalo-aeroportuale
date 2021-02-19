package Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

import DAO.*;
import Models.*;

public class DatabaseController {
		    
    public LinkedList<Tratta> getTrattaInfoFromGate(String nomeGate) {
    	TrattaDAO trattaDao = new TrattaDAO();
    	LinkedList<Tratta> tratte = trattaDao.findTrattaByGate(nomeGate);
    	return tratte;
    }
    
    public LinkedList<Prenotazione> getPrenotatiFromTratta(int trattaId) {
    	PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO();
    	LinkedList<Prenotazione> prenotazioni = prenotazioneDao.findByTrattaId(trattaId);
    	return prenotazioni;
    }
    
    public void updateImbarcatoInDatabase(boolean value, String id) {
    	PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO();
    	prenotazioneDao.updateImbarcato(value, id);
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
    
    public LinkedList<Prenotazione> getPrenotazioni() {
    	PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO();
    	return prenotazioneDao.find();
    }
    
    public LinkedList<Tratta> getTratte() {
    	TrattaDAO trattaDao = new TrattaDAO();
    	return trattaDao.find();
    }
    
	public LinkedList<CentoKilometri> getCentoKilometri() {
		CentoKilometriDAO centoKilometriDao = new CentoKilometriDAO();
    	return centoKilometriDao.find();
	}

    public LinkedList<CompagniaAerea> getCompagnieAeree() {
    	CompagniaAereaDAO compagniaAereaDao = new CompagniaAereaDAO();
    	return compagniaAereaDao.find();
    }
    
    public LinkedList<Gate> getGates() {
    	GateDAO gateDao = new GateDAO();
    	return gateDao.find();
    }
    
   public boolean salvaNuovaPrenotazione(String nome, String cognome, String codicePrenotazione, String centoKilometri, String compagniaCentoKilometri, String compagniaVolo, int idTratta, String coda) {
	   PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO();
	   Prenotazione prenotazione;
	   CodaDAO codaDao = new CodaDAO();
	   CentoKilometriDAO centoKilometriDao = new CentoKilometriDAO();
	   if (!centoKilometri.isBlank())
	   {
		   prenotazione = new Prenotazione(idTratta, nome, cognome, codicePrenotazione, codaDao.findByNameAndTratta(coda, idTratta), centoKilometriDao.findByCodeAndCompany(centoKilometri, compagniaCentoKilometri), new CompagniaAerea(compagniaVolo));
		   if (prenotazioneDao.store(prenotazione))
			   return true;
	   }
	   else {
		   prenotazione = new Prenotazione(idTratta, nome, cognome, codicePrenotazione, codaDao.findByNameAndTratta(coda, idTratta), new CompagniaAerea(compagniaVolo));
		   if (prenotazioneDao.storeWithoutCK(prenotazione))
			   return true;
	   }
	   return false;
   }
   
	public boolean salvaNuovaTratta(String destinazione, String nomeCompagnia, LocalDateTime inizioImbarco, LocalDateTime fineImbarco, ArrayList<Coda> code) {
		TrattaDAO trattaDao = new TrattaDAO();
		Tratta tratta = new Tratta(destinazione, new CompagniaAerea(nomeCompagnia), inizioImbarco, fineImbarco);
		int idTratta = trattaDao.store(tratta);
		if (idTratta != 0) {
			if (salvaNuoveCode(code, idTratta)) {
				return true;
			}
		}
		return false;
	}   
	
	public boolean salvaNuoveCode(ArrayList<Coda> code, int idTratta) {
		CodaDAO codaDao = new CodaDAO();
		for (Coda coda : code) { 
			coda.setIdTratta(idTratta);
			if (!codaDao.store(coda))
				return false;
	    }
		return true;
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
    
    public boolean salvaNuovoCompagniaAerea(String nomeCompagnia) {
    	CompagniaAereaDAO compagniaAereaDao = new CompagniaAereaDAO();
    	CompagniaAerea compagniaAerea = new CompagniaAerea(nomeCompagnia);
    	if (compagniaAereaDao.store(compagniaAerea))
    		return true;
    	return false;
    }
    
    public boolean salvaNuovoGate(String nomeGate) {
    	GateDAO gateDao = new GateDAO();
    	Gate gate = new Gate(nomeGate);
    	if (gateDao.store(gate))
    		return true;
    	return false;
    } 
    
}
