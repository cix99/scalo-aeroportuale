package Models;

import java.time.LocalDateTime;


public class Tratta{

    public int id;
    public String destinazione;
    public String compagniaAerea;
    public LocalDateTime oraInizioImbarco;
    public LocalDateTime oraFineImbarcoStimato;
    public LocalDateTime oraFineImbarcoEffettivo;
    public String gate;
    public Stato statoImbarco;
    public Boolean ritardo;

    public Tratta() {
	}
}
