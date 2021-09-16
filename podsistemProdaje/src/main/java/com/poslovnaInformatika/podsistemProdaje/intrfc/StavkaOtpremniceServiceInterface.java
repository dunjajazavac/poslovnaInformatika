package com.poslovnaInformatika.podsistemProdaje.intrfc;

import java.util.List;

import org.springframework.data.domain.Page;

import com.poslovnaInformatika.podsistemProdaje.model.StavkaOtpremnice;


public interface StavkaOtpremniceServiceInterface {

	StavkaOtpremnice findByRedniBrojProizvoda (int redniBrojProizvoda);
	
	StavkaOtpremnice save(StavkaOtpremnice stavkaOtpremnice);

	StavkaOtpremnice findOne(Long id);

	List<StavkaOtpremnice> findAll();
	
	void remove(Long id);
	
	Page<StavkaOtpremnice> findAll(int pageNo, int pageSize);
	
	Page<StavkaOtpremnice> findAllByRedniBrojProizvoda(int redniBrojProizvoda, int pageNo, int pageSize);
	
	Page<StavkaOtpremnice> findAllByCena (double cena, int pageNo, int pageSize);
}
