package Models;

import java.time.LocalDateTime;


public class Tratta{

    public int id;
    public String destinazione;
    public CompagniaAerea compagniaAerea;
    public LocalDateTime oraInizioImbarco;
    public LocalDateTime oraFineImbarcoStimato;
    public LocalDateTime oraFineImbarcoEffettivo;
    public Gate gate;
    public Stato statoImbarco;
    public Boolean ritardo;

    public Tratta() {
	}
}
