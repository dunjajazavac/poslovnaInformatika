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

import com.poslovnaInformatika.podsistemProdaje.dto.PreduzeceDTO;
import com.poslovnaInformatika.podsistemProdaje.model.NaseljenoMesto;
import com.poslovnaInformatika.podsistemProdaje.model.Preduzece;
import com.poslovnaInformatika.podsistemProdaje.service.MestoService;
import com.poslovnaInformatika.podsistemProdaje.service.PreduzeceService;

@CrossOrigin
@RestController
@RequestMapping(value = "api/preduzece")
@ControllerAdvice
public class PreduzeceController {

	@Autowired
	private PreduzeceService pService;
	@Autowired
	private MestoService mService;

	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<PreduzeceDTO>> getAll() {

		List<Preduzece> preduzeca = pService.findAll();
		List<PreduzeceDTO> pDTO = new ArrayList<>();
		for (Preduzece p : preduzeca) {
			pDTO.add(new PreduzeceDTO(p));
		}
		return new ResponseEntity<>(pDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PreduzeceDTO> getPreduzece(@PathVariable Long id) {
		Preduzece p = pService.findOne(id);

		if (p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new PreduzeceDTO(p), HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json", value = "/{idMesta}")
	public ResponseEntity<PreduzeceDTO> savePreduzece(@RequestBody PreduzeceDTO pDTO,
			@PathVariable("idMesta") Long id) {

		NaseljenoMesto mesto = mService.findOne(id);

		if (mesto == null) {
			return new ResponseEntity<PreduzeceDTO>(HttpStatus.NOT_FOUND);
		}
		Preduzece p = new Preduzece();
		p.setNazivPreduzeca(pDTO.getNazivPreduzeca());
		p.setAdresa(pDTO.getAdresa());
		p.setBrojTelefona(pDTO.getBrojTelefona());
		p.setFax(pDTO.getFax());
		p.setNaseljenoMesto(mesto);

		p = pService.save(p);

		return new ResponseEntity<>(new PreduzeceDTO(p), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{preduzeceId}", consumes = "application/json")
	public ResponseEntity<PreduzeceDTO> updatePreduzece(@RequestBody PreduzeceDTO pDTO, @PathVariable("preduzeceId") Long id) {

		Preduzece p = pService.findOne(id);

		if (p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		p.setNazivPreduzeca(pDTO.getNazivPreduzeca());
		p.setAdresa(pDTO.getAdresa());
		p.setBrojTelefona(pDTO.getBrojTelefona());
		p.setFax(pDTO.getFax());
		
		p = pService.save(p);

		return new ResponseEntity<>(new PreduzeceDTO(p), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePreduzece(@PathVariable Long id) {
		Preduzece p = pService.findOne(id);
		if (p != null) {
			pService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
