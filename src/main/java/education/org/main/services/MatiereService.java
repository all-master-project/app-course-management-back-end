package education.org.main.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import education.org.main.dao.MatiereRepository;
import education.org.main.entities.Matiere;


@Service
public class MatiereService {



	private MatiereRepository MatiereRepo;
	public MatiereService(MatiereRepository MatiereRepo) {
		this.MatiereRepo = MatiereRepo;
	}

	 
	public  Matiere save(Matiere Matiere) {
		return MatiereRepo.save(Matiere);
	}
	 
	public Matiere findById(Long id) {
		return MatiereRepo.findById(id).get();
	}
	 
	public void deleteById(Long id) {
		MatiereRepo.deleteById(id);
		
	}
	 
	public void delete(Matiere Matiere) {
		MatiereRepo.delete(Matiere);
		
	}
	 
	public void deleteAllById(Iterable<? extends Long> ids) {
		MatiereRepo.deleteAllById(ids);
	}
	 	 
	public void deleteAll() {
		MatiereRepo.deleteAll();			
	}
	 
	public List<Matiere> findAll() {
		
		return MatiereRepo.findAll();
	}

	 
	public List<Matiere> findAll(Sort sort) {
		return MatiereRepo.findAll();
	} 

}
