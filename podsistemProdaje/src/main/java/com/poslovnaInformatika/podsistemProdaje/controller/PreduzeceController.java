package com.poslovnaInformatika.podsistemProdaje.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.dto.PreduzeceDTO;
import com.poslovnaInformatika.podsistemProdaje.model.NaseljenoMesto;
import com.poslovnaInformatika.podsistemProdaje.model.Preduzece;
import com.poslovnaInformatika.podsistemProdaje.service.MestoService;
import com.poslovnaInformatika.podsistemProdaje.service.PreduzeceService;
import com.sun.el.parser.ParseException;

@CrossOrigin
@RestController
@RequestMapping(value = "api/preduzece")
@ControllerAdvice
public class PreduzeceController {

	@Autowired
	private PreduzeceService pService;
	@Autowired
	private MestoService mService;

	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public List<Preduzece> getAll(){

		return pService.findAll();

	}
	@GetMapping(path = "/p")
    public ResponseEntity<List<Preduzece>> getAllPreduzece(
                        @RequestParam("pageNo") Integer pageNo, 
                        @RequestParam("pageSize") Integer pageSize) 
    {
       
		Page<Preduzece> preduzeca = pService.findAll(pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(preduzeca.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(preduzeca.getContent());
    }
	
	@GetMapping(path = "/searchByNaziv")
	private ResponseEntity<List<Preduzece>> searchByNaziv(@RequestParam("naziv") String nazivPreduzeca,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) {

		Page<Preduzece> preduzeca = pService.findAllByNazivPreduzeca(nazivPreduzeca, pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(preduzeca.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(preduzeca.getContent());
		
	}
	
	@GetMapping(path = "/searchByAdresa")
	private ResponseEntity<List<Preduzece>> searchByAdresa(@RequestParam("adresa") String adresa,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) {

		Page<Preduzece> preduzeca = pService.findAllByAdresa(adresa, pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(preduzeca.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(preduzeca.getContent());
		
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PreduzeceDTO> getPreduzece(@PathVariable Long id) {
		Preduzece p = pService.findOne(id);

		if (p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new PreduzeceDTO(p), HttpStatus.OK);
	}

	@PostMapping(path = "/dodajPreduzece")
	public ResponseEntity<Void> dodajPreduzece(@Validated @RequestParam("naziv_preduzeca") String nazivPreduzeca,
			@RequestParam("adresa_preduzeca") String adresa, @RequestParam("broj_telefona") String brojTelefona, @RequestParam("fax_preduzeca") String fax,
			@RequestParam("naziv_mesta") String nazivMesta) throws ParseException {

		NaseljenoMesto naseljenoMesto = mService.findByNazivMesta(nazivMesta);
		
		if(nazivPreduzeca == null || adresa == null || brojTelefona == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		Preduzece preduzece = new Preduzece();
		preduzece.setNazivPreduzeca(nazivPreduzeca);
		preduzece.setAdresa(adresa);
		preduzece.setBrojTelefona(brojTelefona);
		preduzece.setFax(fax);
		preduzece.setNaseljenoMesto(naseljenoMesto);
		pService.save(preduzece);
		
		System.out.println("Dodato je novo preduzece");
				
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}

	@PostMapping(path = "/izmeniPreduzece", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ResponseEntity<Void> izmeniPreduzece(@RequestParam("id") long id,
			@RequestParam("naziv_preduzeca") String nazivPreduzeca,
			@RequestParam("adresa_preduzeca") String adresa,
			@RequestParam("broj_telefona") String brojTelefona, @RequestParam("fax_preduzeca") String fax,
			@RequestParam("naziv_mesta") String nazivMesta) throws ParseException {
		
	
		Preduzece preduzece = pService.findOne(id);
		
		NaseljenoMesto naseljenoMesto = mService.findByNazivMesta(nazivMesta);
		
		if(preduzece != null) {
			preduzece.setIdPreduzeca(id);
			preduzece.setNazivPreduzeca(nazivPreduzeca);
			preduzece.setAdresa(adresa);
			preduzece.setBrojTelefona(brojTelefona);
			preduzece.setFax(fax);
			preduzece.setNaseljenoMesto(naseljenoMesto);
			pService.save(preduzece);
			
			System.out.println("Izmenjeno je preduzece.");
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	
		
	}

	@RequestMapping(value = "/obrisiPreduzece/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePreduzece(@PathVariable Long id) {
		Preduzece p = pService.findOne(id);
		if (p != null) {
			pService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
