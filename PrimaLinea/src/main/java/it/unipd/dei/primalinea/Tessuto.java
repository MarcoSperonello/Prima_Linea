package it.unipd.dei.primalinea;

import java.util.HashSet;
import java.util.Set;

public class Tessuto {
	private TessutoPk tessutoKey;
	private Set<Cliente> clienti;
	private Set<Materiale> materiali;

	public Tessuto(){
		clienti = new HashSet<Cliente>(0);
		materiali = new HashSet<Materiale>(0);
	}
	
	public Tessuto(int codice, String varianteColore) {
		tessutoKey = new TessutoPk(codice, varianteColore);
		clienti = new HashSet<Cliente>(0);
		materiali = new HashSet<Materiale>(0);
	}

	public TessutoPk getTessutoKey() {
		return tessutoKey;
	}

	public void setTessutoKey(TessutoPk pk) {
		this.tessutoKey = pk;
	}

	public Set<Cliente> getClienti() {
		return clienti;
	}

	public void setClienti(Set<Cliente> clienti) {
		this.clienti = clienti;
	}

	public Set<Materiale> getMateriali() {
		return materiali;
	}

	public void setMateriali(Set<Materiale> materiali) {
		this.materiali = materiali;
	}

}
