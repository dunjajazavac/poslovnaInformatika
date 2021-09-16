package com.poslovnaInformatika.podsistemProdaje.intrfc;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.poslovnaInformatika.podsistemProdaje.model.Otpremnica;



public interface OtpremnicaServiceInterface {
	
	Otpremnica findByKupac (String kupac);
	
	Otpremnica findByBrojOtpremnice (int brojOtpremnice);
	
	Otpremnica save(Otpremnica otpremnica);

	Otpremnica findOne(Long id);

	List<Otpremnica> findAll();
	
	void remove(Long id);
	
	Page<Otpremnica> findAll(int pageNo, int pageSize);

	Page<Otpremnica> findAllByBrojOtpremnice (int brojOtpremnice, int pageNo, int pageSize);
	
	Page<Otpremnica> findAllByKupac(String kupac, int pageNo, int pageSize);
	
	Page<Otpremnica> findAllByAdresaIsporuke (String adresaIsporuke, int pageNo, int pageSize);
	
	Page<Otpremnica> findAllByDatumIsporuke (Date datumIsporuke, int pageNo, int pageSize);
	
	Page<Otpremnica> findAllByPrevoznik (String prevoznik, int pageNo, int pageSize);

}
