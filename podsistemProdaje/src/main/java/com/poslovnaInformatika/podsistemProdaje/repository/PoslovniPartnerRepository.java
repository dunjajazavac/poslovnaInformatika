package com.poslovnaInformatika.podsistemProdaje.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.PoslovniPartner;

@Repository
public interface PoslovniPartnerRepository extends JpaRepository<PoslovniPartner, Long> {
	

	//List<PoslovniPartner> findByPreduzece_id(Long id);
	//Page<PoslovniPartner> findAll(int pageNo, int pageSize);
	PoslovniPartner findByNazivPoslovnogPartnera (String nazivPoslovnogPartnera);
	Page<PoslovniPartner> findAllByEmail (String email,Pageable page);
	

	Page<PoslovniPartner> findAllByAdresa (String adresa,Pageable page);
	Page<PoslovniPartner> findAllByVrstaPartnera (String vrstaPartnera, Pageable page);


	

}
