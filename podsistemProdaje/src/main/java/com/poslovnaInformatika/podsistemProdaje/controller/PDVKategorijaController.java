package com.poslovnaInformatika.podsistemProdaje.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.dto.PDVKategorijaDTO;
import com.poslovnaInformatika.podsistemProdaje.model.PDVKategorija;
import com.poslovnaInformatika.podsistemProdaje.service.PDVKategorijaService;

@CrossOrigin
@RestController
@RequestMapping(value="api/pdvKategorije")
@ControllerAdvice
public class PDVKategorijaController {

	@Autowired
	private PDVKategorijaService pdvKategorijaService; 
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<PDVKategorijaDTO>> getAllPDVKategorija(){
		
		List<PDVKategorija> pdvKategorije = pdvKategorijaService.findAll();
		List<PDVKategorijaDTO> pdvKategorijeDto = new ArrayList<>();
		for(PDVKategorija pdvKategorija : pdvKategorije) {
			pdvKategorijeDto.add(new PDVKategorijaDTO(pdvKategorija));
		}
		return new ResponseEntity<>(pdvKategorijeDto, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<PDVKategorijaDTO> getPDVKategorija(@PathVariable Long id){
		PDVKategorija pdvKategorija = pdvKategorijaService.findOne(id);
		
		if(pdvKategorija == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new PDVKategorijaDTO(pdvKategorija), HttpStatus.OK);
	}
	
	@PostMapping(value="/{pdvKategorijeId}", consumes="application/json")
	public ResponseEntity<PDVKategorijaDTO> savePDVKategorija(@RequestBody PDVKategorijaDTO pdvKategorijaDTO, @PathVariable("pdvKategorijeId") Long id){		
		PDVKategorija pdvKategorija = new PDVKategorija();
		pdvKategorija.setNaziv(pdvKategorijaDTO.getNaziv());
		
		pdvKategorija = pdvKategorijaService.save(pdvKategorija);
	
		return new ResponseEntity<>(new PDVKategorijaDTO(pdvKategorija), HttpStatus.CREATED);	
	}
	
	@PostMapping(value="/{pdvKategorijeId}", consumes="application/json")
	public ResponseEntity<PDVKategorijaDTO> updatePDVKategorija(@RequestBody PDVKategorijaDTO pdvKategorijaDTO, @PathVariable("pdvKategorijeId") Long id){		
		
		PDVKategorija pdvKategorija = pdvKategorijaService.findOne(id);
		
		if(pdvKategorija == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		pdvKategorija.setNaziv(pdvKategorijaDTO.getNaziv());
		
		pdvKategorija = pdvKategorijaService.save(pdvKategorija);
	
		return new ResponseEntity<>(new PDVKategorijaDTO(pdvKategorija), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletePdvKategorija(@PathVariable Long id){
		PDVKategorija pdvKategorija = pdvKategorijaService.findOne(id);
		if (pdvKategorija != null){
			pdvKategorijaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
