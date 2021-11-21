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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EtudiantService implements UserDetailsService {
	
	private EtudiantRepository etudiantRepo;
	private PromotionRepository promotionRepository;
	private FiliereRepository filiereRepository;
	private PasswordEncoder passwordEncoder;
	
	public EtudiantService(EtudiantRepository etudiantRepo, PromotionRepository promotionRepository,
		   FiliereRepository filiereRepository, PasswordEncoder passwordEncoder) {
		super();
		this.etudiantRepo = etudiantRepo;
		this.promotionRepository = promotionRepository;
		this.filiereRepository = filiereRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	public void addRoleToEtudiant(Etudiant etudiant, Role role)
	{
		Etudiant etud= etudiantRepo.findByUsername(etudiant.getUsername());
		if(etud!=null)
			etud.getRoles().add(role);
		else {throw new UsernameNotFoundException(etudiant.getUsername() );}
	}
	
	@Transactional
	public void removeRoleToEtudiant(Etudiant etudiant, Role role)
	{
		Etudiant etud= etudiantRepo.findByUsername(etudiant.getUsername());
		if(etud!=null)
			etud.getRoles().remove(role);
		else {throw new UsernameNotFoundException(etudiant.getUsername() );}
	}
 
	@Transactional
	public void addPromotionToEtudiant(Etudiant etudiant,Promotion promotion)
	{
		Etudiant etud= etudiantRepo.findByUsername(etudiant.getUsername());
		Promotion promo= promotionRepository.findByTitre(promotion.getTitre());
		if(etud!=null && promo!=null) {
			etud.getPromotions().add(promo);
			promo.getEtudiants().add(etud);
		}else {throw new UsernameNotFoundException(etudiant.getUsername() );}
	}
	
	@Transactional
	public void removePromotionToEtudiant(Etudiant etudiant,Promotion promotion)
	{
		Etudiant etud= etudiantRepo.findByUsername(etudiant.getUsername());
		Promotion promo= promotionRepository.findByTitre(promotion.getTitre());
		if(etud!=null && promo!=null) {
			etud.getPromotions().remove(promo);
			promo.getEtudiants().remove(etud);
		}else {throw new UsernameNotFoundException(etudiant.getUsername() );}
	}
	
	@Transactional
	public void addFiliereToEtudiant(Etudiant etudiant,Filiere filiere)
	{
		Etudiant etud= etudiantRepo.findByUsername(etudiant.getUsername());
		Filiere fil = filiereRepository.findByTitre(filiere.getTitre());
		if(etud!=null && fil!=null) {
			etud.getFilieres().add(fil);
			fil.getEtudiants().add(etud);
		}else {throw new UsernameNotFoundException(etudiant.getUsername() );}
	}
	
	@Transactional
	public void removeFiliereToEtudiant(Etudiant etudiant,Filiere filiere)
	{
		Etudiant etud= etudiantRepo.findByUsername(etudiant.getUsername());
		Filiere fil = filiereRepository.findByTitre(filiere.getTitre());
		if(etud!=null && fil!=null) {
			etud.getFilieres().remove(fil);
			fil.getEtudiants().remove(etud);
		}else {throw new UsernameNotFoundException(etudiant.getUsername() );}
	} 

	public  Etudiant save(Etudiant etudiant) {
		etudiant.setPassword(passwordEncoder.encode(etudiant.getPassword()));
		return etudiantRepo.save(etudiant);
	}
	
	public Etudiant getEtudiant(String username) {
		return etudiantRepo.findByUsername(username);
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
	
	 

	 public void desaffecterProffesseurAMatiere(@RequestBody Etudiant etudiant, @RequestBody Role role)
		{
		 etudiant.getRoles().remove(role);
		}
 
	@Transactional 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Etudiant etudiant = etudiantRepo.findByUsername(username);
		
		if(etudiant == null) {
			log.info("User not found in database :{}",username);
			throw new UsernameNotFoundException("User not found in database");
		}else { 
			log.info("User found in database {} {} ...", etudiant.getUsername(), etudiant.getPassword());
			Collection<GrantedAuthority> authorities= new ArrayList<>();
			etudiant.getRoles().forEach(role->
			{ 
				authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			});
			return new User(etudiant.getUsername(), etudiant.getPassword(), authorities);
		}
	}
}