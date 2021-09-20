package com.poslovnaInformatika.podsistemProdaje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovnaInformatika.podsistemProdaje.model.GrupaRobeUsluga;
import com.poslovnaInformatika.podsistemProdaje.model.RobaUsluga;

public interface GrupaRobeUslugaRepository extends JpaRepository<GrupaRobeUsluga, Long>{

	GrupaRobeUsluga findByNazivGrupe (String nazivRobeUsluge);	
	Page<GrupaRobeUsluga> findAll(Pageable pageable);
	Page<GrupaRobeUsluga> findByNazivGrupe(String name, Pageable page);
	List<GrupaRobeUsluga> findByNazivGrupe(String name, Sort sort);
}
