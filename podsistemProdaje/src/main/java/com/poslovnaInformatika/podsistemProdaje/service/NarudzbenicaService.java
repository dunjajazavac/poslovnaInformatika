package com.poslovnaInformatika.podsistemProdaje.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poslovnaInformatika.podsistemProdaje.intrfc.NarudzbenicaServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.Narudzbenica;
import com.poslovnaInformatika.podsistemProdaje.repository.NarudzbenicaRepository;
@Transactional
@Service
public class NarudzbenicaService implements NarudzbenicaServiceInterface {

	@Autowired
	private NarudzbenicaRepository narudzbenicaRepository;
	@Override
	public Narudzbenica findByBrojNarudzbenice(int brojNarudzbenice) {
		return narudzbenicaRepository.findByBrojNarudzbenice(brojNarudzbenice);
	}

	@Override
	public Narudzbenica save(Narudzbenica narudzbenica) {
		return narudzbenicaRepository.save(narudzbenica);
	}

	@Override
	public Narudzbenica findOne(Long id) {
	
		return narudzbenicaRepository.findById(id).orElse(null);
	}

	@Override
	public List<Narudzbenica> findAll() {
		
		return narudzbenicaRepository.findAll();
	}

	@Override
	public void remove(Long id) {
		narudzbenicaRepository.deleteById(id);
		
	}

	@Override
	public Page<Narudzbenica> findAll(int pageNo, int pageSize) {
		
		return narudzbenicaRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<Narudzbenica> findAllByBrojNarudzbenice(int brojNarudzbenice, int pageNo, int pageSize) {
		return narudzbenicaRepository.findAllByBrojNarudzbenice(brojNarudzbenice, PageRequest.of(pageNo,pageSize, Sort.unsorted()));
	}

}
