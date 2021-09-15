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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.dto.PDVKategorijaDTO;
import com.poslovnaInformatika.podsistemProdaje.dto.RobaUslugeDTO;
import com.poslovnaInformatika.podsistemProdaje.model.GrupaRobeUsluga;
import com.poslovnaInformatika.podsistemProdaje.model.RobaUsluga;
import com.poslovnaInformatika.podsistemProdaje.service.GrupaRobeUslugaService;
import com.poslovnaInformatika.podsistemProdaje.service.RobaUslugaService;

@CrossOrigin
@RestController
@RequestMapping(value="api/robeUsluge")
@ControllerAdvice
public class RobaUslugeController {
 
	//TODO: dodati jedinicu mjere kod roba usluge  
	
	@Autowired
	private GrupaRobeUslugaService grupaRobeUslugaService; 
	
	@Autowired
	private RobaUslugaService robaUslugeService; 
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<RobaUslugeDTO>> getAllRobaUsluge(){
		
		List<RobaUsluga> robeUsluge = robaUslugeService.findAll();
		List<RobaUslugeDTO> robeUslugeDTO = new ArrayList<>();
		for(RobaUsluga robaUsluge : robeUsluge) {
			robeUslugeDTO.add(new RobaUslugeDTO(robaUsluge));
		}
		return new ResponseEntity<>(robeUslugeDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<RobaUslugeDTO> getRobaUsluge(@PathVariable Long id) {
		RobaUsluga robaUsluge = robaUslugeService.findOne(id); 
		
		if(robaUsluge == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<RobaUslugeDTO>(new RobaUslugeDTO(robaUsluge), HttpStatus.OK);
	}
	
	//@PostMapping(consumes="application/json")
	@RequestMapping( method=RequestMethod.POST)
	public ResponseEntity<RobaUslugeDTO> saveRobaUsluge(@RequestBody RobaUslugeDTO robaUslugeDTO) {
		RobaUsluga robaUsluga = new RobaUsluga();

		robaUsluga.setNazivRobeUsluge(robaUslugeDTO.getNaziv());
		robaUsluga.setOpis(robaUslugeDTO.getOpis());
		robaUsluga.setRoba(robaUslugeDTO.isRobaNaStanju());

		robaUsluga = robaUslugeService.save(robaUsluga);
		
		return new ResponseEntity<>(new RobaUslugeDTO(robaUsluga), HttpStatus.CREATED);	
		
	}
	
	/*
	@PostMapping(value="/{robaUslugeId}", consumes="application/json")
	public ResponseEntity<RobaUslugeDTO> saveRobaUsluge(@RequestBody RobaUslugeDTO robaUslugeDTO, @PathVariable("robaUslugeId")Long id) {
		RobaUsluga robaUsluge = new RobaUsluga(); 
		
		robaUsluge.setNazivRobeUsluge(robaUslugeDTO.getNaziv());
		robaUsluge.setOpis(robaUslugeDTO.getOpis());
		robaUsluge.setRoba(robaUslugeDTO.isRobaNaStanju());
		
		robaUsluge = robaUslugeService.save(robaUsluge);
		
		return new ResponseEntity<RobaUslugeDTO>(new RobaUslugeDTO(robaUsluge), HttpStatus.CREATED);
	}
	*/
	
	
	@PutMapping(value="/{robaUslugeId}", consumes="application/json")
	public ResponseEntity<RobaUslugeDTO> updateRobaUsluge(@RequestBody RobaUslugeDTO robaUslugeDTO, @PathVariable("robaUslugeId")Long id) {

		RobaUsluga robaUsluge = robaUslugeService.findOne(id); 
		
		if(robaUsluge == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		robaUsluge.setNazivRobeUsluge(robaUslugeDTO.getNaziv());
		robaUsluge.setOpis(robaUslugeDTO.getOpis());
		robaUsluge.setRoba(robaUslugeDTO.isRobaNaStanju());
		
		robaUsluge = robaUslugeService.save(robaUsluge);
		
		return new ResponseEntity<RobaUslugeDTO>(new RobaUslugeDTO(robaUsluge), HttpStatus.CREATED);
	}
	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE) 
	public ResponseEntity<Void> deleteRobaUslgue(@PathVariable Long id){
		RobaUsluga robaUsluge = robaUslugeService.findOne(id); 
		if(robaUsluge != null) {
			robaUslugeService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
