package Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Models.*;
import Views.*;
import Views.AggiungiView.AggiungiView;
import Views.CercaView.CercaView;
import Views.HomeView.HomeView;
import Views.ImbarcoView.ImbarcoView;
import Views.StatisticheView.StatisticheView;

public class ViewsController {

	private HomeView homeFrame = null;
	private LoginView loginFrame = null;
	private JFrame subFrame = null;
	
	private Utente utenti;
	private LinkedList<Utente> listaUtenti;
	private boolean logedIn = false;
	
	private LinkedList<Prenotazione> prenotati;
	private LinkedList<Coda> code;
	
	private LinkedList<Tratta> tratte;
	private LinkedList<Prenotazione> prenotazioni;
	private LinkedList<CentoKilometri> centoKilometri;
	private LinkedList<CompagniaAerea> compagnie;
	private LinkedList<Gate> gates;
	
	
	private DatabaseController dbController = new DatabaseController();
	//Frames related
    public void openLoginView() {
    	loginFrame = new LoginView(this);
    	loginFrame.setVisible(true);
    	loginFrame.setLocationRelativeTo(null);
    	utenti = new Utente();
    	utenti.loadUtenti();
    }
    
    public void openHomeView(String username) {
    	homeFrame = new HomeView(this, username);
    	homeFrame.setVisible(true);
    	homeFrame.setLocationRelativeTo(null);
    	loginFrame.setVisible(false);
    }

    public void login(String username, char[] passwordChar) {
    	String password = new String (passwordChar); 	
		listaUtenti = utenti.getListaUtenti();
		ListIterator<Utente> cursor = listaUtenti.listIterator();
		while (cursor.hasNext()) {
			Utente current = cursor.next();
			if (current.getUtenteName().equals(username) && current.getUtentePassword().equals(password)) {
	    		openHomeView(username);
	    		logedIn = true;
	    		break;
			}
		}
		if (logedIn == false) {
			JOptionPane.showMessageDialog(loginFrame, "Username o password sbagliati", "Login error", JOptionPane.ERROR_MESSAGE);
			loginFrame.getUsername().setText("");
			loginFrame.getPassword().setText("");
		}
    }
	
	public void logout(){
		loginFrame.getUsername().setText("");
		loginFrame.getPassword().setText("");
		logedIn = false;
    	homeFrame.setVisible(false);
    	loginFrame.setVisible(true);
    }
    
	public void imbarcoView() {
		subFrame = new ImbarcoView (this);
		subFrame.setVisible(true);
		subFrame.setLocationRelativeTo(null);
		homeFrame.setVisible(false);
	}
	
	public void aggiungiView() {
		subFrame = new AggiungiView(this);
		subFrame.setVisible(true);
		subFrame.setLocationRelativeTo(null);
		homeFrame.setVisible(false);
	}
	
	public void cercaView() {
		subFrame = new CercaView(this);
		subFrame.setVisible(true);
		subFrame.setLocationRelativeTo(null);
		homeFrame.setVisible(false);
	}
	
	public void statisticheView() {
		subFrame = new StatisticheView (this);
		subFrame.setVisible(true);
		subFrame.setLocationRelativeTo(null);
		homeFrame.setVisible(false);
	}
	
	public void backToHomeView() {
		subFrame.setVisible(false);
    	homeFrame.setVisible(true);
    }
	
	//Get
	public String[] getGates() {
		gates = dbController.getGates();
		ListIterator<Gate> cursor = gates.listIterator();
		String[] gatesArray = new String[gates.size()];
		int index = 0;
		while (cursor.hasNext()) {
			Gate current = cursor.next();
			gatesArray[index] = current.getNomeGate();
			index++;
		}
		return gatesArray;
	}
	
	public LinkedList<Gate> getGatesList() {
		return gates = dbController.getGates();
	}
	
	public String[] getCompagnieAeree() {
		LinkedList<CompagniaAerea> list = dbController.getCompagnieAeree();
		String [] stringArray = new String[list.size()];
		ListIterator<CompagniaAerea> cursor = list.listIterator();
		int i = 0;
		while (cursor.hasNext()) {
			CompagniaAerea current = cursor.next();
			stringArray[i] = current.getNomeCompagnia();
			i++;
		}
		return stringArray;
	}
	
