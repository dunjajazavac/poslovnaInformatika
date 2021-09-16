package com.poslovnaInformatika.podsistemProdaje.dto;

import com.poslovnaInformatika.podsistemProdaje.model.JedinicaMere;

public class JedinicaMereDTO {
	
	private long idJediniceMere;
	private String naziv;
	private String skrNaziv;
	
	
	public long getIdJediniceMere() {
		return idJediniceMere;
	}
	public void setIdJediniceMere(long idJediniceMere) {
		this.idJediniceMere = idJediniceMere;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getSkrNaziv() {
		return skrNaziv;
	}
	public void setSkrNaziv(String skrNaziv) {
		this.skrNaziv = skrNaziv;
	}
	public JedinicaMereDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public JedinicaMereDTO(JedinicaMere jm) {
		this.idJediniceMere= jm.getIdJediniceMere();
		this.naziv=jm.getNazivJediniceMere();
		this.skrNaziv=jm.getSkraceniNaziv();
	}
	
	public JedinicaMereDTO(long idJediniceMere, String naziv, String skrNaziv) {
		super();
		this.idJediniceMere = idJediniceMere;
		this.naziv = naziv;
		this.skrNaziv = skrNaziv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
