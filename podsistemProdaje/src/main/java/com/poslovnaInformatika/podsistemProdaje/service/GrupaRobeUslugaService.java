package com.poslovnaInformatika.podsistemProdaje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovnaInformatika.podsistemProdaje.model.GrupaRobeUsluga;
import com.poslovnaInformatika.podsistemProdaje.repository.GrupaRobeUslugaRepository;

@Service
public class GrupaRobeUslugaService {
	
	@Autowired
	GrupaRobeUslugaRepository grupaRobeUslugaRepository;
	
	public GrupaRobeUsluga findOne(Long id) {
		return grupaRobeUslugaRepository.getOne(id);
	}
	
	public List<GrupaRobeUsluga> findAll() {
		return grupaRobeUslugaRepository.findAll();
	}
	
	public GrupaRobeUsluga save(GrupaRobeUsluga grupaRobeUsluga) {
		return grupaRobeUslugaRepository.save(grupaRobeUsluga);
	}
	
	public void remove(Long id) {
		grupaRobeUslugaRepository.deleteById(id);
	}
	

}