	public LinkedList<CompagniaAerea> getCompagnieList() {
		return compagnie = dbController.getCompagnieAeree();
	}
	
	public LinkedList<CentoKilometri> getCentoKilometri() {
		return centoKilometri = dbController.getCentoKilometri();
	}
	
	public LinkedList<CentoKilometri> getCentoKilometri(String nomeCompagnia) {
		return centoKilometri = dbController.getCentoKilometri(nomeCompagnia);
	}
	
	public LinkedList<Prenotazione> getPrenotazioni() {
		return prenotazioni = dbController.getPrenotazioni();
	}
	
	public LinkedList<Tratta> getTratte() {
		return tratte = dbController.getTratte();
	}
	
	public LinkedList<Coda> getCodaFromIdTratta(int idTratta) {
		code = new LinkedList<Coda>();
		code = dbController.getCodaByIdTratta(idTratta);
		return code;
	}
	
	public LinkedList<Tratta> getTratteFromCompagnia(String nomeCompagnia) {
		LinkedList<Tratta> trattaList = new LinkedList<Tratta>();
		trattaList = dbController.getTratteFromCompagnia(nomeCompagnia);
		return trattaList;
	}
	
	public LinkedList<Tratta> getTratteFromCompagnia(String nomeCompagnia, Stato stato) {
		LinkedList<Tratta> trattaList = new LinkedList<Tratta>();
		trattaList = dbController.getTratteFromCompagnia(nomeCompagnia, stato);
		return trattaList;
	}
	
	//Load
	public void showCode(Tratta tratta) {
		code = new LinkedList<Coda>();
		code = dbController.getCodaByIdTratta(tratta.getId());	
		LinkedList<Coda> codeList = new LinkedList<Coda>();
		ListIterator<Coda> cursor = code.listIterator();
		while (cursor.hasNext()) {
			Coda current = cursor.next();
			if (current.getFineImbarcoCoda() == null) {
				codeList.add(current);
			}
		}
		((ImbarcoView) subFrame).showCodeOptionsPanel(codeList);
	}
	
