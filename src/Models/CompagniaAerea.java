package Models;

public class CompagniaAerea{

    public String nomeCompagnia;

    public CompagniaAerea(String nomeCompagnia) {
		super();
		this.nomeCompagnia = nomeCompagnia;
	}
	
	public CompagniaAerea() {
	}
	
	public String getName () {
		return this.nomeCompagnia;
	}  
}
