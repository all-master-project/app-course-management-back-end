package education.org.main.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import education.org.main.dao.PromotionRepository;
import education.org.main.entities.Etudiant;
import education.org.main.entities.Promotion;

@Service
public class PromotionService {

	private PromotionRepository PromotionRepo;
	
	public PromotionService(PromotionRepository PromotionRepo) {
		this.PromotionRepo = PromotionRepo;
	}

	public void addEtudiantToPromotion(Promotion promotion, Etudiant etudiant)
	{
		Promotion promo = PromotionRepo.findById(promotion.getId()).get();
		if(promo!=null) promo.getEtudiants().add(etudiant);
		etudiant.getPromotions().add(promo);
	}
	public void removeEtudiantToPromotion(Promotion promotion, Etudiant etudiant)
	{
		Promotion promo = PromotionRepo.findById(promotion.getId()).get();
		if(promo!=null) promo.getEtudiants().remove(etudiant);
		etudiant.getPromotions().remove(promo);
	}
	 
	public  Promotion save(Promotion Promotion) {
		return PromotionRepo.save(Promotion);
	}
	 
	public Promotion findById(Long id) {
		return PromotionRepo.findById(id).get();
	}
	 
	public void deleteById(Long id) {
		PromotionRepo.deleteById(id);
		
	}
	 
	public void delete(Promotion Promotion) {
		PromotionRepo.delete(Promotion);
		
	}
	 
	public void deleteAllById(Iterable<? extends Long> ids) {
		PromotionRepo.deleteAllById(ids);
	}
	 	 
	public void deleteAll() {
		PromotionRepo.deleteAll();			
	}
	 
	public List<Promotion> findAll() {
		
		return PromotionRepo.findAll();
	}

	 
	public List<Promotion> findAll(Sort sort) {
		return PromotionRepo.findAll();
	} 


}
