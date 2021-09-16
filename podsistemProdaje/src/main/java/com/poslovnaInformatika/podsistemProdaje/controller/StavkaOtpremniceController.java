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

import com.poslovnaInformatika.podsistemProdaje.intrfc.StavkaOtpremniceServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.StavkaOtpremnice;

@CrossOrigin
@RestController
@RequestMapping(value = "api/stavkeotpremnice")
@ControllerAdvice
public class StavkaOtpremniceController {
	
	@Autowired
	private StavkaOtpremniceServiceInterface stavkaOtpremniceServicenterface;
	
	
	@GetMapping(path = "/all")
	public List<StavkaOtpremnice> getAll() {
		return stavkaOtpremniceServicenterface.findAll();
	}
	
	@GetMapping(path = "/p")
    public ResponseEntity<List<StavkaOtpremnice>> getAllStavkaOtpremnice(
                        @RequestParam("pageNo") Integer pageNo, 
                        @RequestParam("pageSize") Integer pageSize) 
    {
       
		Page<StavkaOtpremnice> stavkeOtpremnice = stavkaOtpremniceServicenterface.findAll(pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(stavkeOtpremnice.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(stavkeOtpremnice.getContent());
    }
	
	@GetMapping(value = "/searchByRedniBrojProizvoda")
	public ResponseEntity<List<StavkaOtpremnice>> searchByRedniBrojProizvoda(@RequestParam("redni") String redniBrojString,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) {
		
		int redniBrojProizvoda = Integer.parseInt(redniBrojString);
		
		Page<StavkaOtpremnice> stavkeOtpremnice = stavkaOtpremniceServicenterface.findAllByRedniBrojProizvoda(redniBrojProizvoda, pageNo, pageSize);
		HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(stavkeOtpremnice.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(stavkeOtpremnice.getContent());
	}
	
	
	@GetMapping(value = "/searchByCena")
	public ResponseEntity<List<StavkaOtpremnice>> searchByCena(@RequestParam("cena") String cenaString,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) {
		
		double cena = Double.parseDouble(cenaString);
		
		Page<StavkaOtpremnice> stavkeOtpremnice = stavkaOtpremniceServicenterface.findAllByCena(cena, pageNo, pageSize);
		HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(stavkeOtpremnice.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(stavkeOtpremnice.getContent());
	}
	
	@PostMapping(path = "/dodajStavkuOtpremnice")
	public ResponseEntity<Void> dodajStavkuOtpremnice(@RequestParam("redni_broj_proizvoda") String redniBrojProizvoda,
			@RequestParam("jedinica_mere") String nazivJediniceMere, @RequestParam("cena") String cena,
			@RequestParam("kolicina") String kolicina, @RequestParam("napomena") String napomena){
		
		System.out.println("Redni broj proizvoda: " + redniBrojProizvoda);
		System.out.println("Jedinica mere: " + nazivJediniceMere);
		System.out.println("Cena: " + cena);
		System.out.println("Kolicina: " + kolicina);
		System.out.println("Napomena: " + napomena);
		
		int redniBrojProizvodaInt = Integer.parseInt(redniBrojProizvoda);
		
		double cenaDouble = Double.parseDouble(cena);
		double kolicinaDouble = Double.parseDouble(kolicina);
		
		StavkaOtpremnice stavkaOtpremnice = new StavkaOtpremnice();
		stavkaOtpremnice.setRedniBrojProizvoda(redniBrojProizvodaInt);
		stavkaOtpremnice.setJedinicaMere(nazivJediniceMere);
		stavkaOtpremnice.setCena(cenaDouble);
		stavkaOtpremnice.setKolicina(kolicinaDouble);
		stavkaOtpremnice.setNapomena(napomena);
		stavkaOtpremniceServicenterface.save(stavkaOtpremnice);
		
		System.out.println("Dodata je stavka otpremnice.");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@PostMapping(path =  "/izmeniStavkuOtpremnice")
	public ResponseEntity<Void> izmeniStavkuOtpremnice(@RequestParam("redni_broj_proizvoda") String redniBrojProizvoda,
			@RequestParam("novi_broj") String noviBroj,
			@RequestParam("jedinica_mere") String nazivJediniceMere, @RequestParam("cena") String cena,
			@RequestParam("kolicina") String kolicina, @RequestParam("napomena") String napomena) {
			
		int redniBrojProizvodaInt = Integer.parseInt(redniBrojProizvoda);
		
		StavkaOtpremnice stavkaOtpremnice = stavkaOtpremniceServicenterface.findByRedniBrojProizvoda(redniBrojProizvodaInt);
		
		int noviBrojInt = Integer.parseInt(noviBroj);
		
		double cenaDouble = Double.parseDouble(cena);
		double kolicinaDouble = Double.parseDouble(kolicina);
		
		if(stavkaOtpremnice != null) {
			stavkaOtpremnice.setRedniBrojProizvoda(noviBrojInt);
			stavkaOtpremnice.setJedinicaMere(nazivJediniceMere);
			stavkaOtpremnice.setCena(cenaDouble);
			stavkaOtpremnice.setKolicina(kolicinaDouble);
			stavkaOtpremnice.setNapomena(napomena);
			stavkaOtpremniceServicenterface.save(stavkaOtpremnice);
			
			System.out.println("Izmenjena je stavka otpremnice.");
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping(path = "/obrisiStavkuOtpremnice/{id}")
	public ResponseEntity<Void> obrisiStavkuOtpremnice(@PathVariable("id") long id) {
		
		StavkaOtpremnice stavkaOtpremnice = stavkaOtpremniceServicenterface.findOne(id);
		
		if(stavkaOtpremnice == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		stavkaOtpremniceServicenterface.remove(stavkaOtpremnice.getIdStavkeOtpremnice());
		
		System.out.println("Obrisana je stavka otpremnice");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
