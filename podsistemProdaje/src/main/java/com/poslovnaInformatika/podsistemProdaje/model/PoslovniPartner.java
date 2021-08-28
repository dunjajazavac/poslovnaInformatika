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
@Table(name = "poslovni_partner")
public class PoslovniPartner {

		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long idPoslovnogPartnera;
		
		@NotBlank(message = "Naziv poslovnog partnera ne sme biti prazan")
		@Column(name = "naziv_poslovnog_partnera", columnDefinition = "VARCHAR(20)")
		private String nazivPoslovnogPartnera;
		
		@NotBlank(message = "Adresa ne sme biti prazna")
		@Column(name = "adresa", columnDefinition = "VARCHAR(20)")
		private String adresa;
		
		@NotBlank(message = "Telefon ne sme biti prazan")
		@Column(name = "telefon", columnDefinition = "VARCHAR(20)")
		private String telefon;
		
		//fax je opcioni, bitni su telefon i email
		@Column(name = "fax", columnDefinition = "VARCHAR(20)")
		private String fax;
		
		@NotBlank(message = "Email ne sme biti prazan")
		@Column(name = "email", columnDefinition = "VARCHAR(40)")
		private String email;
		
		@NotBlank(message = "Vrsta partnera ne sme biti prazna")
		@Column(name = "vrsta_partnera", columnDefinition = "CHAR(2)")
		private String vrstaPartnera;
		
		@NotNull(message = "Mora postojati naseljeno mesto")
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "id_mesta")
		private NaseljenoMesto naseljenoMesto;
		
		@NotNull(message = "Mora postojati preduzece")
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "id_preduzeca")
		private Preduzece preduzece;
		
		
		@JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "poslovniPartner")
		private List<Faktura> fakture = new ArrayList<Faktura>();
		
		@JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "poslovniPartner")
		private List<Narudzbenica> narudzbenice = new ArrayList<Narudzbenica>();
		
		
		public PoslovniPartner() {
			super();
		}


		public PoslovniPartner(Long idPoslovnogPartnera, String nazivPoslovnogPartnera, String adresa, String telefon,
				String fax, String email, String vrstaPartnera, NaseljenoMesto naseljenoMesto, Preduzece preduzece,
				List<Faktura> fakture, List<Narudzbenica> narudzbenice) {
			super();
			this.idPoslovnogPartnera = idPoslovnogPartnera;
			this.nazivPoslovnogPartnera = nazivPoslovnogPartnera;
			this.adresa = adresa;
			this.telefon = telefon;
			this.fax = fax;
			this.email = email;
			this.vrstaPartnera = vrstaPartnera;
			this.naseljenoMesto = naseljenoMesto;
			this.preduzece = preduzece;
			this.fakture = fakture;
			this.narudzbenice = narudzbenice;
		}


		public Long getIdPoslovnogPartnera() {
			return idPoslovnogPartnera;
		}


		public void setIdPoslovnogPartnera(Long idPoslovnogPartnera) {
			this.idPoslovnogPartnera = idPoslovnogPartnera;
		}


		public String getNazivPoslovnogPartnera() {
			return nazivPoslovnogPartnera;
		}


		public void setNazivPoslovnogPartnera(String nazivPoslovnogPartnera) {
			this.nazivPoslovnogPartnera = nazivPoslovnogPartnera;
		}


		public String getAdresa() {
			return adresa;
		}


		public void setAdresa(String adresa) {
			this.adresa = adresa;
		}


		public String getTelefon() {
			return telefon;
		}


		public void setTelefon(String telefon) {
			this.telefon = telefon;
		}


		public String getFax() {
			return fax;
		}


		public void setFax(String fax) {
			this.fax = fax;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getVrstaPartnera() {
			return vrstaPartnera;
		}


		public void setVrstaPartnera(String vrstaPartnera) {
			this.vrstaPartnera = vrstaPartnera;
		}


		public NaseljenoMesto getNaseljenoMesto() {
			return naseljenoMesto;
		}


		public void setNaseljenoMesto(NaseljenoMesto naseljenoMesto) {
			this.naseljenoMesto = naseljenoMesto;
		}


		public Preduzece getPreduzece() {
			return preduzece;
		}


		public void setPreduzece(Preduzece preduzece) {
			this.preduzece = preduzece;
		}


		public List<Faktura> getFakture() {
			return fakture;
		}


		public void setFakture(List<Faktura> fakture) {
			this.fakture = fakture;
		}


		public List<Narudzbenica> getNarudzbenice() {
			return narudzbenice;
		}


		public void setNarudzbenice(List<Narudzbenica> narudzbenice) {
			this.narudzbenice = narudzbenice;
		}


		@Override
		public String toString() {
			return "PoslovniPartner [idPoslovnogPartnera=" + idPoslovnogPartnera + ", nazivPoslovnogPartnera="
					+ nazivPoslovnogPartnera + ", adresa=" + adresa + ", telefon=" + telefon + ", fax=" + fax + ", email="
					+ email + ", vrstaPartnera=" + vrstaPartnera + ", naseljenoMesto=" + naseljenoMesto + ", preduzece="
					+ preduzece + ", fakture=" + fakture + ", narudzbenice=" + narudzbenice + "]";
		}
		
	}