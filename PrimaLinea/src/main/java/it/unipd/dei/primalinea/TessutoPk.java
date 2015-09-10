package it.unipd.dei.primalinea;

import java.io.Serializable;

public class TessutoPk implements Serializable {
	private int codice;
	private String varianteColore;

	public TessutoPk(int codice, String varianteColore) {
		this.codice = codice;
		this.varianteColore = varianteColore;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getVarianteColore() {
		return varianteColore;
	}

	public void setVarianteColore(String varianteColore) {
		this.varianteColore = varianteColore;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice;
		result = prime * result + ((varianteColore == null) ? 0 : varianteColore.hashCode());
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
		TessutoPk other = (TessutoPk) obj;
		if (codice != other.codice)
			return false;
		if (varianteColore == null) {
			if (other.varianteColore != null)
				return false;
		} else if (!varianteColore.equals(other.varianteColore))
			return false;
		return true;
	}
	
}