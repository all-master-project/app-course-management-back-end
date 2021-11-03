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

import education.org.main.entities.Matiere;
import education.org.main.services.MatiereService;

@RestController
@RequestMapping("api/matieres/")
@CrossOrigin("localhost:4200")
public class MatiereController {

	@Autowired
	private MatiereService MatiereService;

	@PostMapping("save")
	public  Matiere save(Matiere Matiere) {
		return MatiereService.save(Matiere);
	}
	 
	@GetMapping("findById")
	public Matiere findById(Long id) {
		return MatiereService.findById(id);
	}
	 
	@DeleteMapping("deleteById")
	public void deleteById(@PathParam("id") Long id) {
		MatiereService.deleteById(id);
		
	}
	@DeleteMapping("delete")
	public void delete(@RequestBody Matiere Matiere) {
		MatiereService.delete(Matiere);
		
	}
	@DeleteMapping("deleteAll")	 
	public void deleteAll() {
		MatiereService.deleteAll();			
	}
	 @GetMapping("findAll")
	public List<Matiere> findAll() {
		return MatiereService.findAll();
	}
	
	



	

}
