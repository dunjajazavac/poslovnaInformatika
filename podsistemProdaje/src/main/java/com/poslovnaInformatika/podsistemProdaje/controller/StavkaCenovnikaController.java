package com.poslovnaInformatika.podsistemProdaje.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.intrfc.StavkaCenovnikaServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.Cenovnik;
import com.poslovnaInformatika.podsistemProdaje.model.RobaUsluga;
import com.poslovnaInformatika.podsistemProdaje.model.StavkaCenovnika;
import com.poslovnaInformatika.podsistemProdaje.service.CenovnikService;
import com.poslovnaInformatika.podsistemProdaje.service.RobaUslugaService;



@CrossOrigin
@RestController
@RequestMapping(value = "api/stavkecenovnika")
@ControllerAdvice
public class StavkaCenovnikaController {

	@Autowired
	private StavkaCenovnikaServiceInterface stavkaCenovnikaServiceInterface;
	
	@Autowired
	private RobaUslugaService robaUslugaService;
	
	@Autowired
	private CenovnikService cenovnikService;
	
	@GetMapping(path = "/all")
	public List<StavkaCenovnika> getAll(){
		return stavkaCenovnikaServiceInterface.findAll();
	}
	
	@GetMapping(path = "/p")
    public ResponseEntity<List<StavkaCenovnika>> getAllStavkaCenovnika(
                        @RequestParam("pageNo") Integer pageNo, 
                        @RequestParam("pageSize") Integer pageSize) 
    {
       
		Page<StavkaCenovnika> stavkeCenovnika = stavkaCenovnikaServiceInterface.findAll(pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(stavkeCenovnika.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(stavkeCenovnika.getContent());
    }
	
	@GetMapping(path = "/searchByCena")
	private ResponseEntity<List<StavkaCenovnika>> searchByCena(@RequestParam("cena") String cenaString,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) {

		double cena = Double.parseDouble(cenaString);
		
		Page<StavkaCenovnika> stavkeC = stavkaCenovnikaServiceInterface.findAllByCena(cena, pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(stavkeC.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(stavkeC.getContent());
		
	}
	
	@PostMapping(path = "/dodajStavkuCenovnika")
	public ResponseEntity<Void> dodajStavkuCenovnika(@Validated @RequestParam("cena") String cena,
			@RequestParam("cenovnik") String datumVazenjaCenovnika, @RequestParam("roba") String nazivRobe) throws ParseException {
		
		
		System.out.println("Cena: " + cena);
		System.out.println("Datum vazenja: " + datumVazenjaCenovnika);
		System.out.println("Naziv robe: " + nazivRobe);
	
		double cenaDouble = Double.parseDouble(cena);
		
		String datum = datumVazenjaCenovnika;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = formatter.parse(datum);
	    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		Cenovnik cenovnik = cenovnikService.findByDatumPocetkaVazenja(sqlDate);
		
		RobaUsluga robaUsluga = robaUslugaService.findByNazivRobeUsluge(nazivRobe);
		
		StavkaCenovnika stavkaCenovnika = new StavkaCenovnika();
		stavkaCenovnika.setCena(cenaDouble);
		stavkaCenovnika.setCenovnik(cenovnik);
		stavkaCenovnika.setRobaUsluga(robaUsluga);
		
		stavkaCenovnikaServiceInterface.save(stavkaCenovnika);
		
		
		System.out.println("Dodata je stavka cenovnika");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@PostMapping(path = "/izmeniStavkuCenovnika", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ResponseEntity<Void> izmeniStavkuCenovnika(@RequestParam("id") long id,
			@RequestParam("cena") String cena,
			@RequestParam("cenovnik") String datumVazenjaCenovnika,
			@RequestParam("roba") String nazivRobe) throws ParseException {
		
	    
		StavkaCenovnika stavkaCenovnika = stavkaCenovnikaServiceInterface.findOne(id);
		
		double cenaDouble = Double.parseDouble(cena);
		
		String datum = datumVazenjaCenovnika;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = formatter.parse(datum);
	    java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
		
		Cenovnik cenovnik = cenovnikService.findByDatumPocetkaVazenja(sqlDate);
		RobaUsluga robaUsluga = robaUslugaService.findByNazivRobeUsluge(nazivRobe);
		
		if(cena == null || datumVazenjaCenovnika == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		if(stavkaCenovnika != null) {
			stavkaCenovnika.setIdStavke(id);
			stavkaCenovnika.setCena(cenaDouble);
			stavkaCenovnika.setCenovnik(cenovnik);
			stavkaCenovnika.setRobaUsluga(robaUsluga);
			stavkaCenovnikaServiceInterface.save(stavkaCenovnika);
			
			
			System.out.println("Izmenjena je stavka cenovnika.");
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping(path = "/obrisiStavkuCenovnika/{id}") 
	public ResponseEntity<Void> obrisiStavkuCenovnika(@PathVariable("id") long id) {
		
		StavkaCenovnika stavkaCenovnika = stavkaCenovnikaServiceInterface.findOne(id);
		
		if(stavkaCenovnika == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		stavkaCenovnikaServiceInterface.remove(stavkaCenovnika.getIdStavke());
		
		System.out.println("Obrisana je stavka cenovnika.");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NumberFormatException.class)
	public ResponseEntity<Void> handleNumberFormat() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ParseException.class)
	public ResponseEntity<Void> handleParse() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}


