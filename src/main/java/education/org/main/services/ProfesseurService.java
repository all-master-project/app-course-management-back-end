package education.org.main.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import education.org.main.dao.ProfesseurRepository;
import education.org.main.entities.Professeur;
import education.org.main.entities.Utilisateur;

@Service
public class ProfesseurService {
	
	@Autowired ProfesseurRepository professeurRepository;
	
	@Autowired UtilisateurService utilisateurService;
	
	@Transactional
	public void addUserAssociationToEtudiant(Professeur prof,Utilisateur user)
	{
		Professeur professeur= professeurRepository.findById(prof.getId()).get();
		Utilisateur utilisateur = utilisateurService.findByUsername(user.getUsername());
		if(professeur != null && user != null) {
			professeur.setUser(utilisateur);
		}else {throw new UsernameNotFoundException(professeur.getNom() );}
	}
	
	@Transactional
	public void removeUserAssociationToEtudiant(Professeur prof,Utilisateur user)
	{
		Professeur professeur= professeurRepository.findById(prof.getId()).get();
		Utilisateur utilisateur = utilisateurService.findByUsername(user.getUsername());
		if(professeur != null && utilisateur != null) {
			professeur.setUser(null);
		}else {throw new UsernameNotFoundException(professeur.getNom() );}
	}

	 
	public  Professeur save(Professeur Professeur) {
		return professeurRepository.save(Professeur);
	}
	 
	public Professeur findById(Long id) {
		return professeurRepository.findById(id).get();
	}
	 
	public void deleteById(Long id) {
		professeurRepository.deleteById(id);
		
	}
	 
	public void delete(Professeur Professeur) {
		professeurRepository.delete(Professeur);
		
	}
	 
	public void deleteAllById(Iterable<? extends Long> ids) {
		professeurRepository.deleteAllById(ids);
	}
	 	 
	public void deleteAll() {
		professeurRepository.deleteAll();			
	}
	 
	public List<Professeur> findAll() {
		
		return professeurRepository.findAll();
	}

	 
	public List<Professeur> findAll(Sort sort) {
		return professeurRepository.findAll();
	} 

}
