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

@Entity
public class Preduzece {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "naziv", nullable = false)
	private String naziv;

	@Column(name = "adresa", nullable = false)
	private String adresa;

	@Column(name = "fax")
	private String fax;
	
	@Column(name = "tel")
	private String tel;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mesto_id")
	private NaseljenoMesto mesto;

	@OneToMany(mappedBy = "preduzece",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Faktura> fakture = new ArrayList<Faktura>();

	@OneToMany(mappedBy = "preduzece",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PoslovniPartner> poslovniPartneri = new ArrayList<PoslovniPartner>();

	@OneToMany(mappedBy = "preduzece",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Cenovnik> cenovnik = new ArrayList<Cenovnik>();

	@OneToMany(mappedBy = "preduzece",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<GrupaRobe> grupaRobe = new ArrayList<GrupaRobe>();

	public Preduzece() {
		super();
	}

	public Preduzece(long id, String naziv, String adresa, String fax, String tel, NaseljenoMesto mesto,
			List<Faktura> fakture,
			List<PoslovniPartner> poslovniPartneri,
			List<Cenovnik> cenovnik, List<GrupaRobe> grupaRobe) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.fax = fax;
		this.tel = tel;
		this.mesto = mesto;
		this.fakture = fakture;
		this.poslovniPartneri = poslovniPartneri;
		this.cenovnik = cenovnik;
		this.grupaRobe = grupaRobe;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public NaseljenoMesto getMesto() {
		return mesto;
	}

	public void setMesto(NaseljenoMesto mesto) {
		this.mesto = mesto;
	}

	public List<Faktura> getFakture() {
		return fakture;
	}

	public void setFakture(List<Faktura> fakture) {
		this.fakture = fakture;
	}

	public List<PoslovniPartner> getPoslovniPartneri() {
		return poslovniPartneri;
	}

	public void setPoslovniPartneri(List<PoslovniPartner> poslovniPartneri) {
		this.poslovniPartneri = poslovniPartneri;
	}

	public List<Cenovnik> getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(List<Cenovnik> cenovnik) {
		this.cenovnik = cenovnik;
	}

	public List<GrupaRobe> getGrupaRobe() {
		return grupaRobe;
	}

	public void setGrupaRobe(List<GrupaRobe> grupaRobe) {
		this.grupaRobe = grupaRobe;
	}

}
