package com.poslovnaInformatika.podsistemProdaje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.RobaUsluge;

@Repository
public interface RobaUslugeRepository extends JpaRepository<RobaUsluge, Long> {

}
