package com.poslovnaInformatika.podsistemProdaje.intrfc;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poslovnaInformatika.podsistemProdaje.model.JedinicaMere;



public interface JedinicaMereServiceInterface {
	

	JedinicaMere findByNazivJediniceMere (String nazivJediniceMere);
	
	JedinicaMere findBySkraceniNaziv (String skraceniNaziv);

	JedinicaMere save(JedinicaMere jedinicaMere);

	JedinicaMere findOne(Long id);

	List<JedinicaMere> findAll();
	
	void remove(Long id);
	
	Page<JedinicaMere> findAll(Pageable pageable);
	
	Page<JedinicaMere> findPaginated(int pageNo, int pageSize);
	
	Page<JedinicaMere> findAllByNazivJediniceMere(String nazivJediniceMere, int pageNo, int pageSize);
	
	Page<JedinicaMere> findAll(int pageNo, int pageSize);
	
	Page<JedinicaMere> findAllBySkraceniNaziv(String skraceniNaziv, int pageNo, int pageSize);

}
