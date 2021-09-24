package com.poslovnaInformatika.podsistemProdaje.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovnaInformatika.podsistemProdaje.model.PoslovniPartner;
import com.poslovnaInformatika.podsistemProdaje.model.Preduzece;
import com.poslovnaInformatika.podsistemProdaje.repository.PreduzeceRepository;
@Transactional
@Service
public class PreduzeceService {
	@Autowired
	private PreduzeceRepository preduzeceRepo;
	

	public Preduzece findByNazivPreduzeca(String nazivPreduzeca) {
		return preduzeceRepo.findByNazivPreduzeca(nazivPreduzeca);
	}

	public Preduzece save(Preduzece preduzece) {
		return preduzeceRepo.save(preduzece);
	}

	public Preduzece findOne(Long id) {
		return preduzeceRepo.findById(id).orElse(null);
	}

	public void remove(Long id) {
		preduzeceRepo.deleteById(id);

	}

	public List<Preduzece> findAll( ) {
		return preduzeceRepo.findAll();
	}

	
	public Page<Preduzece> findAll(int pageNo, int pageSize) {
		return preduzeceRepo.findAll(PageRequest.of(pageNo, pageSize));
	}

	public Page<Preduzece> findAllByNazivPreduzeca(String nazivPreduzeca, int pageNo, int pageSize) {
		return preduzeceRepo.findAllByNazivPreduzeca(nazivPreduzeca,PageRequest.of(pageNo, pageSize));
	}
	public Page<Preduzece> findAllByAdresa(String adresa, int pageNo, int pageSize) {
		return preduzeceRepo.findAllByAdresa(adresa,PageRequest.of(pageNo, pageSize));
	}
}
