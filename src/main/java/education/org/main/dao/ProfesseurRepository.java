package education.org.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import education.org.main.entities.Professeur;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {

}
