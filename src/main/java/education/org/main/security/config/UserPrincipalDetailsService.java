package education.org.main.security.config;

import education.org.main.dao.UtilisateurRepository;
import education.org.main.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository userRepoitory;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Utilisateur user = userRepoitory.findByUsername(s);
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }

}