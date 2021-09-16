package com.poslovnaInformatika.podsistemProdaje.dto;

public class NarudzbenicaDTO {

	private Long id;
	private int brojNarudzbenice;
	private Long idPreduzeca;
	private Long idPoslovnogPartnera;
	private Long idGodine;
	
	public NarudzbenicaDTO() {
		super();
	}

	public NarudzbenicaDTO(Long id, int brojNarudzbenice, Long idPreduzeca, Long idPoslovnogPartnera, Long idGodine) {
		super();
		this.id = id;
		this.brojNarudzbenice = brojNarudzbenice;
		this.idPreduzeca = idPreduzeca;
		this.idPoslovnogPartnera = idPoslovnogPartnera;
		this.idGodine = idGodine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrojNarudzbenice() {
		return brojNarudzbenice;
	}

	public void setBrojNarudzbenice(int brojNarudzbenice) {
		this.brojNarudzbenice = brojNarudzbenice;
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

	public Long getIdGodine() {
		return idGodine;
	}

	public void setIdGodine(Long idGodine) {
		this.idGodine = idGodine;
	}
}
