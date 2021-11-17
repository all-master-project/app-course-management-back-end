package education.org.main.contollers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import education.org.main.entities.Etudiant;
import education.org.main.entities.Filiere;
import education.org.main.entities.Lesson;
import education.org.main.entities.Matiere;
import education.org.main.entities.NiveauScolaire;
import education.org.main.entities.Professeur;
import education.org.main.entities.Promotion;
import education.org.main.entities.Role;

@RestController
@RequestMapping("api/inscrire/")
@CrossOrigin/*("localhost:4200")*/
public class Inscrire {
	
		//Inscrire un etudiant a une filiere
		 @PostMapping("inscrireEtudiantAMatiere")
		 public void inscrireEtudiantAMatiere(@RequestBody Etudiant etudiant, @RequestBody Filiere filiere)
			{
			 etudiant.getFilieres().add(filiere);
			 filiere.getEtudiants().add(etudiant);
			}
		//Desinscrire un etudiant a une filiere
		 @DeleteMapping("desinscrireEtudiantAMatiere")
		 public void desinscrireEtudiantAMatiere(@RequestBody Etudiant etudiant, @RequestBody Filiere filiere)
			{
				etudiant.getFilieres().remove(filiere);
				 filiere.getEtudiants().remove(etudiant);
			}
		 
		//Inscrire un etudiant a une Promotion
		 @PostMapping("inscrireEtudiantAPormotion")
		 public void inscrireEtudiantAPormotion(@RequestBody Etudiant etudiant, @RequestBody Promotion promotion)
			{
			 etudiant.getPromotions().add(promotion);
			 promotion.getEtudiants().add(etudiant);
			}
		//Desinscrire un etudiant a une Promotion
		 @DeleteMapping("desinscrireEtudiantAPormotion")
		 public void desinscrireEtudiantAPormotion(@RequestBody Etudiant etudiant, @RequestBody Promotion promotion)
			{
			 etudiant.getPromotions().remove(promotion);
			 promotion.getEtudiants().remove(etudiant);
			}
		 
		 //"Affecter une Filiere A un NiveauScolaire
		 @PostMapping("affecterFiliereAuNiveauScolaire")
		 public void addNiveauScolaire(@RequestBody Filiere filiere, @RequestBody NiveauScolaire niveauScolaire)
			{
			 	filiere.getNiveauScolaires().add(niveauScolaire);
				niveauScolaire.getFilieres().add(filiere);
			}
		
		//"Desafecter une Filiere A un NiveauScolaire
		 @DeleteMapping("desaffecterFiliereAuNiveauScolaire")
   		 public void removeNiveauScolaire(@RequestBody Filiere filiere, @RequestBody NiveauScolaire niveauScolaire)
			{
   			filiere.getNiveauScolaires().remove(niveauScolaire);
			niveauScolaire.getFilieres().remove(filiere);
			}
		 
		//associer des Lessons a une ou plusieure Matiere
		 @PostMapping("associerLessonAMatiere")
		 public void addLesson(@RequestBody Lesson lesson, @RequestBody Matiere matiere)
			{
			 lesson.getMatieres().add(matiere);
			 matiere.getLessons().add(lesson);
			}
		//desassocier des Lessons a une ou plusieure Matiere
		 @DeleteMapping("desassocierLessonAMatiere")
		 public void desassocierLessonAMatiere(@RequestBody Lesson lesson, @RequestBody Matiere matiere)
			{
			 lesson.getMatieres().remove(matiere);
			 matiere.getLessons().remove(lesson);
			}
		 
		//associer des Lessons a une ou plusieure Matiere
		 @PostMapping("affecterProffesseurAMatiere")
		 public void affecterProffesseurAMatiere(@RequestBody Professeur professeur, @RequestBody Matiere matiere)
			{
			 professeur.getMatieres().add(matiere);
			 matiere.getProfesseurs().add(professeur);
			}
		//desassocier des Lessons a une ou plusieure Matiere
		 @DeleteMapping("desaffecterProffesseurAMatiere")
		 public void desaffecterProffesseurAMatiere(@RequestBody Professeur professeur, @RequestBody Matiere matiere)
			{
			 professeur.getMatieres().remove(matiere);
			 matiere.getProfesseurs().remove(professeur);
			} 
		 
//		//Affecter Etudiant a un ou plusieure Role
//		 @PostMapping("affecterEtudiantAuRole")
//		 public void affecterProffesseurAMatiere(@RequestBody Etudiant etudiant, @RequestBody Role role)
//			{
//			 etudiant.getRoles().add(role);
//			}
//		//desaffecter Etudiant a un ou plusieure Role
//		 @DeleteMapping("desaffecterEtudiantAuRole")
//		 public void desaffecterProffesseurAMatiere(@RequestBody Etudiant etudiant, @RequestBody Role role)
//			{
//			 etudiant.getRoles().remove(role);
//			}
}
