package com.poslovnaInformatika.podsistemProdaje.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovnaInformatika.podsistemProdaje.model.NaseljenoMesto;
import com.poslovnaInformatika.podsistemProdaje.repository.MestoRepository;
@Transactional
@Service
public class MestoService {
	@Autowired
	private MestoRepository mestoRepo;

	public NaseljenoMesto findByNazivMesto(String nazivMesto) {
		return mestoRepo.findByNazivMesto(nazivMesto);
	}

	public NaseljenoMesto save(NaseljenoMesto mesto) {
		return mestoRepo.save(mesto);
	}

	public NaseljenoMesto findOne(Long id) {
		return mestoRepo.getOne(id);
	}

	public void remove(Long id) {
		mestoRepo.deleteById(id);

	}

	public List<NaseljenoMesto> findAll() {
		return mestoRepo.findAll();
	}

	public Page<NaseljenoMesto> findAllByNazivMesto(String nazivMesto, Pageable page) {
		return mestoRepo.findAllByNazivMesto(nazivMesto, page);
	}

}
