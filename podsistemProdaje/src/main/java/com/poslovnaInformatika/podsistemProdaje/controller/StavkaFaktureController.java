package com.poslovnaInformatika.podsistemProdaje.controller;



import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.intrfc.StavkaFaktureServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.Faktura;
import com.poslovnaInformatika.podsistemProdaje.model.Preduzece;
import com.poslovnaInformatika.podsistemProdaje.model.RobaUsluga;
import com.poslovnaInformatika.podsistemProdaje.model.StavkaFakture;
import com.poslovnaInformatika.podsistemProdaje.repository.FakturaRepository;
import com.poslovnaInformatika.podsistemProdaje.service.PreduzeceService;
import com.poslovnaInformatika.podsistemProdaje.service.RobaUslugaService;

@CrossOrigin
@RestController
@RequestMapping (value = "api/stavkefakture")
@ControllerAdvice
public class StavkaFaktureController {
	@Autowired
	private StavkaFaktureServiceInterface stavkaFaktureServiceInterface;
	
	@Autowired
	private RobaUslugaService robaUslugaService;
	
	@Autowired
	private PreduzeceService preduzeceService;
	
	@GetMapping(path = "/all")
	public List<StavkaFakture> getAll(){
		return stavkaFaktureServiceInterface.findAll();
	}
	
