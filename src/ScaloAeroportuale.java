import Controllers.HomeController;
import dao.*;
import Models.*;
public class ScaloAeroportuale {

    public static void main(String[] args) {
        /*Tratta tratta = new Tratta();
        tratta.destinazione = "MILANO";
        tratta.compagnia_aerea = "Alitalia";
        tratta.ora_inizio_imbarco = LocalDateTime.of(2021,01,24,16,45);
        tratta.ora_fine_imbarco_stimato = LocalDateTime.of(2021,01,24,16,45);
        tratta.ora_fine_imbarco_effettivo = LocalDateTime.of(2021,01,24,16,45);
        tratta.gate = "1";

        tratta.store();*/


        /*
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.id_tratta = 7;
        prenotazione.nome_passeggero = "Ciro";
        prenotazione.cognome_passeggero = "Cefalo";
        prenotazione.coda = "BUSINESS";
        prenotazione.cento_kilometri = null;
        prenotazione.compagnia_aerea = "Alitalia";
        prenotazione.store();

        System.out.println(prenotazione.first().coda);
         */
    	GateDAO gateDao = new GateDAO();
    	//String input = "3";
    	//Gate gate = new Gate();
        //gateDao.Delete("3");

        System.out.println(gateDao.last().getName());
         

        //HomeController.view();
    }
}
