package education.org.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import education.org.main.entities.Etudiant;

@EnableJpaRepositories
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
		Etudiant findByNom(String nom);
}
