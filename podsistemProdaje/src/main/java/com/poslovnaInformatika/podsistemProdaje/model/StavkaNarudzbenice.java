package com.poslovnaInformatika.podsistemProdaje.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;



@Entity
@Table(name = "stavka_narudzbenice")
public class StavkaNarudzbenice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Jedinica mere ne sme biti prazna")
	@Column(name = "jedinica_mere", columnDefinition = "CHAR(5)")
	private String jedinicaMere;
	
	@NotNull
	@Column(name = "kolicina", columnDefinition = "DECIMAL")
	private double kolicina;
	
	@NotNull
	@Column(name = "cena", columnDefinition = "DECIMAL")
	private double cena;
	
	@NotNull
	@Column(name = "iznos", columnDefinition = "DECIMAL")
	private double iznos;
	
	@NotNull(message = "Mora postojati narudzbenica")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_narudzbenice")
	private Narudzbenica narudzbenica;
	
	@NotNull(message = "Mora postojati roba")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_robe")
	private RobaUsluga robaUsluga;
	
	public StavkaNarudzbenice() {
		super();
	}

	public StavkaNarudzbenice(Long id, String jedinicaMere, double kolicina, double cena, double iznos,
			Narudzbenica narudzbenica, RobaUsluga robaUsluga) {
		super();
		this.id = id;
		this.jedinicaMere = jedinicaMere;
		this.kolicina = kolicina;
		this.cena = cena;
		this.iznos = iznos;
		this.narudzbenica = narudzbenica;
		this.robaUsluga = robaUsluga;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public double getKolicina() {
		return kolicina;
	}

	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public Narudzbenica getNarudzbenica() {
		return narudzbenica;
	}

	public void setNarudzbenica(Narudzbenica narudzbenica) {
		this.narudzbenica = narudzbenica;
	}

	public RobaUsluga getRobaUsluga() {
		return robaUsluga;
	}

	public void setRobaUsluga(RobaUsluga robaUsluga) {
		this.robaUsluga = robaUsluga;
	}

	@Override
	public String toString() {
		return "StavkaNarudzbenice [id=" + id + ", jedinicaMere=" + jedinicaMere + ", kolicina=" + kolicina + ", cena="
				+ cena + ", iznos=" + iznos + ", narudzbenica=" + narudzbenica + ", robaUsluga=" + robaUsluga + "]";
	}
}


