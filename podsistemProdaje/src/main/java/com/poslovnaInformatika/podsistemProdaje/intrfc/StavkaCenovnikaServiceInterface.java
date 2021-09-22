package com.poslovnaInformatika.podsistemProdaje.intrfc;

import java.util.List;

import org.springframework.data.domain.Page;

import com.poslovnaInformatika.podsistemProdaje.model.StavkaCenovnika;

public interface StavkaCenovnikaServiceInterface {
StavkaCenovnika findByCena (double cena);
	
	List<StavkaCenovnika> findAll();
	
	StavkaCenovnika save(StavkaCenovnika stavkaCenovnika);

	StavkaCenovnika findOne(Long id);
	
	void remove(Long id);
	
	Page<StavkaCenovnika> findAll(int pageNo, int pageSize);

	Page<StavkaCenovnika> findAllByCena (double cena, int pageNo, int pageSize);
}