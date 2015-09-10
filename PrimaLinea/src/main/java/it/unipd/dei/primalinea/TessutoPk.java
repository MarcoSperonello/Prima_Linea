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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TessutoPk other = (TessutoPk) obj;
        if ((this.codice == 0) ? (other.codice != 0) : this.codice != other.codice) {
            return false;
        }
        if ((this.varianteColore == null) ? (other.varianteColore != null) : !this.varianteColore.equals(other.varianteColore)) {
            return false;
        }
        return true;
    }
}