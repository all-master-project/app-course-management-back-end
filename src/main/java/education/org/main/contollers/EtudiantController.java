package education.org.main.contollers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import education.org.main.entities.Etudiant;
import education.org.main.entities.Filiere;
import education.org.main.services.EtudiantService;

@RestController
@RequestMapping("api/etudiants/")
@CrossOrigin/*("localhost:4200")*/
public class EtudiantController {
	
	@Autowired
	private EtudiantService etudiantService;

	@PostMapping("save")
	public  Etudiant save(Etudiant etudiant) {
		return etudiantService.save(etudiant);
	}
	 
	@GetMapping("findById")
	public Etudiant findById(Long id) {
		return etudiantService.findById(id);
	}
	 
	@DeleteMapping("deleteById")
	public void deleteById(@PathParam("id") Long id) {
		etudiantService.deleteById(id);
		
	}
	@DeleteMapping("delete")
	public void delete(@RequestBody Etudiant etudiant) {
		etudiantService.delete(etudiant);
		
	}
	@DeleteMapping("deleteAll")	 
	public void deleteAll() {
		etudiantService.deleteAll();			
	}
	 @GetMapping("findAll")
	public List<Etudiant> findAll() {
		return etudiantService.findAll();
	}
	
	 
	
}
 