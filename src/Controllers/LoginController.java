package Controllers;

import Views.LoginView;

public class LoginController {
		
	static LoginView frame = null;
    
    public static LoginView getFrame() {
    	if (frame != null)
            return frame;
    	return instantiateFrame ();
    }
    
    private static LoginView instantiateFrame () {
    	frame = new LoginView();
    	return frame;
    }
	
    public static void view(){
		getFrame().setVisible(true);
    }
    
    public static void login(String username, char[] cs) {
    	String input = new String (cs);
    	String password = "password";
    	String password0 = "";
    	if((username.equals("admin") && input.equals(password)) || (username.equals("") && input.equals(password0))) {
    		getFrame().setVisible(false);
    		HomeController.view();
    	}
    }
    
    public static void logout(){
    	getFrame().setVisible(true);
    }
}
