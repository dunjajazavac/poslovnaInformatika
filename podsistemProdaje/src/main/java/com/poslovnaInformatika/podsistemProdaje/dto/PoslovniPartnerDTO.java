package com.poslovnaInformatika.podsistemProdaje.dto;

public class PoslovniPartnerDTO {
	
	private Long idPoslovnogPartnera;
	private String naziv;
	private String adresa;
	private String fax;
	private String email;
	private String vrstaPartnera;
	private Long idMesta;
	private Long idPreduzeca;
	public PoslovniPartnerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PoslovniPartnerDTO(Long idPoslovnogPartnera, String naziv, String adresa, String fax, String email,
			String vrstaPartnera, Long idMesta, Long idPreduzeca) {
		super();
		this.idPoslovnogPartnera = idPoslovnogPartnera;
		this.naziv = naziv;
		this.adresa = adresa;
		this.fax = fax;
		this.email = email;
		this.vrstaPartnera = vrstaPartnera;
		this.idMesta = idMesta;
		this.idPreduzeca = idPreduzeca;
	}
	public Long getIdPoslovnogPartnera() {
		return idPoslovnogPartnera;
	}
	public void setIdPoslovnogPartnera(Long idPoslovnogPartnera) {
		this.idPoslovnogPartnera = idPoslovnogPartnera;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVrstaPartnera() {
		return vrstaPartnera;
	}
	public void setVrstaPartnera(String vrstaPartnera) {
		this.vrstaPartnera = vrstaPartnera;
	}
	public Long getIdMesta() {
		return idMesta;
	}
	public void setIdMesta(Long idMesta) {
		this.idMesta = idMesta;
	}
	public Long getIdPreduzeca() {
		return idPreduzeca;
	}
	public void setIdPreduzeca(Long idPreduzeca) {
		this.idPreduzeca = idPreduzeca;
	}
	
}
