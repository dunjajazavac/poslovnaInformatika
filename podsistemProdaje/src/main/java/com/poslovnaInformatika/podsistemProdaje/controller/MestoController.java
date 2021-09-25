package com.poslovnaInformatika.podsistemProdaje.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.dto.MestoDTO;
import com.poslovnaInformatika.podsistemProdaje.model.NaseljenoMesto;
import com.poslovnaInformatika.podsistemProdaje.model.PoslovniPartner;
import com.poslovnaInformatika.podsistemProdaje.service.MestoService;



@CrossOrigin
@RestController
@RequestMapping(value = "api/mesto")
@ControllerAdvice
public class MestoController {
	
	@Autowired
	private MestoService mService;
	
	@GetMapping(path = "/all")
	public List<NaseljenoMesto> getAll() {
		return mService.findAll();
	}
	
	

	//elena zakomentarisala 
	@GetMapping(path = "/p")
    public ResponseEntity<List<NaseljenoMesto>> getAllNaseljenoMesto(
                        @RequestParam("pageNo") Integer pageNo, 
                        @RequestParam("pageSize") Integer pageSize) 
    {
       
		Page<NaseljenoMesto> mesta = mService.findAll(pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(mesta.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(mesta.getContent());
    }
	
	@GetMapping(path = "/searchByNaziv")
	private ResponseEntity<List<NaseljenoMesto>> searchByNaziv(@RequestParam("naziv") String nazivMesta,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) {

		Page<NaseljenoMesto> mesta = mService.findAllByNazivMesta(nazivMesta, pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(mesta.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(mesta.getContent());
		
	}
	
	@GetMapping(path = "/searchByPttBroj")
	private ResponseEntity<List<NaseljenoMesto>> searchByPttBroj(@RequestParam("ptt_broj") String pttBrojString,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) {

		int pttBroj = Integer.parseInt(pttBrojString);
		
		Page<NaseljenoMesto> mesta = mService.findAllByPttBroj(pttBroj, pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(mesta.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(mesta.getContent());
		
	}
	@PostMapping(path = "/dodajMesto")
	public ResponseEntity<Void> dodajMesto(@Validated @RequestParam("naziv_mesta") String nazivMesta, @RequestParam("ptt_broj") String pttBroj) {
		
		System.out.println("Naziv naseljenog mesta: " + nazivMesta);
		System.out.println("Ptt broj naseljnog mesta: " + pttBroj);
		
		if(nazivMesta == null || pttBroj == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		int pttBrojInt = Integer.parseInt(pttBroj);
			
		NaseljenoMesto mesto = new NaseljenoMesto();
		mesto.setNazivMesta(nazivMesta);
		mesto.setPttBroj(pttBrojInt);
		mService.save(mesto);
		
		System.out.println("Dodato je naseljeno mesto.");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@PostMapping(path = "/izmeniMesto", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ResponseEntity<Void> izmeniMesto(@RequestParam("id") long id, @RequestParam("naziv_mesta") String nazivMesta,
			@RequestParam("ptt_broj") String pttBroj) {
		
		NaseljenoMesto mesto = mService.findOne(id);
		
		int pttBrojInt = Integer.parseInt(pttBroj);
		
		if(mesto != null) {
			mesto.setIdMesta(id);
			mesto.setNazivMesta(nazivMesta);
			mesto.setPttBroj(pttBrojInt);
			mService.save(mesto);
			
			System.out.println("Izmenjeno je naseljeno mesto");
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(path = "/obrisiMesto/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMesto(@PathVariable Long id) {
		NaseljenoMesto m = mService.findOne(id);
		if (m != null) {
			mService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NumberFormatException.class)
	public ResponseEntity<Void> handleNumber() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	

}
