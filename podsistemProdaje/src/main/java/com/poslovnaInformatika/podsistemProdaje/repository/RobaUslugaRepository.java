package com.poslovnaInformatika.podsistemProdaje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.PDVKategorija;
import com.poslovnaInformatika.podsistemProdaje.model.RobaUsluga;



@Repository
public interface RobaUslugaRepository extends JpaRepository<RobaUsluga, Long> {

	RobaUsluga findByNazivRobeUsluge (String nazivRobeUsluge);	
	Page<RobaUsluga> findAll(Pageable pageable);
	Page<RobaUsluga> findByNazivRobeUsluge(String name, Pageable page);
	List<RobaUsluga> findByNazivRobeUsluge(String name, Sort sort);
	

}
