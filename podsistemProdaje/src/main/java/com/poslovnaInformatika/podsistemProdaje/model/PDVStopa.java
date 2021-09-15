package com.poslovnaInformatika.podsistemProdaje.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;




@Entity
@Table(name = "pdv_stopa")
public class PDVStopa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idStope;
	
	@NotNull(message = "Mora da se unese datum vazenja")
	@Column(name = "datum_vazenja", columnDefinition = "DATE")
	private Date datumVazenja;
	
	//@NotBlank(message = "Procenat ne sme biti prazan")
	//@Size(min=1, max=2, message = "Procenat ne sme da bude prazan")
	@DecimalMax("2")
	@Column(name = "procenat", columnDefinition = "DOUBLE")
	private double procenat;

	@NotNull(message = "Mora postojati kategorija")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kategorija_id")
	private PDVKategorija pdvKategorija;
	
	public PDVStopa() {
		super();
	}

	public PDVStopa(Long idStope, Date datumVazenja, double procenat, PDVKategorija pdvKategorija) {
		super();
		this.idStope = idStope;
		this.datumVazenja = datumVazenja;
		this.procenat = procenat;
		this.pdvKategorija = pdvKategorija;
	}

	public Long getIdStope() {
		return idStope;
	}

	public void setIdStope(Long idStope) {
		this.idStope = idStope;
	}

	public Date getDatumVazenja() {
		return datumVazenja;
	}

	public void setDatumVazenja(Date datumVazenja) {
		this.datumVazenja = datumVazenja;
	}

	public double getProcenat() {
		return procenat;
	}

	public void setProcenat(double procenat) {
		this.procenat = procenat;
	}

	public PDVKategorija getPdvKategorija() {
		return pdvKategorija;
	}

	public void setPdvKategorija(PDVKategorija pdvKategorija) {
		this.pdvKategorija = pdvKategorija;
	}

	@Override
	public String toString() {
		return "PDVStopa [idStope=" + idStope + ", datumVazenja=" + datumVazenja + ", procenat=" + procenat;
	}

}