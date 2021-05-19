package com.poslovnaInformatika.podsistemProdaje.model;

import java.util.ArrayList;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class NaseljenoMesto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name = "postanskiBroj",nullable = false)
	private int postanskiBroj;
	
	
    @OneToMany(mappedBy = "mesto", cascade = CascadeType.ALL)
	private List<PoslovniPartner> poslovniPartneri = new ArrayList<PoslovniPartner>();
    
    @OneToMany(mappedBy = "mesto", cascade = CascadeType.ALL)
	private List<Preduzece> preduzeca = new ArrayList<Preduzece>();
	

	public NaseljenoMesto() {}


	public NaseljenoMesto(long id, String naziv, int postanskiBroj, 
			List<PoslovniPartner> poslovniPartneri, List<Preduzece> preduzeca) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.postanskiBroj = postanskiBroj;
		this.poslovniPartneri = poslovniPartneri;
		this.preduzeca = preduzeca;
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


	public int getPostanskiBroj() {
		return postanskiBroj;
	}


	public void setPostanskiBroj(int postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}


	public List<PoslovniPartner> getPoslovniPartneri() {
		return poslovniPartneri;
	}


	public void setPoslovniPartneri(List<PoslovniPartner> poslovniPartneri) {
		this.poslovniPartneri = poslovniPartneri;
	}


	public List<Preduzece> getPreduzeca() {
		return preduzeca;
	}


	public void setPreduzeca(List<Preduzece> preduzeca) {
		this.preduzeca = preduzeca;
	}
	
	
}
