package com.poslovnaInformatika.podsistemProdaje.model;

import java.sql.Date;
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
@Table(name = "otpremnica")
public class Otpremnica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOtpremnice;
	
	@NotNull
	@Column(name = "broj_otpremnice", columnDefinition = "INT")
	private int brojOtpremnice;
	
	@NotBlank(message = "Kupac ne sme biti prazan")
	@Column(name = "kupac", columnDefinition = "VARCHAR(20)")
	private String kupac;
	
	@NotBlank(message = "Adresa isporuke ne sme biti prazna")
	@Column(name = "adresa_isporuke", columnDefinition = "VARCHAR(30)")
	private String adresaIsporuke;
	
	@NotNull
	@Column(name = "datum_isporuke", columnDefinition = "DATE")
	private Date datumIsporuke;
	
	@NotBlank(message = "Prevoznik ne sme biti prazan")
	@Column(name = "prevoznik", columnDefinition = "VARCHAR(20)")
	private String prevoznik;
	
	@Column(name = "potpis_vozaca", columnDefinition = "TINYINT(1)")
	private boolean potpisVozaca;
	
	@Column(name = "primio_robu", columnDefinition = "TINYINT(1)")
	private boolean primioRobu;

	@NotNull(message = "Mora postojati faktura")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_fakture")
	private Faktura faktura;
	
	@NotNull(message = "Mora postojati narudzbenica")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_narudzbenice")
	private Narudzbenica narudzbenica;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "otpremnica")
	private List<StavkaOtpremnice> stavkeOtpremnice = new ArrayList<StavkaOtpremnice>();
	
	
	public Otpremnica() {
		super();
	}


	public Otpremnica(Long idOtpremnice, int brojOtpremnice, String kupac, String adresaIsporuke, Date datumIsporuke,
			String prevoznik, boolean potpisVozaca, boolean primioRobu, Faktura faktura, Narudzbenica narudzbenica,
			List<StavkaOtpremnice> stavkeOtpremnice) {
		super();
		this.idOtpremnice = idOtpremnice;
		this.brojOtpremnice = brojOtpremnice;
		this.kupac = kupac;
		this.adresaIsporuke = adresaIsporuke;
		this.datumIsporuke = datumIsporuke;
		this.prevoznik = prevoznik;
		this.potpisVozaca = potpisVozaca;
		this.primioRobu = primioRobu;
		this.faktura = faktura;
		this.narudzbenica = narudzbenica;
		this.stavkeOtpremnice = stavkeOtpremnice;
	}


	public Long getIdOtpremnice() {
		return idOtpremnice;
	}


	public void setIdOtpremnice(Long idOtpremnice) {
		this.idOtpremnice = idOtpremnice;
	}


	public int getBrojOtpremnice() {
		return brojOtpremnice;
	}


	public void setBrojOtpremnice(int brojOtpremnice) {
		this.brojOtpremnice = brojOtpremnice;
	}


	public String getKupac() {
		return kupac;
	}


	public void setKupac(String kupac) {
		this.kupac = kupac;
	}


	public String getAdresaIsporuke() {
		return adresaIsporuke;
	}


	public void setAdresaIsporuke(String adresaIsporuke) {
		this.adresaIsporuke = adresaIsporuke;
	}


	public Date getDatumIsporuke() {
		return datumIsporuke;
	}


	public void setDatumIsporuke(Date datumIsporuke) {
		this.datumIsporuke = datumIsporuke;
	}


	public String getPrevoznik() {
		return prevoznik;
	}


	public void setPrevoznik(String prevoznik) {
		this.prevoznik = prevoznik;
	}


	public boolean isPotpisVozaca() {
		return potpisVozaca;
	}


	public void setPotpisVozaca(boolean potpisVozaca) {
		this.potpisVozaca = potpisVozaca;
	}


	public boolean isPrimioRobu() {
		return primioRobu;
	}


	public void setPrimioRobu(boolean primioRobu) {
		this.primioRobu = primioRobu;
	}


	public Faktura getFaktura() {
		return faktura;
	}


	public void setFaktura(Faktura faktura) {
		this.faktura = faktura;
	}


	public Narudzbenica getNarudzbenica() {
		return narudzbenica;
	}


	public void setNarudzbenica(Narudzbenica narudzbenica) {
		this.narudzbenica = narudzbenica;
	}


	public List<StavkaOtpremnice> getStavkeOtpremnice() {
		return stavkeOtpremnice;
	}


	public void setStavkeOtpremnice(List<StavkaOtpremnice> stavkeOtpremnice) {
		this.stavkeOtpremnice = stavkeOtpremnice;
	}


	@Override
	public String toString() {
		return "Otpremnica [idOtpremnice=" + idOtpremnice + ", brojOtpremnice=" + brojOtpremnice + ", kupac=" + kupac
				+ ", adresaIsporuke=" + adresaIsporuke + ", datumIsporuke=" + datumIsporuke + ", prevoznik=" + prevoznik
				+ ", potpisVozaca=" + potpisVozaca + ", primioRobu=" + primioRobu + ", faktura=" + faktura
				+ ", narudzbenica=" + narudzbenica + ", stavkeOtpremnice=" + stavkeOtpremnice + "]";
	}
}
