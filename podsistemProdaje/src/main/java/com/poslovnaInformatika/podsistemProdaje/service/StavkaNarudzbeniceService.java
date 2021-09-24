package com.poslovnaInformatika.podsistemProdaje.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.poslovnaInformatika.podsistemProdaje.intrfc.StavkaNarudzbeniceServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.StavkaNarudzbenice;
import com.poslovnaInformatika.podsistemProdaje.repository.StavkaNarudzbeniceRepository;




@Transactional
@Service
public class StavkaNarudzbeniceService implements StavkaNarudzbeniceServiceInterface {
	@Autowired
	private StavkaNarudzbeniceRepository stavkaNarudzbeniceRepository;
	
	public List<StavkaNarudzbenice> findAll() {
		return stavkaNarudzbeniceRepository.findAll();
	}

	
	public StavkaNarudzbenice save(StavkaNarudzbenice stavkaNarudzbenice) {
		return stavkaNarudzbeniceRepository.save(stavkaNarudzbenice);
	}

	
	public StavkaNarudzbenice findOne(Long id) {
		return stavkaNarudzbeniceRepository.findById(id).orElse(null);
	}

	
	public void remove(Long id) {
		stavkaNarudzbeniceRepository.deleteById(id);
	}

	@Override
	public Page<StavkaNarudzbenice> findAll(int pageNo, int pageSize) {
		
		return stavkaNarudzbeniceRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<StavkaNarudzbenice> findAllByCena(double cena, int pageNo, int pageSize) {
		
		return stavkaNarudzbeniceRepository.findAllByCena(cena, PageRequest.of(pageNo, pageSize));
	}
	

}
