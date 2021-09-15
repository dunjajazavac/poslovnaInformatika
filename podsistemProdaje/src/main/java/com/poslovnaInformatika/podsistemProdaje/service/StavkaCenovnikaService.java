package com.poslovnaInformatika.podsistemProdaje.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovnaInformatika.podsistemProdaje.model.StavkaCenovnika;
import com.poslovnaInformatika.podsistemProdaje.repository.StavkaCenovnikaRepository;


@Transactional
@Service
public class StavkaCenovnikaService {
	@Autowired
	StavkaCenovnikaRepository stavkaCenovnikaRepository;
	
	public StavkaCenovnika save(StavkaCenovnika stavkaCenovnika) {
		return stavkaCenovnikaRepository.save(stavkaCenovnika);
	}

}
