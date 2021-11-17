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
	public  void refresh(HttpServletRequest request, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		String authorizationHeader = request.getHeader("AUTHORIZATION");
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer "))
		{
			try {
				String refresh_token= authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier jwtVerifier = JWT.require(algorithm).build();
				DecodedJWT decodeJWT= jwtVerifier.verify(refresh_token);
				String username = decodeJWT.getSubject();
				Etudiant etudiant= etudiantService.getEtudiant(username);
				
				String access_token = JWT.create()
						.withSubject(etudiant.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis()+10*6*100))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", 
								etudiant.getRoles().stream()
											.map(Role::getRoleName)
											.collect(Collectors.toList()))
						.sign(algorithm);

				Map<String, String> tokens= new HashMap<String, String>();
				tokens.put("access_token", access_token);
				tokens.put("refresh_token", refresh_token);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
			} catch (Exception exception) {
				log.info("Error logging in: {}", exception.getMessage());
				response.setHeader("error", exception.getMessage());
				response.setStatus(org.springframework.http.HttpStatus.FORBIDDEN.value());
				Map<String, String> error= new HashMap<String, String>();
				error.put("error", exception.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		}else {
			throw new RuntimeException("Refresh Token is missing...");
		}
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
	public List<Etudiant> findAll(){
		return etudiantService.findAll();
	}
}
 