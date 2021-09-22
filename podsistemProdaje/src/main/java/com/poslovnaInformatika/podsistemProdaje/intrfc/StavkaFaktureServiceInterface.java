package com.poslovnaInformatika.podsistemProdaje.intrfc;

import java.util.List;

import org.springframework.data.domain.Page;

import com.poslovnaInformatika.podsistemProdaje.model.StavkaFakture;


public interface StavkaFaktureServiceInterface {
StavkaFakture findByJedinicnaCena (double jedinicnaCena);
	
	StavkaFakture save(StavkaFakture stavkaFakture);

	StavkaFakture findOne(Long id);

	List<StavkaFakture> findAll();
	
	void remove(Long id);
	
	Page<StavkaFakture> findAll(int pageNo, int pageSize);

	Page<StavkaFakture> findAllByIznos (double iznos, int pageNo, int pageSize);
	
	List<StavkaFakture> findByFaktura(Long idFakture);

}
