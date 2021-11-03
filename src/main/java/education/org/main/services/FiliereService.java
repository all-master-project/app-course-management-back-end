package education.org.main.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import education.org.main.dao.FiliereRepository;
import education.org.main.entities.Filiere;

@Service
public class FiliereService {
	
	private FiliereRepository FiliereRepo;
	public FiliereService(FiliereRepository FiliereRepo) {
		this.FiliereRepo = FiliereRepo;
	}

	 
	public  Filiere save(Filiere Filiere) {
		return FiliereRepo.save(Filiere);
	}
	 
	public Filiere findById(Long id) {
		return FiliereRepo.findById(id).get();
	}
	 
	public void deleteById(Long id) {
		FiliereRepo.deleteById(id);
		
	}
	 
	public void delete(Filiere Filiere) {
		FiliereRepo.delete(Filiere);
		
	}
	 
	public void deleteAllById(Iterable<? extends Long> ids) {
		FiliereRepo.deleteAllById(ids);
	}
	 	 
	public void deleteAll() {
		FiliereRepo.deleteAll();			
	}
	 
	public List<Filiere> findAll() {
		
		return FiliereRepo.findAll();
	}

	 
	public List<Filiere> findAll(Sort sort) {
		return FiliereRepo.findAll();
	} 
	
}
