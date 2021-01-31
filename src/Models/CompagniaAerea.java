package Models;

public class CompagniaAerea{

    private String nomeCompagnia;

    public CompagniaAerea(String nomeCompagnia) {
		super();
		this.nomeCompagnia = nomeCompagnia;
	}
	
	public CompagniaAerea() {
	}
	
	public String getNomeCompagnia () {
		return nomeCompagnia;
	}

	public void setNomeCompagnia(String nomeCompagnia) {
		this.nomeCompagnia = nomeCompagnia;
	}  
	
}
