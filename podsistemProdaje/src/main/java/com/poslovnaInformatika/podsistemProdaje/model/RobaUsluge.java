package com.poslovnaInformatika.podsistemProdaje.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RobaUsluge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRobeUsluge; 

	@Column(name = "naziv")
	private String naziv; 
	
	@Column(name = "opis")
	private String opis; 
	
	//razmotriti sinonime 
	@Column(name = "robaNaStanju", columnDefinition = "TINYINT(1)")
	private boolean robaNaStanju;

	//veze: 
	//roba ili usluga one to many sa stavka fakture 
	//roba ili usluga one to many sa stavka cjenovnika 
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idJediniceMere")
	private JedinicaMere idJediniceMere;

	public long getIdRobeUsluge() {
		return idRobeUsluge;
	}

	public void setIdRobeUsluge(long idRobeUsluge) {
		this.idRobeUsluge = idRobeUsluge;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public boolean isRobaNaStanju() {
		return robaNaStanju;
	}

	public void setRobaNaStanju(boolean robaNaStanju) {
		this.robaNaStanju = robaNaStanju;
	}

	public JedinicaMere getIdJediniceMere() {
		return idJediniceMere;
	}

	public void setIdJediniceMere(JedinicaMere idJediniceMere) {
		this.idJediniceMere = idJediniceMere;
	}

	public RobaUsluge(long idRobeUsluge, String naziv, String opis, boolean robaNaStanju, JedinicaMere idJediniceMere) {
		super();
		this.idRobeUsluge = idRobeUsluge;
		this.naziv = naziv;
		this.opis = opis;
		this.robaNaStanju = robaNaStanju;
		this.idJediniceMere = idJediniceMere;
	}

	public RobaUsluge() {
		super();
	}
	
	
}
