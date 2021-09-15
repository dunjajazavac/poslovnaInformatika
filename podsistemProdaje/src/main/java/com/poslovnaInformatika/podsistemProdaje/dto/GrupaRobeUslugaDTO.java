package com.poslovnaInformatika.podsistemProdaje.dto;

import com.poslovnaInformatika.podsistemProdaje.model.GrupaRobeUsluga;

public class GrupaRobeUslugaDTO {

	private Long idGrupe; 
	private String nazivGrupe; 
	private Long idPdvKategorija;
	
	public GrupaRobeUslugaDTO() {
		
	}
	
	public GrupaRobeUslugaDTO(Long idGrupe, String nazivGrupe, Long idPdvKategorija) {
		super();
		this.idGrupe = idGrupe;
		this.nazivGrupe = nazivGrupe;
		this.idPdvKategorija = idPdvKategorija;
	}
	
	public GrupaRobeUslugaDTO(GrupaRobeUsluga gru) {
		this.idGrupe = gru.getIdGrupe(); 
		this.nazivGrupe = gru.getNazivGrupe(); 
		this.idPdvKategorija = gru.getPdvKategorija().getIdKategorije();
	}
	
	public Long getIdGrupe() {
		return idGrupe;
	}
	public void setIdGrupe(Long idGrupe) {
		this.idGrupe = idGrupe;
	}
	public String getNazivGrupe() {
		return nazivGrupe;
	}
	public void setNazivGrupe(String nazivGrupe) {
		this.nazivGrupe = nazivGrupe;
	}
	public Long getIdPdvKategorija() {
		return idPdvKategorija;
	}
	public void setIdPdvKategorija(Long idPdvKategorija) {
		this.idPdvKategorija = idPdvKategorija;
	} 
	
	
}
