package com.poslovnaInformatika.podsistemProdaje.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.Preduzece;


@Repository
public interface PreduzeceRepository extends JpaRepository<Preduzece, Long>{
	
	Preduzece findByNazivPreduzeca (String nazivPreduzeca);
	List<Preduzece> findAll();
	
	
	
	Page<Preduzece> findAllByNazivPreduzeca (String nazivPreduzeca, Pageable page);
	
	Page<Preduzece> findAllByAdresa (String adresa,Pageable page);

}
