package com.poslovnaInformatika.podsistemProdaje.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovnaInformatika.podsistemProdaje.intrfc.JedinicaMereServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.JedinicaMere;
import com.poslovnaInformatika.podsistemProdaje.repository.JedinicaMereRepository;


@Transactional
@Service
public class JedinicaMereService implements JedinicaMereServiceInterface {
	
	@Autowired
	private JedinicaMereRepository jedinicaMereRepository;

	@Override
	public JedinicaMere findByNazivJediniceMere(String nazivJediniceMere) {
		return jedinicaMereRepository.findByNazivJediniceMere(nazivJediniceMere);
	}

	@Override
	public JedinicaMere findBySkraceniNaziv(String skraceniNaziv) {
		return jedinicaMereRepository.findBySkraceniNaziv(skraceniNaziv);
	}

	@Override
	public JedinicaMere save(JedinicaMere jedinicaMere) {
		return jedinicaMereRepository.save(jedinicaMere);
	}

	@Override
	public JedinicaMere findOne(Long id) {
		return jedinicaMereRepository.getOne(id);
	}

	@Override
	public List<JedinicaMere> findAll() {
		return jedinicaMereRepository.findAll();
	}

	@Override
	public void remove(Long id) {
		jedinicaMereRepository.deleteById(id);
		
	}

	@Override
	public Page<JedinicaMere> findAll(Pageable pageable) {
		return jedinicaMereRepository.findAll(pageable);
	}

	@Override
	public Page<JedinicaMere> findPaginated(int pageNo, int pageSize) {
		return jedinicaMereRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<JedinicaMere> findAllByNazivJediniceMere(String nazivJediniceMere, int pageNo, int pageSize) {
		return jedinicaMereRepository.findAllByNazivJediniceMere(nazivJediniceMere,PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<JedinicaMere> findAll(int pageNo, int pageSize) {
		return jedinicaMereRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<JedinicaMere> findAllBySkraceniNaziv(String skraceniNaziv, int pageNo, int pageSize) {
		return jedinicaMereRepository.findAllBySkraceniNaziv(skraceniNaziv,PageRequest.of(pageNo, pageSize));

	}

	
}



