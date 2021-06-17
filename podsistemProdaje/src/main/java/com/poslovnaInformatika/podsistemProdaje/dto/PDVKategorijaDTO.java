package com.poslovnaInformatika.podsistemProdaje.dto;

import com.poslovnaInformatika.podsistemProdaje.model.PDVKategorija;

public class PDVKategorijaDTO {

	//ono cemu pripadas 
	private Long idKategorije;
	private String naziv;
	
	public PDVKategorijaDTO(PDVKategorija pdvKategorija) {
		this.idKategorije = pdvKategorija.getIdKategorije(); 
		this.naziv = pdvKategorija.getNaziv(); 
	}
	
	public PDVKategorijaDTO() {
		super();
	}
	
	public PDVKategorijaDTO(Long idKategorije, String naziv) {
		super();
		this.idKategorije = idKategorije;
		this.naziv = naziv;
	}
	
	public Long getIdKategorije() {
		return idKategorije;
	}
	
	public void setIdKategorije(Long idKategorije) {
		this.idKategorije = idKategorije;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	} 
	
	
}
