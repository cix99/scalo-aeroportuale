import Controllers.*;

import java.util.Locale;

public class ScaloAeroportuale {
	
	public static void main(String[] args) {
		Locale.setDefault(new Locale("it", "IT"));
		ViewsController viewsController = new ViewsController();
		viewsController.openLoginView();
	}
}
