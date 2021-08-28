package com.poslovnaInformatika.podsistemProdaje.model;

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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "grupa_robe_usluga")
public class GrupaRobeUsluga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGrupe;
	
	@NotBlank(message = "Naziv grupe ne sme biti prazan")
	@Column(name = "naziv_grupe", columnDefinition = "VARCHAR(30)")
	private String nazivGrupe;
	
	@NotNull(message = "Mora da postoji pdv kategorija")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kategorija_id")
	private PDVKategorija pdvKategorija;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "grupaRobeUsluga")
	private List<RobaUsluga> roba = new ArrayList<RobaUsluga>();
	
	public GrupaRobeUsluga() {
		super();
	}

	public GrupaRobeUsluga(Long idGrupe, String nazivGrupe, PDVKategorija pdvKategorija, List<RobaUsluga> roba) {
		super();
		this.idGrupe = idGrupe;
		this.nazivGrupe = nazivGrupe;
		this.pdvKategorija = pdvKategorija;
		this.roba = roba;
	}

	public Long getIdGrupe() {
		return idGrupe;
	}

	public void setIdGrupe(Long idGrupe) {
		this.idGrupe = idGrupe;
	}

	public String getNazivGrupe() {
		return nazivGrupe;
	}

	public void setNazivGrupe(String nazivGrupe) {
		this.nazivGrupe = nazivGrupe;
	}

	public PDVKategorija getPdvKategorija() {
		return pdvKategorija;
	}

	public void setPdvKategorija(PDVKategorija pdvKategorija) {
		this.pdvKategorija = pdvKategorija;
	}

	public List<RobaUsluga> getRoba() {
		return roba;
	}

	public void setRoba(List<RobaUsluga> roba) {
		this.roba = roba;
	}

	@Override
	public String toString() {
		return "GrupaRobeUsluga [idGrupe=" + idGrupe + ", nazivGrupe=" + nazivGrupe + ", pdvKategorija=" + pdvKategorija
				+ ", roba=" + roba + "]";
	}
}
