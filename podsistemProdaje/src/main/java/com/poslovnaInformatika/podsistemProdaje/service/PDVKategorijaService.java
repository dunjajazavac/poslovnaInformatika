package com.poslovnaInformatika.podsistemProdaje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovnaInformatika.podsistemProdaje.model.PDVKategorija;
import com.poslovnaInformatika.podsistemProdaje.repository.PDVKategorijaRepository;
import com.poslovnaInformatika.podsistemProdaje.util.PageRequest;

@Transactional
@Service
public class PDVKategorijaService {

	@Autowired
	PDVKategorijaRepository repo; 
	
	public PDVKategorija findOne(Long id) {
		return repo.getOne(id);
	}
	
	public List<PDVKategorija> findAll() {
		return repo.findAll();
	}
	
	public PDVKategorija save(PDVKategorija pdvKategorija) {
		return repo.save(pdvKategorija);
	}
	
	public void remove(Long id) {
		repo.deleteById(id);
	}
	
	public Page<PDVKategorija> findAll(int pageNo, int pageSize) {
		return repo.findAll(new PageRequest(pageNo, pageSize));
	}
}
