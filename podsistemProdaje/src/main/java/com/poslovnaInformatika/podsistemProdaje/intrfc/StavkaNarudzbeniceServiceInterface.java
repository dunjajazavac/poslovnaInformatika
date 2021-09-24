package com.poslovnaInformatika.podsistemProdaje.intrfc;

import org.springframework.data.domain.Page;

import com.poslovnaInformatika.podsistemProdaje.model.StavkaNarudzbenice;



public interface StavkaNarudzbeniceServiceInterface {
	Page<StavkaNarudzbenice> findAll(int pageNo, int pageSize);
	
	Page<StavkaNarudzbenice> findAllByCena (double cena, int pageNo, int pageSize);

}
