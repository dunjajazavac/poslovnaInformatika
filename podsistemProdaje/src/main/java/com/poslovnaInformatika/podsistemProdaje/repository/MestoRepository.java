package com.poslovnaInformatika.podsistemProdaje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.NaseljenoMesto;

@Repository
public interface MestoRepository extends JpaRepository<NaseljenoMesto, Long> {
	
	NaseljenoMesto findByNazivMesta (String nazivMesta);
		
	List<NaseljenoMesto> findAll();
	Page<NaseljenoMesto> findAll(Pageable pageable);	
Page<NaseljenoMesto> findAllByNazivMesta (String nazivMesta, Pageable page);
	
	Page<NaseljenoMesto> findAllByPttBroj (int pttBroj, Pageable page);
	
		
}
