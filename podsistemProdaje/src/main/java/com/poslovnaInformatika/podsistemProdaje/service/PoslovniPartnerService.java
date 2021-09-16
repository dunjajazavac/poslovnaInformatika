package com.poslovnaInformatika.podsistemProdaje.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.poslovnaInformatika.podsistemProdaje.model.PoslovniPartner;

import com.poslovnaInformatika.podsistemProdaje.repository.PoslovniPartnerRepository;
@Service
public class PoslovniPartnerService {
	@Autowired
	private PoslovniPartnerRepository partnerRepo;

	public PoslovniPartner save(PoslovniPartner partner) {
		return partnerRepo.save(partner);
	}

	public PoslovniPartner findOne(Long id) {
		return partnerRepo.findById(id).orElse(null);
	}

	public void remove(Long id) {
		partnerRepo.deleteById(id);

	}
//
//	public List<PoslovniPartner> findByPreduzece_id(Long PreduzeceId ) {
//		return partnerRepo.findByPreduzece_id(PreduzeceId);
//	}

//	public Page<PoslovniPartner> findAll(Pageable page) {
//		return partnerRepo.findAll(page);
//	}
//	
	public PoslovniPartner findByNazivPoslovnogPartnera(String nazivPoslovnogPartnera) {
		return partnerRepo.findByNazivPoslovnogPartnera(nazivPoslovnogPartnera);
	}
}
