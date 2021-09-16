package com.poslovnaInformatika.podsistemProdaje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.Narudzbenica;

@Repository
public interface NarudzbenicaRepository extends JpaRepository<Narudzbenica, Long> {
	
	
Narudzbenica findByBrojNarudzbenice (int brojNarudzbenice);
	
	Page<Narudzbenica> findAllByBrojNarudzbenice(int brojNarudzbenice, Pageable pageable);

}
