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
import education.org.main.dao.EtudiantRepository;
import education.org.main.entities.Etudiant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EtudiantService implements UserDetailsService {
	
	private EtudiantRepository etudiantRepo;
	private PasswordEncoder passwordEncoder;
	
	public EtudiantService(EtudiantRepository etudiantRepo, PasswordEncoder passwordEncoder) {
		this.etudiantRepo = etudiantRepo;
		this.passwordEncoder=passwordEncoder;
	}

	public  Etudiant save(Etudiant etudiant) {
		etudiant.setPassword(passwordEncoder.encode(etudiant.getPassword()));
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
