package it.unipd.dei.primalinea;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Ordine {
	private String numeroFattura;
	private BigDecimal prezzo;
	private Date dataOrdine;
	private Date dataCompletamento;
	private Cliente cliente;
	private Set<Articolo> articoli;
	
	public Ordine(){
		
	}
	
	public Ordine(String numeroFattura, BigDecimal prezzo, Date dataOrdine, Cliente cliente) {
		this.numeroFattura = numeroFattura;
		this.prezzo = prezzo;
		this.dataOrdine = dataOrdine;
		this.dataCompletamento = null;
		this.cliente = cliente;
		articoli = new HashSet<Articolo>(0);
	}

	public String getNumeroFattura() {
		return numeroFattura;
	}

	public void setNumeroFattura(String numeroFattura) {
		this.numeroFattura = numeroFattura;
	}

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public Date getDataCompletamento() {
		return dataCompletamento;
	}

	public void setDataCompletamento(Date dataCompletamento) {
		this.dataCompletamento = dataCompletamento;
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
