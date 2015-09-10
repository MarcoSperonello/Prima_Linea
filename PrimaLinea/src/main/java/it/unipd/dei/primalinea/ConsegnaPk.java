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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articolo == null) ? 0 : articolo.hashCode());
		result = prime * result + ((dataConsegna == null) ? 0 : dataConsegna.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsegnaPk other = (ConsegnaPk) obj;
		if (articolo == null) {
			if (other.articolo != null)
				return false;
		} else if (!articolo.equals(other.articolo))
			return false;
		if (dataConsegna == null) {
			if (other.dataConsegna != null)
				return false;
		} else if (!dataConsegna.equals(other.dataConsegna))
			return false;
		return true;
	}

}
