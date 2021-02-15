package Models;

public class CentoKilometri{

    private int id;
    private String codiceCompagnia;
    private CompagniaAerea compagniaAerea;
    private int punti;
	
    public CentoKilometri (String codiceCompagnia, CompagniaAerea compagniaAerea, int punti) {
    	super();
    	this.codiceCompagnia = codiceCompagnia;
    	this.compagniaAerea = compagniaAerea;
    	this.punti = punti;
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

	public int getPunti() {
		return punti;
	}

	public void setPunti(int punti) {
		this.punti = punti;
	}
    
}
