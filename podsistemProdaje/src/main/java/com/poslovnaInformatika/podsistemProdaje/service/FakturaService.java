package com.poslovnaInformatika.podsistemProdaje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovnaInformatika.podsistemProdaje.intrfc.FakturaServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.Faktura;
import com.poslovnaInformatika.podsistemProdaje.repository.FakturaRepository;

@Transactional
@Service
public class FakturaService implements FakturaServiceInterface {

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


	@Override
	public Page<Faktura> findAll(int pageNo, int pageSize) {
		return fakturaRepo.findAll(PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<Faktura> findAllByBrojFakture(int brojFakture, int pageNo, int pageSize) {
		
		return fakturaRepo.findAllByBrojFakture(brojFakture, PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<Faktura> findAllByStatusFakture(String statusFakture, int pageNo, int pageSize) {
		return fakturaRepo.findAllByStatusFakture(statusFakture, PageRequest.of(pageNo, pageSize));
	}
	
}
