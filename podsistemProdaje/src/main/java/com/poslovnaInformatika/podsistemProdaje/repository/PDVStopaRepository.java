package com.poslovnaInformatika.podsistemProdaje.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.PDVStopa;

@Repository
public interface PDVStopaRepository extends JpaRepository<PDVStopa, Long> {

	
	
	

}
