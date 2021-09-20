package com.poslovnaInformatika.podsistemProdaje.intrfc;

import java.sql.Date;

import org.springframework.data.domain.Page;

import com.poslovnaInformatika.podsistemProdaje.model.Cenovnik;



public interface CenovnikServiceInterface {
	
	Page<Cenovnik> findAll(int pageNo, int pageSize);
	
	Page<Cenovnik> findAllByDatumPocetkaVazenja(Date datumPocetkaVazenja, int pageNo, int pageSize);


}
