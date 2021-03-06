package com.poslovnaInformatika.podsistemProdaje.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.intrfc.CenovnikServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.Cenovnik;
import com.poslovnaInformatika.podsistemProdaje.model.Preduzece;
import com.poslovnaInformatika.podsistemProdaje.model.StavkaCenovnika;
import com.poslovnaInformatika.podsistemProdaje.service.CenovnikService;
import com.poslovnaInformatika.podsistemProdaje.service.PreduzeceService;
import com.poslovnaInformatika.podsistemProdaje.service.StavkaCenovnikaService;
import com.sun.el.parser.ParseException;



@CrossOrigin
@RestController
@RequestMapping(value="api/cenovnici")
@ControllerAdvice
public class CenovnikController {

	@Autowired
	private CenovnikService cenovnikService;
	
	@Autowired
	private CenovnikServiceInterface cenovnikServiceInterface;
	
	
	@Autowired
	private PreduzeceService preduzeceService;
	
	@Autowired
	private StavkaCenovnikaService stavkaCenovnikaService;
	
	@GetMapping(path="/all")
	public List<Cenovnik> getAll(){
		return cenovnikService.findAll();
	}
	@GetMapping(path = "/p")
    public ResponseEntity<List<Cenovnik>> getAllCenovnik(
                        @RequestParam("pageNo") Integer pageNo, 
                        @RequestParam("pageSize") Integer pageSize) 
    {
       
		Page<Cenovnik> cenovnici = cenovnikServiceInterface.findAll(pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(cenovnici.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(cenovnici.getContent());
    }
	
	@GetMapping(path = "/searchByDatumPocetkaVazenja")
	private ResponseEntity<List<Cenovnik>> searchByDatumPocetkaVazenja(@RequestParam("datum_vazenja") String datumString,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) throws ParseException, java.text.ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = formatter.parse(datumString);
	    java.sql.Date datumPocetkaVazenja = new java.sql.Date(date.getTime());

		Page<Cenovnik> cenovnik = cenovnikServiceInterface.findAllByDatumPocetkaVazenja(datumPocetkaVazenja, pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(cenovnik.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(cenovnik.getContent());
		
	}
	
	
	@PostMapping(path = "/dodajCenovnik")
	public ResponseEntity<Void> dodajCenovnik( @RequestParam("datum_vazenja") String datumVazenja,
			@RequestParam("preduzece") String nazivPreduzeca) throws ParseException, java.text.ParseException {
		
		System.out.println("Datum pocetka vazenja: " + datumVazenja);
	
		String datum = datumVazenja;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = formatter.parse(datum);
	    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		System.out.println("Datum"+datum);
	    Preduzece preduzece = preduzeceService.findByNazivPreduzeca(nazivPreduzeca);
	    
	    if(datumVazenja == null || nazivPreduzeca == null) {
	    	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	    }
		
		Cenovnik cenovnik = new Cenovnik();
		cenovnik.setDatumPocetkaVazenja(sqlDate);
		cenovnik.setPreduzece(preduzece);
		cenovnikService.save(cenovnik);
		
		System.out.println("Dodat je novi cenovnik");
		
		return null;
		
	}
	
	@PostMapping(path = "/izmeniCenovnik", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ResponseEntity<Void> izmeniCenovnik(@RequestParam("id") Long id,
			@RequestParam("datum_vazenja") String datumVazenja, 
			@RequestParam("preduzece") String nazivPreduzeca) throws ParseException, java.text.ParseException{
	    
	    Cenovnik cenovnik = cenovnikService.findOne(id);
	    
		String datum = datumVazenja;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = formatter.parse(datum);
	    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	    
	    Preduzece preduzece = preduzeceService.findByNazivPreduzeca(nazivPreduzeca);
	    
	    if(cenovnik != null) {
	    	cenovnik.setIdCenovnika(id);
	    	cenovnik.setDatumPocetkaVazenja(sqlDate);
	    	cenovnik.setPreduzece(preduzece);
	    	cenovnikService.save(cenovnik);
	    	
	    	System.out.println("Izmenjen je cenovnik");
	    	
	    	return new ResponseEntity<Void>(HttpStatus.OK);
	    }else {
	    	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	    }
		
	}
	
	@DeleteMapping(path = "/obrisiCenovnik/{id}")
	public ResponseEntity<Void> obrisiCenovnik(@PathVariable("id") Long id) {
		
		Cenovnik cenovnik = cenovnikService.findOne(id);
		
		if(cenovnik == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		cenovnikService.remove(cenovnik.getIdCenovnika());
		
		System.out.println("Obrisan je cenovnik");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	

	
	
	@PostMapping(path="/kopirajCenovnik")
	public ResponseEntity<Void> kopirajCenovnik(@RequestParam("datum_vazenja") String datumVazenja) throws java.text.ParseException{
		String datum = datumVazenja;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = formatter.parse(datum);
	    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

	    Cenovnik cenovnik1 = cenovnikService.findByDatumPocetkaVazenja(sqlDate);
	    Cenovnik cenovnik = cenovnikService.findOne(cenovnik1.getIdCenovnika());
	    
	    if(cenovnik != null) {
	    	Cenovnik cenovnikCopy = new Cenovnik();
	    	cenovnikCopy.setDatumPocetkaVazenja(cenovnik.getDatumPocetkaVazenja());
	    	cenovnikCopy.setPreduzece(cenovnik.getPreduzece());
	    	
	    	List<StavkaCenovnika> stavkeCenovnika = cenovnik.getStavkeCenovnika();
			
			for(StavkaCenovnika stavkaCenovnika : stavkeCenovnika) {
				StavkaCenovnika novaStavka = new StavkaCenovnika();
				novaStavka.setCena(stavkaCenovnika.getCena());
				novaStavka.setCenovnik(stavkaCenovnika.getCenovnik());
				novaStavka.setRobaUsluga(stavkaCenovnika.getRobaUsluga());
				
				cenovnikCopy.getStavkeCenovnika().add(novaStavka);
				stavkaCenovnikaService.save(novaStavka);
			}
			
			cenovnikService.save(cenovnikCopy);
	    
	    }
	    return new ResponseEntity<Void>(HttpStatus.OK);
	    
	}
	
}
	
	
	
	
	
	

