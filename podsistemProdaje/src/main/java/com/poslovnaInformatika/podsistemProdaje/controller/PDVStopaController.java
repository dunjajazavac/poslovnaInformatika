//package com.poslovnaInformatika.podsistemProdaje.controller;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.List;
//
//import javax.validation.ConstraintViolationException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.poslovnaInformatika.podsistemProdaje.model.Faktura;
//import com.poslovnaInformatika.podsistemProdaje.model.PDVKategorija;
//import com.poslovnaInformatika.podsistemProdaje.model.PDVStopa;
//import com.poslovnaInformatika.podsistemProdaje.repository.PDVKategorijaRepository;
//import com.poslovnaInformatika.podsistemProdaje.repository.PDVStopaRepository;
//
//
//
//
//@CrossOrigin
//@RestController
//@RequestMapping(value= "api/pdvstopa")
//@ControllerAdvice
//public class PDVStopaController {
//	
//	@Autowired
//	PDVStopaRepository pdvStopaRepo;
//	
//	@Autowired
//	PDVKategorijaRepository pdvKategorijaRepo;
//	
//	@GetMapping(path = "/all")
//	public List<PDVStopa> getAll(){
//		return pdvStopaRepo.findAll();
//		}
//	
//	
//	@GetMapping(path = "/p")
//    public ResponseEntity<List<PDVStopa>> getAllPDVStopa(@RequestParam Pageable page) 
//    {
//       Page<PDVStopa> pdvStopa = pdvStopaRepo.findAll(page);
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("total", String.valueOf(pdvStopa.getTotalPages()));
//        return ResponseEntity.ok().headers(headers).body(pdvStopa.getContent());
//    }
//	
//	@GetMapping(path = "/findByDatumVazenja")
//	private ResponseEntity<List<PDVStopa>> searchByDatumVazenja(@RequestParam("datumVazenja") String datumVazenjaString, @RequestParam Pageable page) throws ParseException {
//		
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		java.util.Date date = formatter.parse(datumVazenjaString);
//	    java.sql.Date datumVazenja = new java.sql.Date(date.getTime());
//
//		Page<PDVStopa> pdvStope = pdvStopaRepo.findAllByDatumVazenja(datumVazenja, page);
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("total", String.valueOf(pdvStope.getTotalPages()));
//        return ResponseEntity.ok().headers(headers).body(pdvStope.getContent());
//		
//	}
//	
//	@GetMapping(path = "/findByProcenat")
//	private ResponseEntity<List<PDVStopa>> searchByProcenat(@RequestParam("procenat") String procenatString, @RequestParam Pageable page) {
//		
//		double procenat = Double.parseDouble(procenatString);
//
//		Page<PDVStopa> pdvStope = pdvStopaRepo.findAllByProcenat(procenat,page);
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("total", String.valueOf(pdvStope.getTotalPages()));
//        return ResponseEntity.ok().headers(headers).body(pdvStope.getContent());
//		
//	}
//	
//	@DeleteMapping(value = "/obrisiPDVStopu/{id}")
//	public ResponseEntity<Void> obrisiPDVStopu(@PathVariable("id") long id) {
//		
//		PDVStopa pdvStopa = pdvStopaRepo.getOne(id);
//		
//		if(pdvStopa == null) {
//			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
//		}
//		
//		pdvStopaRepo.deleteById(pdvStopa.getId());
//		
//		System.out.println("Obrisana pdv stopa.");
//		
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
//	
//	
//	@ExceptionHandler(value = ConstraintViolationException.class)
//	public ResponseEntity<Void> handle() {
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}
//	
//	
//}
