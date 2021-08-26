package com.poslovnaInformatika.podsistemProdaje.service;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovnaInformatika.podsistemProdaje.interfac.CenovnikInterface;
import com.poslovnaInformatika.podsistemProdaje.model.Cenovnik;
import com.poslovnaInformatika.podsistemProdaje.repository.CenovnikRepository;

@Transactional
@Service
public class CenovnikService implements CenovnikInterface{

	@Autowired
	CenovnikRepository cenovnikRepo;
	@Override
	public Cenovnik findByDatumPocetkaVazenja(Date datumVazenja) {
		
		return cenovnikRepo.findByDatumPocetkaVazenja(datumVazenja);
	}

	@Override
	public Cenovnik save(Cenovnik cenovnik) {
		
		return cenovnikRepo.save(cenovnik);
	}

	@Override
	public Cenovnik findOne(Long id) {
		
		return cenovnikRepo.findOne(id);
	}

	@Override
	public List<Cenovnik> findAll() {
		return cenovnikRepo.findAll();
	}

	@Override
	public void remove(Long id) {
		cenovnikRepo.deleteById(id);
	}

	@Override
	public Page<Cenovnik> findAll(int pageNo, int pageSize) {
		
		return cenovnikRepo.findAll(new PageRequest(pageNo,pageSize));
	}

	@Override
	public Page<Cenovnik> findAllByDatumPocetkaVazenja(Date datumPocetkaVazenja, int pageNo, int pageSize) {
		
		return cenovnikRepo.findAllByDatumPocetkaVazenja(datumPocetkaVazenja, new PageRequest(pageNo, pageSize));

	}

	@Override
	public Page<Cenovnik> findAll(int pageNo, int pageSize, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

		
}
