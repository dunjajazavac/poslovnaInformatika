//package com.poslovnaInformatika.podsistemProdaje.dto;
//
//import com.poslovnaInformatika.podsistemProdaje.model.RobaUsluge;
//
//public class RobaUslugeDTO {
//
//	private Long idRobeUsluge; 
//	private String naziv; 
//	private String opis; 
//	private boolean robaNaStanju; 
//	private Long idJediniceMjere;
//	
//	public RobaUslugeDTO(RobaUsluge robaUsluge) {
//		this.idJediniceMjere = robaUsluge.getIdRobeUsluge(); 
//		this.naziv = robaUsluge.getNaziv(); 
//		this.opis = robaUsluge.getOpis(); 
//		this.idJediniceMjere = robaUsluge.getIdJediniceMere().getIdJediniceMere();
//	}
//	
//	public RobaUslugeDTO() {
//		super();
//	}
//	
//	public RobaUslugeDTO(Long idRobeUsluge, String naziv, String opis, boolean robaNaStanju, Long idJediniceMjere) {
//		super();
//		this.idRobeUsluge = idRobeUsluge;
//		this.naziv = naziv;
//		this.opis = opis;
//		this.robaNaStanju = robaNaStanju;
//		this.idJediniceMjere = idJediniceMjere;
//	}
//	
//	public Long getIdRobeUsluge() {
//		return idRobeUsluge;
//	}
//	public void setIdRobeUsluge(Long idRobeUsluge) {
//		this.idRobeUsluge = idRobeUsluge;
//	}
//	public String getNaziv() {
//		return naziv;
//	}
//	public void setNaziv(String naziv) {
//		this.naziv = naziv;
//	}
//	public String getOpis() {
//		return opis;
//	}
//	public void setOpis(String opis) {
//		this.opis = opis;
//	}
//	public boolean isRobaNaStanju() {
//		return robaNaStanju;
//	}
//	public void setRobaNaStanju(boolean robaNaStanju) {
//		this.robaNaStanju = robaNaStanju;
//	}
//	public Long getIdJediniceMjere() {
//		return idJediniceMjere;
//	}
//	public void setIdJediniceMjere(Long idJediniceMjere) {
//		this.idJediniceMjere = idJediniceMjere;
//	} 
//	
//	
//}
