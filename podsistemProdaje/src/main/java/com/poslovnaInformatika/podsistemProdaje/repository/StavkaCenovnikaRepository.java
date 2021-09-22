package com.poslovnaInformatika.podsistemProdaje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.StavkaCenovnika;
@Repository
public interface StavkaCenovnikaRepository extends JpaRepository<StavkaCenovnika, Long> {

StavkaCenovnika findByCena (double cena);
	
	List<StavkaCenovnika> findAll();
	
	Page<StavkaCenovnika> findAllByCena (double cena, Pageable pageable);
}

