package com.poslovnaInformatika.podsistemProdaje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.Faktura;
@Repository
public interface FakturaRepository extends JpaRepository<Faktura, Long> {

	
	Faktura findByBrojFakture(int brojFakture);
	Page<Faktura> findAllByBrojFakture (int brojFakture, Pageable pageable);
	
	Page<Faktura> findAllByStatusFakture (String statusFakture, Pageable pageable);
}
