package com.poslovnaInformatika.podsistemProdaje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.StavkaFakture;
@Repository

public interface StavkaFaktureRepository extends JpaRepository<StavkaFakture, Long> {
	
	StavkaFakture findByJedinicnaCena (double jedinicnaCena);
	
	List<StavkaFakture> findAll();
	
	Page<StavkaFakture> findAllByIznos (double iznos, Pageable pageable);
	
	List<StavkaFakture> findByFaktura(Long idFakture);
}
