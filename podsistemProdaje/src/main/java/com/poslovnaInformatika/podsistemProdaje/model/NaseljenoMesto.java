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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name = "naseljeno_mesto")
public class NaseljenoMesto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mesta")
	private Long idMesta;

	@NotNull(message = "PTT broj ne sme biti 0 ili prazan")
	@Column(name = "ptt_broj", columnDefinition = "INT")
	private int pttBroj;
	
	@NotBlank(message = "Naziv mesta ne sme biti prazan")
	@Column(name = "naziv_mesta", columnDefinition = "VARCHAR(20)")
	private String nazivMesta;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "naseljenoMesto")
	private List<Preduzece> preduzeca = new ArrayList<Preduzece>();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "naseljenoMesto")
	private List<PoslovniPartner> poslovniPartneri = new ArrayList<PoslovniPartner>();

	public NaseljenoMesto() {
		super();
	}

	public NaseljenoMesto(Long idMesta, int pttBroj, String nazivMesta, List<Preduzece> preduzeca,
			List<PoslovniPartner> poslovniPartneri) {
		super();
		this.idMesta = idMesta;
		this.pttBroj = pttBroj;
		this.nazivMesta = nazivMesta;
		this.preduzeca = preduzeca;
		this.poslovniPartneri = poslovniPartneri;
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

	public void setPttBroj(int pttBroj) {
		this.pttBroj = pttBroj;
	}

	public String getNazivMesta() {
		return nazivMesta;
	}

	public void setNazivMesta(String nazivMesta) {
		this.nazivMesta = nazivMesta;
	}

	public List<Preduzece> getPreduzeca() {
		return preduzeca;
	}

	public void setPreduzeca(List<Preduzece> preduzeca) {
		this.preduzeca = preduzeca;
	}

	public List<PoslovniPartner> getPoslovniPartneri() {
		return poslovniPartneri;
	}

	public void setPoslovniPartneri(List<PoslovniPartner> poslovniPartneri) {
		this.poslovniPartneri = poslovniPartneri;
	}

	@Override
	public String toString() {
		return "NaseljenoMesto [idMesta=" + idMesta + ", pttBroj=" + pttBroj + ", nazivMesta=" + nazivMesta
				+ ", preduzeca=" + preduzeca + ", poslovniPartneri=" + poslovniPartneri + "]";
	}
	
	
}