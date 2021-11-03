package education.org.main.services;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import education.org.main.dao.EtudiantRepository;
import education.org.main.entities.Etudiant;
import education.org.main.entities.Filiere;

@Service
public class EtudiantService {
	
	private EtudiantRepository etudiantRepo;
	
	public EtudiantService(EtudiantRepository etudiantRepo) {
		this.etudiantRepo = etudiantRepo;
	}

	public  Etudiant save(Etudiant etudiant) {
		
		return etudiantRepo.save(etudiant);
	}
	 
	public Etudiant findById(Long id) {
		return etudiantRepo.findById(id).get();
	} 
	 
	public void deleteById(Long id) {
		etudiantRepo.deleteById(id);
		
	}
	 
	public void delete(Etudiant etudiant) {
		etudiantRepo.delete(etudiant);
		
	}
	 	 
	public void deleteAll() {
		etudiantRepo.deleteAll();			
	}
	 
	public List<Etudiant> findAll() {
		
		return etudiantRepo.findAll();
	}

}
