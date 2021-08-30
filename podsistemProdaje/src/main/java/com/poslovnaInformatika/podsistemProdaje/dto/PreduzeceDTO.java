package com.poslovnaInformatika.podsistemProdaje.dto;

public class PreduzeceDTO {

	private Long idPreduzeca;
	private String nazivPreduzeca;
	private String adresa;
	private String brojTelefona;
	private String fax;
	private Long idMesta;
	public PreduzeceDTO() {
		super();
		// TODO Auto-generated constructor stub
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
