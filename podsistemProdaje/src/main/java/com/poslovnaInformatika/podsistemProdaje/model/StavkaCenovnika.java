package com.poslovnaInformatika.podsistemProdaje.model;



import javax.persistence.CascadeType;
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
@Table(name = "stavka_cenovnika")
public class StavkaCenovnika {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idStavke;
	
	@NotNull(message = "Cena ne sme biti prazna")
	@Column(name = "cena", columnDefinition = "DECIMAL")
	private double cena;
	
	@NotNull(message = "Mora postojati cenovnik")
	@ManyToOne(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cenovnika")
	private Cenovnik cenovnik;
	
	@NotNull(message = "Mora postojati roba")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_robe_usluge")
	private RobaUsluga robaUsluga;
	

	public StavkaCenovnika() {
		super();
	}
	
	public StavkaCenovnika(Long idStavke, double cena, Cenovnik cenovnik, RobaUsluga robaUsluga) {
		super();
		this.idStavke = idStavke;
		this.cena = cena;
		this.cenovnik = cenovnik;
		this.robaUsluga = robaUsluga;
	}

	public Long getIdStavke() {
		return idStavke;
	}

	public void setIdStavke(Long idStavke) {
		this.idStavke = idStavke;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Cenovnik getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
	}

	
	public RobaUsluga getRobaUsluga() {
		return robaUsluga;
	}

	public void setRobaUsluga(RobaUsluga robaUsluga) {
		this.robaUsluga = robaUsluga;
	}

	@Override
	public String toString() {
		return "StavkaCenovnika [idStavke=" + idStavke + ", cena=" + cena + ", cenovnik=" + cenovnik + ", robaUsluga="
				+ robaUsluga + "]";
	}

	
}
