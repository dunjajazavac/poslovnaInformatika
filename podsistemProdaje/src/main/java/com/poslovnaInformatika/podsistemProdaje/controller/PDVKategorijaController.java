
package com.poslovnaInformatika.podsistemProdaje.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.dto.PDVKategorijaDTO;
import com.poslovnaInformatika.podsistemProdaje.model.PDVKategorija;
import com.poslovnaInformatika.podsistemProdaje.repository.PDVKategorijaRepository;
import com.poslovnaInformatika.podsistemProdaje.service.PDVKategorijaService;

@CrossOrigin
@RestController
@RequestMapping(value="api/pdvKategorije")
//@ControllerAdvice
public class PDVKategorijaController {

	@Autowired
	private PDVKategorijaService pdvKategorijaService; 
	
	@Autowired
	private PDVKategorijaRepository pdvKategorijaRepository; 
	
	@RequestMapping(value="/allSorted", method = RequestMethod.GET)
	public ResponseEntity<List<PDVKategorija>> getAllPdvCategories(
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
		          orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
		        }
		      } else {
		        // sort=[field, direction]
		        orders.add(new Order(getSortDirection(sort[1]), sort[0]));
		      }
		      
		     Pageable paging = PageRequest.of(page, size);
		     Page<PDVKategorija> pdvKategorije;
		     
		     if(name == null) 
					pdvKategorije = pdvKategorijaRepository.findAll(paging);
		     else 
		    	 pdvKategorije = pdvKategorijaRepository.findByNazivKategorije(name, paging);

		     
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("totalPages", String.valueOf(pdvKategorije.getTotalPages()));
	        return ResponseEntity.ok().headers(headers).body(pdvKategorije.getContent());
		        
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
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
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<PDVKategorijaDTO> savePDVKategorija(@RequestBody PDVKategorijaDTO pdvKategorijaDTO){		
		PDVKategorija pdvKategorija = new PDVKategorija();
		pdvKategorija.setNazivKategorije(pdvKategorijaDTO.getNaziv());
	
		pdvKategorija = pdvKategorijaService.save(pdvKategorija);

		return new ResponseEntity<>(new PDVKategorijaDTO(pdvKategorija), HttpStatus.CREATED);	
	}
	
	@PutMapping(value="/{pdvKategorijeId}", consumes="application/json")
	public ResponseEntity<PDVKategorijaDTO> updatePDVKategorija(@RequestBody PDVKategorijaDTO pdvKategorijaDTO, @PathVariable("pdvKategorijeId") Long id){		
		
		PDVKategorija pdvKategorija = pdvKategorijaService.findOne(id);
		
		if(pdvKategorija == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		pdvKategorija.setNazivKategorije(pdvKategorijaDTO.getNaziv());
		
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
	

	
	//helper method 
	private Sort.Direction getSortDirection(String direction) {
	    if (direction.equals("asc")) {
	      return Sort.Direction.ASC;
	    } else if (direction.equals("desc")) {
	      return Sort.Direction.DESC;
	    }

	    return Sort.Direction.ASC;
	  }
	
}
