package com.poslovnaInformatika.podsistemProdaje.dto;

public class MestoDTO {
	private Long idMesta;
	private int ptt;
	private String nazivPreduzeca;
	private Long idPreduzeca;
	
	
	public MestoDTO() {
		super();
		
	}


	public MestoDTO(Long idMesta, int ptt, String nazivPreduzeca, Long idPreduzeca) {
		super();
		this.idMesta = idMesta;
		this.ptt = ptt;
		this.nazivPreduzeca = nazivPreduzeca;
		this.idPreduzeca = idPreduzeca;
	}


	public Long getIdMesta() {
		return idMesta;
	}


	public void setIdMesta(Long idMesta) {
		this.idMesta = idMesta;
	}


	public int getPtt() {
		return ptt;
	}


	public void setPtt(int ptt) {
		this.ptt = ptt;
	}


	public String getNazivPreduzeca() {
		return nazivPreduzeca;
	}


	public void setNazivPreduzeca(String nazivPreduzeca) {
		this.nazivPreduzeca = nazivPreduzeca;
	}


	public Long getIdPreduzeca() {
		return idPreduzeca;
	}


	public void setIdPreduzeca(Long idPreduzeca) {
		this.idPreduzeca = idPreduzeca;
	}
	
	
	

}
