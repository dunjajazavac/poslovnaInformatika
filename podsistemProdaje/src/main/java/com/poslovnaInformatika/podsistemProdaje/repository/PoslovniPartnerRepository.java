//package com.poslovnaInformatika.podsistemProdaje.repository;
//
//import java.awt.print.Pageable;
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.poslovnaInformatika.podsistemProdaje.model.PoslovniPartner;
//
//public interface PoslovniPartnerRepository extends JpaRepository<PoslovniPartner, Long> {
//	
//	PoslovniPartner findOne(Long id);
//	List<PoslovniPartner> findByPreduzece_id(Long id);
//	Page<PoslovniPartner> findAll(Pageable page);
//
//}
