package com.poslovnaInformatika.podsistemProdaje.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovnaInformatika.podsistemProdaje.model.RobaUsluge;
import com.poslovnaInformatika.podsistemProdaje.repository.RobaUslugeRepository;

@Transactional
@Service
public class RobaUslugeService {

	@Autowired
	RobaUslugeRepository repo;
	
	public RobaUsluge findOne(Long id) {
		return repo.getOne(id);
	}
	
	public List<RobaUsluge> findAll() {
		return repo.findAll();
	}
	
	public RobaUsluge save(RobaUsluge robaUsluge) {
		return repo.save(robaUsluge);
	}
	
	public void remove(Long id) {
		repo.deleteById(id);
	}
}
