package com.poslovnaInformatika.podsistemProdaje.dto;

import java.sql.Date;

public class CenovnikDTO {
	
	private Long idCenovnika;
	private Date datumPocetkaVazenja;
	private Long idPreduzeca;
	public CenovnikDTO() {
		super();
	}
	public CenovnikDTO(Long idCenovnika, Date datumPocetkaVazenja, Long idPreduzeca) {
		super();
		this.idCenovnika = idCenovnika;
		this.datumPocetkaVazenja = datumPocetkaVazenja;
		this.idPreduzeca = idPreduzeca;
	}
	public Long getIdCenovnika() {
		return idCenovnika;
	}
	public void setIdCenovnika(Long idCenovnika) {
		this.idCenovnika = idCenovnika;
	}
	public Date getDatumPocetkaVazenja() {
		return datumPocetkaVazenja;
	}
	public void setDatumPocetkaVazenja(Date datumPocetkaVazenja) {
		this.datumPocetkaVazenja = datumPocetkaVazenja;
	}
	public Long getIdPreduzeca() {
		return idPreduzeca;
	}
	public void setIdPreduzeca(Long idPreduzeca) {
		this.idPreduzeca = idPreduzeca;
	}
	
	

}
