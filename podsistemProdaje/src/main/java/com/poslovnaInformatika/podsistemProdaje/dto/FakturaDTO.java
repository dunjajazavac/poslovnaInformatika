package com.poslovnaInformatika.podsistemProdaje.dto;

import java.sql.Date;

public class FakturaDTO {
	
	private Long idFakture;
	private int brojFakture;
	private Date datumFakture;
	private Date datumValute;
	private double ukupnaOsnovica;
	private double ukupanPDV;
	private double ukupanIznos;
	private String statusFakture;
	private Long idGodine;
	private Long idPreduzeca;
	private Long idPoslovnogPartnera;
	private Long idNarudzenice;
	public FakturaDTO() {
		super();
	}
	public FakturaDTO(Long idFakture, int brojFakture, Date datumFakture, Date datumValute, double ukupnaOsnovica,
			double ukupanPDV, double ukupanIznos, String statusFakture, Long idGodine, Long idPreduzeca,
			Long idPoslovnogPartnera, Long idNarudzenice) {
		super();
		this.idFakture = idFakture;
		this.brojFakture = brojFakture;
		this.datumFakture = datumFakture;
		this.datumValute = datumValute;
		this.ukupnaOsnovica = ukupnaOsnovica;
		this.ukupanPDV = ukupanPDV;
		this.ukupanIznos = ukupanIznos;
		this.statusFakture = statusFakture;
		this.idGodine = idGodine;
		this.idPreduzeca = idPreduzeca;
		this.idPoslovnogPartnera = idPoslovnogPartnera;
		this.idNarudzenice = idNarudzenice;
	}
	public Long getIdFakture() {
		return idFakture;
	}
	public void setIdFakture(Long idFakture) {
		this.idFakture = idFakture;
	}
	public int getBrojFakture() {
		return brojFakture;
	}
	public void setBrojFakture(int brojFakture) {
		this.brojFakture = brojFakture;
	}
	public Date getDatumFakture() {
		return datumFakture;
	}
	public void setDatumFakture(Date datumFakture) {
		this.datumFakture = datumFakture;
	}
	public Date getDatumValute() {
		return datumValute;
	}
	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}
	public double getUkupnaOsnovica() {
		return ukupnaOsnovica;
	}
	public void setUkupnaOsnovica(double ukupnaOsnovica) {
		this.ukupnaOsnovica = ukupnaOsnovica;
	}
	public double getUkupanPDV() {
		return ukupanPDV;
	}
	public void setUkupanPDV(double ukupanPDV) {
		this.ukupanPDV = ukupanPDV;
	}
	public double getUkupanIznos() {
		return ukupanIznos;
	}
	public void setUkupanIznos(double ukupanIznos) {
		this.ukupanIznos = ukupanIznos;
	}
	public String getStatusFakture() {
		return statusFakture;
	}
	public void setStatusFakture(String statusFakture) {
		this.statusFakture = statusFakture;
	}
	public Long getIdGodine() {
		return idGodine;
	}
	public void setIdGodine(Long idGodine) {
		this.idGodine = idGodine;
	}
	public Long getIdPreduzeca() {
		return idPreduzeca;
	}
	public void setIdPreduzeca(Long idPreduzeca) {
		this.idPreduzeca = idPreduzeca;
	}
	public Long getIdPoslovnogPartnera() {
		return idPoslovnogPartnera;
	}
	public void setIdPoslovnogPartnera(Long idPoslovnogPartnera) {
		this.idPoslovnogPartnera = idPoslovnogPartnera;
	}
	public Long getIdNarudzenice() {
		return idNarudzenice;
	}
	public void setIdNarudzenice(Long idNarudzenice) {
		this.idNarudzenice = idNarudzenice;
	}
	

}
