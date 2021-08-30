package com.poslovnaInformatika.podsistemProdaje.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovnaInformatika.podsistemProdaje.model.Preduzece;



public interface PreduzeceRepository extends JpaRepository<Preduzece, Long>{
	
	Preduzece findOne(Long id);
	List<Preduzece> findAll();
	Page<Preduzece> findAll(Pageable page);

}
