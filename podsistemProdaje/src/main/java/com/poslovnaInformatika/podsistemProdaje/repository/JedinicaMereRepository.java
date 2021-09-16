package com.poslovnaInformatika.podsistemProdaje.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.JedinicaMere;

@Repository
public interface JedinicaMereRepository extends JpaRepository<JedinicaMere, Long> {

	
}
