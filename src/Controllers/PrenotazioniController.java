package Controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Models.Tratta;
import Views.HomeView;

public class PrenotazioniController {
		
	static HomeView frame = null;
    
    public static HomeView getFrame() {
    	if (frame != null)
            return frame;
    	return instantiateFrame ();
    }
    
    private static HomeView instantiateFrame () {
    	frame = new HomeView();
    	return frame;
    }
	
    public static void view(){
		getFrame().setVisible(true);
    }
    
    public static void goToCreaTratta() {
    	
    }
}
