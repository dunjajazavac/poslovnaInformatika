package com.poslovnaInformatika.podsistemProdaje.interfac;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.poslovnaInformatika.podsistemProdaje.model.Cenovnik;



public interface CenovnikInterface {
	
	Cenovnik findByDatumPocetkaVazenja (Date datumVazenja);
	
	Cenovnik save(Cenovnik cenovnik);

	Cenovnik findOne(Long id);

	List<Cenovnik> findAll();
	
	void remove(Long id);
	
	Page<Cenovnik> findAll(int pageNo, int pageSize,Sort sort);
	
	Page<Cenovnik> findAllByDatumPocetkaVazenja(Date datumPocetkaVazenja, int pageNo, int pageSize);
	

}
