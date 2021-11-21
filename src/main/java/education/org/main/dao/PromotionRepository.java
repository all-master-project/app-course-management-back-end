package education.org.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import education.org.main.entities.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
	Promotion findByTitre(String titre);
}
