package education.org.main.contollers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import education.org.main.entities.Professeur;
import education.org.main.services.ProfesseurService;


@RestController
@RequestMapping("api/Professeurs/")
@CrossOrigin("localhost:4200")
public class ProfesseurController {

	@Autowired
	private ProfesseurService professeurService;

	@PostMapping("save")
	public  Professeur save(Professeur Professeur) {
		return professeurService.save(Professeur);
	}
	 
	@GetMapping("findById")
	public Professeur findById(Long id) {
		return professeurService.findById(id);
	}
	 
	@DeleteMapping("deleteById")
	public void deleteById(@PathParam("id") Long id) {
		professeurService.deleteById(id);
		
	}
	@DeleteMapping("delete")
	public void delete(@RequestBody Professeur Professeur) {
		professeurService.delete(Professeur);
		
	}
	@DeleteMapping("deleteAll")	 
	public void deleteAll() {
		professeurService.deleteAll();			
	}
	 @GetMapping("findAll")
	public List<Professeur> findAll() {
		return professeurService.findAll();
	}
	
	



	


}
