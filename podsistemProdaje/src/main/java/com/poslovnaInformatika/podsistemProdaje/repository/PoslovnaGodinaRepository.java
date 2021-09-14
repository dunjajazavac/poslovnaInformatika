package com.poslovnaInformatika.podsistemProdaje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.PoslovnaGodina;

@Repository
public interface PoslovnaGodinaRepository extends JpaRepository<PoslovnaGodina, Long>{

<<<<<<< HEAD
	PoslovnaGodina findOneByGodina(Long idGodine);
=======
	
>>>>>>> branch 'milicaNovaGrana' of ssh://git@github.com/dunjajazavac/poslovnaInformatika.git

	PoslovnaGodina findByGodina(int godina);
	
	Page<PoslovnaGodina> findAllByGodina(int godina,Pageable page);



	

}
