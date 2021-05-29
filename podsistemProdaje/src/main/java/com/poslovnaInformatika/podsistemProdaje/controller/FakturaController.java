package com.poslovnaInformatika.podsistemProdaje.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.model.Faktura;
import com.poslovnaInformatika.podsistemProdaje.repository.FakturaRepository;




@CrossOrigin
@RestController
@RequestMapping(value = "api/fakture")
@ControllerAdvice
public class FakturaController {
	@Autowired
	private FakturaRepository fakturaRepo;

	@GetMapping(path = "/all")
	public List<Faktura> getAll() {
		return fakturaRepo.findAll();
	}
	
	@GetMapping(path = "/p")
    public ResponseEntity<List<Faktura>> getAllFaktura(
                        @RequestParam Pageable page) 
    {
       
		Page<Faktura> fakture = fakturaRepo.findAll(page);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(fakture.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(fakture.getContent());
    }
	
	
	@GetMapping(value = "/searchByBrojFakture")
	public ResponseEntity<List<Faktura>> searchByBrojFakture(@RequestParam("broj") String brojFaktureString,
			@RequestParam Pageable page) {
		
		int brojFakture = Integer.parseInt(brojFaktureString);
		
		Page<Faktura> fakture = fakturaRepo.findAllByBrojFakture(brojFakture, page);
		HttpHeaders headers = new HttpHeaders();
	    headers.set("total", String.valueOf(fakture.getTotalPages()));
	    return ResponseEntity.ok().headers(headers).body(fakture.getContent());
	}
	
	@GetMapping(value = "/searchByStatusFakture")
	public ResponseEntity<List<Faktura>> searchByStatusFakture(@RequestParam("status") String statusFakture,
			@RequestParam Pageable page){
		
		Page<Faktura> fakture = fakturaRepo.findAllByStatusFakture(statusFakture, page);
		HttpHeaders headers = new HttpHeaders();
	    headers.set("total", String.valueOf(fakture.getTotalPages()));
	    return ResponseEntity.ok().headers(headers).body(fakture.getContent());
	}
	
	@DeleteMapping(path = "/obrisiFakturu/{id}")
	public ResponseEntity<Void> obrisiFakturu(@PathVariable("id") long id) {
		
		Faktura faktura = fakturaRepo.findOne(id);
		
		if(faktura == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		fakturaRepo.deleteById(faktura.getIdFakture());
		
		System.out.println("Obrisana je faktura.");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	

	

}
