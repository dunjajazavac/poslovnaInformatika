package com.poslovnaInformatika.podsistemProdaje.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.dto.PDVKategorijaDTO;
import com.poslovnaInformatika.podsistemProdaje.dto.RobaUslugeDTO;
import com.poslovnaInformatika.podsistemProdaje.intrfc.JedinicaMereServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.GrupaRobeUsluga;
import com.poslovnaInformatika.podsistemProdaje.model.JedinicaMere;
import com.poslovnaInformatika.podsistemProdaje.model.PDVKategorija;
import com.poslovnaInformatika.podsistemProdaje.model.RobaUsluga;
import com.poslovnaInformatika.podsistemProdaje.repository.GrupaRobeUslugaRepository;
import com.poslovnaInformatika.podsistemProdaje.repository.JedinicaMereRepository;
import com.poslovnaInformatika.podsistemProdaje.repository.RobaUslugaRepository;
import com.poslovnaInformatika.podsistemProdaje.service.GrupaRobeUslugaService;
import com.poslovnaInformatika.podsistemProdaje.service.JedinicaMereService;
import com.poslovnaInformatika.podsistemProdaje.service.RobaUslugaService;
import com.poslovnaInformatika.podsistemProdaje.util.SortingHelperMethod;

@CrossOrigin
@RestController
@RequestMapping(value="api/robeUsluge")
@ControllerAdvice
public class RobaUslugeController {
 	
	@Autowired
	private GrupaRobeUslugaService grupaRobeUslugaService; 
	
	@Autowired
	private GrupaRobeUslugaRepository grupaRobeUslugaRepository; 
	
	@Autowired
	private RobaUslugaService robaUslugeService; 
	
	@Autowired
	RobaUslugaRepository robaUslugaRepository;
	
	@Autowired 
	JedinicaMereServiceInterface jedinicaMereInterface; 
	
	@RequestMapping(value="/allSorted", method = RequestMethod.GET)
	public ResponseEntity<List<RobaUsluga>> getAll(
			@RequestParam(required = false) String name, 
			@RequestParam("pageNo") int page, 
			@RequestParam("pageSize") int size,
			@RequestParam(defaultValue="id, desc") String[] sort) {
		
		try {
			
			 List<Order> orders = new ArrayList<Order>();

		      if (sort[0].contains(",")) {
		        // will sort more than 2 fields
		        // sortOrder="field, direction"
		        for (String sortOrder : sort) {
		          String[] _sort = sortOrder.split(",");
		          orders.add(new Order(SortingHelperMethod.getSortDirection(_sort[1]), _sort[0]));
		        }
		      } else {
		        // sort=[field, direction]
		        orders.add(new Order(SortingHelperMethod.getSortDirection(sort[1]), sort[0]));
		      }
		      
		     Pageable paging = PageRequest.of(page, size);
		     Page<RobaUsluga> robaUsluga;
		     
		     if(name == null) 
					robaUsluga = robaUslugaRepository.findAll(paging);
		     else 
		    	 robaUsluga = robaUslugaRepository.findByNazivRobeUsluge(name, paging);

		     
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("totalPages", String.valueOf(robaUsluga.getTotalPages()));
	        return ResponseEntity.ok().headers(headers).body(robaUsluga.getContent());
		        
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	@RequestMapping(value="/byName", method = RequestMethod.GET)
	public ResponseEntity<List<RobaUsluga>> getAllByName(
			@RequestParam(required = false) String name, 
			@RequestParam("pageNo") int page, 
			@RequestParam("pageSize") int size,
			@RequestParam(defaultValue="id, desc") String[] sort) {
		
		try {
			
			 List<Order> orders = new ArrayList<Order>();

		      if (sort[0].contains(",")) {
		        // will sort more than 2 fields
		        // sortOrder="field, direction"
		        for (String sortOrder : sort) {
		          String[] _sort = sortOrder.split(",");
		          orders.add(new Order(SortingHelperMethod.getSortDirection(_sort[1]), _sort[0]));
		        }
		      } else {
		        // sort=[field, direction]
		        orders.add(new Order(SortingHelperMethod.getSortDirection(sort[1]), sort[0]));
		      }
		      
		     Pageable paging = PageRequest.of(page, size);
		     Page<RobaUsluga> robaUsluga;
		  
		     robaUsluga = robaUslugaRepository.findByNazivRobeUsluge(name, paging);
		   
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("totalPages", String.valueOf(robaUsluga.getTotalPages()));
	        return ResponseEntity.ok().headers(headers).body(robaUsluga.getContent());
		        
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
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
	
	@PostMapping(consumes="application/json", value="/addGoodsService")
	public ResponseEntity<RobaUslugeDTO> saveGoodsService(
			@RequestParam("name") String name,
			@RequestParam("desc") String desc,
			@RequestParam("goods") boolean goods,
			@RequestParam("measureName") String measureName,
			@RequestParam("groupName") String groupName) {
		
		
		GrupaRobeUsluga group = grupaRobeUslugaRepository.findByNazivGrupe(groupName);
		GrupaRobeUsluga getGroup = grupaRobeUslugaService.findOne(group.getIdGrupe());
		
		JedinicaMere measure = jedinicaMereInterface.findByNazivJediniceMere(measureName);
		JedinicaMere getMeasure = jedinicaMereInterface.findOne(measure.getIdJediniceMere());

		RobaUsluga robaUsluga = new RobaUsluga();

		robaUsluga.setGrupaRobeUsluga(null);
		robaUsluga.setJedinicaMere(getMeasure);
		robaUsluga.setNazivRobeUsluge(name);
		robaUsluga.setOpis(desc);
		robaUsluga.setRoba(goods);
		robaUsluga.setGrupaRobeUsluga(getGroup);
		
		robaUsluga = robaUslugeService.save(robaUsluga);
		
		return new ResponseEntity<>(new RobaUslugeDTO(robaUsluga), HttpStatus.CREATED);	
		
	}
	
	
	@PutMapping(value="/updateGoodsService/{id}/{name}/{desc}/{goods}/{measureName}/{groupName}", consumes="application/json")
	public ResponseEntity<RobaUslugeDTO> updateGoodsService(
			@PathVariable("id") Long id,
			@PathVariable("name") String name,
			@PathVariable("desc") String desc,
			@PathVariable("goods") boolean goods,
			@PathVariable("measureName") String measureName,
			@PathVariable("groupName") String groupName) {

		RobaUsluga robaUsluga = robaUslugeService.findOne(id); 
		
		JedinicaMere measure = jedinicaMereInterface.findByNazivJediniceMere(measureName);
		JedinicaMere getMeasure = jedinicaMereInterface.findOne(measure.getIdJediniceMere());
		
		GrupaRobeUsluga group = grupaRobeUslugaRepository.findByNazivGrupe(groupName);
		GrupaRobeUsluga getGroup = grupaRobeUslugaService.findOne(group.getIdGrupe());
		
		if(robaUsluga == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		robaUsluga.setGrupaRobeUsluga(null);
		robaUsluga.setJedinicaMere(getMeasure);
		robaUsluga.setNazivRobeUsluge(name);
		robaUsluga.setOpis(desc);
		robaUsluga.setRoba(goods);
		robaUsluga.setGrupaRobeUsluga(getGroup);
		
		robaUsluga = robaUslugeService.save(robaUsluga);
		
		return new ResponseEntity<RobaUslugeDTO>(new RobaUslugeDTO(robaUsluga), HttpStatus.CREATED);
	}
	
	

	@RequestMapping(value="/deleteGoodsService/{id}", method=RequestMethod.DELETE) 
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
