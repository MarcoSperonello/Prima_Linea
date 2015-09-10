package it.unipd.dei.primalinea;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Filato {
	private FilatoPk filatoKey;
	private BigDecimal costo;
	private Set<Materiale> materiali;
	
	public Filato(){
		materiali = new HashSet<Materiale>(0);
	}
	
	public Filato(int titolo, int codiceColore, String fornitore, BigDecimal costo){
		filatoKey = new FilatoPk(titolo, codiceColore, fornitore);
		this.costo = costo;
		materiali = new HashSet<Materiale>(0);
	}

	public FilatoPk getFilatoKey() {
		return filatoKey;
	}

	public void setFilatoKey(FilatoPk filatoKey) {
		this.filatoKey = filatoKey;
	}

	public BigDecimal getCosto() {
		return costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public Set<Materiale> getMateriali() {
		return materiali;
	}

	public void setMateriali(Set<Materiale> materiali) {
		this.materiali = materiali;
	}
}
