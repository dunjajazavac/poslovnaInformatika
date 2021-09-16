package com.poslovnaInformatika.podsistemProdaje.intrfc;

import java.util.List;

import org.springframework.data.domain.Page;

import com.poslovnaInformatika.podsistemProdaje.model.Narudzbenica;



public interface NarudzbenicaServiceInterface {

Narudzbenica findByBrojNarudzbenice (int brojNarudzbenice);
	
	Narudzbenica save(Narudzbenica narudzbenica);

	Narudzbenica findOne(Long id);
	
	List<Narudzbenica> findAll();

	void remove(Long id);
	
	Page<Narudzbenica> findAll(int pageNo, int pageSize);
	
	Page<Narudzbenica> findAllByBrojNarudzbenice(int brojNarudzbenice, int pageNo, int pageSize);

}
