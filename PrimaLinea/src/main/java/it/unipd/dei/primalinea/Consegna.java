package it.unipd.dei.primalinea;

import java.util.Date;

public class Consegna {
	private ConsegnaPk consegnaKey;
	private int quantitaConsegnata;
	private String corriere;
	private boolean completata;
	
	public Consegna(){
		
	}
	
	public Consegna(Date dataConsegna, Articolo articolo, int quantitaConsegnata, String corriere, boolean completata) {
		this.consegnaKey = new ConsegnaPk(dataConsegna, articolo);
		this.quantitaConsegnata = quantitaConsegnata;
		this.corriere = corriere;
		this.completata = completata;
	}
	
	public Date getDataConsegna(){
		return consegnaKey.getDataConsegna();
	}
	
	public void setDataConsegna(Date dataConsegna){
		consegnaKey.setDataConsegna(dataConsegna);
	}
	
	public Articolo getArticolo(){
		return consegnaKey.getArticolo();
	}
	
	public void setArticolo(Articolo articolo){
		consegnaKey.setArticolo(articolo);
	}
	
	public ConsegnaPk getConsegnaKey() {
		return consegnaKey;
	}

	public void setConsegnaKey(ConsegnaPk consegnaKey) {
		this.consegnaKey = consegnaKey;
	}

	public int getQuantitaConsegnata() {
		return quantitaConsegnata;
	}

	public void setQuantitaConsegnata(int quantitaConsegnata) {
		this.quantitaConsegnata = quantitaConsegnata;
	}

	public String getCorriere() {
		return corriere;
	}

	public void setCorriere(String corriere) {
		this.corriere = corriere;
	}

	public boolean isCompletata() {
		return completata;
	}

	public void setCompletata(boolean completata) {
		this.completata = completata;
	}

}
