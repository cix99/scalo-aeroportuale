package Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

import DAO.*;
import Models.*;

public class DatabaseController {
	
	private TrattaDAO trattaDao;
	private PrenotazioneDAO prenotazioneDao;
	private CentoKilometriDAO centoKilometriDao;
	private CompagniaAereaDAO compagniaAereaDao;
	private GateDAO gateDao;
	private CodaDAO codaDao;
	
	public DatabaseController() {
		trattaDao = new TrattaDAO();
		prenotazioneDao = new PrenotazioneDAO();
		centoKilometriDao = new CentoKilometriDAO();
		compagniaAereaDao = new CompagniaAereaDAO();
		gateDao = new GateDAO();
		codaDao = new CodaDAO();
	}
	
	//Get
    public LinkedList<Tratta> getTrattaInfoFromGate(String nomeGate) {
    	LinkedList<Tratta> tratte = trattaDao.findTrattaByGate(nomeGate);
    	return tratte;
    }
    
    public LinkedList<Prenotazione> getPrenotatiFromTratta(int trattaId) {
    	LinkedList<Prenotazione> prenotazioni = prenotazioneDao.findByTrattaId(trattaId);
    	return prenotazioni;
    }
    
    public LinkedList<Tratta> getTratteFromCompagnia(String nomeCompagnia) {
    	LinkedList<Tratta> tratte = trattaDao.findTrattaByCompagnia(nomeCompagnia);
    	return tratte;
    }
    
    public LinkedList<Coda> getCodaByIdTratta(int idTratta) {
    	LinkedList<Coda> code = codaDao.findCodaByIdTratta(idTratta);
    	return code;
    }
    
    public LinkedList<Prenotazione> getPrenotazioni() {
    	return prenotazioneDao.find();
    }
    
    public LinkedList<Tratta> getTratte() {
    	return trattaDao.find();
    }
    
	public LinkedList<CentoKilometri> getCentoKilometri() {
    	return centoKilometriDao.find();
	}

    public LinkedList<CompagniaAerea> getCompagnieAeree() {
    	return compagniaAereaDao.find();
    }
    
    public LinkedList<Gate> getGates() {
    	return gateDao.find();
    }
    
	public void getClientiCK(ArrayList<String> clientiCKList, int idCentoKilometri) {
		LinkedList<Prenotazione> prenotazioniList = prenotazioneDao.findByCentoKilometriId(idCentoKilometri);
		clientiCKList.add(prenotazioniList.getFirst().getNomePasseggero());
		clientiCKList.add(prenotazioniList.getFirst().getCognomePasseggero());
	}
    
	//Save
	public boolean saveNuovaPrenotazione(String nome, String cognome, String codicePrenotazione, String centoKilometri, String compagniaCentoKilometri, String compagniaVolo, int idTratta, String coda) {
		Prenotazione prenotazione;
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
		for (Coda coda : code) { 
			coda.setIdTratta(idTratta);
			if (!codaDao.store(coda))
				return false;
	    }
		return true;
	}
    
    public boolean saveNuovoCentoKilometri(String codice, String nomeCompagnia, String punti) {
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
    	CompagniaAerea compagniaAerea = new CompagniaAerea(nomeCompagnia);
    	if (compagniaAereaDao.store(compagniaAerea))
    		return true;
    	return false;
    }
    
    public boolean saveNuovoGate(String nomeGate) {
    	Gate gate = new Gate(nomeGate);
    	if (gateDao.store(gate))
    		return true;
    	return false;
    }

    //Delete
    public boolean deleteTratta(int idTratta) {
		if (trattaDao.delete(idTratta))
			return true;
		return false;
	}
    
	public boolean deletePrenotazione(String idPrenotazione) {
		if (prenotazioneDao.delete(idPrenotazione))
			return true;
		return false;
	}
	
	public boolean deleteCentoKilometri(int idCentoKilometri) {
		if (centoKilometriDao.delete(idCentoKilometri))
			return true;
		return false;
	}
	
	public boolean deleteCompagniaAerea(String nomeCompagnia) {
		if (compagniaAereaDao.delete(nomeCompagnia))
			return true;
		return false;
	}
	
	public boolean deleteGate(String nomeGate) {
		if (gateDao.delete(nomeGate))
			return true;
		return false;
	}

	//Update
    public boolean updateImbarcatoInDatabase(boolean value, String id) {
    	if (prenotazioneDao.updateImbarcato(value, id))
    		return true;
    	return false;
    }
    
	public boolean updateInizioImbarco(Tratta tratta) {
		if (trattaDao.updateInizioImbarco(tratta))
			return true;
		return false;
	}
	
	public boolean updateCodaInizioImbarco(Coda coda) {
		if (codaDao.updateInizioImbarco(coda))
			return true;
		return false;
	}
	
	public boolean updateCodaFineImbarco(Coda coda) {
		if (codaDao.updateFineImbarco(coda))
			return true;
		return false;
	}
	
	public boolean updateFineImbarco(Tratta tratta) {
		if (trattaDao.updateFineImbarco(tratta))
			return true;
		return false;
	} 
    
}
