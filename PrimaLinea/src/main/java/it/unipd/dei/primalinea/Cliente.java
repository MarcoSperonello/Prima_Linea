package it.unipd.dei.primalinea;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Cliente {
	private BigDecimal partitaIva;
	private String email;
	private String nome;
	private String telefono;
	private String indirizzo;
	
	private Set<Ordine> ordini;
	private Set<Modello> modelli;
	private Set<Tessuto> tessuti;

	public Cliente(){
		ordini = new HashSet<Ordine>(0);
		modelli = new HashSet<Modello>(0);
		tessuti = new HashSet<Tessuto>(0);
	}
	
	public Cliente(BigDecimal partitaIva, String email, String nome, String telefono, String indirizzo) {
		this.partitaIva = partitaIva;
		this.email = email;
		this.nome = nome;
		this.telefono = telefono;
		this.indirizzo = indirizzo;
		ordini = new HashSet<Ordine>(0);
		modelli = new HashSet<Modello>(0);
		tessuti = new HashSet<Tessuto>(0);
	}

	public BigDecimal getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(BigDecimal partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Set<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(Set<Ordine> ordini) {
		this.ordini = ordini;
	}

	public Set<Modello> getModelli() {
		return modelli;
	}

	public void setModelli(Set<Modello> modelli) {
		this.modelli = modelli;
	}

	public Set<Tessuto> getTessuti() {
		return tessuti;
	}

	public void setTessuti(Set<Tessuto> tessuti) {
		this.tessuti = tessuti;
	}
}
