package it.unipd.dei.primalinea;

import java.io.Serializable;
import java.util.Date;

public class ConsegnaPk implements Serializable {
	private Date dataConsegna;
	private Articolo articolo;

	public ConsegnaPk(){
		
	}
	
	public ConsegnaPk(Date dataConsegna, Articolo articolo) {
		this.dataConsegna = dataConsegna;
		this.articolo = articolo;
	}

	public Date getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

}
