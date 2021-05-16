package com.poslovnaInformatika.podsistemProdaje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.Faktura;
@Repository
public interface FakturaRepository extends JpaRepository<Faktura, Long> {

}
