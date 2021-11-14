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

import education.org.main.entities.Role;
import education.org.main.entities.Filiere;
import education.org.main.services.RoleService;
import education.org.main.services.RoleService;

@RestController
@RequestMapping("api/roles/")
@CrossOrigin/*("localhost:4200")*/
public class RoleControler {
	
	@Autowired
	private RoleService roleService;

	@PostMapping("save")
	public  Role save(Role role) {
		return roleService.save(role);
	}
	 
	@GetMapping("findById")
	public Role findById(Long id) {
		return roleService.findById(id);
	}
	 
	@DeleteMapping("deleteById")
	public void deleteById(@PathParam("id") Long id) {
		roleService.deleteById(id);
		
	}
	@DeleteMapping("delete")
	public void delete(@RequestBody Role role) {
		roleService.delete(role);
		
	}
	@DeleteMapping("deleteAll")	 
	public void deleteAll() {
		roleService.deleteAll();			
	}
	 @GetMapping("findAll")
	public List<Role> findAll() {
		return roleService.findAll();
	}
	
	 
	
}
 