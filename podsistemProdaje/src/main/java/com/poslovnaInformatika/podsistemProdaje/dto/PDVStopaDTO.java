package com.poslovnaInformatika.podsistemProdaje.dto;

import java.sql.Date;

import com.poslovnaInformatika.podsistemProdaje.model.PDVStopa;

public class PDVStopaDTO {
	
	private long id;
	private Date datum;
	private double procenaat;
	private long idKategorije;
	
	public PDVStopaDTO(long id, Date datum, double procenaat, long idKategorije) {
		super();
		this.id = id;
		this.datum = datum;
		this.procenaat = procenaat;
		this.idKategorije=idKategorije;
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
	
	
	public long getIdKategorije() {
		return idKategorije;
	}

	public void setIdKategorije(long idKategorije) {
		this.idKategorije = idKategorije;
	}

	public PDVStopaDTO(PDVStopa pdvStopa) {
		this.id=pdvStopa.getIdStope();
		this.datum=pdvStopa.getDatumVazenja();
		this.procenaat=pdvStopa.getProcenat();
	}
	
	
	

}
