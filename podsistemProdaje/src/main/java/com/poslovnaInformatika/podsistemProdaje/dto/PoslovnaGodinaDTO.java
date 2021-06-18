package com.poslovnaInformatika.podsistemProdaje.dto;

public class PoslovnaGodinaDTO {
	
	private Long idGodine;
	private int godina;
	private boolean zakljucena;
	private Long idPreduzeca;
	
	public PoslovnaGodinaDTO() {
		super();
	}
	public PoslovnaGodinaDTO(Long idGodine, int godina, boolean zakljucena, Long idPreduzeca) {
		super();
		this.idGodine = idGodine;
		this.godina = godina;
		this.zakljucena = zakljucena;
		this.idPreduzeca = idPreduzeca;
	}
	public Long getIdGodine() {
		return idGodine;
	}
	public void setIdGodine(Long idGodine) {
		this.idGodine = idGodine;
	}
	public int getGodina() {
		return godina;
	}
	public void setGodina(int godina) {
		this.godina = godina;
	}
	public boolean isZakljucena() {
		return zakljucena;
	}
	public void setZakljucena(boolean zakljucena) {
		this.zakljucena = zakljucena;
	}
	public Long getIdPreduzeca() {
		return idPreduzeca;
	}
	public void setIdPreduzeca(Long idPreduzeca) {
		this.idPreduzeca = idPreduzeca;
	}
	
	

}
