package com.poslovnaInformatika.podsistemProdaje.intrfc;

import org.springframework.data.domain.Page;

import com.poslovnaInformatika.podsistemProdaje.model.Faktura;



public interface FakturaServiceInterface {
	Page<Faktura> findAll(int pageNo, int pageSize);
	
	Page<Faktura> findAllByBrojFakture (int brojFakture, int pageNo, int pageSize);
	
	Page<Faktura> findAllByStatusFakture (String statusFakture, int pageNo, int pageSize);


}
