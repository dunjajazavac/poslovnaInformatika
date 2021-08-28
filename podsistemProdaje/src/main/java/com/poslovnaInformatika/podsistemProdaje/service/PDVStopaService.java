//package com.poslovnaInformatika.podsistemProdaje.service;
//
//
//import java.sql.Date;
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//
//
//import com.poslovnaInformatika.podsistemProdaje.model.PDVStopa;
//import com.poslovnaInformatika.podsistemProdaje.repository.PDVStopaRepository;
//
//
//@Transactional
//@Service
//public class PDVStopaService {
//	
//	@Autowired
//	PDVStopaRepository pdvStopaRepository;
//	
//	
//	public PDVStopa findOne(Long id) {
//		return pdvStopaRepository.getOne(id);
//	}
//	
//
//	public PDVStopa findByProcenat(double procenat) {
//		return pdvStopaRepository.findByProcenat(procenat);
//	}
//
//	
//	public PDVStopa save(PDVStopa pdvStopa) {
//		return pdvStopaRepository.save(pdvStopa);
//	}
//	
//
//	public List<PDVStopa> findAll() {
//		return pdvStopaRepository.findAll();
//	}
//
//
//	public void remove(Long id) {
//		pdvStopaRepository.deleteById(id);
//
//	}
//
//
//	
//	public Page<PDVStopa> findAllByDatumVazenja(Date datumVazenja, Pageable pageable) {
//		return pdvStopaRepository.findAllByDatumVazenja(datumVazenja,pageable);
//	}
//
//
//	public Page<PDVStopa> findAllByProcenat(double procenat, Pageable pageable) {
//		return pdvStopaRepository.findAllByProcenat(procenat,pageable);
//	}
//
//
//
//}
