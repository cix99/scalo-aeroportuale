package Models;

public class Prenotazione{

	private String id;
	private int idTratta;
	private String nomePasseggero;
	private String cognomePasseggero;
	private Coda coda;
	private CentoKilometri centoKilometri;
    private CompagniaAerea compagniaAerea;

    
    public Prenotazione(int idTratta, String nomePasseggero, String cognomePasseggero, Coda coda, CentoKilometri centoKilometri, CompagniaAerea compagniaAerea) {
		super();
		this.idTratta = idTratta;
		this.nomePasseggero = nomePasseggero;
		this.cognomePasseggero = cognomePasseggero;
		this.coda = coda;
		this.centoKilometri = centoKilometri;
		this.compagniaAerea = compagniaAerea;
	}

	public Prenotazione() {
	}

	public String getId() {
		return id;
	}

	public int getIdTratta() {
		return idTratta;
	}

	public String getNomePasseggero() {
		return nomePasseggero;
	}

	public String getCognomePasseggero() {
		return cognomePasseggero;
	}

	public Coda getCoda() {
		return coda;
	}

	public CentoKilometri getCentoKilometri() {
		return centoKilometri;
	}
	
	public CompagniaAerea getCompagniaAerea() {
		return compagniaAerea;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIdTratta(int idTratta) {
		this.idTratta = idTratta;
	}

	public void setNomePasseggero(String nomePasseggero) {
		this.nomePasseggero = nomePasseggero;
	}

	public void setCognomePasseggero(String cognomePasseggero) {
		this.cognomePasseggero = cognomePasseggero;
	}

	public void setCoda(Coda coda) {
		this.coda = coda;
	}

	public void setCentoKilometri(CentoKilometri centoKilometri) {
		this.centoKilometri = centoKilometri;
	}

	public void setCompagniaAerea(CompagniaAerea compagniaAerea) {
		this.compagniaAerea = compagniaAerea;
	}

}
