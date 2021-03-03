package Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

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
    
    public LinkedList<Tratta> getTratteFromCompagnia(String nomeCompagnia, Stato stato) {
    	LinkedList<Tratta> tratte = trattaDao.findTrattaByCompagnia(nomeCompagnia, stato);
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
	
	public LinkedList<CentoKilometri> getCentoKilometri(String nomeCompagnia) {
    	return centoKilometriDao.findByCompany(nomeCompagnia);
	}
	
	public CentoKilometri getCentoKilometri(String centoKilometri, String nomeCompagnia) {
		return centoKilometriDao.findByCodeAndCompany(centoKilometri, nomeCompagnia);
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
		prenotazione = new Prenotazione(idTratta, nome, cognome, codicePrenotazione, codaDao.findByNameAndTratta(coda, idTratta), centoKilometriDao.findByCodeAndCompany(centoKilometri, compagniaCentoKilometri), new CompagniaAerea(compagniaVolo));
		if (prenotazioneDao.store(prenotazione))
			return true;
		return false;
	}
	
	public boolean saveNuovaPrenotazione(String nome, String cognome, String codicePrenotazione, String compagniaVolo, int idTratta, String coda) {
		Prenotazione prenotazione;
		prenotazione = new Prenotazione(idTratta, nome, cognome, codicePrenotazione, codaDao.findByNameAndTratta(coda, idTratta), new CompagniaAerea(compagniaVolo));
		if (prenotazioneDao.storeWithoutCK(prenotazione))
			return true;
		return false;
	}

	public boolean saveNuovaTratta(String destinazione, String nomeCompagnia, String gate, LocalDateTime inizioImbarco, LocalDateTime fineImbarco, int maxPrenotazioni, ArrayList<Coda> code) {
		Tratta tratta = new Tratta(destinazione, new CompagniaAerea(nomeCompagnia), new Gate(gate), inizioImbarco, fineImbarco, maxPrenotazioni);
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
    
    public boolean saveNuovoCentoKilometri(String codice, String nomeCompagnia, String nome, String cognome, String punti) {
    	int puntiValore;
    	try {
    	   puntiValore = Integer.parseInt(punti);
    	} catch (NumberFormatException e) {
    	   puntiValore = 0;
    	}
    	CompagniaAerea compagniaAerea = new CompagniaAerea(nomeCompagnia);
    	CentoKilometri centoKilometri = new CentoKilometri(codice, compagniaAerea, nome, cognome, puntiValore);
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
    
	public boolean updateTratta(int idTratta, String gate, LocalDateTime dataInizio, LocalDateTime dataFine, String maxPrenotazione, ArrayList<Coda> nuoveCodeList, int numeroCodeUpdate) {
		int max;
    	try {
    	   max = Integer.parseInt(maxPrenotazione);
    	} catch (NumberFormatException e) {
    	   max = 0;
    	   return false;
    	}
		if (trattaDao.update(gate, dataInizio, dataFine, max, idTratta)) {
			if (updateCode(nuoveCodeList, idTratta, numeroCodeUpdate)) {
				for (int i = 0; i < numeroCodeUpdate; i++)
					nuoveCodeList.remove(0);
				if (!saveNuoveCode(nuoveCodeList, idTratta)) 
					return false;
			}
		}
		return true;
	}  
	
	public boolean updateCode(ArrayList<Coda> code, int idTratta, int numeroCodeUpdate) {
		for (int i = 0; i < numeroCodeUpdate; i ++) { 
			code.get(i).setIdTratta(idTratta);
			if (!codaDao.update(code.get(i)))
				return false;
	    }
		return true;
	}
    
	public boolean updatePrenotazione(String coda, int idTratta, String id) {
		int idCoda = codaDao.findByNameAndTratta(coda, idTratta).getId(); 
		if (prenotazioneDao.update(idCoda, id))
			return true;
		return false;
	} 
    
	public boolean updateCentoKilometri(String codice, String nomeCompagnia, String nome, String cognome, int punti, int id) {
		if (centoKilometriDao.update(codice, nomeCompagnia, nome, cognome, punti, id))
			return true;
		return false;
	}
	
    public boolean updateNomeCompagnia(String nomeCompagnia, String oldNomeCompagnia) {
    	if (compagniaAereaDao.updateNomeCompagnia(nomeCompagnia, oldNomeCompagnia))
    		return true;
    	return false;
    }
    
    public boolean updateNomeGate(String nomeGate, String oldNomeGate) {
    	if (gateDao.updateNomeGate(nomeGate, oldNomeGate))
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

	public boolean existCentoKilometri(String codice, String nomeCompagnia) {
		if (centoKilometriDao.exist(codice, nomeCompagnia))
			return true;
		return false;
	}

	public boolean checkCentoKilometri(CentoKilometri ck, String nome, String cognome) {
		if (centoKilometriDao.isCorrect(ck, nome, cognome))
			return true;
		return false;
	}

	public boolean isPrenotazionePossible(int idTratta) {
		if (prenotazioneDao.isPrenotazionePossible(idTratta))
			return true;
		return false;
	}

	public boolean isGateAvailable(String gate, LocalDateTime dataInizio, LocalDateTime dataFine) {
		LinkedList<Tratta> tratte1 = trattaDao.isGateAvailableAfter(gate, dataInizio);
		LinkedList<Tratta> tratte2 = trattaDao.isGateAvailableBefore(gate, dataInizio);
		if (tratte1.isEmpty() && tratte2.isEmpty())
			return true;
		else if (tratte1.isEmpty() && !tratte2.isEmpty()) {
			if (tratte2.getFirst().getOraInizioImbarcoStimato().isAfter(dataFine))
				return true;
			else
				return false;
		} else if (!tratte1.isEmpty() && tratte2.isEmpty()) {
			if (tratte1.getFirst().getOraFineImbarcoStimato().isBefore(dataInizio))
				return true;
			else
				return false;
		} else {
			if (tratte1.getFirst().getOraFineImbarcoStimato().isBefore(dataInizio)) {
				if (tratte2.getFirst().getOraInizioImbarcoStimato().isAfter(dataFine))
					return true;
			}
		}
		return false;
	}

	public Map<String, Map<String, Integer>> getStatistiche(){
		return trattaDao.statistiche();
	}

	public boolean updatePunti(int idTratta, String nomeCompagnia) {
		LinkedList<Prenotazione> prenotatiCk = prenotazioneDao.findForPunti(idTratta, nomeCompagnia);
		ListIterator<Prenotazione> cursor = prenotatiCk.listIterator();
		while (cursor.hasNext()) {
			Prenotazione current = cursor.next();
			CentoKilometri ck = centoKilometriDao.findById(current.getCentoKilometri().getId());
			if (!centoKilometriDao.updatePunti(ck)) {
				break;
			}
		}
		if (!cursor.hasNext())
			return true;
		return false;
	}
    
}
