package Models;

import java.lang.String;


public class Gate{

    private String nomeGate;

	public Gate(String nomeGate) {
		super();
		this.nomeGate = nomeGate;
	}
	
	public Gate() {
	}
	
	public String getNomeGate () {
		return nomeGate;
	}

	public void setNomeGate(String nomeGate) {
		this.nomeGate = nomeGate;
	}
 
	
}
