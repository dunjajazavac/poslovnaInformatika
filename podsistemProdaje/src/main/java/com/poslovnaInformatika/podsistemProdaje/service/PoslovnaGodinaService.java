package com.poslovnaInformatika.podsistemProdaje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovnaInformatika.podsistemProdaje.model.PoslovnaGodina;
import com.poslovnaInformatika.podsistemProdaje.repository.PoslovnaGodinaRepository;
@Transactional
@Service
public class PoslovnaGodinaService {

	@Autowired
	PoslovnaGodinaRepository poslovnaGodinaRepo;
	
	public PoslovnaGodina findOne(Long id) {
		return poslovnaGodinaRepo.findOne(id);
	}
	
	public List<PoslovnaGodina> findAll() {
		return poslovnaGodinaRepo.findAll();
	}
	
	public PoslovnaGodina save(PoslovnaGodina poslovnaGodina) {
		return poslovnaGodinaRepo.save(poslovnaGodina);
	}
	public PoslovnaGodina findByGodina(int godina) {
		return poslovnaGodinaRepo.findByGodina(godina);
	}
	public Page<PoslovnaGodina> findAll(Pageable page) {
		return poslovnaGodinaRepo.findAll(page);
	}
	public void remove(Long id) {
		 poslovnaGodinaRepo.delete(id);
	}
	
	public Page<PoslovnaGodina> findAllByGodina(int godina,Pageable page){
		return poslovnaGodinaRepo.findAllByGodina(godina, page);
	}
}
