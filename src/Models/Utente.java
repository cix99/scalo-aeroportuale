package Models;

import java.util.LinkedList;

public class Utente {

	private String utenteName;
	private String utentePassword;
	
	private LinkedList<Utente> listaUtenti;
	
	public Utente() {
		addUtente ("",""); //Only for test purposes
		addUtente ("admin","password");
		addUtente ("Federico","Napoli23");
		addUtente ("Ciro","Napoli24");
		addUtente ("Ferdinando","Napoli25");
	}
	
	public Utente (String username, String password) {
		this.utenteName = username;
		this.utentePassword = password;
	}
	
	public String getUtenteName () {
		return this.utenteName;
	}
	
	public String getUtentePassword() {
		return this.utentePassword;
	}
	
	public void addUtente (String utenteName, String utentePassword) {
		Utente utente = new Utente(utenteName,utentePassword);
		if (listaUtenti == null) {
			listaUtenti = new LinkedList<Utente>();
		}
			listaUtenti.add(utente);
	}
	
	public LinkedList<Utente> getListaUtenti () {
		return listaUtenti;
	}
}
