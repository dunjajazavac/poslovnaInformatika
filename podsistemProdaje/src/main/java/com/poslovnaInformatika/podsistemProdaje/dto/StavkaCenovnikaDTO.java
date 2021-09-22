package com.poslovnaInformatika.podsistemProdaje.dto;

public class StavkaCenovnikaDTO {
	
	private Long idStavke;
	private double cena;
	private Long idCenovnika;
	private Long idRobeUsluge;
	
	public StavkaCenovnikaDTO() {
		super();
	}	

	public StavkaCenovnikaDTO(Long idStavke, double cena, Long idCenovnika, Long idRobeUsluge) {
		super();
		this.idStavke = idStavke;
		this.cena = cena;
		this.idCenovnika = idCenovnika;
		this.idRobeUsluge = idRobeUsluge;
	}


//	public StavkaCenovnikaDTO(StavkaCenovnika stavkaCenovnika) {
//		this.idStavke = stavkaCenovnika.getIdStavke();
//		this.cena = stavkaCenovnika.getCena();
//		this.cenovnik = stavkaCenovnika.getCenovnik();
//		this.robaUsluga = stavkaCenovnika.getRobaUsluga();
//	}

	public Long getIdStavke() {
		return idStavke;
	}

	public void setIdStavke(Long idStavke) {
		this.idStavke = idStavke;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Long getIdCenovnika() {
		return idCenovnika;
	}

	public void setIdCenovnika(Long idCenovnika) {
		this.idCenovnika = idCenovnika;
	}

	public Long getIdRobeUsluge() {
		return idRobeUsluge;
	}

	public void setIdRobeUsluge(Long idRobeUsluge) {
		this.idRobeUsluge = idRobeUsluge;
	}
}