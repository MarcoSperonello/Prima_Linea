package it.unipd.dei.primalinea;

import java.util.HashSet;
import java.util.Set;

public class Modello {
	private int numero;
	private Cliente cliente;
	private Set<Articolo> articoli;

	public Modello(){
		articoli = new HashSet<Articolo>(0);
	}
	
	public Modello(int numero, Cliente cliente) {
		this.numero = numero;
		this.cliente = cliente;
		articoli = new HashSet<Articolo>(0);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Articolo> getArticoli() {
		return articoli;
	}

	public void setArticoli(Set<Articolo> articoli) {
		this.articoli = articoli;
	}
}
