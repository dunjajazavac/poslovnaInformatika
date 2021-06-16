package com.poslovnaInformatika.podsistemProdaje.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;



@Entity


@Table(name = "cenovnik")
public class Cenovnik {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCenovnika;
	
	@javax.validation.constraints.NotNull()
	@Column(name="datum_pocetka_vazenja", columnDefinition = "DATE")
	private Date datumPocetkaVazenja;
	
	@javax.validation.constraints.NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_preduzeca")
	private Preduzece preduzece;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "cenovnik")
	private List<StavkaCenovnika> stavkeCenovnika = new ArrayList<StavkaCenovnika>();
	
	public Cenovnik() {
		super();
	}

	public Cenovnik(Long idCenovnika, Date datumPocetkaVazenja, Preduzece preduzece,
			List<StavkaCenovnika> stavkeCenovnika) {
		super();
		this.idCenovnika = idCenovnika;
		this.datumPocetkaVazenja = datumPocetkaVazenja;
		this.preduzece = preduzece;
		this.stavkeCenovnika = stavkeCenovnika;
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

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public List<StavkaCenovnika> getStavkeCenovnika() {
		return stavkeCenovnika;
	}

	public void setStavkeCenovnika(List<StavkaCenovnika> stavkeCenovnika) {
		this.stavkeCenovnika = stavkeCenovnika;
	}

	@Override
	public String toString() {
		return "Cenovnik [idCenovnika=" + idCenovnika + ", datumPocetkaVazenja=" + datumPocetkaVazenja + ", preduzece="
				+ preduzece + ", stavkeCenovnika=" + stavkeCenovnika + "]";
	}
	
	
	
}
