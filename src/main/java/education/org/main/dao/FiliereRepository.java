package education.org.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import education.org.main.entities.Filiere;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
	Filiere findByTitre(String titre);
}
