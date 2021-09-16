package com.poslovnaInformatika.podsistemProdaje.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovnaInformatika.podsistemProdaje.model.JedinicaMere;
import com.poslovnaInformatika.podsistemProdaje.repository.JedinicaMereRepository;


@Transactional
@Service
public class JedinicaMereService {
	
	@Autowired
	private JedinicaMereRepository jedinicaMereRepo;
	

	public JedinicaMere findByNazivJediniceMere(String nazivJediniceMere) {
		return jedinicaMereRepo.findByNazivJediniceMere(nazivJediniceMere);
	}


	public JedinicaMere findBySkraceniNaziv(String skraceniNazivJediniceMere) {
		return jedinicaMereRepo.findBySkraceniNaziv(skraceniNazivJediniceMere);
	}


	public JedinicaMere save(JedinicaMere jedinicaMere) {
		return jedinicaMereRepo.save(jedinicaMere);
		
	}


	public JedinicaMere findOne(Long id) {
		return jedinicaMereRepo.getOne(id);
	}

	
	public List<JedinicaMere> findAll() {
		return jedinicaMereRepo.findAll();
	}


	public void remove(Long id) {
		jedinicaMereRepo.deleteById(id);

	}

	
	public Page<JedinicaMere> findAllByNazivJediniceMere(String nazivJediniceMere,Pageable page) {
		return jedinicaMereRepo.findAllByNazivJediniceMere(nazivJediniceMere,page);
	}

	
	public Page<JedinicaMere> findAll(Pageable page){
		return jedinicaMereRepo.findAll(page);
	}


	

	public Page<JedinicaMere> findAllBySkraceniNaziv(String skraceniNazivJediniceMere, Pageable pageable) {
		return jedinicaMereRepo.findAllBySkraceniNaziv(skraceniNazivJediniceMere, pageable);
	}

}



