package com.poslovnaInformatika.podsistemProdaje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.PDVKategorija;

@Repository
public interface PDVKategorijaRepository extends JpaRepository<PDVKategorija, Long> {

	Page<PDVKategorija> findAll(Pageable pageable);
	Page<PDVKategorija> findByNazivKategorije(String name, Pageable page);
	List<PDVKategorija> findByNazivKategorije(String name, Sort sort);
	
	//ovde
	//Page<PDVKategorija> findAllByPageAndSize(int pageNo, int pageSize);

}
