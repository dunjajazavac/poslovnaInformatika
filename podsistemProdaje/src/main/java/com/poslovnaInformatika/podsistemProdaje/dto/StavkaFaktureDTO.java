package com.poslovnaInformatika.podsistemProdaje.dto;

public class StavkaFaktureDTO {
	private Long idStavke;
	private double kolicina;
	private double rabat;
	private double jedinicnaCena;
	private double iznos;
	private double pdvStopa;
	private double osnovicaZaPDV;
	private double iznosPDV;
	private double ukupanIznos;
	private Long idPreduzeca;
	private Long idFakture;
	private Long idRobeUsluge;
	
	public StavkaFaktureDTO() {
		super();
	}

	
	public StavkaFaktureDTO(Long idStavke, double kolicina, double rabat, double jedinicnaCena, double iznos, double pdvStopa,
			double osnovicaZaPDV, double iznosPDV, double ukupanIznos, Long idPreduzeca, Long idFakture,
			Long idRobeUsluge) {
		super();
		this.idStavke = idStavke;
		this.kolicina = kolicina;
		this.rabat = rabat;
		this.jedinicnaCena = jedinicnaCena;
		this.iznos = iznos;
		this.pdvStopa = pdvStopa;
		this.osnovicaZaPDV = osnovicaZaPDV;
		this.iznosPDV = iznosPDV;
		this.ukupanIznos = ukupanIznos;
		this.idPreduzeca = idPreduzeca;
		this.idFakture = idFakture;
		this.idRobeUsluge = idRobeUsluge;
	}

//	public StavkaFaktureDTO(StavkaFakture stavkaFakture) {
//		this.idStavke = stavkaFakture.getIdStavke();
//		this.kolicina = stavkaFakture.getKolicina();
//		this.rabat = stavkaFakture.getRabat();
//		this.jedinicnaCena = stavkaFakture.getJedinicnaCena();
//		this.pdvStopa = stavkaFakture.getPdvStopa();
//		this.osnovicaZaPDV = stavkaFakture.getOsnovicaZaPDV();
//		this.iznosPDV = stavkaFakture.getIznosPDV();
//		this.ukupanIznos = stavkaFakture.getIznosPDV();
//		this.preduzece = stavkaFakture.getPreduzece();
//		this.robaUsluga = stavkaFakture.getRobaUsluga();
//	}

	public Long getIdStavke() {
		return idStavke;
	}

	public void setIdStavke(Long idStavke) {
		this.idStavke = idStavke;
	}

	public double getKolicina() {
		return kolicina;
	}

	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}

	public double getRabat() {
		return rabat;
	}

	public void setRabat(double rabat) {
		this.rabat = rabat;
	}

	public double getJedinicnaCena() {
		return jedinicnaCena;
	}

	public void setJedinicnaCena(double jedinicnaCena) {
		this.jedinicnaCena = jedinicnaCena;
	}

	public double getPdvStopa() {
		return pdvStopa;
	}

	public void setPdvStopa(double pdvStopa) {
		this.pdvStopa = pdvStopa;
	}

	public double getOsnovicaZaPDV() {
		return osnovicaZaPDV;
	}

	public void setOsnovicaZaPDV(double osnovicaZaPDV) {
		this.osnovicaZaPDV = osnovicaZaPDV;
	}

	public double getIznosPDV() {
		return iznosPDV;
	}

	public void setIznosPDV(double iznosPDV) {
		this.iznosPDV = iznosPDV;
	}

	public double getUkupanIznos() {
		return ukupanIznos;
	}

	public void setUkupanIznos(double ukupanIznos) {
		this.ukupanIznos = ukupanIznos;
	}


	public Long getIdPreduzeca() {
		return idPreduzeca;
	}


	public void setIdPreduzeca(Long idPreduzeca) {
		this.idPreduzeca = idPreduzeca;
	}


	public Long getIdFakture() {
		return idFakture;
	}


	public void setIdFakture(Long idFakture) {
		this.idFakture = idFakture;
	}


	public Long getIdRobeUsluge() {
		return idRobeUsluge;
	}


	public void setIdRobeUsluge(Long idRobeUsluge) {
		this.idRobeUsluge = idRobeUsluge;
	}


	public double getIznos() {
		return iznos;
	}


	public void setIznos(double iznos) {
		this.iznos = iznos;
	}
	
}