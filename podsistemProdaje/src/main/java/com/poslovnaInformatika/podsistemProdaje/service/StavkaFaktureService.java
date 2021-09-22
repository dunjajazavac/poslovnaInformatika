package com.poslovnaInformatika.podsistemProdaje.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.poslovnaInformatika.podsistemProdaje.intrfc.StavkaFaktureServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.StavkaFakture;
import com.poslovnaInformatika.podsistemProdaje.repository.StavkaFaktureRepository;



@Transactional
@Service
public class StavkaFaktureService implements StavkaFaktureServiceInterface {
	
	@Autowired
	StavkaFaktureRepository stavkaFakRepo;
	
	public StavkaFakture save(StavkaFakture stavkaFakture) {
		return stavkaFakRepo.save(stavkaFakture);
	}

	@Override
	public StavkaFakture findOne(Long id) {
		return stavkaFakRepo.findById(id).orElse(null);
	}

	@Override
	public List<StavkaFakture> findAll() {
		return stavkaFakRepo.findAll();
	}

	@Override
	public void remove(Long id) {
		stavkaFakRepo.deleteById(id);

	}

	@Override
	public StavkaFakture findByJedinicnaCena(double jedinicnaCena) {
		return stavkaFakRepo.findByJedinicnaCena(jedinicnaCena);
	}

	@Override
	public Page<StavkaFakture> findAll(int pageNo, int pageSize) {
		return stavkaFakRepo.findAll( PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<StavkaFakture> findAllByIznos(double iznos, int pageNo, int pageSize) {
		return stavkaFakRepo.findAllByIznos(iznos, PageRequest.of(pageNo, pageSize));
	}

	@Override
	public List<StavkaFakture> findByFaktura(Long idFakture) {
		return stavkaFakRepo.findByFaktura(idFakture);
	}

}
