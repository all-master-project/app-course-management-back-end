package education.org.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import education.org.main.entities.Matiere;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {

}
