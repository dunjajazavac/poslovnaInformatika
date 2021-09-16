package com.poslovnaInformatika.podsistemProdaje.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poslovnaInformatika.podsistemProdaje.model.Otpremnica;

@Repository
public interface OtpremnicaRepository extends JpaRepository<Otpremnica, Long> {
	

	Otpremnica findByKupac (String kupac);
	
	Otpremnica findByBrojOtpremnice (int brojOtpremnice);
	
	List<Otpremnica> findAll();

	Page<Otpremnica> findAllByBrojOtpremnice (int brojOtpremnice, Pageable pageable);
	
	Page<Otpremnica> findAllByKupac(String kupac, Pageable pageable);
	
	Page<Otpremnica> findAllByAdresaIsporuke (String adresaIsporuke, Pageable pageable);
	
	Page<Otpremnica> findAllByDatumIsporuke (Date datumIsporuke, Pageable pageable);
	
	Page<Otpremnica> findAllByPrevoznik (String prevoznik, Pageable pageable);



}
