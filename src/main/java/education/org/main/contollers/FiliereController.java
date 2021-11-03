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

import education.org.main.entities.Filiere;
import education.org.main.services.FiliereService;

@RestController
@RequestMapping("api/filieres/")
@CrossOrigin("localhost:4200")
public class FiliereController {

	
	@Autowired
	private FiliereService FiliereService;

	@PostMapping("save")
	public  Filiere save(Filiere Filiere) {
		return FiliereService.save(Filiere);
	}
	 
	@GetMapping("findById")
	public Filiere findById(Long id) {
		return FiliereService.findById(id);
	}
	 
	@DeleteMapping("deleteById")
	public void deleteById(@PathParam("id") Long id) {
		FiliereService.deleteById(id);
		
	}
	@DeleteMapping("delete")
	public void delete(@RequestBody Filiere Filiere) {
		FiliereService.delete(Filiere);
		
	}
	@DeleteMapping("deleteAll")	 
	public void deleteAll() {
		FiliereService.deleteAll();			
	}
	 @GetMapping("findAll")
	public List<Filiere> findAll() {
		return FiliereService.findAll();
	}
	
	


}
