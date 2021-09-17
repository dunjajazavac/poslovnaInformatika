package com.poslovnaInformatika.podsistemProdaje.dto;

import com.poslovnaInformatika.podsistemProdaje.model.NaseljenoMesto;

public class MestoDTO {
	private Long idMesta;
	private int pttBroj;
	private String nazivMesta;

	public MestoDTO() {
		super();

	}

	public MestoDTO(Long idMesta, int pttBroj, String nazivMesta, String nazivPreduzeca, Long idPreduzeca) {
		super();
		this.idMesta = idMesta;
		this.pttBroj = pttBroj;
		this.nazivMesta = nazivMesta;
	}

	public MestoDTO(NaseljenoMesto m) {
		this.idMesta = m.getIdMesta();
		this.pttBroj = m.getPttBroj();
		this.nazivMesta = m.getNazivMesta();

	}

	public Long getIdMesta() {
		return idMesta;
	}

	public void setIdMesta(Long idMesta) {
		this.idMesta = idMesta;
	}

	public int getPttBroj() {
		return pttBroj;
	}

	public void setPttBroj(int ptt) {
		this.pttBroj = pttBroj;
	}

	public String getNazivMesta() {
		return nazivMesta;
	}

	public void setNazivMesta(String nazivMesta) {
		this.nazivMesta = nazivMesta;
	}

}
