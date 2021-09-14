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
	PoslovnaGodinaRepository poslovnaGodinaRepository;
	
	public PoslovnaGodina findOne(Long idGodine) {
		return poslovnaGodinaRepository.findOneByGodina(idGodine);
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
	public Page<PoslovnaGodina> findAll(Pageable page) {
		return poslovnaGodinaRepository.findAll(page);
	}
	public void remove(Long id) {
		poslovnaGodinaRepository.deleteById(id);
	}
	
	public Page<PoslovnaGodina> findAllByGodina(int godina,Pageable page){
		return poslovnaGodinaRepository.findAllByGodina(godina, page);
	}
}
