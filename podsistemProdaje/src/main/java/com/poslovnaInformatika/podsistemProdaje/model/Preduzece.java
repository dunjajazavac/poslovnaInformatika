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
@Table(name = "preduzece")
public class Preduzece {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_preduzeca")
	private Long idPreduzeca;
	
	@NotBlank(message = "Naziv preduzeca ne sme biti prazno")
	@Column(name = "naziv_preduzeca", columnDefinition = "VARCHAR(20)")
	private String nazivPreduzeca;
	
	@NotBlank(message = "Adresa preduzeca ne sme biti prazna")
	@Column(name = "adresa_preduzeca", columnDefinition = "VARCHAR(20)")
	private String adresa;
	
	@NotBlank(message = "Broj telefona ne sme biti prazan")
	@Column(name = "broj_telefona", columnDefinition = "VARCHAR(20)")
	private String brojTelefona;
	
	//firma ne mora da ima fax, bitan je telefon
	@Column(name = "fax_preduzeca", columnDefinition = "VARCHAR(20)")
	private String fax;
	
	@NotNull(message = "Naseljeno mesto ne sme biti prazno")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_mesta")
	private NaseljenoMesto naseljenoMesto;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "preduzece")
	private List<Cenovnik> cenovnici = new ArrayList<Cenovnik>();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "preduzece")
	private List<PoslovniPartner> poslovniPartneri = new ArrayList<PoslovniPartner>();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "preduzece")
	private List<Faktura> fakture = new ArrayList<Faktura>();
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "preduzece")
	private List<StavkaFakture> stavkeFakture = new ArrayList<StavkaFakture>();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "preduzece")
	private List<Narudzbenica> narudzbenice = new ArrayList<Narudzbenica>();
	
	public Preduzece() {
		super();
	}

	public Preduzece(Long idPreduzeca, String nazivPreduzeca, String adresa, String brojTelefona, String fax,
			NaseljenoMesto naseljenoMesto, List<Cenovnik> cenovnici, List<PoslovniPartner> poslovniPartneri,
			List<Faktura> fakture, List<StavkaFakture> stavkeFakture, List<Narudzbenica> narudzbenice) {
		super();
		this.idPreduzeca = idPreduzeca;
		this.nazivPreduzeca = nazivPreduzeca;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.fax = fax;
		this.naseljenoMesto = naseljenoMesto;
		this.cenovnici = cenovnici;
		this.poslovniPartneri = poslovniPartneri;
		this.fakture = fakture;
		this.stavkeFakture = stavkeFakture;
		this.narudzbenice = narudzbenice;
	}

	public Long getIdPreduzeca() {
		return idPreduzeca;
	}

	public void setIdPreduzeca(Long idPreduzeca) {
		this.idPreduzeca = idPreduzeca;
	}

	public String getNazivPreduzeca() {
		return nazivPreduzeca;
	}

	public void setNazivPreduzeca(String nazivPreduzeca) {
		this.nazivPreduzeca = nazivPreduzeca;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public NaseljenoMesto getNaseljenoMesto() {
		return naseljenoMesto;
	}

	public void setNaseljenoMesto(NaseljenoMesto naseljenoMesto) {
		this.naseljenoMesto = naseljenoMesto;
	}

	public List<Cenovnik> getCenovnici() {
		return cenovnici;
	}

	public void setCenovnici(List<Cenovnik> cenovnici) {
		this.cenovnici = cenovnici;
	}

	public List<PoslovniPartner> getPoslovniPartneri() {
		return poslovniPartneri;
	}

	public void setPoslovniPartneri(List<PoslovniPartner> poslovniPartneri) {
		this.poslovniPartneri = poslovniPartneri;
	}

	public List<Faktura> getFakture() {
		return fakture;
	}

	public void setFakture(List<Faktura> fakture) {
		this.fakture = fakture;
	}

	public List<StavkaFakture> getStavkeFakture() {
		return stavkeFakture;
	}

	public void setStavkeFakture(List<StavkaFakture> stavkeFakture) {
		this.stavkeFakture = stavkeFakture;
	}

	public List<Narudzbenica> getNarudzbenice() {
		return narudzbenice;
	}

	public void setNarudzbenice(List<Narudzbenica> narudzbenice) {
		this.narudzbenice = narudzbenice;
	}

	@Override
	public String toString() {
		return "Preduzece [idPreduzeca=" + idPreduzeca + ", nazivPreduzeca=" + nazivPreduzeca + ", adresa=" + adresa
				+ ", brojTelefona=" + brojTelefona + ", fax=" + fax + ", naseljenoMesto=" + naseljenoMesto
				+ ", cenovnici=" + cenovnici + ", poslovniPartneri=" + poslovniPartneri + ", fakture=" + fakture
				+ ", stavkeFakture=" + stavkeFakture + ", narudzbenice=" + narudzbenice + "]";
	}

}