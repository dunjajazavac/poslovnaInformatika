package com.poslovnaInformatika.podsistemProdaje.service;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poslovnaInformatika.podsistemProdaje.model.JedinicaMere;
import com.poslovnaInformatika.podsistemProdaje.model.PoslovniPartner;
import com.poslovnaInformatika.podsistemProdaje.model.StavkaOtpremnice;
import com.poslovnaInformatika.podsistemProdaje.repository.PoslovniPartnerRepository;

@Transactional
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

//	public List<PoslovniPartner> findByPreduzece_id(Long PreduzeceId ) {
//		return partnerRepo.findByPreduzece_id(PreduzeceId);
//	}
	public List<PoslovniPartner> findAll( ) {
		return partnerRepo.findAll();
	}

	/*public Page<PoslovniPartner> findAll(Pageable page) {
		return partnerRepo.findAll(page);
	}*/
	public Page<PoslovniPartner> findAll(int pageNo, int pageSize) {
		return partnerRepo.findAll(PageRequest.of(pageNo, pageSize));
	}

	public PoslovniPartner findByNazivPoslovnogPartnera(String nazivPoslovnogPartnera) {
		return partnerRepo.findByNazivPoslovnogPartnera(nazivPoslovnogPartnera);
	}
	
	
	public Page<PoslovniPartner> findAllByEmail(String email, int pageNo, int pageSize) {
		return partnerRepo.findAllByEmail(email,PageRequest.of(pageNo, pageSize));
	}

	public Page<PoslovniPartner> findAllByAdresa(String adresa, int pageNo, int pageSize) {
		return partnerRepo.findAllByAdresa(adresa,PageRequest.of(pageNo, pageSize));
	}
	public Page<PoslovniPartner> findAllByVrstaPartnera(String vrstaPartnera, int pageNo, int pageSize) {
		return partnerRepo.findAllByVrstaPartnera(vrstaPartnera,PageRequest.of(pageNo, pageSize));
	}

	public Page<PoslovniPartner> findAllByNazivPoslovnogPartnera(String nazivPoslovnogPartnera, Integer pageNo,
			Integer pageSize) {
		return partnerRepo.findAllByNazivPoslovnogPartnera(nazivPoslovnogPartnera,PageRequest.of(pageNo, pageSize));
	}
}
