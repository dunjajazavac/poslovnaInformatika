package com.poslovnaInformatika.podsistemProdaje.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovnaInformatika.podsistemProdaje.model.StavkaFakture;
import com.poslovnaInformatika.podsistemProdaje.repository.StavkaFaktureRepository;

@Transactional
@Service
public class StavkaFaktureService {
	
	@Autowired
	StavkaFaktureRepository stavkaFakRepo;
	
	public StavkaFakture save(StavkaFakture stavkaFakture) {
		return stavkaFakRepo.save(stavkaFakture);
	}

}
