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
@Table(name = "roba_usluga")
public class RobaUsluga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_robe_usluge")
	private Long idRobeUsluge;
	
	@NotBlank(message = "Naziv robe ne sme biti prazan")
	@Column(name = "naziv_robe_usluge", columnDefinition = "VARCHAR(30)")
	private String nazivRobeUsluge;
	
	//opis je opcioni
	@Column(name = "opis", columnDefinition = "VARCHAR(30)")
	private String opis;
	
	@Column(name = "roba", columnDefinition = "TINYINT(1)")
	private boolean roba;
	
	//@NotNull(message = "Mora da postoji jedinica mere")
	@ManyToOne
	@JoinColumn(name = "id_jedinice_mere")
	private JedinicaMere jedinicaMere;
	
	//@NotNull(message = "Mora da postoji grupa robe")
	@ManyToOne
	@JoinColumn(name = "id_grupe")
	private GrupaRobeUsluga grupaRobeUsluga;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "robaUsluga")
	private List<StavkaCenovnika> stavkeCenovnika = new ArrayList<StavkaCenovnika>();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "robaUsluga")
	private List<StavkaFakture> stavkeFakture = new ArrayList<StavkaFakture>();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "robaUsluga")
	private List<StavkaOtpremnice> stavkeOtpremnice = new ArrayList<StavkaOtpremnice>();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "robaUsluga")
	private List<StavkaNarudzbenice> stavkeNarudzbenice = new ArrayList<StavkaNarudzbenice>();
	
	public RobaUsluga() {
		super();
	}

	public RobaUsluga(Long idRobeUsluge, String nazivRobeUsluge, String opis, boolean roba, JedinicaMere jedinicaMere,
			GrupaRobeUsluga grupaRobeUsluga, List<StavkaCenovnika> stavkeCenovnika, List<StavkaFakture> stavkeFakture,
			List<StavkaOtpremnice> stavkeOtpremnice, List<StavkaNarudzbenice> stavkeNarudzbenice) {
		super();
		this.idRobeUsluge = idRobeUsluge;
		this.nazivRobeUsluge = nazivRobeUsluge;
		this.opis = opis;
		this.roba = roba;
		this.jedinicaMere = jedinicaMere;
		this.grupaRobeUsluga = grupaRobeUsluga;
		this.stavkeCenovnika = stavkeCenovnika;
		this.stavkeFakture = stavkeFakture;
		this.stavkeOtpremnice = stavkeOtpremnice;
		this.stavkeNarudzbenice = stavkeNarudzbenice;
	}

	public Long getIdRobeUsluge() {
		return idRobeUsluge;
	}

	public void setIdRobeUsluge(Long idRobeUsluge) {
		this.idRobeUsluge = idRobeUsluge;
	}

	public String getNazivRobeUsluge() {
		return nazivRobeUsluge;
	}

	public void setNazivRobeUsluge(String nazivRobeUsluge) {
		this.nazivRobeUsluge = nazivRobeUsluge;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public boolean isRoba() {
		return roba;
	}

	public void setRoba(boolean roba) {
		this.roba = roba;
	}

	public JedinicaMere getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(JedinicaMere jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public GrupaRobeUsluga getGrupaRobeUsluga() {
		return grupaRobeUsluga;
	}

	public void setGrupaRobeUsluga(GrupaRobeUsluga grupaRobeUsluga) {
		this.grupaRobeUsluga = grupaRobeUsluga;
	}

	public List<StavkaCenovnika> getStavkeCenovnika() {
		return stavkeCenovnika;
	}

	public void setStavkeCenovnika(List<StavkaCenovnika> stavkeCenovnika) {
		this.stavkeCenovnika = stavkeCenovnika;
	}

	public List<StavkaFakture> getStavkeFakture() {
		return stavkeFakture;
	}

	public void setStavkeFakture(List<StavkaFakture> stavkeFakture) {
		this.stavkeFakture = stavkeFakture;
	}

	public List<StavkaOtpremnice> getStavkeOtpremnice() {
		return stavkeOtpremnice;
	}

	public void setStavkeOtpremnice(List<StavkaOtpremnice> stavkeOtpremnice) {
		this.stavkeOtpremnice = stavkeOtpremnice;
	}

	public List<StavkaNarudzbenice> getStavkeNarudzbenice() {
		return stavkeNarudzbenice;
	}

	public void setStavkeNarudzbenice(List<StavkaNarudzbenice> stavkeNarudzbenice) {
		this.stavkeNarudzbenice = stavkeNarudzbenice;
	}

	
}
