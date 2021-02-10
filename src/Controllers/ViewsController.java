package Controllers;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import Views.*;
import Views.AggiungiView.AggiungiView;

public class ViewsController {
	
	static HomeView homeFrame = null;
	static LoginView loginFrame = null;
	static AggiungiView aggiungiFrame = null;
	static String user;
	
	
	
    public static void viewLoginView() {
    	loginFrame = new LoginView();
    	loginFrame.setVisible(true);
    	
    }
    
    public static void viewHomeView() {
    	homeFrame = new HomeView();
    	homeFrame.setVisible(true);
    }
    
    public static void viewAggiungiView() {
    	aggiungiFrame = new AggiungiView();
    	aggiungiFrame.setVisible(true);
    	homeFrame = new HomeView();
    	homeFrame.setVisible(false);
    }

	public static void setUser(String username) {
		user = username;
	}
	
	public static String getUser () {
		return user;
	}

	public void imbarcoView() {
		homeFrame.createImbarcoView();
		homeFrame.setVisible(true);
	}
	
	public void aggiungiView() {
		homeFrame.createAggiungiView();
		homeFrame.setVisible(true);
	}
	
	public void cercaView() {
		homeFrame.createCercaView();
		homeFrame.setVisible(true);
	}
	
	public void eliminaView() {
		homeFrame.createEliminaView();
		homeFrame.setVisible(true);
	}
	
	public void login(String username, char[] cs) {
    	String input = new String (cs);
    	String password = "password";
    	String password0 = "";
    	if((username.equals("admin") && input.equals(password)) || (username.equals("") && input.equals(password0))) {
    		loginFrame.setVisible(false);
    		setUser(username);
    		viewHomeView();
    	}
    }
	
	public void logout(){

		loginFrame.getUsername().setText("");
		loginFrame.getPassword().setText("");
    	homeFrame.setVisible(false);
    	loginFrame.setVisible(true);
    }

//	public void nuovaTrattaView() {
//	}
//    
//	public void nuovaCompagniaView() {
//		
//		
//	}
//	
//	public void nuovoGateView() {
//		
//	}
//	
//	public void nuovoCKView() {
//		
//	}
//	
//	public void nuovaPrenotazioneView() {
//		
//	}
}
