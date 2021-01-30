package Controllers;

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
