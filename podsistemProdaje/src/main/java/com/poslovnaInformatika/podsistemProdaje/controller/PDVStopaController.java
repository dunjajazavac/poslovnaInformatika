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

import com.poslovnaInformatika.podsistemProdaje.intrfc.PDVStopaServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.PDVKategorija;
import com.poslovnaInformatika.podsistemProdaje.model.PDVStopa;
import com.poslovnaInformatika.podsistemProdaje.repository.PDVKategorijaRepository;
import com.poslovnaInformatika.podsistemProdaje.service.PDVKategorijaService;
import com.poslovnaInformatika.podsistemProdaje.service.PDVStopaService;

@CrossOrigin
@RestController
@RequestMapping(value= "api/pdvstopa")
@ControllerAdvice
public class PDVStopaController {
	
	@Autowired
	private PDVStopaServiceInterface pdvStopaServiceInterface;
	
	@Autowired
	private PDVKategorijaService pdvKategorijaService;
	
	@GetMapping(path = "/all")
	public List<PDVStopa> getAll(){
		return pdvStopaServiceInterface.findAll();
	}
	
	@GetMapping(path = "/p")
    public ResponseEntity<List<PDVStopa>> getAllPDVStopa(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) 
    {
       
		Page<PDVStopa> pdvStope = pdvStopaServiceInterface.findAll(pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(pdvStope.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(pdvStope.getContent());
    }
	
	@GetMapping(path = "/searchByDatumVazenja")
	private ResponseEntity<List<PDVStopa>> searchByDatumVazenja(@RequestParam("datum_vazenja") String datumVazenjaString,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = formatter.parse(datumVazenjaString);
	    java.sql.Date datumVazenja = new java.sql.Date(date.getTime());

		Page<PDVStopa> pdvStope = pdvStopaServiceInterface.findAllByDatumVazenja(datumVazenja, pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(pdvStope.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(pdvStope.getContent());
		
	}
	
	@GetMapping(path = "/searchByProcenat")
	private ResponseEntity<List<PDVStopa>> searchByProcenat(@RequestParam("procenat") String procenatString,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) {
		
		double procenat = Double.parseDouble(procenatString);

		Page<PDVStopa> pdvStope = pdvStopaServiceInterface.findAllByProcenat(procenat, pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(pdvStope.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(pdvStope.getContent());
		
	}
	
	@PostMapping(value = "/dodajPDVStopu")
	public ResponseEntity<Void> dodajPDVStopu(@Validated @RequestParam("datum_vazenja") String datumVazenja, @RequestParam("procenat") String procenat, 
			@RequestParam("pdvKategorija") String nazivKategorije) throws ParseException {
		
		if(datumVazenja == null || procenat == "" || nazivKategorije == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		double procenatDouble = Double.parseDouble(procenat);
		String datum = datumVazenja;
		
		PDVKategorija pdvKategorija = pdvKategorijaService.findByNazivKategorije(nazivKategorije);
		System.out.println(pdvKategorija);
	
		PDVKategorija pdvKategorija2 = pdvKategorijaService.findOne(pdvKategorija.getIdKategorije());
		System.out.println(pdvKategorija2);
		System.out.println("Procenat: " + procenat);
		System.out.println("Datum: " + datum);
		System.out.println("Naziv pdv kategorije: " + nazivKategorije);
		
		PDVStopa pdvStopa = new PDVStopa();
		pdvStopa.setProcenat(procenatDouble);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = formatter.parse(datum);
	    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		pdvStopa.setDatumVazenja(sqlDate);
		pdvStopa.setPdvKategorija(pdvKategorija2);
		pdvStopaServiceInterface.save(pdvStopa);
		
		System.out.println("Dodata je nova pdv stopa.");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/izmeniPDVStopu", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ResponseEntity<Void> izmeniPDVStopu(@RequestParam("id") long id, @RequestParam("datum_vazenja") String datumVazenja,
			@RequestParam("procenat") String procenat, @RequestParam("pdvKategorija") String nazivKategorije) throws ParseException {
		
		PDVStopa pdvStopa = pdvStopaServiceInterface.findOne(id);
		
		double procenatDouble = Double.parseDouble(procenat);
		String datum = datumVazenja;
		
		PDVKategorija pdvKategorija = pdvKategorijaService.findByNazivKategorije(nazivKategorije);
		
		PDVKategorija pdvKategorija2 = pdvKategorijaService.findOne(pdvKategorija.getIdKategorije());
		
		if(datum == null || procenat == null || nazivKategorije == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		if(pdvStopa != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date = formatter.parse(datum);
		    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		    pdvStopa.setIdStope(id);
			pdvStopa.setDatumVazenja(sqlDate);
			pdvStopa.setProcenat(procenatDouble);
			pdvStopa.setPdvKategorija(pdvKategorija2);
			pdvStopaServiceInterface.save(pdvStopa);
			
			System.out.println("Izmena pdv stope.");
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}		
	}
	
	@DeleteMapping(value = "/obrisiPDVStopu/{id}")
	public ResponseEntity<Void> obrisiPDVStopu(@PathVariable("id") long id) {
		
		PDVStopa pdvStopa = pdvStopaServiceInterface.findOne(id);
		
		if(pdvStopa == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		pdvStopaServiceInterface.remove(pdvStopa.getIdStope());
		
		System.out.println("Obrisana pdv stopa.");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
}
