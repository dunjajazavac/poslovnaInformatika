package com.poslovnaInformatika.podsistemProdaje.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import com.poslovnaInformatika.podsistemProdaje.dto.JedinicaMereDTO;
import com.poslovnaInformatika.podsistemProdaje.dto.RobaUslugeDTO;
import com.poslovnaInformatika.podsistemProdaje.model.JedinicaMere;
import com.poslovnaInformatika.podsistemProdaje.model.RobaUsluga;
import com.poslovnaInformatika.podsistemProdaje.service.JedinicaMereService;


@CrossOrigin
@RestController
@RequestMapping(value="api/jedinicaMere")
public class JedinicaMereController {
	
	@Autowired
	private JedinicaMereService jedinicaMereService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<JedinicaMereDTO>> getAllJediniceMere(){
		
		List<JedinicaMere> jMere = jedinicaMereService.findAll();
		List<JedinicaMereDTO> jedinicaMereDTO = new ArrayList<>();
		for(JedinicaMere jedinicaMere : jMere) {
			jedinicaMereDTO.add(new JedinicaMereDTO(jedinicaMere));
		}
		return new ResponseEntity<>(jedinicaMereDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<JedinicaMereDTO> getOneJM(@PathVariable Long id) {
		JedinicaMere jm = jedinicaMereService.findOne(id); 
		if(jm == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<JedinicaMereDTO>(new JedinicaMereDTO(jm), HttpStatus.OK);
	}
	
	@RequestMapping( method=RequestMethod.POST)
	public ResponseEntity<JedinicaMereDTO> saveJedinicaMere(@RequestBody JedinicaMereDTO jedinicaMereDTO) {
		JedinicaMere jm = new JedinicaMere();

		jm.setNazivJediniceMere(jedinicaMereDTO.getNaziv());
		jm.setSkraceniNaziv(jedinicaMereDTO.getSkrNaziv());

		jm = jedinicaMereService.save(jm);
		
		return new ResponseEntity<>(new JedinicaMereDTO(jm), HttpStatus.CREATED);	
	
	}
	
	@PutMapping(value="/{jedinicaMereId}", consumes="application/json")
	public ResponseEntity<JedinicaMereDTO> updateJedinicaMere(@RequestBody JedinicaMereDTO jedinicaMereDTO, @PathVariable("jedinicaMereId")Long id) {

		JedinicaMere jedinicaMere = jedinicaMereService.findOne(id); 
		
		if(jedinicaMere == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		jedinicaMere.setNazivJediniceMere(jedinicaMereDTO.getNaziv());
		jedinicaMere.setSkraceniNaziv(jedinicaMereDTO.getSkrNaziv());
		
		jedinicaMere = jedinicaMereService.save(jedinicaMere);
		
		return new ResponseEntity<JedinicaMereDTO>(new JedinicaMereDTO(jedinicaMere), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
	public ResponseEntity<Void> deleteJedinicaMere(@PathVariable Long id){
		JedinicaMere jm = jedinicaMereService.findOne(id); 
		if(jm!= null) {
			jedinicaMereService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
/*	@GetMapping(path = "/searchByNaziv")
	private ResponseEntity<List<JedinicaMere>> searchByNaziv(@RequestParam("naziv") String nazivJediniceMere, @RequestParam Pageable page) {

		Page<JedinicaMere> jediniceMere = jedinicaMereService.findAllByNazivJediniceMere(nazivJediniceMere, page);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(jediniceMere.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(jediniceMere.getContent());
		
	}
	
	@GetMapping(path = "/searchBySkraceniNaziv")
	private ResponseEntity<List<JedinicaMere>> searchBySkraceniNaziv(@RequestParam("skraceno") String skraceniNaziv, @RequestParam Pageable page) {

		Page<JedinicaMere> jediniceMere = jedinicaMereService.findAllBySkraceniNaziv(skraceniNaziv, page);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(jediniceMere.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(jediniceMere.getContent());
		
	}*/
	
	
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
	
	
	


