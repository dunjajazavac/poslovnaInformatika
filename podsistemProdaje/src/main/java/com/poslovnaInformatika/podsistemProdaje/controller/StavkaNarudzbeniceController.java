package com.poslovnaInformatika.podsistemProdaje.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.intrfc.StavkaNarudzbeniceServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.Narudzbenica;
import com.poslovnaInformatika.podsistemProdaje.model.RobaUsluga;
import com.poslovnaInformatika.podsistemProdaje.model.StavkaNarudzbenice;
import com.poslovnaInformatika.podsistemProdaje.service.NarudzbenicaService;
import com.poslovnaInformatika.podsistemProdaje.service.RobaUslugaService;
import com.poslovnaInformatika.podsistemProdaje.service.StavkaNarudzbeniceService;


@CrossOrigin
@RestController
@RequestMapping(value = "api/stavkenarudzbenice")
public class StavkaNarudzbeniceController {
	@Autowired
	private StavkaNarudzbeniceServiceInterface stavkaNarudzbeniceServiceInterface;
	
	@Autowired
	private StavkaNarudzbeniceService stavkaService;
	@Autowired
	private NarudzbenicaService narudzbenicaService;
	
	@Autowired
	private RobaUslugaService robaUslugaService;
	
	
	@GetMapping(path = "/all")
	public List<StavkaNarudzbenice> getAll() {
		return stavkaService.findAll();
	}
	
	@GetMapping(path = "/p")
    public ResponseEntity<List<StavkaNarudzbenice>> getAllStavkaNarudzbenice(
                        @RequestParam("pageNo") Integer pageNo, 
                        @RequestParam("pageSize") Integer pageSize) 
    {
       
		Page<StavkaNarudzbenice> stavkeNarudzbenice = stavkaNarudzbeniceServiceInterface.findAll(pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(stavkeNarudzbenice.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(stavkeNarudzbenice.getContent());
    }
	
	
	@GetMapping(value = "/searchByCena")
	public ResponseEntity<List<StavkaNarudzbenice>> searchByCena(@RequestParam("cena") String cenaString,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) {
		
		double cena = Double.parseDouble(cenaString);
		
		Page<StavkaNarudzbenice> stavkeN = stavkaNarudzbeniceServiceInterface.findAllByCena(cena, pageNo, pageSize);
		HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(stavkeN.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(stavkeN.getContent());
	}
	
	@PostMapping(value = "/dodajStavkuNarudzbenice")
	public ResponseEntity<Void> dodajStavkuNarudzbenice(@RequestParam("jedinica_mere") String jedinicaMere,
			@RequestParam("kolicina") String kolicinaString, @RequestParam("cena") String cenaString,
			@RequestParam("narudzbenica") String broj, 
			@RequestParam("roba") String robaString){
		
		int brojNarudzbenice = Integer.parseInt(broj);
		
		double kolicina = Double.parseDouble(kolicinaString);
		
		double cena = Double.parseDouble(cenaString);
		
	//	double iznos = Double.parseDouble(iznosString);
		
		Narudzbenica narudzbenica1 = narudzbenicaService.findByBrojNarudzbenice(brojNarudzbenice);
		Narudzbenica narudzbenica = narudzbenicaService.findOne(narudzbenica1.getId());
		
		RobaUsluga robaUsluga1 = robaUslugaService.findByNazivRobeUsluge(robaString);
		RobaUsluga robaUsluga = robaUslugaService.findOne(robaUsluga1.getIdRobeUsluge());
				
		if(jedinicaMere == null || kolicina == 0 || cena == 0 || narudzbenica == null || robaUsluga == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		double iznos = kolicina * cena;
		
		System.out.println("Iznos stavke narudzbenice: " + iznos);
		
		StavkaNarudzbenice stavkaNarudzbenice = new StavkaNarudzbenice();
		stavkaNarudzbenice.setJedinicaMere(jedinicaMere);
		stavkaNarudzbenice.setKolicina(kolicina);
		stavkaNarudzbenice.setCena(cena);
		stavkaNarudzbenice.setIznos(iznos);
		stavkaNarudzbenice.setNarudzbenica(narudzbenica);
		stavkaNarudzbenice.setRobaUsluga(robaUsluga);
		stavkaService.save(stavkaNarudzbenice);
		
		System.out.println("Dodata je nova stavka narudzbenice");
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}

	@DeleteMapping(value = "/obrisiStavkuNarudzbenice/{id}")
	private ResponseEntity<Void> obrisiStavkuNarudzbenice(@PathVariable("id") long id) {
		
		StavkaNarudzbenice stavkaNarudzbenice = stavkaService.findOne(id);
		
		if(stavkaNarudzbenice == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		stavkaService.remove(stavkaNarudzbenice.getId());
		
		System.out.println("Obrisana je stavka narudzbenice.");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NumberFormatException.class)
	public ResponseEntity<Void> handleNumberFormat() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
