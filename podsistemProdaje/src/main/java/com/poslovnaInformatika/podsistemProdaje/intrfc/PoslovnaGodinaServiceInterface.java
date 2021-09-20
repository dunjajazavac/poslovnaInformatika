package com.poslovnaInformatika.podsistemProdaje.intrfc;

import org.springframework.data.domain.Page;

import com.poslovnaInformatika.podsistemProdaje.model.PoslovnaGodina;


public interface PoslovnaGodinaServiceInterface {
Page<PoslovnaGodina> findAll(int pageNo, int pageSize);
	
	Page<PoslovnaGodina> findAllByGodina(int godina, int pageNo, int pageSize);


}
