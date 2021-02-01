package Controllers;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import Views.*;

public class ViewsController {
		
	/*
	 * Class cls = Class.forName(path of the class);
	 * "Views/HomeView"
	 * frameClass frame = (frameClass) cls.newInstance(); */
	
	//static Map<String, JFrame> frames = new HashMap<>();
	HomeView homeFrame = null;
	LoginView loginFrame = null;
	static String user;
    
//    public static JFrame getFrame(String frameName) {
//    	if (frames.get(frameName) != null) {
//    		 return frames.get(frameName);
//    	}
//           
//    	return instantiateFrame (frameName);
//    }
//    
//    private static JFrame instantiateFrame (String frameName) {
//		try {
//			Class cls = Class.forName("Views/" + frameName);
//	    	JFrame frame = (JFrame) cls.newInstance();
//			frames.put(frameName, frame);
//			return frame;
//		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	return null;
//    	
//    }
//	
//    public static void view(String frameName){
//		getFrame(frameName).setVisible(true);
//    }
    
	
	
    public void viewLoginView() {
    	loginFrame = new LoginView();
    	loginFrame.setVisible(true);
    	
    }
    
    public void viewHomeView() {
    	homeFrame = new HomeView();
    	homeFrame.setVisible(true);
    }

	public static void setUser(String username) {
		user = username;
	}
	
	public static String getUser () {
		return user;
	}

	public void imbarcoView() {
		homeFrame.ImbarcoView();
		homeFrame.setVisible(true);
	}
	
	public void aggiungiView() {
		homeFrame.AggiungiView();
		homeFrame.setVisible(true);
	}
	
	public void cercaView() {
		homeFrame.CercaView();
		homeFrame.setVisible(true);
	}
	
	public void eliminaView() {
		homeFrame.EliminaView();
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
    	//getFrame().setVisible(false);
    	//LoginController loginController = new LoginController();
		//loginController.view();
    }
    
}
