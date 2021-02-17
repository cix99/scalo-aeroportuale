package Models;

import java.time.LocalDateTime;

public class Coda {

	private int id;
	private int idTratta;
    private String nomeCoda;
    private LocalDateTime inizioImbarcoCoda;
    private LocalDateTime fineImbarcoCoda;
    private int priority;

    public Coda(String nomeCoda) {
		super();
		this.nomeCoda = nomeCoda;
	}
    
    public Coda(String nomeCoda, int priority) {
		super();
		this.nomeCoda = nomeCoda;
		this.priority = priority;
	}
	
	public Coda() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTratta() {
		return idTratta;
	}

	public void setIdTratta(int idTratta) {
		this.idTratta = idTratta;
	}
	
	public String getNomeCoda () {
		return nomeCoda;
	}

	public void setNomeCoda(String nomeCoda) {
		this.nomeCoda = nomeCoda;
	}

	public LocalDateTime getInizioImbarcoCoda() {
		return inizioImbarcoCoda;
	}

	public void setInizioImbarcoCoda(LocalDateTime inizioImbarcoCoda) {
		this.inizioImbarcoCoda = inizioImbarcoCoda;
	}

	public LocalDateTime getFineImbarcoCoda() {
		return fineImbarcoCoda;
	}

	public void setFineImbarcoCoda(LocalDateTime fineImbarcoCoda) {
		this.fineImbarcoCoda = fineImbarcoCoda;
	}    

	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
