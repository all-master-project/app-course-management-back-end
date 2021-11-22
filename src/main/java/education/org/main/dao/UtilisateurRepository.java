package education.org.main.dao;

import education.org.main.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository  extends JpaRepository<Utilisateur, Long> {

    Utilisateur findByUsername(String username);

}

