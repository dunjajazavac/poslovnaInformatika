package com.poslovnaInformatika.podsistemProdaje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.JedinicaMere;

@Repository
public interface JedinicaMereRepository extends JpaRepository<JedinicaMere, Long> {

	JedinicaMere findByNazivJediniceMere (String nazivJediniceMere);
	
	JedinicaMere findBySkraceniNaziv (String skraceniNazivJediniceMere);
	
	List<JedinicaMere> findAll();
	
	Page<JedinicaMere> findAllByNazivJediniceMere(String nazivJediniceMere, Pageable pageable);
	
	Page<JedinicaMere> findAllBySkraceniNaziv(String skraceniNazivJediniceMere, Pageable pageable);
}
