package it.unipd.dei.primalinea;

import java.util.HashSet;
import java.util.Set;

public class Articolo {
	private int codice;
	private Ordine ordine;
	private String tipo;
	private Materiale materiale;
	private int quantita;
	private String lavorazione;
	private Modello modello;
	private Set<Consegna> consegne;
	
	public Articolo(){
		consegne = new HashSet<Consegna>(0);
	}
	

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Materiale getMateriale() {
		return materiale;
	}

	public void setMateriale(Materiale materiale) {
		this.materiale = materiale;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		if (quantita > 0) {
			this.quantita = quantita;
		} else {
			System.out.println("Quantita errata");
		}

	}

	public String getLavorazione() {
		return lavorazione;
	}

	public void setLavorazione(String lavorazione) {
		this.lavorazione = lavorazione;
	}

	public Modello getModello() {
		return modello;
	}

	public void setModello(Modello modello) {
		this.modello = modello;
	}

	public Set<Consegna> getConsegne() {
		return consegne;
	}

	public void setConsegne(Set<Consegna> consegne) {
		this.consegne = consegne;
	}

	public void setReferences(Ordine ordine, Materiale materiale, Modello modello) {
		this.ordine = ordine;
		this.materiale = materiale;
		this.modello = modello;
	}

}
