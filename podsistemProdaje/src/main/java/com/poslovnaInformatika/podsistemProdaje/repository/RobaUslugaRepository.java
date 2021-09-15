package com.poslovnaInformatika.podsistemProdaje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.RobaUsluga;



@Repository
public interface RobaUslugaRepository extends JpaRepository<RobaUsluga, Long> {

}
