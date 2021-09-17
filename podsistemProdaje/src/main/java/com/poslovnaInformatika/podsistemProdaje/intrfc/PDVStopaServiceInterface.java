package com.poslovnaInformatika.podsistemProdaje.intrfc;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.poslovnaInformatika.podsistemProdaje.model.PDVStopa;



public interface PDVStopaServiceInterface {
	
	PDVStopa findByProcenat(double procenat);

	PDVStopa save(PDVStopa pdvStopa);

	PDVStopa findOne(Long id);

	List<PDVStopa> findAll();
	
	void remove(Long id);

	Page<PDVStopa> findAll(int pageNo, int pageSize);
	
	Page<PDVStopa> findAllByDatumVazenja(Date datumVazenja, int pageNo, int pageSize);
	
	Page<PDVStopa> findAllByProcenat(double procenat, int pageNo, int pageSize);

}
