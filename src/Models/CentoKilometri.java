package Models;

public class CentoKilometri{

    private int id;
    private String codiceCompagnia;
    private CompagniaAerea compagniaAerea;
    private String nome;
    private String cognome;
    private int punti;
	
    public CentoKilometri (String codiceCompagnia, CompagniaAerea compagniaAerea, String nome, String cognome, int punti) {
    	super();
    	this.codiceCompagnia = codiceCompagnia;
    	this.compagniaAerea = compagniaAerea;
    	this.nome = nome;
    	this.cognome = cognome;
    	this.punti = punti;
    }
    
    public CentoKilometri (String codiceCompagnia, CompagniaAerea compagniaAerea) {
    	super();
    	this.codiceCompagnia = codiceCompagnia;
    	this.compagniaAerea = compagniaAerea;
    }
    
	public CentoKilometri() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodiceCompagnia() {
		return codiceCompagnia;
	}

	public void setCodiceCompagnia(String codiceCompagnia) {
		this.codiceCompagnia = codiceCompagnia;
	}

	public CompagniaAerea getCompagniaAerea() {
		return compagniaAerea;
	}

	public void setCompagniaAerea(CompagniaAerea compagniaAerea) {
		this.compagniaAerea = compagniaAerea;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public int getPunti() {
		return punti;
	}

	public void setPunti(int punti) {
		this.punti = punti;
	}


    
}
