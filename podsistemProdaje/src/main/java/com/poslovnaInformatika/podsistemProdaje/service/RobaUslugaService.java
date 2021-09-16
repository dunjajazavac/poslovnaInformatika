package com.poslovnaInformatika.podsistemProdaje.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovnaInformatika.podsistemProdaje.model.RobaUsluga;
import com.poslovnaInformatika.podsistemProdaje.repository.RobaUslugaRepository;

@Transactional
@Service
public class RobaUslugaService {

	@Autowired
	RobaUslugaRepository repo;
	
	public RobaUsluga findOne(Long id) {
		return repo.getOne(id);
	}
	
	public List<RobaUsluga> findAll() {
		return repo.findAll();
	}
	
	public RobaUsluga save(RobaUsluga robaUsluge) {
		return repo.save(robaUsluge);
	}
	
	public void remove(Long id) {
		repo.deleteById(id);
	}
	
	public RobaUsluga findByNazivRobeUsluge(String nazivRobeUsluge) {
		return repo.findByNazivRobeUsluge(nazivRobeUsluge);
	}
}
