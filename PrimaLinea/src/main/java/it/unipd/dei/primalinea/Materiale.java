package it.unipd.dei.primalinea;

import java.util.HashSet;
import java.util.Set;

public class Materiale {
	private int id;
	private Set<Articolo> articoli;
	private Set<Tessuto> tessuti;
	private Set<Filato> filati;

	public Materiale() {
		articoli = new HashSet<Articolo>(0);
		tessuti = new HashSet<Tessuto>(0);
		filati = new HashSet<Filato>(0);
	}
	
	public Materiale(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Articolo> getArticoli() {
		return articoli;
	}

	public void setArticoli(Set<Articolo> articoli) {
		this.articoli = articoli;
	}

	public Set<Tessuto> getTessuti() {
		return tessuti;
	}

	public void setTessuti(Set<Tessuto> tessuti) {
		this.tessuti = tessuti;
	}

	public Set<Filato> getFilati() {
		return filati;
	}

	public void setFilati(Set<Filato> filati) {
		this.filati = filati;
	}
}
