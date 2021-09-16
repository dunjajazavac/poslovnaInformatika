package com.poslovnaInformatika.podsistemProdaje.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poslovnaInformatika.podsistemProdaje.intrfc.OtpremnicaServiceInterface;
import com.poslovnaInformatika.podsistemProdaje.model.Otpremnica;
import com.poslovnaInformatika.podsistemProdaje.repository.OtpremnicaRepository;

@Transactional
@Service
public class OtpremnicaService implements OtpremnicaServiceInterface {

	@Autowired
	private OtpremnicaRepository otpremnicaRepository;
	@Override
	public Otpremnica findByKupac(String kupac) {
		return otpremnicaRepository.findByKupac(kupac);
	}

	@Override
	public Otpremnica findByBrojOtpremnice(int brojOtpremnice) {
		return otpremnicaRepository.findByBrojOtpremnice(brojOtpremnice);
	}

	@Override
	public Otpremnica save(Otpremnica otpremnica) {
		return otpremnicaRepository.save(otpremnica);
	}

	@Override
	public Otpremnica findOne(Long id) {
		return otpremnicaRepository.getOne(id);
	}

	@Override
	public List<Otpremnica> findAll() {
		return otpremnicaRepository.findAll();
	}

	@Override
	public void remove(Long id) {
		otpremnicaRepository.deleteById(id);
		
	}

	@Override
	public Page<Otpremnica> findAll(int pageNo, int pageSize) {
		return otpremnicaRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	@Override
	public Page<Otpremnica> findAllByBrojOtpremnice(int brojOtpremnice, int pageNo, int pageSize) {
		return otpremnicaRepository.findAllByBrojOtpremnice(brojOtpremnice, PageRequest.of(pageNo, pageSize));

	}

	@Override
	public Page<Otpremnica> findAllByKupac(String kupac, int pageNo, int pageSize) {
		return otpremnicaRepository.findAllByKupac(kupac, PageRequest.of(pageNo, pageSize));

	}

	@Override
	public Page<Otpremnica> findAllByAdresaIsporuke(String adresaIsporuke, int pageNo, int pageSize) {
		return otpremnicaRepository.findAllByAdresaIsporuke(adresaIsporuke, PageRequest.of(pageNo, pageSize));

	}

	@Override
	public Page<Otpremnica> findAllByDatumIsporuke(Date datumIsporuke, int pageNo, int pageSize) {
		return otpremnicaRepository.findAllByDatumIsporuke(datumIsporuke,PageRequest.of(pageNo, pageSize));

	}

	@Override
	public Page<Otpremnica> findAllByPrevoznik(String prevoznik, int pageNo, int pageSize) {
		return otpremnicaRepository.findAllByPrevoznik(prevoznik, PageRequest.of(pageNo, pageSize));

	}

}
