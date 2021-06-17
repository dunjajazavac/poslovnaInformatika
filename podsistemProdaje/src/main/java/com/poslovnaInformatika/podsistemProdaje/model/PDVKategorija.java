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


@Entity
public class PDVKategorija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idKategorije; 

	@Column(name = "naziv")
	private String naziv; 
	
	//veze: 
	//one to many sa grupa robe ili usluga 
	
	@OneToMany(mappedBy="pdvKategorija", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<PDVStopa> pdvStope = new ArrayList<PDVStopa>();

	public PDVKategorija() {
		super();
	}

	public PDVKategorija(long idKategorije, String naziv, List<PDVStopa> pdvStope) {
		super();
		this.idKategorije = idKategorije;
		this.naziv = naziv;
		this.pdvStope = pdvStope;
	}

	public long getIdKategorije() {
		return idKategorije;
	}

	public void setIdKategorije(long idKategorije) {
		this.idKategorije = idKategorije;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<PDVStopa> getPdvStope() {
		return pdvStope;
	}

	public void setPdvStope(List<PDVStopa> pdvStope) {
		this.pdvStope = pdvStope;
	}

	
}
