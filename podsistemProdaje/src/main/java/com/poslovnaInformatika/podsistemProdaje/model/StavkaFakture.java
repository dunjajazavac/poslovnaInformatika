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

@Entity
@Table(name = "stavka_fakture")
public class StavkaFakture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idStavke;
	
	@NotNull
	@Column(name = "kolicina", columnDefinition = "DECIMAL")
	private double kolicina;
	
	@NotNull
	@Column(name = "rabat", columnDefinition = "DECIMAL")
	private double rabat;
	
	@NotNull
	@Column(name = "jedinicna_cena", columnDefinition = "DECIMAL")
	private double jedinicnaCena;
	
	@NotNull
	@Column(name = "iznos", columnDefinition = "DECIMAL")
	private double iznos;
	
	@NotNull
	@Column(name = "pdv_stopa", columnDefinition = "DECIMAL")
	private double pdvStopa;
	
	@NotNull
	@Column(name = "osnovica_za_pdv", columnDefinition = "DECIMAL")
	private double osnovicaZaPDV;
	
	@NotNull
	@Column(name = "iznos_pdv", columnDefinition = "DECIMAL")
	private double iznosPDV;
	
	@NotNull
	@Column(name = "ukupan_iznos", columnDefinition = "DECIMAL")
	private double ukupanIznos;

	@NotNull(message = "Mora postojati preduzece")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_preduzeca")
	private Preduzece preduzece;
	
	@NotNull(message = "Mora postojati faktura")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_fakture")
	private Faktura faktura;
	
	@NotNull(message = "Mora postojati roba")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_robe")
	private RobaUsluga robaUsluga;

	
	public StavkaFakture() {
		super();
	}


	public StavkaFakture(Long idStavke, double kolicina, double rabat, double jedinicnaCena, double iznos,double pdvStopa,
			double osnovicaZaPDV, double iznosPDV, double ukupanIznos, Preduzece preduzece, Faktura faktura,
			RobaUsluga robaUsluga) {
		super();
		this.idStavke = idStavke;
		this.kolicina = kolicina;
		this.rabat = rabat;
		this.jedinicnaCena = jedinicnaCena;
		this.iznos = iznos;
		this.pdvStopa = pdvStopa;
		this.osnovicaZaPDV = osnovicaZaPDV;
		this.iznosPDV = iznosPDV;
		this.ukupanIznos = ukupanIznos;
		this.preduzece = preduzece;
		this.faktura = faktura;
		this.robaUsluga = robaUsluga;
	}


	public Long getIdStavke() {
		return idStavke;
	}


	public void setIdStavke(Long idStavke) {
		this.idStavke = idStavke;
	}


	public double getKolicina() {
		return kolicina;
	}


	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}


	public double getRabat() {
		return rabat;
	}


	public void setRabat(double rabat) {
		this.rabat = rabat;
	}


	public double getJedinicnaCena() {
		return jedinicnaCena;
	}


	public void setJedinicnaCena(double jedinicnaCena) {
		this.jedinicnaCena = jedinicnaCena;
	}


	public double getPdvStopa() {
		return pdvStopa;
	}


	public void setPdvStopa(double pdvStopa) {
		this.pdvStopa = pdvStopa;
	}


	public double getOsnovicaZaPDV() {
		return osnovicaZaPDV;
	}


	public void setOsnovicaZaPDV(double osnovicaZaPDV) {
		this.osnovicaZaPDV = osnovicaZaPDV;
	}


	public double getIznosPDV() {
		return iznosPDV;
	}


	public void setIznosPDV(double iznosPDV) {
		this.iznosPDV = iznosPDV;
	}


	public double getUkupanIznos() {
		return ukupanIznos;
	}


	public void setUkupanIznos(double ukupanIznos) {
		this.ukupanIznos = ukupanIznos;
	}


	public Preduzece getPreduzece() {
		return preduzece;
	}


	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}


	public Faktura getFaktura() {
		return faktura;
	}


	public void setFaktura(Faktura faktura) {
		this.faktura = faktura;
	}


	public RobaUsluga getRobaUsluga() {
		return robaUsluga;
	}


	public void setRobaUsluga(RobaUsluga robaUsluga) {
		this.robaUsluga = robaUsluga;
	}
	public double getIznos() {
		return iznos;
	}


	public void setIznos(double iznos) {
		this.iznos = iznos;
	}


	@Override
	public String toString() {
		return "StavkaFakture [idStavke=" + idStavke + ", kolicina=" + kolicina + ", rabat=" + rabat
				+ ", jedinicnaCena=" + jedinicnaCena + ", iznos=" + iznos + ", pdvStopa=" + pdvStopa
				+ ", osnovicaZaPDV=" + osnovicaZaPDV + ", iznosPDV=" + iznosPDV + ", ukupanIznos=" + ukupanIznos
				+ ", preduzece=" + preduzece + ", faktura=" + faktura + ", robaUsluga=" + robaUsluga + "]";
	}

}