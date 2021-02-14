package Controllers;

import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Models.*;
import Views.*;
import Views.AggiungiView.AggiungiView;
import Views.CercaView.CercaView;

public class ViewsController {

	private HomeView homeFrame = null;
	private LoginView loginFrame = null;
	private JFrame subFrame = null;
	
	private Utente utenti;
	LinkedList<Utente> listaUtenti;
	private boolean logedIn = false;
	
	LinkedList<Tratta> tratte;
	LinkedList<Prenotazione> prenotati;
	
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
		//TODO: Problema: se viene effettuato il logout e si inseriscono credenziali sbagliate non appare pi� il JDialog
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
					((ImbarcoView) subFrame).showListaPrenotati(prenotati, this);
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
	
	public void salvaNuovoGate(String nomeGate) {
	
		//Inserisci nuovo gate nel database
		if (true) {
			JOptionPane.showMessageDialog(subFrame, "L\'operazione non � andata a buon fine", "Errore gate", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void salvaNuovaCompagniaAerea(String nomeCompagnia) {
		
		//Inserisci nuovo gate nel database
		if (true) {
			JOptionPane.showMessageDialog(subFrame, "Impossibile aggiungere la compagnia", "Errore compagnia", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public LinkedList<Tratta> getTratteFromCompagnie(String nomeCompagnia) {
		LinkedList<Tratta> trattaList = new LinkedList<Tratta>();
		trattaList = dbController.getTratteFromCompagnia(nomeCompagnia);
		return trattaList;
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
    	//homeFrame.setLocationRelativeTo(null);
    }
	
	public void updateImbarcatoInDatabase(boolean value, String id) {
		dbController.updateImbarcatoInDatabase(value, id);
	}
	
}
