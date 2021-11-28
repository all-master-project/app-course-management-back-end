package education.org.main.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import education.org.main.dao.EtudiantRepository;
import education.org.main.dao.FiliereRepository;
import education.org.main.dao.PromotionRepository;
import education.org.main.dao.RoleRepository;
import education.org.main.entities.Etudiant;
import education.org.main.entities.Filiere;
import education.org.main.entities.Promotion;
import education.org.main.entities.Role;
import education.org.main.entities.Utilisateur;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EtudiantService {
	
	private EtudiantRepository etudiantRepo;
	private PromotionRepository promotionRepository;
	private FiliereRepository filiereRepository;
	private PasswordEncoder passwordEncoder;
	private UtilisateurService utilisateurService;
	
	public EtudiantService(EtudiantRepository etudiantRepo, PromotionRepository promotionRepository,
		   FiliereRepository filiereRepository, PasswordEncoder passwordEncoder, UtilisateurService utilisateurService) {
		super();
		this.etudiantRepo = etudiantRepo;
		this.promotionRepository = promotionRepository;
		this.filiereRepository = filiereRepository;
		this.passwordEncoder = passwordEncoder;
		this.utilisateurService = utilisateurService;
	}
	
	@Transactional
	public void addUserAssociationToEtudiant(Etudiant etudiant,Utilisateur user)
	{
		Etudiant etud= etudiantRepo.findById(etudiant.getEtudiant_id()).get();
		Utilisateur utilisateur = utilisateurService.findByUsername(user.getUsername());
		if(etud != null && user != null) {
			etud.setUser(utilisateur);
		}else {throw new UsernameNotFoundException(etudiant.getNom() );}
	}
	
	@Transactional
	public void removeUserAssociationToEtudiant(Etudiant etudiant,Utilisateur user)
	{
		Etudiant etud= etudiantRepo.findById(etudiant.getEtudiant_id()).get();
		Utilisateur utilisateur = utilisateurService.findByUsername(user.getUsername());
		if(etud != null && user != null) {
			etud.setUser(null);
		}else {throw new UsernameNotFoundException(etudiant.getNom() );}
	}
 
	@Transactional
	public void addPromotionToEtudiant(Etudiant etudiant,Promotion promotion)
	{
		Etudiant etud= etudiantRepo.findById(etudiant.getEtudiant_id()).get();
		Promotion promo= promotionRepository.findByTitre(promotion.getTitre());
		if(etud != null && promo != null) {
			etud.getPromotions().add(promo);
			promo.getEtudiants().add(etud);
		}else {throw new UsernameNotFoundException(etudiant.getNom() );}
	}
	
	@Transactional
	public void removePromotionToEtudiant(Etudiant etudiant,Promotion promotion)
	{
		Etudiant etud= etudiantRepo.findById(etudiant.getEtudiant_id()).get();
		Promotion promo= promotionRepository.findByTitre(promotion.getTitre());
		if(etud!=null && promo!=null) {
			etud.getPromotions().remove(promo);
			promo.getEtudiants().remove(etud);
		}else {throw new UsernameNotFoundException(etudiant.getNom()  );}
	}
	
	@Transactional
	public void addFiliereToEtudiant(Etudiant etudiant,Filiere filiere)
	{
		Etudiant etud= etudiantRepo.findById(etudiant.getEtudiant_id()).get();
		Filiere fil = filiereRepository.findByTitre(filiere.getTitre());
		if(etud!=null && fil!=null) {
			etud.getFilieres().add(fil);
			fil.getEtudiants().add(etud);
		}else {throw new UsernameNotFoundException(etudiant.getNom()  );}
	}
	
	@Transactional
	public void removeFiliereToEtudiant(Etudiant etudiant,Filiere filiere)
	{
		Etudiant etud= etudiantRepo.findById(etudiant.getEtudiant_id()).get();
		Filiere fil = filiereRepository.findByTitre(filiere.getTitre());
		if(etud!=null && fil!=null) {
			etud.getFilieres().remove(fil);
			fil.getEtudiants().remove(etud);
		}else {throw new UsernameNotFoundException(etudiant.getNom() );}
	} 

	public  Etudiant save(Etudiant etudiant) {
		return etudiantRepo.save(etudiant);
	}
	
	public Etudiant getEtudiant(String nom) {
		return etudiantRepo.findByNom(nom);
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