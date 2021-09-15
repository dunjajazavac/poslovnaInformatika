package com.poslovnaInformatika.podsistemProdaje.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.dto.GrupaRobeUslugaDTO;
import com.poslovnaInformatika.podsistemProdaje.model.GrupaRobeUsluga;
import com.poslovnaInformatika.podsistemProdaje.model.PDVKategorija;
import com.poslovnaInformatika.podsistemProdaje.service.GrupaRobeUslugaService;
import com.poslovnaInformatika.podsistemProdaje.service.PDVKategorijaService;

@RestController
@RequestMapping(value="api/grupaRobeUsluga")
public class GrupaRobeUslugaController {
	
	@Autowired
	private GrupaRobeUslugaService grupaRobeUslugaService; 
	
	@Autowired
	private PDVKategorijaService pdvKategorijaService; 
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<GrupaRobeUslugaDTO>> getAllGrupaUsluga() {
		List<GrupaRobeUsluga> grupeRobaUsluga = grupaRobeUslugaService.findAll(); 
		List<GrupaRobeUslugaDTO> grupeRobaUslugaDTO = new ArrayList<>();
		
		for(GrupaRobeUsluga gru : grupeRobaUsluga) {
			grupeRobaUslugaDTO.add(new GrupaRobeUslugaDTO(gru));
		}
		
		return new ResponseEntity<>(grupeRobaUslugaDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<GrupaRobeUslugaDTO> getGrupaUsluga(@PathVariable("id") Long id) {
		GrupaRobeUsluga grupeRobaUsluga = grupaRobeUslugaService.findOne(id); 
		
		if (grupeRobaUsluga == null) {
			return new ResponseEntity<GrupaRobeUslugaDTO>(HttpStatus.NOT_FOUND); 
		}
		
		
		return new ResponseEntity<>(new GrupaRobeUslugaDTO(grupeRobaUsluga), HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json", value= "/{idPdvKat}")
	public ResponseEntity<GrupaRobeUslugaDTO> saveGrupaRobeUsluga(@RequestBody GrupaRobeUslugaDTO grupaRobeUslugaDTO, @PathVariable("idPdvKat") Long id){		
		GrupaRobeUsluga grupaRobeUsluga = new GrupaRobeUsluga();
		
		PDVKategorija pdvKategorija = pdvKategorijaService.findOne(id); 
		
		if(pdvKategorija == null) {
			return new ResponseEntity<GrupaRobeUslugaDTO>(HttpStatus.NOT_FOUND);
		}
		
		grupaRobeUsluga.setNazivGrupe(grupaRobeUslugaDTO.getNazivGrupe());
		grupaRobeUsluga.setRoba(null);
		grupaRobeUsluga.setPdvKategorija(pdvKategorija);
		
		grupaRobeUsluga = grupaRobeUslugaService.save(grupaRobeUsluga);
		
		return new ResponseEntity<>(new GrupaRobeUslugaDTO(grupaRobeUsluga), HttpStatus.CREATED);	
	}
	
	@PutMapping(consumes="application/json", value= "/{id}")
	public ResponseEntity<GrupaRobeUslugaDTO> updateGrupaRobeUsluga(@RequestBody GrupaRobeUslugaDTO grupaRobeUslugaDTO, @PathVariable("id") Long id){		
		
		GrupaRobeUsluga grupaRobeUsluga = grupaRobeUslugaService.findOne(id);
				
		if(grupaRobeUsluga == null) {
			return new ResponseEntity<GrupaRobeUslugaDTO>(HttpStatus.NOT_FOUND);
		}
		
		grupaRobeUsluga.setNazivGrupe(grupaRobeUslugaDTO.getNazivGrupe());
		grupaRobeUsluga.setRoba(null);
		
		grupaRobeUsluga = grupaRobeUslugaService.save(grupaRobeUsluga);
		
		return new ResponseEntity<>(new GrupaRobeUslugaDTO(grupaRobeUsluga), HttpStatus.CREATED);	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteGrupaRobeUsluga(@PathVariable Long id){
		GrupaRobeUsluga grupaRobeUsluga = grupaRobeUslugaService.findOne(id);
		if (grupaRobeUsluga != null){
			grupaRobeUslugaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
