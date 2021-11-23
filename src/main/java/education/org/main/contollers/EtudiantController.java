package education.org.main.contollers;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import education.org.main.entities.Etudiant;
import education.org.main.entities.Role;
import education.org.main.services.EtudiantService;
import lombok.extern.slf4j.Slf4j;
 
@Slf4j
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
	
	@PostMapping("refresh/token")
	public  void refresh(HttpServletRequest request, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {}
	 
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
	public List<Etudiant> findAll(){
		return etudiantService.findAll();
	}
}
 