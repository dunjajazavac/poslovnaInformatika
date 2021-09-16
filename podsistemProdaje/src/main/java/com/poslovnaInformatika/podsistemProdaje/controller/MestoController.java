package com.poslovnaInformatika.podsistemProdaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.model.NaseljenoMesto;
import com.poslovnaInformatika.podsistemProdaje.repository.MestoRepository;
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

	@GetMapping(path = "/searchByNaziv")
	private ResponseEntity<List<NaseljenoMesto>> searchByNaziv(@RequestParam("naziv") String nazivMesta,
			@RequestParam Pageable page) {

		Page<NaseljenoMesto> mesta = mService.findAllByNazivMesto(nazivMesta, page);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(mesta.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(mesta.getContent());
		
	}
	
	

}