	@GetMapping(path = "/p")
    public ResponseEntity<List<StavkaFakture>> getAllStavkaFakture(
                        @RequestParam("pageNo") Integer pageNo, 
                        @RequestParam("pageSize") Integer pageSize) 
    {
       
		Page<StavkaFakture> stavkeFakture = stavkaFaktureServiceInterface.findAll(pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(stavkeFakture.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(stavkeFakture.getContent());
    }
	
	
	@GetMapping(value = "/searchByIznos")
	public ResponseEntity<List<StavkaFakture>> searchByIznos(@RequestParam("iznos") String iznosString,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) {
		  
		double iznos = Double.parseDouble(iznosString);
		
		Page<StavkaFakture> stavkeFakture = stavkaFaktureServiceInterface.findAllByIznos(iznos, pageNo, pageSize);
		HttpHeaders headers = new HttpHeaders();
	    headers.set("total", String.valueOf(stavkeFakture.getTotalPages()));
	    return ResponseEntity.ok().headers(headers).body(stavkeFakture.getContent());
	}
	
	@PostMapping(path = "/dodajStavkuFakture")
	public ResponseEntity<Void> dodajStavkuFakture(@RequestParam("kolicina") String kolicina,
			@RequestParam("rabat") String rabat, @RequestParam("jedinicna_cena") String jedinicnaCena, 
			@RequestParam("pdv_stopa") String pdvStopa, @RequestParam("osnovica_pdv") String osnovicaZaPDV, 
			@RequestParam("iznos_pdv") String iznosPDV, @RequestParam("ukupan_iznos") String ukupanIznos,
			@RequestParam("naziv_preduzeca") String nazivPreduzeca, @RequestParam("roba") String roba) {
		
		System.out.println("Kolicina: " + kolicina);
		System.out.println("Rabat: " + rabat);
		System.out.println("Jedinicna cena: " + jedinicnaCena);
		System.out.println("PDV stopa: " + pdvStopa);
		System.out.println("Osnovica za pdv: " + osnovicaZaPDV);
		System.out.println("Iznos pdv: " + iznosPDV);
		System.out.println("Ukupan iznos: " + ukupanIznos);
		System.out.println("Naziv preduzeca: " + nazivPreduzeca);
		System.out.println("Roba: " + roba);
		
		
		double kolicinaDouble = Double.parseDouble(kolicina);
		double rabatDouble = Double.parseDouble(rabat);
		double jedinicnaCenaDouble = Double.parseDouble(jedinicnaCena);
		
		double pdvStopaDouble = Double.parseDouble(pdvStopa);
		double osnovicaZaPDVDouble = Double.parseDouble(osnovicaZaPDV);
		double iznosPDVDouble = Double.parseDouble(iznosPDV);
		double ukupanIznosDouble = Double.parseDouble(ukupanIznos);
		
		Preduzece preduzece = preduzeceService.findByNazivPreduzeca(nazivPreduzeca);
		RobaUsluga robaUsluga = robaUslugaService.findByNazivRobeUsluge(roba); 
		
		StavkaFakture stavkaFakture = new StavkaFakture();
		stavkaFakture.setKolicina(kolicinaDouble);
		stavkaFakture.setRabat(rabatDouble);
		stavkaFakture.setJedinicnaCena(jedinicnaCenaDouble);
		stavkaFakture.setPdvStopa(pdvStopaDouble);
		stavkaFakture.setOsnovicaZaPDV(osnovicaZaPDVDouble);
		stavkaFakture.setIznosPDV(iznosPDVDouble);
		stavkaFakture.setUkupanIznos(ukupanIznosDouble);
		stavkaFakture.setPreduzece(preduzece);
		stavkaFakture.setRobaUsluga(robaUsluga);
		stavkaFaktureServiceInterface.save(stavkaFakture);
		
		System.out.println("Dodata je stavka fakture");

		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/izmeniStavkuFakture")
	public ResponseEntity<Void> izmeniStavkuFakture (@RequestParam("jedinicna_cena") String jedinicnaCena,
			@RequestParam("kolicina") String kolicina,
			@RequestParam("rabat") String rabat, @RequestParam("nova_cena") String novaJedinicnaCena, 
			@RequestParam("pdv_stopa") String pdvStopa, @RequestParam("osnovica_pdv") String osnovicaZaPDV, 
			@RequestParam("iznos_pdv") String iznosPDV, @RequestParam("ukupan_iznos") String ukupanIznos,
			@RequestParam("naziv_preduzeca") String nazivPreduzeca, @RequestParam("roba") String roba) {
		
		double jedinicnaCenaDouble = Double.parseDouble(jedinicnaCena);
		
		StavkaFakture stavkaFakture = stavkaFaktureServiceInterface.findByJedinicnaCena(jedinicnaCenaDouble);
		
		double kolicinaDouble = Double.parseDouble(kolicina);
		double rabatDouble = Double.parseDouble(rabat);
		double novaJedinicnaCenaDouble = Double.parseDouble(novaJedinicnaCena);
		
		double pdvStopaDouble = Double.parseDouble(pdvStopa);
		double osnovicaZaPDVDouble = Double.parseDouble(osnovicaZaPDV);
		double iznosPDVDouble = Double.parseDouble(iznosPDV);
		double ukupanIznosDouble = Double.parseDouble(ukupanIznos);
		
		Preduzece preduzece = preduzeceService.findByNazivPreduzeca(nazivPreduzeca);
		
		RobaUsluga robaUsluga = robaUslugaService.findByNazivRobeUsluge(roba); 
		
		if(stavkaFakture != null) {
			stavkaFakture.setKolicina(kolicinaDouble);
			stavkaFakture.setRabat(rabatDouble);
			stavkaFakture.setJedinicnaCena(novaJedinicnaCenaDouble);
			stavkaFakture.setPdvStopa(pdvStopaDouble);
			stavkaFakture.setOsnovicaZaPDV(osnovicaZaPDVDouble);
			stavkaFakture.setIznosPDV(iznosPDVDouble);
			stavkaFakture.setUkupanIznos(ukupanIznosDouble);
			stavkaFakture.setPreduzece(preduzece);
			stavkaFakture.setRobaUsluga(robaUsluga);
			
			stavkaFaktureServiceInterface.save(stavkaFakture);
			
			System.out.println("Izmenjena je stavka fakture.");
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	@DeleteMapping(path = "/obrisiStavkuFakture/{id}")
	public ResponseEntity<Void> obrisiStavkuFakture(@PathVariable("id") long id) {
		
		StavkaFakture stavkaFakture = stavkaFaktureServiceInterface.findOne(id);
		
		if(stavkaFakture == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		stavkaFaktureServiceInterface.remove(stavkaFakture.getIdStavke());
		
		System.out.println("Obrisana je stavka fakture.");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}