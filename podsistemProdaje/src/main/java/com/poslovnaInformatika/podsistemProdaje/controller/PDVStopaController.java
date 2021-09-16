package com.poslovnaInformatika.podsistemProdaje.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ControllerAdvice;
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
import com.poslovnaInformatika.podsistemProdaje.dto.PDVStopaDTO;
import com.poslovnaInformatika.podsistemProdaje.model.JedinicaMere;
import com.poslovnaInformatika.podsistemProdaje.model.PDVStopa;
import com.poslovnaInformatika.podsistemProdaje.repository.PDVKategorijaRepository;
import com.poslovnaInformatika.podsistemProdaje.repository.PDVStopaRepository;
import com.poslovnaInformatika.podsistemProdaje.service.PDVStopaService;




@CrossOrigin
@RestController
@RequestMapping(value= "api/pdvstopa")
@ControllerAdvice
public class PDVStopaController {
	
	@Autowired
	PDVStopaService pdvStopaService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<PDVStopaDTO>> getAllPDVStopa(){
		
		List<PDVStopa> stopa = pdvStopaService.findAll();
		List<PDVStopaDTO> stopaDTO = new ArrayList<>();
		for(PDVStopa pdvStopa : stopa) {
			stopaDTO.add(new PDVStopaDTO(pdvStopa));
		}
		return new ResponseEntity<>(stopaDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<PDVStopaDTO> getOnePS(@PathVariable Long id) {
		PDVStopa stopa = pdvStopaService.findOne(id); 
		if(stopa == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PDVStopaDTO>(new PDVStopaDTO(stopa), HttpStatus.OK);
	}
	
	@RequestMapping( method=RequestMethod.POST)
	public ResponseEntity<PDVStopaDTO> savePDVStopa(@RequestBody PDVStopaDTO pdvStopaDTO) {
		PDVStopa pdvStopa = new PDVStopa();
		
		pdvStopa.setDatumVazenja(pdvStopaDTO.getDatum());
		pdvStopa.setProcenat(pdvStopaDTO.getProcenaat());

		pdvStopa = pdvStopaService.save(pdvStopa);
		
		return new ResponseEntity<>(new PDVStopaDTO(pdvStopa), HttpStatus.CREATED);	
	
	}
	
	@PutMapping(value="/{pdvStopaId}", consumes="application/json")
	public ResponseEntity<PDVStopaDTO> updatePDVStopa(@RequestBody PDVStopaDTO pdvStopaDTO, @PathVariable("pdvStopaId")Long id) {

		PDVStopa stopa= pdvStopaService.findOne(id); 
		
		if(stopa == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		stopa.setDatumVazenja(pdvStopaDTO.getDatum());
		stopa.setProcenat(pdvStopaDTO.getProcenaat());

		stopa = pdvStopaService.save(stopa);
		
		return new ResponseEntity<PDVStopaDTO>(new PDVStopaDTO(stopa), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
	public ResponseEntity<Void> deletePDVStopa(@PathVariable Long id){
		PDVStopa stopa= pdvStopaService.findOne(id); 
		if(stopa!= null) {
			pdvStopaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	
/*	@GetMapping(path = "/p")
    public ResponseEntity<List<PDVStopa>> getAllPDVStopa(@RequestParam Pageable page) 
    {
       Page<PDVStopa> pdvStopa = pdvStopaRepo.findAll(page);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(pdvStopa.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(pdvStopa.getContent());
    }
	
	@GetMapping(path = "/findByDatumVazenja")
	private ResponseEntity<List<PDVStopa>> searchByDatumVazenja(@RequestParam("datumVazenja") String datumVazenjaString, @RequestParam Pageable page) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = formatter.parse(datumVazenjaString);
	    java.sql.Date datumVazenja = new java.sql.Date(date.getTime());

		Page<PDVStopa> pdvStope = pdvStopaRepo.findAllByDatumVazenja(datumVazenja, page);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(pdvStope.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(pdvStope.getContent());
		
	}
	
	@GetMapping(path = "/findByProcenat")
	private ResponseEntity<List<PDVStopa>> searchByProcenat(@RequestParam("procenat") String procenatString, @RequestParam Pageable page) {
		
		double procenat = Double.parseDouble(procenatString);

		Page<PDVStopa> pdvStope = pdvStopaRepo.findAllByProcenat(procenat,page);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(pdvStope.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(pdvStope.getContent());
		
	}*/
	

	
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
}
