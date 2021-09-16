package com.poslovnaInformatika.podsistemProdaje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.StavkaOtpremnice;

@Repository
public interface StavkaOtpremniceRepository extends JpaRepository<StavkaOtpremnice, Long> {

	StavkaOtpremnice findByRedniBrojProizvoda (int redniBrojProizvoda);
	
	List<StavkaOtpremnice> findAll();
	
	Page<StavkaOtpremnice> findAllByRedniBrojProizvoda (int redniBrojProizvoda, Pageable pageable);
	
	Page<StavkaOtpremnice> findAllByCena (double cena, Pageable pageable);
}
