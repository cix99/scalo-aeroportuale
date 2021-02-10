import Controllers.*;

import java.util.Locale;

public class ScaloAeroportuale {

//    public static void main(String[] args) {
//        /*Tratta tratta = new Tratta();
//        tratta.destinazione = "MILANO";
//        tratta.compagnia_aerea = "Alitalia";
//        tratta.ora_inizio_imbarco = LocalDateTime.of(2021,01,24,16,45);
//        tratta.ora_fine_imbarco_stimato = LocalDateTime.of(2021,01,24,16,45);
//        tratta.ora_fine_imbarco_effettivo = LocalDateTime.of(2021,01,24,16,45);
//        tratta.gate = "1";
//        tratta.store();*/
//    }
	
	public static void main(String[] args) {
		Locale.setDefault(new Locale("it", "IT"));
		ViewsController homeController = new ViewsController();
		homeController.viewLoginView();
	}
}
