package com.poslovnaInformatika.podsistemProdaje.interfac;

import java.util.List;

import org.springframework.data.domain.Page;

import com.poslovnaInformatika.podsistemProdaje.model.PoslovnaGodina;


public interface PoslovnaGodinaInterface {
	
	PoslovnaGodina findByGodina (int godina);
	
	PoslovnaGodina save(PoslovnaGodina poslovnaGodina);

	PoslovnaGodina findOne(Long id);

	List<PoslovnaGodina> findAll();
	
	void remove(Long id);
	
	Page<PoslovnaGodina> findAll(int pageNo, int pageSize);
	
	Page<PoslovnaGodina> findAllByGodina(int godina, int pageNo, int pageSize);
	
}
