package Models;

import java.time.LocalDateTime;


public class Tratta{

    private int id;
    private String destinazione;
    private CompagniaAerea compagniaAerea;
    private LocalDateTime oraInizioImbarco;
    private LocalDateTime oraFineImbarcoStimato;
    private LocalDateTime oraFineImbarcoEffettivo;
    private Gate gate;
    private Stato statoImbarco;
    private Boolean ritardo;
    
    
    public Tratta(String destinazione, CompagniaAerea compagniaAerea, LocalDateTime oraInizioImbarco) {
		super();
		this.destinazione = destinazione;
		this.compagniaAerea = compagniaAerea;
		this.oraInizioImbarco = oraInizioImbarco;
	}

	public Tratta() {
	}

	public int getId() {
		return id;
	}

	public String getDestinazione() {
		return destinazione;
	}

	public CompagniaAerea getCompagniaAerea() {
		return compagniaAerea;
	}

	public LocalDateTime getOraInizioImbarco() {
		return oraInizioImbarco;
	}
	
	public LocalDateTime getOraFineImbarcoStimato() {
		return oraFineImbarcoStimato;
	}

	public LocalDateTime getOraFineImbarcoEffettivo() {
		return oraFineImbarcoEffettivo;
	}

	public Gate getGate() {
		return gate;
	}

	public Stato getStatoImbarco() {
		return statoImbarco;
	}

	public Boolean getRitardo() {
		return ritardo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	public void setCompagniaAerea(CompagniaAerea compagniaAerea) {
		this.compagniaAerea = compagniaAerea;
	}

	public void setOraInizioImbarco(LocalDateTime oraInizioImbarco) {
		this.oraInizioImbarco = oraInizioImbarco;
	}

	public void setOraFineImbarcoStimato(LocalDateTime oraFineImbarcoStimato) {
		this.oraFineImbarcoStimato = oraFineImbarcoStimato;
	}

	public void setOraFineImbarcoEffettivo(LocalDateTime oraFineImbarcoEffettivo) {
		this.oraFineImbarcoEffettivo = oraFineImbarcoEffettivo;
	}

	public void setGate(Gate gate) {
		this.gate = gate;
	}

	public void setStatoImbarco(Stato statoImbarco) {
		this.statoImbarco = statoImbarco;
	}

	public void setRitardo(Boolean ritardo) {
		this.ritardo = ritardo;
	}
    
}
