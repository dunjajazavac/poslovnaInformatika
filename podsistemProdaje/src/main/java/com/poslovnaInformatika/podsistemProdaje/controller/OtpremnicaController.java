package com.poslovnaInformatika.podsistemProdaje.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.intrfc.OtpremnicaServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.Faktura;
import com.poslovnaInformatika.podsistemProdaje.model.Otpremnica;
import com.poslovnaInformatika.podsistemProdaje.model.StavkaOtpremnice;
import com.poslovnaInformatika.podsistemProdaje.service.FakturaService;



@CrossOrigin
@RestController
@RequestMapping(value="api/otpremnice")
public class OtpremnicaController {
	
	@Autowired
	OtpremnicaServiceInterface otpremnicaServiceInterface;
	
	@Autowired
	private FakturaService fakturaService;
	
	@GetMapping(path = "/all")
	public List<Otpremnica> getAll() {
		return otpremnicaServiceInterface.findAll();
	}
	
	@GetMapping(path = "/p")
    public ResponseEntity<List<Otpremnica>> getAllOtpremnica(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) 
    {
       Page<Otpremnica> otpremnice = otpremnicaServiceInterface.findAll(pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(otpremnice.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(otpremnice.getContent());
    }
	
	@GetMapping(value = "/searchByBrojOtpremnice")
	public ResponseEntity<List<Otpremnica>> searchByBrojOtpremnice(@RequestParam("broj") String brojOtpremniceString, @RequestParam("pageNo") Integer pageNo,  @RequestParam("pageSize") Integer pageSize){
		
		int brojOtpremnice = Integer.parseInt(brojOtpremniceString);
		
		Page<Otpremnica> otpremnice = otpremnicaServiceInterface.findAllByBrojOtpremnice(brojOtpremnice, pageNo, pageSize);
 		HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(otpremnice.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(otpremnice.getContent());
	}
	
	@GetMapping(value = "/searchByKupac")
	public ResponseEntity<List<Otpremnica>> searchByKupac(@RequestParam("kupac") String kupac, @RequestParam("pageNo") Integer pageNo,  @RequestParam("pageSize") Integer pageSize) {
		
		Page<Otpremnica> otpremnice = otpremnicaServiceInterface.findAllByKupac(kupac, pageNo, pageSize);
		HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(otpremnice.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(otpremnice.getContent());
	}
	
	@GetMapping(value = "/searchByPrevoznik")
	public ResponseEntity<List<Otpremnica>> searchByPrevoznik(@RequestParam("prevoznik") String prevoznik,@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
		
		Page<Otpremnica> otpremnice = otpremnicaServiceInterface.findAllByPrevoznik(prevoznik, pageNo, pageSize);
		HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(otpremnice.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(otpremnice.getContent());
	}
	
	@PostMapping(path = "/dodajOtpremnicu")
	public ResponseEntity<Void> dodajOtpremnicu(@RequestParam("broj_otpremnice") String brojOtpremnice, @RequestParam("kupac") String kupac, @RequestParam("adresa_isporuke") String adresaIsporuke, @RequestParam("datum_isporuke") String datumIsporuke, @RequestParam("prevoznik") String prevoznik,@RequestParam("faktura") String brojFakture, @RequestParam("redni_broj_proizvoda") String stavkaOtpremnice) throws ParseException {
		
		System.out.println("Broj otpremnice: " + brojOtpremnice);
		System.out.println("Kupac: " + kupac);
		System.out.println("Adresa isporuke: " + adresaIsporuke);
		System.out.println("Datum isporuke: " + datumIsporuke);
		System.out.println("Prevoznik: " + prevoznik);
		System.out.println("Faktura: " +  brojFakture);
		System.out.println("Stavka otpremnice: " + stavkaOtpremnice);
		
		int brojOtpremniceInt = Integer.parseInt(brojOtpremnice);
		
		String datum = datumIsporuke;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = formatter.parse(datum);
	    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	    
	    int brojFaktureInt = Integer.parseInt(brojFakture);
	    
	    
	    Faktura faktura = fakturaService.findByBrojFakture(brojFaktureInt);
	    
	    Otpremnica otpremnica = new Otpremnica();
	    otpremnica.setBrojOtpremnice(brojOtpremniceInt);
	    otpremnica.setKupac(kupac);
	    otpremnica.setAdresaIsporuke(adresaIsporuke);
	    otpremnica.setDatumIsporuke(sqlDate);
	    otpremnica.setPrevoznik(prevoznik);
	    otpremnica.setPotpisVozaca(false);
	    otpremnica.setPrimioRobu(false);
	    otpremnica.setFaktura(faktura);
	    otpremnicaServiceInterface.save(otpremnica);
	    
	    System.out.println("Dodata je nova otpremnica");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/izmeniOtpremnicu")
	public ResponseEntity<Void> izmeniOtpremnicu(@RequestParam("broj_otpremnice") String brojOtpremnice, @RequestParam("novi_broj") String noviBroj,@RequestParam("kupac") String kupac, @RequestParam("adresa_isporuke") String adresaIsporuke, @RequestParam("datum_isporuke") String datumIsporuke, @RequestParam("prevoznik") String prevoznik,@RequestParam("faktura") String brojFakture, @RequestParam("redni_broj_proizvoda") String stavkaOtpremnice) throws ParseException {
		
		
		int brojOtpremniceInt = Integer.parseInt(brojOtpremnice);
		
		Otpremnica otpremnica = otpremnicaServiceInterface.findByBrojOtpremnice(brojOtpremniceInt);
		
		int noviBrojInt = Integer.parseInt(noviBroj);
		String datum = datumIsporuke;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = formatter.parse(datum);
	    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	    
	    int brojFaktureInt = Integer.parseInt(brojFakture);
	    
	    Faktura faktura = fakturaService.findByBrojFakture(brojFaktureInt);
	    
		if(otpremnica != null) {
			otpremnica.setBrojOtpremnice(noviBrojInt);
			otpremnica.setKupac(kupac);
			otpremnica.setAdresaIsporuke(adresaIsporuke);
			otpremnica.setDatumIsporuke(sqlDate);
			otpremnica.setPrevoznik(prevoznik);
			otpremnica.setPotpisVozaca(true);
			otpremnica.setPrimioRobu(true);
			otpremnica.setFaktura(faktura);
			otpremnicaServiceInterface.save(otpremnica);
			
			System.out.println("Izmenjena je otpremnica.");
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
	}

	
	@DeleteMapping(path = "/obrisiOtpremnicu/{id}")
	public ResponseEntity<Void> obrisiOtpremnicu(@PathVariable("id") long id){
		
		Otpremnica otpremnica = otpremnicaServiceInterface.findOne(id);
		
		if(otpremnica == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		otpremnicaServiceInterface.remove(otpremnica.getIdOtpremnice());
		
		System.out.println("Obrisana je otpremnica.");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(path = "/otpremnicaSaFakturom")
	public List<Otpremnica> otpremnicaSaFakturom() {
		
		List<Otpremnica> otpremnice = new ArrayList<Otpremnica>();
		
		try {
			Connection conn = null;
			Statement stmt = null;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pi", "root", "root");
			
			stmt = conn.createStatement();
			
			String query = "SELECT * from otpremnica o, stavka_otpremnice s, faktura f WHERE o.id_fakture = f.id_fakture AND o.id_stavke_otpremnice = s.id_stavke_otpremnice";
			
			ResultSet rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Long idOtpremnice = rset.getLong("id_otpremnice");
				int brojOtpremnice = rset.getInt("broj_otpremnice");
				String kupac = rset.getString("kupac");
				String adresaIsporuke = rset.getString("adresa_isporuke");
				Date datumIsporuke = rset.getDate("datum_isporuke");
				String prevoznik = rset.getString("prevoznik");
				boolean potpisVozaca = rset.getBoolean("potpis_vozaca");
				boolean primioRobu = rset.getBoolean("primio_robu");
				
				
				Long idStavkeOtpremnice = rset.getLong("id_stavke_otpremnice");
				int redniBrojProizvoda = rset.getInt("redni_broj_proizvoda");
				
				Long idFakture = rset.getLong("id_fakture");
				int brojFakture = rset.getInt("broj_fakture");
				Date datumFakture = rset.getDate("datum_fakture");
				Date datumValute = rset.getDate("datum_valute");
				double ukupnaOsnovica = rset.getDouble("ukupna_osnovica");
				double ukupanPDV = rset.getDouble("ukupan_pdv");
				double ukupanIznos = rset.getDouble("ukupan_iznos");
				String statusFakture = rset.getString("status_fakture");
				
				
				Otpremnica otpremnica = new Otpremnica();
				otpremnica.setIdOtpremnice(idOtpremnice);
				otpremnica.setBrojOtpremnice(brojOtpremnice);
				otpremnica.setKupac(kupac);
				otpremnica.setAdresaIsporuke(adresaIsporuke);
				otpremnica.setDatumIsporuke(datumIsporuke);
				otpremnica.setPrevoznik(prevoznik);
				otpremnica.setPotpisVozaca(potpisVozaca);
				otpremnica.setPrimioRobu(primioRobu);
				
				StavkaOtpremnice stavkaOtpremnice = new StavkaOtpremnice();
				stavkaOtpremnice.setIdStavkeOtpremnice(idStavkeOtpremnice);
				stavkaOtpremnice.setRedniBrojProizvoda(redniBrojProizvoda);
				
				Faktura faktura = new Faktura();
				faktura.setIdFakture(idFakture);
				faktura.setBrojFakture(brojFakture);
				faktura.setDatumFakture(datumFakture);
				faktura.setDatumValute(datumValute);
				faktura.setUkupnaOsnovica(ukupnaOsnovica);
				faktura.setUkupanPDV(ukupanPDV);
				faktura.setUkupanIznos(ukupanIznos);
				faktura.setStatusFakture(statusFakture);
				otpremnica.setFaktura(faktura);
				
				otpremnice.add(otpremnica);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return otpremnice;
	}

}
