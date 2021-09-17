package com.poslovnaInformatika.podsistemProdaje.service;


import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poslovnaInformatika.podsistemProdaje.intrfc.PDVStopaServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.PDVStopa;
import com.poslovnaInformatika.podsistemProdaje.repository.PDVStopaRepository;


@Transactional
@Service
public class PDVStopaService implements PDVStopaServiceInterface {
	
	@Autowired
	PDVStopaRepository pdvStopaRepository;

	@Override
	public PDVStopa findByProcenat(double procenat) {
		return pdvStopaRepository.findByProcenat(procenat);
	}

	@Override
	public PDVStopa save(PDVStopa pdvStopa) {
		return pdvStopaRepository.save(pdvStopa);
	}

	@Override
	public PDVStopa findOne(Long id) {
		return pdvStopaRepository.getOne(id);
	}

	@Override
	public List<PDVStopa> findAll() {
		return pdvStopaRepository.findAll();
	}

	@Override
	public void remove(Long id) {
		 pdvStopaRepository.deleteById(id);
		
	}

	@Override
	public Page<PDVStopa> findAll(int pageNo, int pageSize) {
		return pdvStopaRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<PDVStopa> findAllByDatumVazenja(Date datumVazenja, int pageNo, int pageSize) {
		return pdvStopaRepository.findAllByDatumVazenja(datumVazenja, PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<PDVStopa> findAllByProcenat(double procenat, int pageNo, int pageSize) {
		return pdvStopaRepository.findAllByProcenat(procenat, PageRequest.of(pageNo, pageSize));

	}
	
	
	


}
