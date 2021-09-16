package com.poslovnaInformatika.podsistemProdaje.dto;

import java.sql.Date;

import com.poslovnaInformatika.podsistemProdaje.model.PDVStopa;

public class PDVStopaDTO {
	
	private long id;
	private Date datum;
	private double procenaat;
	
	public PDVStopaDTO(long id, Date datum, double procenaat) {
		super();
		this.id = id;
		this.datum = datum;
		this.procenaat = procenaat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public double getProcenaat() {
		return procenaat;
	}

	public void setProcenaat(double procenaat) {
		this.procenaat = procenaat;
	}
	
	public PDVStopaDTO(PDVStopa pdvStopa) {
		this.id=pdvStopa.getIdStope();
		this.datum=pdvStopa.getDatumVazenja();
		this.procenaat=pdvStopa.getProcenat();
	}
	
	
	

}
