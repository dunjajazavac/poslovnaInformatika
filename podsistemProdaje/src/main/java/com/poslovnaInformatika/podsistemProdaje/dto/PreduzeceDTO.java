package com.poslovnaInformatika.podsistemProdaje.dto;

import com.poslovnaInformatika.podsistemProdaje.model.Preduzece;

public class PreduzeceDTO {

	private Long idPreduzeca;
	private String nazivPreduzeca;
	private String adresa;
	private String brojTelefona;
	private String fax;
	private Long idMesta;
	public PreduzeceDTO() {
		super();
		
	}
	public PreduzeceDTO(Long idPreduzeca, String nazivPreduzeca, String adresa, String brojTelefona, String fax,
			Long idMesta) {
		super();
		this.idPreduzeca = idPreduzeca;
		this.nazivPreduzeca = nazivPreduzeca;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.fax = fax;
		this.idMesta = idMesta;
	}
	public PreduzeceDTO(Preduzece p) {
		
		this.idPreduzeca = p.getIdPreduzeca();
		this.nazivPreduzeca = p.getNazivPreduzeca();
		this.adresa = p.getAdresa();
		this.brojTelefona = p.getBrojTelefona();
		this.fax = p.getFax();
		this.idMesta = p.getIdPreduzeca();
		
	}
	public Long getIdPreduzeca() {
		return idPreduzeca;
	}
	public void setIdPreduzeca(Long idPreduzeca) {
		this.idPreduzeca = idPreduzeca;
	}
	public String getNazivPreduzeca() {
		return nazivPreduzeca;
	}
	public void setNazivPreduzeca(String nazivPreduzeca) {
		this.nazivPreduzeca = nazivPreduzeca;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public Long getIdMesta() {
		return idMesta;
	}
	public void setIdMesta(Long idMesta) {
		this.idMesta = idMesta;
	}
	
	
}
