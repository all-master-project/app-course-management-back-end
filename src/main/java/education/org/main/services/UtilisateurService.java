package education.org.main.services;

import education.org.main.dao.UtilisateurRepository;
import education.org.main.entities.Etudiant;
import education.org.main.entities.Role;
import education.org.main.entities.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UtilisateurService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurService.class);
    public static final String USER_DATA_INVALID = "USER DATA IS NOT VALID";

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
	public void addRoleToUser(Utilisateur user, Role role)
	{
    	Utilisateur utilisateur = utilisateurRepository.findByUsername(user.getUsername());
		if(utilisateur!=null)
			utilisateur.getRoles().add(role);
		else {throw new UsernameNotFoundException(utilisateur.getUsername() );}
	}
	
	@Transactional
	public void removeRoleToUser(Utilisateur user, Role role)
	{
		Utilisateur utlisateur= utilisateurRepository.findByUsername(user.getUsername());
		if(utlisateur!=null)
			utlisateur.getRoles().remove(role);
		else {throw new UsernameNotFoundException(utlisateur.getUsername() );}
	}
    
    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur save(Utilisateur user) {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        return utilisateurRepository.saveAndFlush(user);
    }

     
    public Utilisateur update(Utilisateur user) {
        return utilisateurRepository.saveAndFlush(user);
    }

     
    public Utilisateur getById(long idUser) {
        return utilisateurRepository.findById(idUser).get();
    }

     
    public void delete(long id) {
        utilisateurRepository.deleteById(id);
    }

    public Map<String, Object> addNewUser(Utilisateur user) {

        Map<String, Object> jsonMap = new HashMap<>();
        LOGGER.info("Saving the user to DB ...");
        save(user);
        jsonMap.put("succeeded", true);
        LOGGER.info("THE User has been added succefully.");
        return jsonMap;
    }

    public Map<String, Object> changeUserStatus(Utilisateur user) {

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Utilisateur utilisateur = findByUsername(user.getUsername());

        if (utilisateur == null) {
            jsonMap.put("succeeded", false);
            jsonMap.put("message", USER_DATA_INVALID);
            LOGGER.info("failed to change user status =>  invalid provided data ");
            return jsonMap;
        }
        LOGGER.info("Processing the request ... ");

        if (user.getActive() == 1) {
            utilisateur.setActive(0);
        }else {
            utilisateur.setActive(1);
        }
        save(utilisateur);
        jsonMap.put("success", true);
        LOGGER.info("The user status updated successfully");
        return jsonMap;
    }

     
    public Utilisateur findByUsername(String username) {
        return  utilisateurRepository.findByUsername(username);
    }


}
