package com.poslovnaInformatika.podsistemProdaje.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.poslovnaInformatika.podsistemProdaje.intrfc.StavkaCenovnikaServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.StavkaCenovnika;
import com.poslovnaInformatika.podsistemProdaje.repository.StavkaCenovnikaRepository;


@Transactional
@Service
public class StavkaCenovnikaService implements StavkaCenovnikaServiceInterface{
	@Autowired
	StavkaCenovnikaRepository stavkaCenovnikaRepository;
	
	public StavkaCenovnika save(StavkaCenovnika stavkaCenovnika) {
		return stavkaCenovnikaRepository.save(stavkaCenovnika);
	}
	
	@Override
	public StavkaCenovnika findByCena(double cena) {
		return stavkaCenovnikaRepository.findByCena(cena);
	}

	@Override
	public List<StavkaCenovnika> findAll() {
		return stavkaCenovnikaRepository.findAll();
	}

	@Override
	public StavkaCenovnika findOne(Long id) {
		return stavkaCenovnikaRepository.findById(id).orElse(null);
	}

	@Override
	public void remove(Long id) {
		stavkaCenovnikaRepository.deleteById(id);
		
	}

	@Override
	public Page<StavkaCenovnika> findAll(int pageNo, int pageSize) {
		return stavkaCenovnikaRepository.findAll( PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<StavkaCenovnika> findAllByCena(double cena, int pageNo, int pageSize) {
		return stavkaCenovnikaRepository.findAllByCena(cena, PageRequest.of(pageNo, pageSize));
	}

}
