package education.org.main.services;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import education.org.main.entities.Role;
import education.org.main.entities.Utilisateur;
import education.org.main.security.config.JwtProperties;
import education.org.main.security.config.LoginViewModel;
import education.org.main.security.config.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@Service
public class LoginService {

	@Autowired
    UtilisateurService utilisateurService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	public Authentication authenticate(HttpServletRequest request, HttpServletResponse response)
	{
		
        log.info("Authentication operation => begin");
        	String username= request.getParameter("username");
        	String password= request.getParameter("password");
            // Create login token
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            		username,
            		password,
                    new ArrayList<>());

            // Authenticate user
            log.info("Processing the Authentication request");
            Authentication auth = authenticationManager.authenticate(authenticationToken);
            return auth;
	}
	
	public Map<String, String> generateToken(Authentication auth)
	{
		  UserPrincipal user = (UserPrincipal) auth.getPrincipal();
        log.info("generate JWT Token ...");
        log.info("successfulAuthentication( username: {}",user.getUsername());
		log.info("successfulAuthentication( password: {}",user.getPassword());
		String access_token = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+10*6*1000))
				.withClaim("roles",
									user.getAuthorities().stream()
									.map(GrantedAuthority::getAuthority)
									.collect(Collectors.toList()))
				.sign(HMAC512(JwtProperties.SECRET));

		String refresh_token = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
				.sign(HMAC512(JwtProperties.SECRET));


		Map<String, String> tokens= new HashMap<String, String>();
		tokens.put("access_token", access_token);
		tokens.put("refresh_token", refresh_token);
        
        return tokens;
	}
	 
	public void jwtRefresher(HttpServletRequest request, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException
	{


		String authorizationHeader = request.getHeader("AUTHORIZATION");
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer "))
		{
			try {
				String refresh_token= authorizationHeader.substring("Bearer ".length());
				JWTVerifier jwtVerifier = JWT.require(HMAC512(JwtProperties.SECRET)).build();
				DecodedJWT decodeJWT= jwtVerifier.verify(refresh_token);
				String username = decodeJWT.getSubject();
				Utilisateur user= utilisateurService.findByUsername(username);
				
				String access_token = JWT.create()
						.withSubject(user.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis()+10*6*100))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", 
								user.getRoles().stream()
											.map(Role::getRoleName)
											.collect(Collectors.toList()))
						.sign(HMAC512(JwtProperties.SECRET));

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
	
}
