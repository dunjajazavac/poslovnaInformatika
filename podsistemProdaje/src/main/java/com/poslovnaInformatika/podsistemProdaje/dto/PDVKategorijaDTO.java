package com.poslovnaInformatika.podsistemProdaje.dto;

import com.poslovnaInformatika.podsistemProdaje.model.PDVKategorija;

public class PDVKategorijaDTO {

	private Long idKategorije;
	private String nazivKategorije;
	
	public PDVKategorijaDTO(PDVKategorija pdvKategorija) {
		this.idKategorije = pdvKategorija.getIdKategorije(); 
		this.nazivKategorije = pdvKategorija.getNazivKategorije(); 
	}
	
	public PDVKategorijaDTO() {
		super();
	}
	
	public PDVKategorijaDTO(Long idKategorije, String naziv) {
		super();
		this.idKategorije = idKategorije;
		this.nazivKategorije = naziv;
	}
	
	public Long getIdKategorije() {
		return idKategorije;
	}
	
	public void setIdKategorije(Long idKategorije) {
		this.idKategorije = idKategorije;
	}
	
	public String getNaziv() {
		return nazivKategorije;
	}
	
	public void setNaziv(String naziv) {
		this.nazivKategorije = naziv;
	} 
	
	
}
