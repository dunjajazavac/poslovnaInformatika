package com.poslovnaInformatika.podsistemProdaje.interfac;

import java.util.List;

import org.springframework.data.domain.Page;

import com.poslovnaInformatika.podsistemProdaje.model.Faktura;


public interface FakturaInterface {
	
	Faktura findByBrojFakture (int brojFakture);
	
	Faktura save(Faktura faktura);

	Faktura findOne(Long id);

	List<Faktura> findAll();
	
	void remove(Long id);

	Page<Faktura> findAll(int pageNo, int pageSize);
	
	Page<Faktura> findAllByBrojFakture (int brojFakture, int pageNo, int pageSize);
	
	Page<Faktura> findAllByStatusFakture (String statusFakture, int pageNo, int pageSize);

}