	public void loadTrattaInfo(String nomeGate) { 		
		tratte = dbController.getTrattaInfoFromGate(nomeGate);
		if (!tratte.isEmpty()) 
			((ImbarcoView) subFrame).showTrattaInfo(tratte.getFirst());
		else  {
			((ImbarcoView) subFrame).emptyCenterPanel();
			JOptionPane.showMessageDialog(subFrame, "Non ci sono tratte per questo gate", "Nessuna tratta trovata", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	public boolean loadPrenotatiImbarcoPerCoda(String nomeCoda) { 
		prenotati = dbController.getPrenotatiFromTratta(tratte.getFirst().getId());		
		if (!prenotati.isEmpty()) {
			if (nomeCoda.equals("Tutte")) {
				((ImbarcoView) subFrame).showPrenotatiPanel(prenotati);
			}
			else {
				updateInizioImbarcoCoda(nomeCoda);
				LinkedList<Prenotazione> prenotatiCoda = new LinkedList<Prenotazione>();
				ListIterator<Prenotazione> cursor = prenotati.listIterator();
				while (cursor.hasNext()) {
					Prenotazione current = cursor.next();
					if (current.getCoda().getNomeCoda().equals(nomeCoda)) {
						prenotatiCoda.add(current);
					}
				}
				if (!prenotatiCoda.isEmpty()) {
					((ImbarcoView) subFrame).showPrenotatiPanel(prenotatiCoda);
				}			
				else {
					((ImbarcoView) subFrame).emptyPrenotatiPanelTable();
					JOptionPane.showMessageDialog(subFrame, "Non ci sono prenotazioni per questa coda", "Nessuno in coda", JOptionPane.ERROR_MESSAGE);
				}
			}
			return true;
		}	
		else {
			JOptionPane.showMessageDialog(subFrame, "Non ci sono prenotazioni per questo volo", "Nessuna prenotazione", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public void loadCercaCenterPanel(String scelta) {
		((CercaView) subFrame).emptyCenterPanel();
		switch (scelta) {
			case "Tratte":
			{
				tratte = dbController.getTratte();
				if (!tratte.isEmpty()) {
					((CercaView) subFrame).showListaTratte(tratte);
				} else {
					JOptionPane.showMessageDialog(subFrame, "Non ci sono tratte salvate", "Nessuna tratta trovata", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case "Prenotazioni":
			{
				prenotazioni = dbController.getPrenotazioni();
				if (!prenotazioni.isEmpty()) {
					((CercaView) subFrame).showListaPrenotazioni(prenotazioni);
				} else {
					JOptionPane.showMessageDialog(subFrame, "Non ci sono prenotazioni salvate", "Nessuna prenotazione trovata", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case "Cento Kilometri":
			{
				centoKilometri = dbController.getCentoKilometri();
				if (!centoKilometri.isEmpty()) {
					((CercaView) subFrame).showListaCentoKilometri(centoKilometri);
				} else {
					JOptionPane.showMessageDialog(subFrame, "Non ci sono clienti cento kilometri salvati", "Nessun cento kilometri trovato", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case "Compagnie Aeree":
			{
				compagnie = dbController.getCompagnieAeree();
				if (!compagnie.isEmpty()) {
					((CercaView) subFrame).showListaCompagnie(compagnie);
				} else {
					JOptionPane.showMessageDialog(subFrame, "Non ci sono compagnie aeree salvate", "Nessuna compagnia trovata", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case "Gates":
			{
				gates = dbController.getGates();
				if (!gates.isEmpty()) {
					((CercaView) subFrame).showListaGates(gates);
				} else {
					JOptionPane.showMessageDialog(subFrame, "Non ci sono gates salvati", "Nessun gate trovato", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			default:
				break;
		}
	}

	//Save
	public void saveNuovaPrenotazione(String nome, String cognome, String codicePrenotazione, String centoKilometri, String compagniaCentoKilometri, String compagniaVolo, int idTratta, String coda) {
		if (nome.isBlank()) {
			JOptionPane.showMessageDialog(subFrame, "Il campo nome non deve essere vuoto", "Errore inserimento prenotazione", JOptionPane.ERROR_MESSAGE);
		} else if (cognome.isBlank()) {
			JOptionPane.showMessageDialog(subFrame, "Il campo cognome non deve essere vuoto", "Errore inserimento prenotazione", JOptionPane.ERROR_MESSAGE);
		} else if (codicePrenotazione.isBlank()) {
			JOptionPane.showMessageDialog(subFrame, "Il campo codice non deve essere vuoto", "Errore inserimento prenotazione", JOptionPane.ERROR_MESSAGE);
		} else if (codicePrenotazione.length() != 6) {
			JOptionPane.showMessageDialog(subFrame, "Il campo codice deve avere 6 caratteri", "Errore inserimento prenotazione", JOptionPane.ERROR_MESSAGE);
		} else if (dbController.isPrenotazionePossible(idTratta) == false) {
			JOptionPane.showMessageDialog(subFrame, "Raggiunto il limite di prenotazioni per questa tratta", "Errore inserimento prenotazione", JOptionPane.ERROR_MESSAGE);
		} else {
			if (!centoKilometri.isBlank()) {
				CentoKilometri ck = dbController.getCentoKilometri(centoKilometri, compagniaCentoKilometri);
				if (dbController.checkCentoKilometri(ck, nome, cognome)) {
					if (dbController.saveNuovaPrenotazione(nome, cognome, codicePrenotazione, centoKilometri, compagniaCentoKilometri, compagniaVolo, idTratta, coda)) {
						JOptionPane.showMessageDialog(subFrame, "Prenotazione (" + codicePrenotazione + ") inserita con successo!", "Inserimento riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
					}
					else {
						JOptionPane.showMessageDialog(subFrame, "Errore durante l\'inserimento della prenotazione", "Errore inserimento prenotazione", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(subFrame, "Il cento kilometri selezionato appartiene ad un altro cliente", "Errore Cento Kilometri", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				if (dbController.saveNuovaPrenotazione(nome, cognome, codicePrenotazione, compagniaVolo, idTratta, coda)) {
					JOptionPane.showMessageDialog(subFrame, "Prenotazione (" + codicePrenotazione + ") inserita con successo!", "Inserimento riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
				}
				else {
					JOptionPane.showMessageDialog(subFrame, "Errore durante l\'inserimento della prenotazione", "Errore inserimento prenotazione", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public void saveNuovaTratta(String destinazione, String nomeCompagnia, String gate, LocalDateTime dataInizio, LocalDateTime dataFine, String maxPrenotazioni, ArrayList<Coda> code) {
		if (destinazione.isBlank()) {
			JOptionPane.showMessageDialog(subFrame, "Destinazione non valida", "Errore destinazione", JOptionPane.ERROR_MESSAGE);
		} else if (dataFine.isBefore(dataInizio)) {
			JOptionPane.showMessageDialog(subFrame, "La data di fine imbarco deve essere dopo quella di inizio", "Errore data", JOptionPane.ERROR_MESSAGE);
		} else if (maxPrenotazioni.isBlank()) {
			JOptionPane.showMessageDialog(subFrame, "Inserire un numero per il limite di prenotazioni", "Errore max prenotazione", JOptionPane.ERROR_MESSAGE);
		} else if (code.isEmpty()) {
			JOptionPane.showMessageDialog(subFrame, "Non sono state inserite code per la tratta", "Errore code", JOptionPane.ERROR_MESSAGE);
		} else if (!dbController.isGateAvailable(gate, dataInizio, dataFine)) {
			JOptionPane.showMessageDialog(subFrame, "Gate non disponibile nell\'arco di tempo selezionato", "Errore gate", JOptionPane.ERROR_MESSAGE);
		} else if (dbController.saveNuovaTratta(destinazione, nomeCompagnia, gate, dataInizio, dataFine, Integer.parseInt(maxPrenotazioni), code)) {
			JOptionPane.showMessageDialog(subFrame, "Tratta per (" + destinazione + ") inserita con successo!", "Inserimento riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
		} else {
			JOptionPane.showMessageDialog(subFrame, "Errore durante l\'inserimento della tratta", "Errore inserimento tratta", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void saveNuovoCentoKilometri(String codice, String nomeCompagnia, String nome, String cognome, String punti) {
		if (codice.isBlank()) {
			JOptionPane.showMessageDialog(subFrame, "Il cento kilometri deve avere un codice", "Errore inserimento cento kilometri", JOptionPane.ERROR_MESSAGE);
		} else if (nome.isBlank()) {
			JOptionPane.showMessageDialog(subFrame, "Il campo nome non deve essere vuoto", "Errore inserimento cento kilometri", JOptionPane.ERROR_MESSAGE);
		} else if (cognome.isBlank()) {
			JOptionPane.showMessageDialog(subFrame, "Il campo cognome non deve essere vuoto", "Errore inserimento cento kilometri", JOptionPane.ERROR_MESSAGE);
		} else if (dbController.existCentoKilometri(codice, nomeCompagnia)){
			JOptionPane.showMessageDialog(subFrame, "Il cento kilometri inserito esiste gi� per la compagnia (" + nomeCompagnia + ")", "Errore inserimento cento kilometri", JOptionPane.ERROR_MESSAGE);
		} else if (dbController.saveNuovoCentoKilometri(codice, nomeCompagnia, nome, cognome, punti)) {
			JOptionPane.showMessageDialog(subFrame, "Cliente (" + codice + ") cento kilometri inserito con successo!", "Inserimento riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
		} else {
			JOptionPane.showMessageDialog(subFrame, "Errore durante l\'inserimento del cento kilometri", "Errore inserimento cento kilometri", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void saveNuovaCompagniaAerea(String nomeCompagnia) {
		if (nomeCompagnia.isBlank()) {
			JOptionPane.showMessageDialog(subFrame, "La compagnia deve avere un nome", "Errore inserimento compagnia", JOptionPane.ERROR_MESSAGE);
		} else if (dbController.saveNuovoCompagniaAerea(nomeCompagnia)) {
			JOptionPane.showMessageDialog(subFrame, "Compagnia " + nomeCompagnia + " inserita con successo!", "Inserimento riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
		} else {
			JOptionPane.showMessageDialog(subFrame, "La compagnia gi� presente", "Errore inserimento compagnia", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void saveNuovoGate(String nomeGate) {
		if (nomeGate.isBlank()) {
			JOptionPane.showMessageDialog(subFrame, "Il gate deve avere un nome", "Errore inserimento gate", JOptionPane.ERROR_MESSAGE);
		} else if (dbController.saveNuovoGate(nomeGate)) {
			JOptionPane.showMessageDialog(subFrame, "Gate " + nomeGate + " inserito con successo!", "Inserimento riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
		} else {
			JOptionPane.showMessageDialog(subFrame, "Errore durante l\'inserimento del gate", "Errore inserimento gate", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public boolean checkCode(ArrayList<Coda> codeArrayList) {
		for (int i = 0; i < codeArrayList.size(); i++) {
			for (int j = 0; j < codeArrayList.size(); j++) {
				if (j != i) {
					if (codeArrayList.get(j).getNomeCoda().equals(codeArrayList.get(i).getNomeCoda())) {
						JOptionPane.showMessageDialog(subFrame, "Non possono esserci code con lo stesso nome (" + codeArrayList.get(i).getNomeCoda() +")", "Errore inserimento code", JOptionPane.ERROR_MESSAGE);
						return false;
					}	
					else if (codeArrayList.get(j).getPriority() == codeArrayList.get(i).getPriority()) {
						JOptionPane.showMessageDialog(subFrame, "Non possono esserci code con la stessa priorit� (" + codeArrayList.get(i).getPriority() +")", "Errore inserimento code", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}
			}
		}
		return true;
	}
	
	//Delete
	public boolean deleteTratta (int idTratta) {
		if (dbController.deleteTratta(idTratta)) {
			JOptionPane.showMessageDialog(subFrame, "Tratta eliminata con successo!", "Cancellazione riuscita", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
			return true;
		}
		else {
			JOptionPane.showMessageDialog(subFrame, "Cancellazione fallita", "Errore durante cancellazione", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public boolean deletePrenotazione(String idPrenotazione) {
		if (dbController.deletePrenotazione(idPrenotazione)) {
			JOptionPane.showMessageDialog(subFrame, "Prenotazione eliminata con successo!", "Cancellazione riuscita", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
			return true;
		}
		else {
			JOptionPane.showMessageDialog(subFrame, "Cancellazione fallita", "Errore durante cancellazione", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public boolean deleteCentoKilometri(int idCentoKilometri) {
		if (dbController.deleteCentoKilometri(idCentoKilometri)) {
			JOptionPane.showMessageDialog(subFrame, "Cento kilometri eliminato con successo!", "Cancellazione riuscita", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
			return true;
		}
		else {
			JOptionPane.showMessageDialog(subFrame, "Cancellazione fallita", "Errore durante cancellazione", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public boolean deleteCompagniaAerea(String nomeCompagnia) {
		if (dbController.deleteCompagniaAerea(nomeCompagnia)) {
			JOptionPane.showMessageDialog(subFrame, "Compagnia aerea eliminata con successo!", "Cancellazione riuscita", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
			return true;
		}
		else {
			JOptionPane.showMessageDialog(subFrame, "Cancellazione fallita", "Errore durante cancellazione", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public boolean deleteGate(String nomeGate) {
		if (dbController.deleteGate(nomeGate)) {
			JOptionPane.showMessageDialog(subFrame, "Gate eliminato con successo!", "Cancellazione riuscita", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
			return true;
		}
		else {
			JOptionPane.showMessageDialog(subFrame, "Cancellazione fallita", "Errore durante cancellazione", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	//Update
	public void updateImbarcatoInDatabase(boolean value, String id) {
		if (!dbController.updateImbarcatoInDatabase(value, id)) {
			JOptionPane.showMessageDialog(subFrame, "Aggiornamento imbarcato non riuscito", "Errore update", JOptionPane.ERROR_MESSAGE);
		}
	}

	public boolean updateTratta(int idTratta, String gate, LocalDateTime dataInizio, LocalDateTime dataFine, String maxPrenotazione, ArrayList<Coda> nuoveCodeList, int numeroCodeUpdate) {
		if (dataFine.isBefore(dataInizio)) {
			JOptionPane.showMessageDialog(subFrame, "La data di fine imbarco deve essere dopo quella di inizio", "Errore data", JOptionPane.ERROR_MESSAGE);
		} else if (maxPrenotazione.isBlank()) {
			JOptionPane.showMessageDialog(subFrame, "Inserire un numero per il limite di prenotazioni", "Errore max prenotazione", JOptionPane.ERROR_MESSAGE);
		} else if (maxPrenotazione.equals("0") || maxPrenotazione.equals("00") || maxPrenotazione.equals("000")) {
			JOptionPane.showMessageDialog(subFrame, "Il limite di prenotazioni deve essere maggiore di 0", "Errore max prenotazione", JOptionPane.ERROR_MESSAGE);
		} else if (!dbController.isGateAvailable(gate, dataInizio, dataFine)) {
			JOptionPane.showMessageDialog(subFrame, "Gate non disponibile nell\'arco di tempo selezionato", "Errore gate", JOptionPane.ERROR_MESSAGE);
		} else if (!dbController.updateTratta(idTratta, gate, dataInizio, dataFine, maxPrenotazione, nuoveCodeList, numeroCodeUpdate)) {
			JOptionPane.showMessageDialog(subFrame, "Aggiornamento tratta non riuscito", "Errore update", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(subFrame, "Tratta aggiornata con successo!", "Update riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
			return true;
		}
		return false;
	}
	
	public boolean updatePrenotazione(String coda, int idTratta, String id) {
		if (!dbController.updatePrenotazione(coda, idTratta, id)) {
			JOptionPane.showMessageDialog(subFrame, "Aggiornamento prenotazione non riuscito", "Errore update", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(subFrame, "Prenotazione aggiornata con successo!", "Update riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
			return true;
		}
		return false;
	}
	
	public boolean updateCentoKilometri (String codice, String nomeCompagnia, String nome, String cognome, int punti, int id) {
		if (codice.isBlank()) {
			JOptionPane.showMessageDialog(subFrame, "Il cento kilometri deve avere un codice", "Errore update", JOptionPane.ERROR_MESSAGE);
		} else if (!dbController.updateCentoKilometri(codice, nomeCompagnia, nome, cognome, punti, id)) {
			JOptionPane.showMessageDialog(subFrame, "Aggiornamento cento kilometri non riuscito", "Errore update", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(subFrame, "Cento kilometri aggiornato con successo!", "Update riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
			return true;
		}
		return false;
	}
	
	public boolean updateNomeCompagnia (String nomeCompagnia, String oldNomeCompagnia) {
		if (nomeCompagnia.isBlank()) {
			JOptionPane.showMessageDialog(subFrame, "La compagnia deve avere un nome", "Errore update", JOptionPane.ERROR_MESSAGE);
		} else if (!dbController.updateNomeCompagnia(nomeCompagnia, oldNomeCompagnia)) {
			JOptionPane.showMessageDialog(subFrame, "Aggiornamento compagnia non riuscito", "Errore update", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(subFrame, "Compagnia aerea aggiornata con successo!", "Update riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
			return true;
		}
		return false;
	}
	
	public boolean updateNomeGate (String nomeGate, String oldNomeGate) {
		if (nomeGate.isBlank()) {
			JOptionPane.showMessageDialog(subFrame, "Il gate deve avere un nome", "Errore update", JOptionPane.ERROR_MESSAGE);
		} else if (!dbController.updateNomeGate(nomeGate, oldNomeGate)) {
			JOptionPane.showMessageDialog(subFrame, "Aggiornamento gate non riuscito", "Errore update", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(subFrame, "Gate aggiornato con successo!", "Update riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
			return true;
		}
		return false;
	}
	
	public void updateInizioImbarco() {
		Tratta tratta = tratte.getFirst();
		if (tratta.getStatoImbarco() == Stato.IN_ATTESA) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime time = convertiIntToDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute());
			tratta.setOraInizioImbarcoEffettivo(time);
			tratta.setStatoImbarco(Stato.IN_CORSO);
			if (!dbController.updateInizioImbarco(tratta)) {
				JOptionPane.showMessageDialog(subFrame, "Aggiornamento orario inizio imbarco non riuscito", "Errore update", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	

	public void updateInizioImbarcoCoda(String nomeCoda) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime time = convertiIntToDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute());
		ListIterator<Coda> cursor = code.listIterator();
		if (cursor.hasNext()) {
			Coda current = cursor.next();
			while (!current.getNomeCoda().equals(nomeCoda)) {
				current = cursor.next();
			}
			current.setInizioImbarcoCoda(time);
			if (!dbController.updateCodaInizioImbarco(current)) {
				JOptionPane.showMessageDialog(subFrame, "Aggiornamento orario inizio imbarco per coda non riuscito", "Errore update", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	public void updateFineImbarcoCoda(String nomeCoda) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime time = convertiIntToDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute());
		ListIterator<Coda> cursor = code.listIterator();
		if (cursor.hasNext()) {
			Coda current = cursor.next();
			while (!current.getNomeCoda().equals(nomeCoda)) {
				current = cursor.next();
			}
			if (current.getFineImbarcoCoda() == null) {
				current.setFineImbarcoCoda(time);
				if (!dbController.updateCodaFineImbarco(current)) {
					JOptionPane.showMessageDialog(subFrame, "Aggiornamento orario fine imbarco per coda non riuscito", "Errore update", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(subFrame, "La coda " + nomeCoda + " risulta gi� chiusa", "Errore update", JOptionPane.ERROR_MESSAGE);
			}	
		}
		showCode(tratte.getFirst());
		((ImbarcoView) subFrame).showPrenotatiPanel(prenotati);
	}
	
	
	public boolean updateFineImbarco() {
		Tratta tratta = tratte.getFirst();
		if (tratta.getStatoImbarco() == Stato.IN_CORSO) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime time = convertiIntToDate(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute());
			tratta.setOraFineImbarcoEffettivo(time);
			tratta.setStatoImbarco(Stato.CONCLUSO);
			if (!dbController.updateFineImbarco(tratta)) {
				JOptionPane.showMessageDialog(subFrame, "Aggiornamento orario fine imbarco non riuscito", "Errore update", JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				if (tratta.getOraFineImbarcoEffettivo().isAfter(tratta.getOraFineImbarcoStimato())) {
					if (!dbController.updatePunti(tratta.getId(), tratta.getCompagniaAerea().getNomeCompagnia())) {
						JOptionPane.showMessageDialog(subFrame, "Imbarco chiuso ma punti non aggiornati", "Errore update punti", JOptionPane.ERROR_MESSAGE);
						return false;
					} else {
						JOptionPane.showMessageDialog(subFrame, "Imbarco chiuso con successo", "Chiuso Imbarco", JOptionPane.INFORMATION_MESSAGE);
					}				
				} else {
					JOptionPane.showMessageDialog(subFrame, "Imbarco chiuso con successo", "Chiuso Imbarco", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		return true;
	}
	
	//Stats
	public Map<String, Map<String, Integer>> getStatistiche(){
    	return dbController.getStatistiche();
	}
	
	//Other
	public LocalDateTime convertiIntToDate(int anno, int mese, int giorno, int ora, int minuti) {
		LocalDateTime time = LocalDateTime.of(anno, mese, giorno, ora, minuti);
		return time;
	}	
}
