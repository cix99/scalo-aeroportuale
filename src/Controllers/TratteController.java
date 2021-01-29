package Controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Models.Tratta;
import Views.TratteView;
import Views.CreateTrattaView;

public class TratteController {
		
	/*static TratteView indexPanel = null;
	static CreaTratteView createPanel = null;
    
    public static TratteView getPanel() {
    	if (indexPanel != null)
            return indexPanel;
    	return instantiatePanel ();
    }
    
    private static TratteView instantiatePanel () {
    	indexPanel = new TratteView();
    	return indexPanel;
    }*/
	
    public static TratteView viewIndex(){
    	return new TratteView();
    }
    public static CreateTrattaView viewCreate() {
    	return new CreateTrattaView();
    }
    
    public static void creaTratta() {
    	
    }
    
    public static void trovaTratta() {
    	
    }
    
    public static void eliminaTratta() {
    	
    }
}
