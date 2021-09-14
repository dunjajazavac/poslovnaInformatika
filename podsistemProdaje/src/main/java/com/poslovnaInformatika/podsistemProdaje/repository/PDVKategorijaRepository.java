package com.poslovnaInformatika.podsistemProdaje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.PDVKategorija;

@Repository
public interface PDVKategorijaRepository extends JpaRepository<PDVKategorija, Long> {

}
