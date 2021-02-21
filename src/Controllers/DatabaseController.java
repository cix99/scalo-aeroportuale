package Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

import DAO.*;
import Models.*;

public class DatabaseController {
		    
	//Get
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
    
	public void getClientiCK(ArrayList<String> clientiCKList, int idCentoKilometri) {
		PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO();
		LinkedList<Prenotazione> prenotazioniList = prenotazioneDao.findByCentoKilometriId(idCentoKilometri);
		clientiCKList.add(prenotazioniList.getFirst().getNomePasseggero());
		clientiCKList.add(prenotazioniList.getFirst().getCognomePasseggero());
	}
    
	//Save
	public boolean saveNuovaPrenotazione(String nome, String cognome, String codicePrenotazione, String centoKilometri, String compagniaCentoKilometri, String compagniaVolo, int idTratta, String coda) {
		PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO();
		Prenotazione prenotazione;
		CodaDAO codaDao = new CodaDAO();
		CentoKilometriDAO centoKilometriDao = new CentoKilometriDAO();
		if (!centoKilometri.isBlank()) {
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
	
   
	public boolean saveNuovaTratta(String destinazione, String nomeCompagnia, LocalDateTime inizioImbarco, LocalDateTime fineImbarco, int maxPrenotazioni, ArrayList<Coda> code) {
		TrattaDAO trattaDao = new TrattaDAO();
		Tratta tratta = new Tratta(destinazione, new CompagniaAerea(nomeCompagnia), inizioImbarco, fineImbarco, maxPrenotazioni);
		int idTratta = trattaDao.store(tratta);
		if (idTratta != 0) {
			if (saveNuoveCode(code, idTratta)) {
				return true;
			}
		}
		return false;
	}   
	
	
	public boolean saveNuoveCode(ArrayList<Coda> code, int idTratta) {
		CodaDAO codaDao = new CodaDAO();
		for (Coda coda : code) { 
			coda.setIdTratta(idTratta);
			if (!codaDao.store(coda))
				return false;
	    }
		return true;
	}
    
    public boolean saveNuovoCentoKilometri(String codice, String nomeCompagnia, String punti) {
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
    
    public boolean saveNuovoCompagniaAerea(String nomeCompagnia) {
    	CompagniaAereaDAO compagniaAereaDao = new CompagniaAereaDAO();
    	CompagniaAerea compagniaAerea = new CompagniaAerea(nomeCompagnia);
    	if (compagniaAereaDao.store(compagniaAerea))
    		return true;
    	return false;
    }
    
    public boolean saveNuovoGate(String nomeGate) {
    	GateDAO gateDao = new GateDAO();
    	Gate gate = new Gate(nomeGate);
    	if (gateDao.store(gate))
    		return true;
    	return false;
    }

	
    //Delete
    public boolean deleteTratta(int idTratta) {
		TrattaDAO trattaDao = new TrattaDAO();
		if (trattaDao.delete(idTratta))
			return true;
		return false;
	}
    

	public boolean deletePrenotazione(String idPrenotazione) {
		PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO();
		if (prenotazioneDao.delete(idPrenotazione))
			return true;
		return false;
	}
	

	public boolean deleteCentoKilometri(int idCentoKilometri) {
		CentoKilometriDAO centoKilometriDao = new CentoKilometriDAO();
		if (centoKilometriDao.delete(idCentoKilometri))
			return true;
		return false;
	}
	

	public boolean deleteCompagniaAerea(String nomeCompagnia) {
		CompagniaAereaDAO compagniaAereaDao = new CompagniaAereaDAO();
		if (compagniaAereaDao.delete(nomeCompagnia))
			return true;
		return false;
	}
	

	public boolean deleteGate(String nomeGate) {
		GateDAO gateDao = new GateDAO();
		if (gateDao.delete(nomeGate))
			return true;
		return false;
	}

	//Update
    public boolean updateImbarcatoInDatabase(boolean value, String id) {
    	PrenotazioneDAO prenotazioneDao = new PrenotazioneDAO();
    	if (prenotazioneDao.updateImbarcato(value, id))
    		return true;
    	return false;
    }
    
	
	public boolean updateInizioImbarco(Tratta tratta) {
		TrattaDAO trattaDao = new TrattaDAO();
		if (trattaDao.updateInizioImbarco(tratta))
			return true;
		return false;
	}
	

	public boolean updateCodaInizioImbarco(Coda coda) {
		CodaDAO codaDao = new CodaDAO();
		if (codaDao.updateInizioImbarco(coda))
			return true;
		return false;
	}
	

	public boolean updateCodaFineImbarco(Coda coda) {
		CodaDAO codaDao = new CodaDAO();
		if (codaDao.updateFineImbarco(coda))
			return true;
		return false;
	}
	

	public boolean updateFineImbarco(Tratta tratta) {
		TrattaDAO trattaDao = new TrattaDAO();
		if (trattaDao.updateFineImbarco(tratta))
			return true;
		return false;
	} 
    
}
