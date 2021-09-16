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
@Table(name = "faktura")
public class Faktura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFakture;
	
	@NotNull
	@Column(name = "broj_fakture", columnDefinition = "INT")
	private int brojFakture;
	
	@NotNull
	@Column(name = "datum_fakture", columnDefinition = "DATE")
	private Date datumFakture;
	
	@NotNull
	@Column(name = "datum_valute", columnDefinition = "DATE")
	private Date datumValute;
	
	@NotNull
	@Column(name = "ukupna_osnovica", columnDefinition = "DECIMAL")
	private double ukupnaOsnovica;
	
	@NotNull
	@Column(name = "ukupan_pdv", columnDefinition = "DECIMAL")
	private double ukupanPDV;
	
	@NotNull
	@Column(name = "ukupan_iznos", columnDefinition = "DECIMAL")
	private double ukupanIznos;
	
	@NotBlank(message = "Status fakture ne sme biti prazno")
	@Column(name = "status_fakture", columnDefinition = "CHAR(1)")
	private String statusFakture;
	
	@NotNull(message = "Mora postojati poslovna godina")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_godine")
	private PoslovnaGodina poslovnaGodina;
	
	@NotNull(message = "Mora postojati preduzece")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_preduzeca")
	private Preduzece preduzece;
	
	@NotNull(message = "Mora postojati poslovni partner")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_poslovnog_partnera")
	private PoslovniPartner poslovniPartner;
	
	@NotNull(message = "Mora postojati narudzbenica")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_narudzbenice")
	private Narudzbenica narudzbenica;
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "faktura")
	private List<StavkaFakture> stavkeFakture = new ArrayList<StavkaFakture>();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "faktura")
	private List<Otpremnica> otpremnice = new ArrayList<Otpremnica>();
	
	
	public Faktura() {
		super();
	}


	public Faktura(Long idFakture, int brojFakture, Date datumFakture, Date datumValute, double ukupnaOsnovica,
			double ukupanPDV, double ukupanIznos, String statusFakture, PoslovnaGodina poslovnaGodina,
			Preduzece preduzece, PoslovniPartner poslovniPartner, Narudzbenica narudzbenica,
			List<StavkaFakture> stavkeFakture, List<Otpremnica> otpremnice) {
		super();
		this.idFakture = idFakture;
		this.brojFakture = brojFakture;
		this.datumFakture = datumFakture;
		this.datumValute = datumValute;
		this.ukupnaOsnovica = ukupnaOsnovica;
		this.ukupanPDV = ukupanPDV;
		this.ukupanIznos = ukupanIznos;
		this.statusFakture = statusFakture;
		this.poslovnaGodina = poslovnaGodina;
		this.preduzece = preduzece;
		this.poslovniPartner = poslovniPartner;
		this.narudzbenica = narudzbenica;
		this.stavkeFakture = stavkeFakture;
		this.otpremnice = otpremnice;
	}


	public Long getIdFakture() {
		return idFakture;
	}


	public void setIdFakture(Long idFakture) {
		this.idFakture = idFakture;
	}


	public int getBrojFakture() {
		return brojFakture;
	}


	public void setBrojFakture(int brojFakture) {
		this.brojFakture = brojFakture;
	}


	public Date getDatumFakture() {
		return datumFakture;
	}


	public void setDatumFakture(Date datumFakture) {
		this.datumFakture = datumFakture;
	}


	public Date getDatumValute() {
		return datumValute;
	}


	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}


	public double getUkupnaOsnovica() {
		return ukupnaOsnovica;
	}


	public void setUkupnaOsnovica(double ukupnaOsnovica) {
		this.ukupnaOsnovica = ukupnaOsnovica;
	}


	public double getUkupanPDV() {
		return ukupanPDV;
	}


	public void setUkupanPDV(double ukupanPDV) {
		this.ukupanPDV = ukupanPDV;
	}


	public double getUkupanIznos() {
		return ukupanIznos;
	}


	public void setUkupanIznos(double ukupanIznos) {
		this.ukupanIznos = ukupanIznos;
	}


	public String getStatusFakture() {
		return statusFakture;
	}


	public void setStatusFakture(String statusFakture) {
		this.statusFakture = statusFakture;
	}


	public PoslovnaGodina getPoslovnaGodina() {
		return poslovnaGodina;
	}


	public void setPoslovnaGodina(PoslovnaGodina poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
	}


	public Preduzece getPreduzece() {
		return preduzece;
	}


	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}


	public PoslovniPartner getPoslovniPartner() {
		return poslovniPartner;
	}


	public void setPoslovniPartner(PoslovniPartner poslovniPartner) {
		this.poslovniPartner = poslovniPartner;
	}


	public Narudzbenica getNarudzbenica() {
		return narudzbenica;
	}


	public void setNarudzbenica(Narudzbenica narudzbenica) {
		this.narudzbenica = narudzbenica;
	}


	public List<StavkaFakture> getStavkeFakture() {
		return stavkeFakture;
	}


	public void setStavkeFakture(List<StavkaFakture> stavkeFakture) {
		this.stavkeFakture = stavkeFakture;
	}


	public List<Otpremnica> getOtpremnice() {
		return otpremnice;
	}


	public void setOtpremnice(List<Otpremnica> otpremnice) {
		this.otpremnice = otpremnice;
	}


	@Override
	public String toString() {
		return "Faktura [idFakture=" + idFakture + ", brojFakture=" + brojFakture + ", datumFakture=" + datumFakture
				+ ", datumValute=" + datumValute + ", ukupnaOsnovica=" + ukupnaOsnovica + ", ukupanPDV=" + ukupanPDV
				+ ", ukupanIznos=" + ukupanIznos + ", statusFakture=" + statusFakture + ", poslovnaGodina="
				+ poslovnaGodina + ", preduzece=" + preduzece + ", poslovniPartner=" + poslovniPartner
				+ ", narudzbenica=" + narudzbenica + ", stavkeFakture=" + stavkeFakture + ", otpremnice=" + otpremnice
				+ "]";
	}
	
}