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
			((ImbarcoView) subFrame).showTrattaInfoView(tratte.getFirst()); //(tratta)
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
    	//homeFrame.setLocationRelativeTo(null);
    }
	
	public void updateImbarcatoInDatabase(boolean value, String id) {
		dbController.updateImbarcatoInDatabase(value, id);
	}
    
}
