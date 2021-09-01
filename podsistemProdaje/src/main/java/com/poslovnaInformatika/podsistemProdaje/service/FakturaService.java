package com.poslovnaInformatika.podsistemProdaje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovnaInformatika.podsistemProdaje.model.Faktura;
import com.poslovnaInformatika.podsistemProdaje.repository.FakturaRepository;

@Transactional
@Service
public class FakturaService {

	@Autowired
	private FakturaRepository fakturaRepo;
	
	
	public Faktura findOne(Long id) {
		return fakturaRepo.findById(id).orElse(null);
	}
	
	public Faktura save(Faktura faktura) {
		return fakturaRepo.save(faktura);
	}
	
	public List<Faktura> findAll(){
		return fakturaRepo.findAll();
	}
	public Faktura findByBrojFakture(int brojFakture){
		return fakturaRepo.findByBrojFakture(brojFakture);
	}
	
	public void remove(Long id) {
		fakturaRepo.deleteById(id);
		
	}
	public Page<Faktura> findAll(Pageable page){
		return fakturaRepo.findAll(page);
	}
	public Page<Faktura> findAllByBrojFakture(int brojFakture,Pageable page){
		return fakturaRepo.findAllByBrojFakture(brojFakture, page);
	}
	public Page<Faktura> findAllByStatusFakture(String statusFakture,Pageable page){
		return fakturaRepo.findAllByStatusFakture(statusFakture, page);
	}
	
}
