package com.poslovnaInformatika.podsistemProdaje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovnaInformatika.podsistemProdaje.intrfc.PoslovnaGodinaServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.PoslovnaGodina;
import com.poslovnaInformatika.podsistemProdaje.repository.PoslovnaGodinaRepository;
@Transactional
@Service
public class PoslovnaGodinaService implements PoslovnaGodinaServiceInterface{

	@Autowired
	PoslovnaGodinaRepository poslovnaGodinaRepository;
	
	public PoslovnaGodina findOne(Long id) {
		return poslovnaGodinaRepository.findById(id).orElse(null);
	}
	
	public List<PoslovnaGodina> findAll() {
		return poslovnaGodinaRepository.findAll();
	}
	
	public PoslovnaGodina save(PoslovnaGodina poslovnaGodina) {
		return poslovnaGodinaRepository.save(poslovnaGodina);
	}
	public PoslovnaGodina findByGodina(int godina) {
		return poslovnaGodinaRepository.findByGodina(godina);
	}
	
	public void remove(Long id) {
		poslovnaGodinaRepository.deleteById(id);
	}

	@Override
	public Page<PoslovnaGodina> findAll(int pageNo, int pageSize) {
		
		return poslovnaGodinaRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<PoslovnaGodina> findAllByGodina(int godina, int pageNo, int pageSize) {
		
		return poslovnaGodinaRepository.findAllByGodina(godina, PageRequest.of(pageNo, pageSize));
	}
	
	
	


}
