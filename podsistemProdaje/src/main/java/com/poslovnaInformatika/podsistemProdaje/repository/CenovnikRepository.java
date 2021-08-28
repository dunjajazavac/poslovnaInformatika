//package com.poslovnaInformatika.podsistemProdaje.repository;
//
//import java.sql.Date;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.poslovnaInformatika.podsistemProdaje.model.Cenovnik;
//@Repository
//public interface CenovnikRepository extends JpaRepository<Cenovnik,Long> {
//	Cenovnik findByDatumPocetkaVazenja (Date datumVazenja);
//	
//	Page<Cenovnik> findAll(Pageable page);
//	
//
//	Page<Cenovnik> findAllByDatumPocetkaVazenja(Date datumPocetkaVazenja, Pageable page);
//	
//}
