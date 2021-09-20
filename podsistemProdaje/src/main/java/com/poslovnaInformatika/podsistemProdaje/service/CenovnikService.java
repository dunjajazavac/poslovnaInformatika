package com.poslovnaInformatika.podsistemProdaje.service;

import java.awt.print.Pageable;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovnaInformatika.podsistemProdaje.intrfc.CenovnikServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.Cenovnik;
import com.poslovnaInformatika.podsistemProdaje.repository.CenovnikRepository;
@Transactional
@Service
public class CenovnikService implements CenovnikServiceInterface {
	@Autowired
	private CenovnikRepository cenovnikRepo;
	
	public Cenovnik findOne(Long id) {
		return cenovnikRepo.findById(id).orElse(null);
	}
	
	public List<Cenovnik> findAll() {
		return cenovnikRepo.findAll();
	}
	
	public Cenovnik save(Cenovnik cenovnik) {
		return cenovnikRepo.save(cenovnik);
		
	}
	
	
	
	public void remove(Long id) {
		cenovnikRepo.deleteById(id);
	}
	
	
	public Cenovnik findByDatumPocetkaVazenja(Date datumVazenja) {
		return cenovnikRepo.findByDatumPocetkaVazenja(datumVazenja);
	}
	
	

	@Override
	public Page<Cenovnik> findAll(int pageNo, int pageSize) {
		
		return cenovnikRepo.findAll(PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<Cenovnik> findAllByDatumPocetkaVazenja(Date datumPocetkaVazenja, int pageNo, int pageSize) {
		
		return cenovnikRepo.findAllByDatumPocetkaVazenja(datumPocetkaVazenja, PageRequest.of(pageNo, pageSize));
	} 
	

}
