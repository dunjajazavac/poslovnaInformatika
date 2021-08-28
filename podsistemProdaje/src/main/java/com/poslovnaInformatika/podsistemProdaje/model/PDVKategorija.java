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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
@Table(name = "pdv_kategorija")
public class PDVKategorija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kategorija_id")
	private Long idKategorije;
	
	@NotBlank(message = "Naziv kategorije ne sme biti prazan")
	@Column(name = "naziv_kategorije", columnDefinition = "VARCHAR(20)")
	private String nazivKategorije;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "pdvKategorija")
	private List<PDVStopa> pdvStope = new ArrayList<PDVStopa>();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "pdvKategorija")
	private List<GrupaRobeUsluga> grupeRobe = new ArrayList<GrupaRobeUsluga>();
	
	public PDVKategorija() {
		super();
	}

	public PDVKategorija(Long idKategorije, String nazivKategorije, List<PDVStopa> pdvStope,
			List<GrupaRobeUsluga> grupeRobe) {
		super();
		this.idKategorije = idKategorije;
		this.nazivKategorije = nazivKategorije;
		this.pdvStope = pdvStope;
		this.grupeRobe = grupeRobe;
	}

	public Long getIdKategorije() {
		return idKategorije;
	}

	public void setIdKategorije(Long idKategorije) {
		this.idKategorije = idKategorije;
	}

	public String getNazivKategorije() {
		return nazivKategorije;
	}

	public void setNazivKategorije(String nazivKategorije) {
		this.nazivKategorije = nazivKategorije;
	}

	public List<PDVStopa> getPdvStope() {
		return pdvStope;
	}

	public void setPdvStope(List<PDVStopa> pdvStope) {
		this.pdvStope = pdvStope;
	}

	public List<GrupaRobeUsluga> getGrupeRobe() {
		return grupeRobe;
	}

	public void setGrupeRobe(List<GrupaRobeUsluga> grupeRobe) {
		this.grupeRobe = grupeRobe;
	}

	@Override
	public String toString() {
		return "PDVKategorija [idKategorije=" + idKategorije + ", nazivKategorije=" + nazivKategorije + ", pdvStope="
				+ pdvStope + ", grupeRobe=" + grupeRobe + "]";
	}

	

}