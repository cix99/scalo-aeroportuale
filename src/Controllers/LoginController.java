package Controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Models.Tratta;
import Views.LoginFrame;

public class LoginController {
		
	static LoginFrame frame = null;
    
    public static LoginFrame getFrame() {
    	if (frame != null)
            return frame;
    	return instantiateFrame ();
    }
    
    private static LoginFrame instantiateFrame () {
    	frame = new LoginFrame();
    	return frame;
    }
	
    public static void view(){
		getFrame().setVisible(true);
    }
    
    public static void login() {
        getFrame().setVisible(false);
    }
}
