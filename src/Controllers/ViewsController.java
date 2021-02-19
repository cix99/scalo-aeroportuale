package Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	LinkedList<Utente> listaUtenti;
	private boolean logedIn = false;
	
	LinkedList<Prenotazione> prenotati;
	LinkedList<Coda> codeVolo;
	
	LinkedList<Tratta> tratte;
	LinkedList<Prenotazione> prenotazioni;
	LinkedList<CentoKilometri> centoKilometri;
	LinkedList<CompagniaAerea> compagnie;
	LinkedList<Gate> gates;
	
	
	private DatabaseController dbController = new DatabaseController();
	
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
		//TODO: Problema: se viene effettuato il logout e si inseriscono credenziali sbagliate non appare più il JDialog
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
    	homeFrame.setVisible(false);
    	loginFrame.setVisible(true);
    }
    
	public void imbarcoView() {
		subFrame = new ImbarcoView (this);
		subFrame.setVisible(true);
		subFrame.setLocationRelativeTo(null);
		homeFrame.setVisible(false);
	}
	
	public void loadImbarcoCenterPanel(String nomeGate) { 
														
		((ImbarcoView) subFrame).emptyCenterPanel();
		tratte = dbController.getTrattaInfoFromGate(nomeGate);
		if (tratte.isEmpty() == false) {
			((ImbarcoView) subFrame).showTrattaInfoView(tratte.getFirst(), this); //(tratta)
			if (dbController.getPrenotatiFromTratta(tratte.getFirst().getId()) != null) {
				prenotati = dbController.getPrenotatiFromTratta(tratte.getFirst().getId());
				if (prenotati.isEmpty() == false) {
					//listCodaPriority = getCodaPrioritiesForTratta(tratte.getFirst().getId());  [return i valori int delle code presenti per la tratta]
					((ImbarcoView) subFrame).showListaPrenotati(this, prenotati, 5);  
				}
				else {
					JOptionPane.showMessageDialog(subFrame, "Non ci sono prenotazioni per questo volo", "Nessuna prenotazione", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(subFrame, "Non ci sono tratte per questo gate", "Nessuna tratta trovata", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void loadImbarcoCenterPanelCoda(String nomeCoda, int idTratta) { 
		
		//((ImbarcoView) subFrame).emptyCenterPanel();
		
		((ImbarcoView) subFrame).emptyTable();
		codeVolo = dbController.getCodaByIdTratta(idTratta);
		
		if (tratte.isEmpty() == false) {
			//((ImbarcoView) subFrame).showTrattaInfoView(tratte.getFirst(), this); //(tratta)
			//if (dbController.getPrenotatiFromTratta(tratte.getFirst().getId()) != null) {
				//prenotati = dbController.getPrenotatiFromTratta(tratte.getFirst().getId());
				if (prenotati.isEmpty() == false) {
					//listCodaPriority = getCodaPrioritiesForTratta(tratte.getFirst().getId());  [return i valori int delle code presenti per la tratta]
					LinkedList<Prenotazione> prenotatiCoda = new LinkedList<Prenotazione>();
					
					ListIterator<Prenotazione> cursor = prenotati.listIterator();
					while (cursor.hasNext()) {
						Prenotazione current = (Prenotazione) cursor.next();
						if (current.getCoda().getNomeCoda().equals(nomeCoda)) {
							prenotatiCoda.add(current);
						}
					}
					if (prenotatiCoda.isEmpty() == false)
						((ImbarcoView) subFrame).showListaPrenotati(this, prenotatiCoda, prenotatiCoda.getFirst().getCoda().getPriority());
					else
						JOptionPane.showMessageDialog(subFrame, "Non ci sono prenotazioni per questa coda", "Nessuno in coda", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(subFrame, "Non ci sono prenotazioni per questo volo", "Nessuna prenotazione", JOptionPane.ERROR_MESSAGE);
				}
			}
		//}
		else {
			JOptionPane.showMessageDialog(subFrame, "Non ci sono tratte per questo gate", "Nessuna tratta trovata", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String[] getCodaFromIdTratta(int idTratta) {
		LinkedList<Coda> list = dbController.getCodaByIdTratta(idTratta);
		
		String [] stringArray = new String[list.size()];
		ListIterator<Coda> cursor = list.listIterator();
		int i = 0;
		while (cursor.hasNext()) {
			Coda current = cursor.next();
			stringArray[i] = current.getNomeCoda();
			i++;
		}
		return stringArray;
	}
	
	public void aggiungiView() {
		subFrame = new AggiungiView(this);
		subFrame.setVisible(true);
		subFrame.setLocationRelativeTo(null);
		homeFrame.setVisible(false);
	}
	
	public void loadAggiungiCenterPanel(JPanel panel) { 
		
		//((AggiungiView) subFrame).emptyCenterPanel();
		
//		((ImbarcoView) subFrame).emptyCenterPanel();
//		tratte = dbController.getTrattaInfoFromGate(nomeGate);
//		if (tratte.isEmpty() == false) {
//			((ImbarcoView) subFrame).showTrattaInfoView(tratte.getFirst(), this); //(tratta)
//			if (dbController.getPrenotatiFromTratta(tratte.getFirst().getId()) != null) {
//				prenotati = dbController.getPrenotatiFromTratta(tratte.getFirst().getId());
//				if (prenotati.isEmpty() == false) {
//					//listCodaPriority = getCodaPrioritiesForTratta(tratte.getFirst().getId());  [return i valori int delle code presenti per la tratta]
//					((ImbarcoView) subFrame).showListaPrenotati(this, prenotati, 5);  
//				}
//				else {
//					JOptionPane.showMessageDialog(subFrame, "Non ci sono prenotazioni per questo volo", "Nessuna prenotazione", JOptionPane.ERROR_MESSAGE);
//				}
//			}
//		}
//		else {
//			JOptionPane.showMessageDialog(subFrame, "Non ci sono tratte per questo gate", "Nessuna tratta trovata", JOptionPane.ERROR_MESSAGE);
//		}
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
	
	public void salvaNuovaPrenotazione(String nome, String cognome, String codicePrenotazione, String centoKilometri, String compagniaCentoKilometri, String compagniaVolo, int idTratta, String coda) {
		if (dbController.salvaNuovaPrenotazione(nome, cognome, codicePrenotazione, centoKilometri, compagniaCentoKilometri, compagniaVolo, idTratta, coda)) {
			JOptionPane.showMessageDialog(subFrame, "Prenotazione (" + codicePrenotazione + ") inserita con successo!", "Inserimento riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
		}
		else {
			JOptionPane.showMessageDialog(subFrame, "L\'operazione non è andata a buon fine", "Errore inserimento prenotazione", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void salvaNuovaTratta(String destinazione, String nomeCompagnia, LocalDateTime dataInizio, LocalDateTime dataFine, ArrayList<Coda> code) {
		//check che datafine sia posteriore a datainizio
		if (dataFine.isBefore(dataInizio)) {
			JOptionPane.showMessageDialog(subFrame, "La data di fine imbarco non può essere prima di quella di inizio", "Errore data", JOptionPane.ERROR_MESSAGE);
		}
		else {
			if (dbController.salvaNuovaTratta(destinazione, nomeCompagnia, dataInizio, dataFine, code)) {
				JOptionPane.showMessageDialog(subFrame, "Tratta per (" + destinazione + ") inserita con successo!", "Inserimento riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
			}
			else {
				JOptionPane.showMessageDialog(subFrame, "L\'operazione non è andata a buon fine", "Errore inserimento tratta", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	public void salvaNuovoCentoKilometri(String codice, String nomeCompagnia, String punti) {
		if (dbController.salvaNuovoCentoKilometri(codice, nomeCompagnia, punti)) {
			JOptionPane.showMessageDialog(subFrame, "Cliente (" + codice + ") cento kilometri inserito con successo!", "Inserimento riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
		}
		else {
			JOptionPane.showMessageDialog(subFrame, "L\'operazione non è andata a buon fine", "Errore inserimento cento kilometri", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void salvaNuovaCompagniaAerea(String nomeCompagnia) {
		if (dbController.salvaNuovoCompagniaAerea(nomeCompagnia)) {
			JOptionPane.showMessageDialog(subFrame, "Compagnia " + nomeCompagnia + " inserita con successo!", "Inserimento riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
		}
		else {
			JOptionPane.showMessageDialog(subFrame, "L\'operazione non è andata a buon fine", "Errore inserimento compagnia", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void salvaNuovoGate(String nomeGate) {
		if (dbController.salvaNuovoGate(nomeGate)) {
			JOptionPane.showMessageDialog(subFrame, "Gate " + nomeGate + " inserito con successo!", "Inserimento riuscito", JOptionPane.INFORMATION_MESSAGE, new ImageIcon (this.getClass().getResource("/checkmark.png")));
		}
		else {
			JOptionPane.showMessageDialog(subFrame, "L\'operazione non è andata a buon fine", "Errore inserimento gate", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public LinkedList<Tratta> getTratteFromCompagnie(String nomeCompagnia) {
		LinkedList<Tratta> trattaList = new LinkedList<Tratta>();
		trattaList = dbController.getTratteFromCompagnia(nomeCompagnia);
		return trattaList;
	}
	
	public boolean checkCode(ArrayList<Coda> codeList) {
		for (int i = 0; i < codeList.size(); i++) {
			for (int j = 0; j < codeList.size(); j++) {
				if (j != i) {
					if (codeList.get(j).getNomeCoda().equals(codeList.get(i).getNomeCoda())) {
						JOptionPane.showMessageDialog(subFrame, "Non possono esserci code con lo stesso nome (" + codeList.get(i).getNomeCoda() +")", "Errore inserimento code", JOptionPane.ERROR_MESSAGE);
						return false;
					}	
					else if (codeList.get(j).getPriority() == codeList.get(i).getPriority()) {
						JOptionPane.showMessageDialog(subFrame, "Non possono esserci code con la stessa priorità (" + codeList.get(i).getPriority() +")", "Errore inserimento code", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public LocalDateTime convertiIntToDate(int anno, int mese, int giorno, int ora, int minuti) {
		LocalDateTime time = LocalDateTime.of(anno, mese, giorno, ora, minuti);
		return time;
	}
	
	public void cercaView() {
		subFrame = new CercaView(this);
		subFrame.setVisible(true);
		subFrame.setLocationRelativeTo(null);
		homeFrame.setVisible(false);
	}
	
	public void loadCercaCenterPanel(String scelta) {
		((CercaView) subFrame).emptyCenterPanel();
			
		switch (scelta) {
			case "Tratte":
			{
				tratte = dbController.getTratte();
				if (tratte.isEmpty() == false) {
					//((CercaView) subFrame).showTrattaInfoView(tratte.getFirst(), this); //(tratta)
					if (dbController.getPrenotatiFromTratta(tratte.getFirst().getId()) != null) {
						prenotati = dbController.getPrenotatiFromTratta(tratte.getFirst().getId());
						if (prenotati.isEmpty() == false) {
							((CercaView) subFrame).showListaTratte(tratte, this);
						}
						else {
							JOptionPane.showMessageDialog(subFrame, "Non ci sono prenotazioni per questo volo", "Nessuna prenotazione", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(subFrame, "Non ci sono tratte per questo gate", "Nessuna tratta trovata", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
			case "Prenotazioni":
			{
				prenotazioni = dbController.getPrenotazioni();
				((CercaView) subFrame).showListaPrenotazioni(prenotazioni, this);
				break;
			}
			case "Cento Kilometri":
			{
				centoKilometri = dbController.getCentoKilometri();
				((CercaView) subFrame).showListaCentoKilometri(centoKilometri, this);
				break;
			}
			case "Compagnie":
			{
				compagnie = dbController.getCompagnieAeree();
				((CercaView) subFrame).showListaCompagnie(compagnie, this);
				break;
			}
			case "Gate":
			{
				gates = dbController.getGates();
				((CercaView) subFrame).showListaGates(gates, this);
				break;
			}
			default:
				break;
		}
		
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
    	//homeFrame.setLocationRelativeTo(null);
    }
	
	public void updateImbarcatoInDatabase(boolean value, String id) {
		dbController.updateImbarcatoInDatabase(value, id);
	}
	
}
