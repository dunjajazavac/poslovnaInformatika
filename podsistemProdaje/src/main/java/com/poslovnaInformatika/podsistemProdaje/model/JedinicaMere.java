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
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "jedinica_mere")
public class JedinicaMere {
	
	@Id
	@Column(name = "id_jedinice_mere")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idJediniceMere;
	
	@NotBlank(message = "Naziv jedinice mere ne sme biti prazan")
	@Size(max=20)
	@Column(name = "naziv_jedinice_mere", columnDefinition = "VARCHAR(20)")
	private String nazivJediniceMere;
	
	@NotBlank(message = "Skraceni naziv ne sme biti prazan")
	@Size(min=1, max=5)
	@Column(name = "skraceni_naziv", columnDefinition = "CHAR(5)")
	private String skraceniNaziv;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "jedinicaMere")
	private List<RobaUsluga> roba = new ArrayList<RobaUsluga>();
	
	public JedinicaMere() {
		super();
	}

	public JedinicaMere(Long idJediniceMere, String nazivJediniceMere, String skraceniNaziv, List<RobaUsluga> roba) {
		super();
		this.idJediniceMere = idJediniceMere;
		this.nazivJediniceMere = nazivJediniceMere;
		this.skraceniNaziv = skraceniNaziv;
		this.roba = roba;
	}

	public Long getIdJediniceMere() {
		return idJediniceMere;
	}

	public void setIdJediniceMere(Long idJediniceMere) {
		this.idJediniceMere = idJediniceMere;
	}

	public String getNazivJediniceMere() {
		return nazivJediniceMere;
	}

	public void setNazivJediniceMere(String nazivJediniceMere) {
		this.nazivJediniceMere = nazivJediniceMere;
	}

	public String getSkraceniNaziv() {
		return skraceniNaziv;
	}

	public void setSkraceniNaziv(String skraceniNaziv) {
		this.skraceniNaziv = skraceniNaziv;
	}

	public List<RobaUsluga> getRoba() {
		return roba;
	}

	public void setRoba(List<RobaUsluga> roba) {
		this.roba = roba;
	}

	@Override
	public String toString() {
		return "JedinicaMere [idJediniceMere=" + idJediniceMere + ", nazivJediniceMere=" + nazivJediniceMere
				+ ", skraceniNaziv=" + skraceniNaziv + ", roba=" + roba + "]";
	}

}
	


