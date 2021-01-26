package Models;

import java.lang.String;


public class Gate{

    public String nomeGate;

	public Gate(String nomeGate) {
		super();
		this.nomeGate = nomeGate;
	}
	
	public Gate() {
	}
	
	public String getName () {
		return this.nomeGate;
	}
 
}
