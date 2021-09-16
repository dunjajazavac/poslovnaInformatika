package com.poslovnaInformatika.podsistemProdaje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovnaInformatika.podsistemProdaje.intrfc.StavkaOtpremniceServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.StavkaOtpremnice;
import com.poslovnaInformatika.podsistemProdaje.repository.StavkaOtpremniceRepository;


@Transactional
@Service
public class StavkaOtpremniceService implements StavkaOtpremniceServiceInterface {
	
	@Autowired
	private StavkaOtpremniceRepository stavkaOtpremniceRepository;

	@Override
	public StavkaOtpremnice findByRedniBrojProizvoda(int redniBrojProizvoda) {
		return stavkaOtpremniceRepository.findByRedniBrojProizvoda(redniBrojProizvoda);
	}

	@Override
	public StavkaOtpremnice save(StavkaOtpremnice stavkaOtpremnice) {
		return stavkaOtpremniceRepository.save(stavkaOtpremnice);
	}

	@Override
	public StavkaOtpremnice findOne(Long id) {
		return stavkaOtpremniceRepository.getOne(id);
	}

	@Override
	public List<StavkaOtpremnice> findAll() {
		return stavkaOtpremniceRepository.findAll();
	}

	@Override
	public void remove(Long id) {
		stavkaOtpremniceRepository.deleteById(id);

	}

	@Override
	public Page<StavkaOtpremnice> findAll(int pageNo, int pageSize) {
		return stavkaOtpremniceRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<StavkaOtpremnice> findAllByRedniBrojProizvoda(int redniBrojProizvoda, int pageNo, int pageSize) {
		return stavkaOtpremniceRepository.findAllByRedniBrojProizvoda(redniBrojProizvoda, PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<StavkaOtpremnice> findAllByCena(double cena, int pageNo, int pageSize) {
		return stavkaOtpremniceRepository.findAllByCena(cena, PageRequest.of(pageNo, pageSize));
	}

}
