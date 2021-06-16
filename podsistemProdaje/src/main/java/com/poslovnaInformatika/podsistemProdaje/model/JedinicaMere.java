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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name= "jedinicaMere")
public class JedinicaMere {
	
	@Id
	@Column(name = "idJedinice")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idJediniceMere;
	
	
	@Size(max=20)
	@Column(name = "naziv", columnDefinition = "VARCHAR(20)")
	private String nazivJediniceMere;
	
	
	@Size(min=1, max=5)
	@Column(name = "skraceniNaziv", columnDefinition = "CHAR(5)")
	private String skraceniNazivJediniceMere;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false, mappedBy = "jedinicaMere")
	private List<RobaUsluge> roba = new ArrayList<RobaUsluge>();

	public JedinicaMere() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JedinicaMere(Long idJediniceMere, @Size(max = 20) String nazivJediniceMere,
			@Size(min = 1, max = 5) String skraceniNazivJediniceMere,
			List<RobaUsluge> roba) {
		super();
		this.idJediniceMere = idJediniceMere;
		this.nazivJediniceMere = nazivJediniceMere;
		this.skraceniNazivJediniceMere = skraceniNazivJediniceMere;
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

	public String getSkraceniNazivJediniceMere() {
		return skraceniNazivJediniceMere;
	}

	public void setSkraceniNazivJediniceMere(String skraceniNazivJediniceMere) {
		this.skraceniNazivJediniceMere = skraceniNazivJediniceMere;
	}

	public List<RobaUsluge> getRoba() {
		return roba;
	}

	public void setRoba(List<RobaUsluge> roba) {
		this.roba = roba;
	}

	@Override
	public String toString() {
		return "JedinicaMere [idJediniceMere=" + idJediniceMere + ", nazivJediniceMere=" + nazivJediniceMere
				+ ", skraceniNazivJediniceMere=" + skraceniNazivJediniceMere + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idJediniceMere == null) ? 0 : idJediniceMere.hashCode());
		result = prime * result + ((nazivJediniceMere == null) ? 0 : nazivJediniceMere.hashCode());
		result = prime * result + ((skraceniNazivJediniceMere == null) ? 0 : skraceniNazivJediniceMere.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JedinicaMere other = (JedinicaMere) obj;
		if (idJediniceMere == null) {
			if (other.idJediniceMere != null)
				return false;
		} else if (!idJediniceMere.equals(other.idJediniceMere))
			return false;
		if (nazivJediniceMere == null) {
			if (other.nazivJediniceMere != null)
				return false;
		} else if (!nazivJediniceMere.equals(other.nazivJediniceMere))
			return false;
		if (skraceniNazivJediniceMere == null) {
			if (other.skraceniNazivJediniceMere != null)
				return false;
		} else if (!skraceniNazivJediniceMere.equals(other.skraceniNazivJediniceMere))
			return false;
		return true;
	}
	
	

}
