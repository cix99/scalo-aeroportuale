package Controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JPanel;

import Models.Tratta;
import Views.HomeView;


public class HomeController {
		
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
    
//    public static JPanel openCreaTratta() {
//    	//return TratteController.viewCreate();
//    	/*getFrame().setVisible(false);
//    	TratteController.view();*/
//    }
}
