package com.poslovnaInformatika.podsistemProdaje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.Faktura;
@Repository
public interface FakturaRepository extends JpaRepository<Faktura, Long> {

	Faktura findOne(Long id);
	List<Faktura> findByBrojFakture(int brojFakture);
	Page<Faktura> findAllByStatusFakture(String status,Pageable page);
	Page<Faktura> findAll(Pageable page);
	Page<Faktura> findAllByBrojFakture(int brojFakture, Pageable page);
}
