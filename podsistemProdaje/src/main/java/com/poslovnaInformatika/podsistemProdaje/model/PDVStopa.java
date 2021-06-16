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
@Table(name= "pdvStopa")
public class PDVStopa {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@NotNull()
	@Column(name = "datumVazenja", columnDefinition = "DATE")
	private Date datumVazenja;
	
	
	@DecimalMax("2")
	@Column(name = "procenat", columnDefinition = "DOUBLE")
	private double procenat;

	@NotNull()
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kategorijaId")
	private PDVKategorija pdvKategorija;

	public PDVStopa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PDVStopa(Long id, @NotNull Date datumVazenja, @DecimalMax("2") double procenat, PDVKategorija pdvKategorija) {
		super();
		this.id = id;
		this.datumVazenja = datumVazenja;
		this.procenat = procenat;
		this.pdvKategorija = pdvKategorija;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "PDVStopa [id=" + id + ", datumVazenja=" + datumVazenja + ", procenat=" + procenat + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datumVazenja == null) ? 0 : datumVazenja.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(procenat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PDVStopa other = (PDVStopa) obj;
		if (datumVazenja == null) {
			if (other.datumVazenja != null)
				return false;
		} else if (!datumVazenja.equals(other.datumVazenja))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(procenat) != Double.doubleToLongBits(other.procenat))
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
