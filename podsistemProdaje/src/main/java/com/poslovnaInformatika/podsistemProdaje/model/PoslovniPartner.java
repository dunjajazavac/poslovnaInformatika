package com.poslovnaInformatika.podsistemProdaje.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class PoslovniPartner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name = "adresa", nullable = false)
	private String adresa;

	
	private int vrstaPartnera;
	
	@Column(name = "fax")
	private String fax;
	
	@Column(name = "tel")
	private String tel;

	@Column(name = "email")
	private String email;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "preduzece_id")
	private Preduzece preduzece;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "mesto_id")
	public NaseljenoMesto mesto;

	@OneToMany(mappedBy = "poslovniPartner",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Faktura> fakture = new ArrayList<Faktura>();

	public PoslovniPartner(long id, String naziv, String adresa, int vrstaPartnera, String fax, String tel,
			String email, com.poslovnaInformatika.podsistemProdaje.model.Preduzece preduzece, NaseljenoMesto mesto,
			List<Faktura> fakture) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.vrstaPartnera = vrstaPartnera;
		this.fax = fax;
		this.tel = tel;
		this.email = email;
		this.preduzece = preduzece;
		this.mesto = mesto;
		this.fakture = fakture;
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

	public int getVrstaPartnera() {
		return vrstaPartnera;
	}

	public void setVrstaPartnera(int vrstaPartnera) {
		this.vrstaPartnera = vrstaPartnera;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
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

}
