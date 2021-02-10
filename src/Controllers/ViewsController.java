package Controllers;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Models.Prenotazione;
import Models.Tratta;
import Views.*;

public class ViewsController {

	HomeView homeFrame = null;
	LoginView loginFrame = null;
	JFrame newFrame = null;
	
	DatabaseController dbController = new DatabaseController();
	
	Utente utenti;
	private boolean logedIn = false;
	
    public void viewLoginView() {
    	loginFrame = new LoginView(this);
    	loginFrame.setVisible(true);
    	loginFrame.setLocationRelativeTo(null);
    	utenti = new Utente();
		
    }
    
    public void viewHomeView(String username) {
    	homeFrame = new HomeView(this, username);
    	homeFrame.setVisible(true);
    	homeFrame.setLocationRelativeTo(null);
    }

	public void imbarcoView() {
		newFrame = new ImbarcoView (this);
		newFrame.setVisible(true);
		newFrame.setLocationRelativeTo(null);
		homeFrame.setVisible(false);
	}
	
	public void loadImbarcoCenterPanel(JPanel mainPanel, String nomeGate) {
		//Cerca le info usando il database controller e salva tutto in una variabile di tipo Tratta
		LinkedList<Tratta> tratte = dbController.getTrattaInfoFromGate(nomeGate);
		LinkedList<Prenotazione> prenotati = dbController.getPrenotatiFromTratta(tratte.getFirst().getId());
		//Una volta ottenuta la tratta, chiama una funzione che crea un nuovo panel in ImbarcoView
		((ImbarcoView) newFrame).showTrattaInfoView(tratte.getFirst()); //(tratta)
		((ImbarcoView) newFrame).showListaPrenotati(prenotati);
	}
	
	public void aggiungiView() {
		newFrame = new AggiungiView ();
		newFrame.setVisible(true);
		homeFrame.setVisible(false);
	}
	
	public void cercaView() {
		newFrame = new CercaView ();
		newFrame.setVisible(true);
		homeFrame.setVisible(false);
	}
	
	public void statisticheView() {
		newFrame = new StatisticheView ();
		newFrame.setVisible(true);
		homeFrame.setVisible(false);
	}
	
	public void backToHomeView() {
		newFrame.setVisible(false);
    	homeFrame.setVisible(true);
    	//homeFrame.setLocationRelativeTo(null);
    }
	
	public void login(String username, char[] cs) {
    	String input = new String (cs);
    	
		LinkedList<Utente> listaUtenti = utenti.getListaUtenti();
		ListIterator<Utente> cursor = listaUtenti.listIterator();
		while (cursor.hasNext()) {
			Utente current = cursor.next();
			if (current.getUtenteName().equals(username) && current.getUtentePassword().equals(input)) {
				loginFrame.setVisible(false);
	    		viewHomeView(username);
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
    
}
