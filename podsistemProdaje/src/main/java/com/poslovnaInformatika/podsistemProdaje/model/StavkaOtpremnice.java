package com.poslovnaInformatika.podsistemProdaje.model
;

import java.io.Serializable;

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

@SuppressWarnings("serial")
@Entity
@Table(name = "stavka_otpremnice")
public class StavkaOtpremnice implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idStavkeOtpremnice;
	
	@NotNull
	@Column(name = "redni_broj_proizvoda" , columnDefinition = "INT")
	private int redniBrojProizvoda;
	
	@NotBlank(message = "Jedinica mere ne sme biti prazna")
	@Column(name = "jedinica_mere", columnDefinition = "CHAR(5)")
	private String jedinicaMere;
	
	@NotNull
	@Column(name = "cena", columnDefinition = "DECIMAL")
	private double cena;
	
	@NotNull
	@Column(name = "kolicina", columnDefinition = "DECIMAL")
	private double kolicina;
	
	//napomena je opciona
	@Column(name = "napomena", columnDefinition = "VARCHAR(20)")
	private String napomena;
	
	@NotNull(message = "Mora postojati otpremnica")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_otpremnice")
	private Otpremnica otpremnica;
	
	@NotNull(message = "Mora postojati roba")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_robe")
	private RobaUsluga robaUsluga;
	
	public StavkaOtpremnice() {
		super();
	}

	public StavkaOtpremnice(Long idStavkeOtpremnice, int redniBrojProizvoda, String jedinicaMere, double cena,
			double kolicina, String napomena, Otpremnica otpremnica, RobaUsluga robaUsluga) {
		super();
		this.idStavkeOtpremnice = idStavkeOtpremnice;
		this.redniBrojProizvoda = redniBrojProizvoda;
		this.jedinicaMere = jedinicaMere;
		this.cena = cena;
		this.kolicina = kolicina;
		this.napomena = napomena;
		this.otpremnica = otpremnica;
		this.robaUsluga = robaUsluga;
	}

	public Long getIdStavkeOtpremnice() {
		return idStavkeOtpremnice;
	}

	public void setIdStavkeOtpremnice(Long idStavkeOtpremnice) {
		this.idStavkeOtpremnice = idStavkeOtpremnice;
	}

	public int getRedniBrojProizvoda() {
		return redniBrojProizvoda;
	}

	public void setRedniBrojProizvoda(int redniBrojProizvoda) {
		this.redniBrojProizvoda = redniBrojProizvoda;
	}

	public String getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public double getKolicina() {
		return kolicina;
	}

	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public Otpremnica getOtpremnica() {
		return otpremnica;
	}

	public void setOtpremnica(Otpremnica otpremnica) {
		this.otpremnica = otpremnica;
	}

	public RobaUsluga getRobaUsluga() {
		return robaUsluga;
	}

	public void setRobaUsluga(RobaUsluga robaUsluga) {
		this.robaUsluga = robaUsluga;
	}

	@Override
	public String toString() {
		return "StavkaOtpremnice [idStavkeOtpremnice=" + idStavkeOtpremnice + ", redniBrojProizvoda="
				+ redniBrojProizvoda + ", jedinicaMere=" + jedinicaMere + ", cena=" + cena + ", kolicina=" + kolicina
				+ ", napomena=" + napomena + ", otpremnica=" + otpremnica + ", robaUsluga=" + robaUsluga + "]";
	}
	
}