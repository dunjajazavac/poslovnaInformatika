//package com.poslovnaInformatika.podsistemProdaje.service;
//
//import java.awt.print.Pageable;
//
//import java.sql.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.poslovnaInformatika.podsistemProdaje.model.Cenovnik;
//import com.poslovnaInformatika.podsistemProdaje.repository.CenovnikRepository;
//@Transactional
//@Service
//public class CenovnikService {
//	@Autowired
//	private CenovnikRepository cenovnikRepo;
//	
//	public Cenovnik findOne(Long id) {
//		return cenovnikRepo.getOne(id);
//	}
//	
//	public List<Cenovnik> findAll() {
//		return cenovnikRepo.findAll();
//	}
//	
//	public Cenovnik save(Cenovnik cenovnik) {
//		return cenovnikRepo.save(cenovnik);
//		
//	}
//	
//	public void remove(Long id) {
//		cenovnikRepo.deleteById(id);
//	}
//	
//	
//	public Cenovnik findByDatumPocetkaVazenja(Date datumVazenja) {
//		return cenovnikRepo.findByDatumPocetkaVazenja(datumVazenja);
//	}
//	
//	public Page<Cenovnik> findAll(org.springframework.data.domain.Pageable page) {
//		return cenovnikRepo.findAll(page);
//	}
//	
//	public Page<Cenovnik> findAllByDatumPocetkaVazenja(Date datumPocetkaVazenja,org.springframework.data.domain.Pageable page) {
//		return cenovnikRepo.findAllByDatumPocetkaVazenja(datumPocetkaVazenja,page);
//	} 
//	
//
//}
