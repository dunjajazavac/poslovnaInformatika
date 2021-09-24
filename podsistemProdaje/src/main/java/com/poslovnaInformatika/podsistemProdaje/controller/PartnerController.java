package com.poslovnaInformatika.podsistemProdaje.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovnaInformatika.podsistemProdaje.dto.PoslovniPartnerDTO;

import com.poslovnaInformatika.podsistemProdaje.model.NaseljenoMesto;

import com.poslovnaInformatika.podsistemProdaje.model.PoslovniPartner;
import com.poslovnaInformatika.podsistemProdaje.model.Preduzece;

import com.poslovnaInformatika.podsistemProdaje.service.MestoService;
import com.poslovnaInformatika.podsistemProdaje.service.PoslovniPartnerService;
import com.poslovnaInformatika.podsistemProdaje.service.PreduzeceService;
import com.sun.el.parser.ParseException;

@CrossOrigin
@RestController
@RequestMapping(value = "api/partner")
@ControllerAdvice
public class PartnerController {
	@Autowired
	private PoslovniPartnerService pService;
	@Autowired
	private MestoService mService;
	@Autowired
	private PreduzeceService predService;

	@GetMapping(path = "/p")
	public ResponseEntity<List<PoslovniPartner>> getAllPartneri(@RequestParam("pageNo") Integer pageNo,
			@RequestParam("pageSize") Integer pageSize) {

		Page<PoslovniPartner> partner = pService.findAll(pageNo, pageSize);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(partner.getTotalPages()));
		return ResponseEntity.ok().headers(headers).body(partner.getContent());
	}

	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<PoslovniPartnerDTO>> getAll() {

		List<PoslovniPartner> partneri = pService.findAll();
		List<PoslovniPartnerDTO> pDTO = new ArrayList<>();
		for (PoslovniPartner p : partneri) {
			pDTO.add(new PoslovniPartnerDTO(p));
		}
		return new ResponseEntity<>(pDTO, HttpStatus.OK);

	}
	
	@GetMapping(path = "/allMilica")
	public List<PoslovniPartner> getAllMilica(){
		return pService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PoslovniPartnerDTO> getPreduzece(@PathVariable Long id) {
		PoslovniPartner p = pService.findOne(id);

		if (p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new PoslovniPartnerDTO(p), HttpStatus.OK);
	}
	@GetMapping(path = "/searchByNaziv")
	private ResponseEntity<List<PoslovniPartner>> searchByNaziv(@RequestParam("naziv") String nazivPoslovnogPartnera,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) {

		Page<PoslovniPartner> partner = pService.findAllByNazivPoslovnogPartnera(nazivPoslovnogPartnera, pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(partner.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(partner.getContent());
		
	}
	
	@GetMapping(path = "/searchByAdresa")
	private ResponseEntity<List<PoslovniPartner>> searchByAdresa(@RequestParam("adresa") String adresa,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) {

		Page<PoslovniPartner> partner = pService.findAllByAdresa(adresa, pageNo, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(partner.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(partner.getContent());
		
	}
	
	@GetMapping(path = "/searchByEmail")
	private ResponseEntity<List<PoslovniPartner>> searchByEmail(@RequestParam("email") String email, 
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) {
		
		Page<PoslovniPartner> partner = pService.findAllByEmail(email, pageNo, pageSize);
		HttpHeaders headers = new HttpHeaders();
	    headers.set("total", String.valueOf(partner.getTotalPages()));
	    return ResponseEntity.ok().headers(headers).body(partner.getContent());
		
	}
	
	@GetMapping(path = "/searchByVrstaPartnera")
	private ResponseEntity<List<PoslovniPartner>> searchByVrstaPartnera(@RequestParam("vrsta") String vrstaPartnera,
			@RequestParam("pageNo") Integer pageNo, 
            @RequestParam("pageSize") Integer pageSize) {
		
		Page<PoslovniPartner> partner = pService.findAllByVrstaPartnera(vrstaPartnera, pageNo, pageSize);
		HttpHeaders headers = new HttpHeaders();
	    headers.set("total", String.valueOf(partner.getTotalPages()));
	    return ResponseEntity.ok().headers(headers).body(partner.getContent());
	}
	@PostMapping(path = "/dodajPoslovnogPartnera")
	public ResponseEntity<Void> dodajPoslovnogPartnera(
			@Validated @RequestParam("naziv_poslovnog_partnera") String nazivPoslovnogPartnera,
			@RequestParam("adresa") String adresa, @RequestParam("telefon") String telefon,
			@RequestParam("fax") String fax, @RequestParam("email") String email,
			@RequestParam("vrsta_partnera") String vrstaPartnera, @RequestParam("mesto") String nazivMesta,
			@RequestParam("preduzece") String nazivPreduzeca)throws ParseException  {

		NaseljenoMesto mesto = mService.findByNazivMesta(nazivMesta);
		Preduzece preduzece = predService.findByNazivPreduzeca(nazivPreduzeca);

		Preduzece preduzece2 = predService.findOne(preduzece.getIdPreduzeca());

		if (nazivPoslovnogPartnera == null || adresa == null || telefon == null || email == null
				|| vrstaPartnera == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		PoslovniPartner partner = new PoslovniPartner();
		partner.setNazivPoslovnogPartnera(nazivPoslovnogPartnera);
		partner.setAdresa(adresa);
		partner.setTelefon(telefon);
		partner.setFax(fax);
		partner.setEmail(email);
		partner.setVrstaPartnera(vrstaPartnera);
		partner.setNaseljenoMesto(mesto);
		partner.setPreduzece(preduzece2);
		pService.save(partner);

		System.out.println("Dodat je novi poslovni partner");

		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@PostMapping(path = "/izmeniPoslovnogPartnera", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ResponseEntity<Void> izmeniPoslovnogPartnera(@RequestParam("id") long id,
			@RequestParam("naziv_poslovnog_partnera") String nazivPoslovnogPartnera,
			@RequestParam("adresa") String adresa, @RequestParam("telefon") String telefon,
			@RequestParam("fax") String fax, @RequestParam("email") String email,
			@RequestParam("vrsta_partnera") String vrstaPartnera, @RequestParam("mesto") String nazivMesta,
			@RequestParam("preduzece") String nazivPreduzeca) {

		PoslovniPartner partner = pService.findOne(id);

		NaseljenoMesto mesto = mService.findByNazivMesta(nazivMesta);

		Preduzece preduzece = predService.findByNazivPreduzeca(nazivPreduzeca);

		if (partner != null) {
			partner.setIdPoslovnogPartnera(id);
			partner.setNazivPoslovnogPartnera(nazivPoslovnogPartnera);
			partner.setAdresa(adresa);
			partner.setTelefon(telefon);
			partner.setFax(fax);
			partner.setEmail(email);
			partner.setVrstaPartnera(vrstaPartnera);
			partner.setNaseljenoMesto(mesto);
			partner.setPreduzece(preduzece);
			pService.save(partner);

			System.out.println("Izmenjen je poslovni partner");

			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/obrisiPoslovnogPartnera/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePreduzece(@PathVariable Long id) {
		PoslovniPartner p = pService.findOne(id);
		if (p != null) {
			pService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
