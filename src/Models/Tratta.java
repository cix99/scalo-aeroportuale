package Models;

import java.time.LocalDateTime;

public class Tratta{

    private int id;
    private String destinazione;
    private CompagniaAerea compagniaAerea;
    private LocalDateTime oraInizioImbarcoStimato;
    private LocalDateTime oraInizioImbarcoEffettivo;
    private LocalDateTime oraFineImbarcoStimato;
    private LocalDateTime oraFineImbarcoEffettivo;
    private Gate gate;
    private Stato statoImbarco;
    private Boolean ritardo;    
    private int maxPrenotazioni;
    
    public Tratta(String destinazione, CompagniaAerea compagniaAerea, Gate gate, LocalDateTime oraInizioImbarcoStimato, LocalDateTime oraFineImbarcoStimato, int maxPrenotazioni) {
		super();
		this.destinazione = destinazione;
		this.compagniaAerea = compagniaAerea;
		this.gate = gate;
		this.oraInizioImbarcoStimato = oraInizioImbarcoStimato;
		this.oraFineImbarcoStimato = oraFineImbarcoStimato;
		this.maxPrenotazioni = maxPrenotazioni;
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

	public LocalDateTime getOraInizioImbarcoStimato() {
		return oraInizioImbarcoStimato;
	}
	
	public LocalDateTime getOraInizioImbarcoEffettivo() {
		return oraInizioImbarcoEffettivo;
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

	public int getMaxPrenotazioni() {
		return maxPrenotazioni;
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

	public void setOraInizioImbarcoStimato(LocalDateTime oraInizioImbarcoStimato) {
		this.oraInizioImbarcoStimato = oraInizioImbarcoStimato;
	}
	
	public void setOraInizioImbarcoEffettivo(LocalDateTime oraInizioImbarcoEffettivo) {
		this.oraInizioImbarcoEffettivo = oraInizioImbarcoEffettivo;
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
	
	public void setMaxPrenotazioni(int maxPrenotazioni) {
		this.maxPrenotazioni = maxPrenotazioni;
	}
    
}
