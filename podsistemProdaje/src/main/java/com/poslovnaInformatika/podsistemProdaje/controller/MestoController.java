package com.poslovnaInformatika.podsistemProdaje.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.poslovnaInformatika.podsistemProdaje.dto.MestoDTO;
import com.poslovnaInformatika.podsistemProdaje.model.NaseljenoMesto;
import com.poslovnaInformatika.podsistemProdaje.service.MestoService;



@CrossOrigin
@RestController
@RequestMapping(value = "api/mesto")
@ControllerAdvice
public class MestoController {
	
	@Autowired
	private MestoService mService;
	
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<MestoDTO>> getAll(){
			
			List<NaseljenoMesto> mesta = mService.findAll();
			List<MestoDTO> mDTO = new ArrayList<>();
			for(NaseljenoMesto m : mesta) {
				mDTO.add(new MestoDTO(m));
			}
			return new ResponseEntity<>(mDTO, HttpStatus.OK);
		
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
			@RequestParam Pageable page) {

		Page<NaseljenoMesto> mesta = mService.findAllByNazivMesta(nazivMesta, page);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(mesta.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(mesta.getContent());
		
	}
	@GetMapping(path = "/searchByPttBroj")
	private ResponseEntity<List<NaseljenoMesto>> searchByPttBroj(@RequestParam("ptt_broj") String pttBrojString,
			@RequestParam Pageable page) {

		int pttBroj = Integer.parseInt(pttBrojString);
		
		Page<NaseljenoMesto> mesta = mService.findAllByPttBroj(pttBroj, page);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(mesta.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(mesta.getContent());
		
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<MestoDTO> getMesto(@PathVariable Long id){
		NaseljenoMesto mesto = mService.findOne(id);
		
		if(mesto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new MestoDTO(mesto), HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<MestoDTO> saveMesto(@RequestBody MestoDTO mDTO){		
		NaseljenoMesto mesto = new NaseljenoMesto();
		mesto.setPttBroj(mDTO.getPttBroj());
		mesto.setNazivMesta(mDTO.getNazivMesta());
	
		mesto = mService.save(mesto);

		return new ResponseEntity<>(new MestoDTO(mesto), HttpStatus.CREATED);	
	}
	
	@PutMapping(value="/{mestoId}", consumes="application/json")
	public ResponseEntity<MestoDTO> updateMesto(@RequestBody MestoDTO mDTO, @PathVariable("mestoId") Long id){		
		
		NaseljenoMesto mesto = mService.findOne(id);
		
		if(mesto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		mesto.setPttBroj(mDTO.getPttBroj());
		mesto.setNazivMesta(mDTO.getNazivMesta());
		
		mesto = mService.save(mesto);
	
		return new ResponseEntity<>(new MestoDTO(mesto), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMesto(@PathVariable Long id){
		NaseljenoMesto mesto = mService.findOne(id);
		if (mesto != null){
			mService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	

}
