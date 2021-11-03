package education.org.main.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import education.org.main.dao.ProfesseurRepository;
import education.org.main.entities.Professeur;

@Service
public class ProfesseurService {
	


	private ProfesseurRepository ProfesseurRepo;
	public ProfesseurService(ProfesseurRepository ProfesseurRepo) {
		this.ProfesseurRepo = ProfesseurRepo;
	}

	 
	public  Professeur save(Professeur Professeur) {
		return ProfesseurRepo.save(Professeur);
	}
	 
	public Professeur findById(Long id) {
		return ProfesseurRepo.findById(id).get();
	}
	 
	public void deleteById(Long id) {
		ProfesseurRepo.deleteById(id);
		
	}
	 
	public void delete(Professeur Professeur) {
		ProfesseurRepo.delete(Professeur);
		
	}
	 
	public void deleteAllById(Iterable<? extends Long> ids) {
		ProfesseurRepo.deleteAllById(ids);
	}
	 	 
	public void deleteAll() {
		ProfesseurRepo.deleteAll();			
	}
	 
	public List<Professeur> findAll() {
		
		return ProfesseurRepo.findAll();
	}

	 
	public List<Professeur> findAll(Sort sort) {
		return ProfesseurRepo.findAll();
	} 

}
