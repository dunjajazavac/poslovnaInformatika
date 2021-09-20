package com.poslovnaInformatika.podsistemProdaje.controller;

import java.util.ArrayList;
import java.util.List;
import com.poslovnaInformatika.podsistemProdaje.util.SortingHelperMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.dto.GrupaRobeUslugaDTO;
import com.poslovnaInformatika.podsistemProdaje.model.GrupaRobeUsluga;
import com.poslovnaInformatika.podsistemProdaje.model.JedinicaMere;
import com.poslovnaInformatika.podsistemProdaje.model.PDVKategorija;
import com.poslovnaInformatika.podsistemProdaje.repository.GrupaRobeUslugaRepository;
import com.poslovnaInformatika.podsistemProdaje.service.GrupaRobeUslugaService;
import com.poslovnaInformatika.podsistemProdaje.service.PDVKategorijaService;

@RestController
@RequestMapping(value="api/grupaRobeUsluga")
public class GrupaRobeUslugaController {
	
	@Autowired
	private GrupaRobeUslugaService grupaRobeUslugaService; 
	
	@Autowired 
	private GrupaRobeUslugaRepository grupaRobeUslugaRepository; 
	
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
	
	@PostMapping(value= "/addGroup")
	public ResponseEntity<GrupaRobeUslugaDTO> addGroup(
			@RequestParam("groupName") String groupName,
			@RequestParam("pdvCategoryName") String pdvCategoryName
			){		
		
		
		if(groupName == null) {
			return new ResponseEntity<GrupaRobeUslugaDTO>(HttpStatus.BAD_REQUEST);
		}
		
		GrupaRobeUsluga grupaRobeUsluga = new GrupaRobeUsluga();
		
		PDVKategorija pdvKategorija = pdvKategorijaService.findByNazivKategorije(pdvCategoryName);
		PDVKategorija categoryName = pdvKategorijaService.findOne(pdvKategorija.getIdKategorije());
			
		grupaRobeUsluga.setNazivGrupe(groupName);
		grupaRobeUsluga.setPdvKategorija(categoryName);
		
		grupaRobeUsluga = grupaRobeUslugaService.save(grupaRobeUsluga);
		
		return new ResponseEntity<>(new GrupaRobeUslugaDTO(grupaRobeUsluga), HttpStatus.CREATED);	
	}
	
	@PutMapping(value= "/updateGroup")
	public ResponseEntity<GrupaRobeUslugaDTO> updateGroup(
			@RequestParam("id") Long id,
			@RequestParam("groupName") String groupName,
			@RequestParam("pdvCategoryName") String pdvCategoryName){		
		
		GrupaRobeUsluga grupaRobeUsluga = grupaRobeUslugaService.findOne(id);
		
		PDVKategorija pdvKategorija = pdvKategorijaService.findByNazivKategorije(pdvCategoryName);
		PDVKategorija categoryName = pdvKategorijaService.findOne(pdvKategorija.getIdKategorije());
				
		if(grupaRobeUsluga == null) {
			return new ResponseEntity<GrupaRobeUslugaDTO>(HttpStatus.NOT_FOUND);
		}
		
		grupaRobeUsluga.setNazivGrupe(groupName);
		grupaRobeUsluga.setPdvKategorija(categoryName);
		
		grupaRobeUsluga = grupaRobeUslugaService.save(grupaRobeUsluga);
		
		return new ResponseEntity<>(new GrupaRobeUslugaDTO(grupaRobeUsluga), HttpStatus.OK);	
	}
	
	
	@RequestMapping(value="/deleteGroup/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteGroup(@PathVariable Long id){
		GrupaRobeUsluga grupaRobeUsluga = grupaRobeUslugaService.findOne(id);
		if (grupaRobeUsluga != null){
			grupaRobeUslugaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/allSorted", method = RequestMethod.GET)
	public ResponseEntity<List<GrupaRobeUsluga>> getAllGroup(
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
		     Page<GrupaRobeUsluga> grupaRobeUsluga;
		     
		     if(name == null) 
		    	 grupaRobeUsluga = grupaRobeUslugaRepository.findAll(paging);
		     else 
		    	 grupaRobeUsluga = grupaRobeUslugaRepository.findByNazivGrupe(name, paging);

		     
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("totalPages", String.valueOf(grupaRobeUsluga.getTotalPages()));
	        return ResponseEntity.ok().headers(headers).body(grupaRobeUsluga.getContent());
		        
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@RequestMapping(value="/byName", method = RequestMethod.GET)
	public ResponseEntity<List<GrupaRobeUsluga>> getAllGroupsByName(
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
		     Page<GrupaRobeUsluga> grupaRobeUsluga;
		  
		     grupaRobeUsluga = grupaRobeUslugaRepository.findByNazivGrupe(name, paging);
		   
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("totalPages", String.valueOf(grupaRobeUsluga.getTotalPages()));
	        return ResponseEntity.ok().headers(headers).body(grupaRobeUsluga.getContent());
		        
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
