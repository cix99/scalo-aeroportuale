package Models;

public class Prenotazione{

	private String id;
	private int idTratta;
	private String codicePrenotazione;
	private String nomePasseggero;
	private String cognomePasseggero;
	private Coda coda;
	private CentoKilometri centoKilometri;
    private CompagniaAerea compagniaAerea;
    private boolean imbarcato;
    
    public Prenotazione(int idTratta, String nomePasseggero, String cognomePasseggero, String codicePrenotazione, Coda coda, CentoKilometri centoKilometri, CompagniaAerea compagniaAerea) {
		super();
		this.idTratta = idTratta;
		this.nomePasseggero = nomePasseggero;
		this.cognomePasseggero = cognomePasseggero;
		this.codicePrenotazione = codicePrenotazione;
		this.coda = coda;
		this.centoKilometri = centoKilometri;
		this.compagniaAerea = compagniaAerea;
	}
    
    public Prenotazione(int idTratta, String nomePasseggero, String cognomePasseggero, String codicePrenotazione, Coda coda, CompagniaAerea compagniaAerea) {
 		super();
 		this.idTratta = idTratta;
 		this.nomePasseggero = nomePasseggero;
 		this.cognomePasseggero = cognomePasseggero;
 		this.codicePrenotazione = codicePrenotazione;
 		this.coda = coda;
 		this.compagniaAerea = compagniaAerea;
 	}

	public Prenotazione() {
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public int getIdTratta() {
		return idTratta;
	}
	
	public void setIdTratta(int idTratta) {
		this.idTratta = idTratta;
	}

	public String getCodicePrenotazione() {
		return codicePrenotazione;
	}
	
	public void setCodicePrenotazione (String codicePrenotazione) {
		this.codicePrenotazione = codicePrenotazione;
	}
	
	public String getNomePasseggero() {
		return nomePasseggero;
	}
	
	public void setNomePasseggero(String nomePasseggero) {
		this.nomePasseggero = nomePasseggero;
	}

	public String getCognomePasseggero() {
		return cognomePasseggero;
	}
	
	public void setCognomePasseggero(String cognomePasseggero) {
		this.cognomePasseggero = cognomePasseggero;
	}

	public Coda getCoda() {
		return coda;
	}
	
	public void setCoda(Coda coda) {
		this.coda = coda;
	}

	public CentoKilometri getCentoKilometri() {
		return centoKilometri;
	}

	public void setCentoKilometri(CentoKilometri centoKilometri) {
		this.centoKilometri = centoKilometri;
	}
	
	public CompagniaAerea getCompagniaAerea() {
		return compagniaAerea;
	}
	
	public void setCompagniaAerea(CompagniaAerea compagniaAerea) {
		this.compagniaAerea = compagniaAerea;
	}

	public boolean getImbarcato() {
		return imbarcato;
	}
	
	public void setImbarcato(boolean imbarcato) {
		this.imbarcato = imbarcato;
	}
}
