package it.unipd.dei.primalinea;

import java.io.Serializable;

public class FilatoPk  implements Serializable {
	private int titolo;
	private int codiceColore;
	private String fornitore;

	public FilatoPk(){
		
	}
	
	public FilatoPk(int titolo, int codiceColore, String fornitore) {
		this.titolo = titolo;
		this.codiceColore = codiceColore;
		this.fornitore = fornitore;
	}

	public int getTitolo() {
		return titolo;
	}

	public void setTitolo(int titolo) {
		this.titolo = titolo;
	}

	public int getCodiceColore() {
		return codiceColore;
	}

	public void setCodiceColore(int codiceColore) {
		this.codiceColore = codiceColore;
	}

	public String getFornitore() {
		return fornitore;
	}

	public void setFornitore(String fornitore) {
		this.fornitore = fornitore;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codiceColore;
		result = prime * result + ((fornitore == null) ? 0 : fornitore.hashCode());
		result = prime * result + titolo;
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
		FilatoPk other = (FilatoPk) obj;
		if (codiceColore != other.codiceColore)
			return false;
		if (fornitore == null) {
			if (other.fornitore != null)
				return false;
		} else if (!fornitore.equals(other.fornitore))
			return false;
		if (titolo != other.titolo)
			return false;
		return true;
	}
}
