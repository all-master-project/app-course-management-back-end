package education.org.main.contollers;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import education.org.main.dto.DisplayErrorDto;
import education.org.main.dto.JwtTokenDto;
import education.org.main.entities.Utilisateur;
import education.org.main.security.config.JwtProperties;
import education.org.main.security.config.LoginViewModel;
import education.org.main.security.config.UserPrincipal;
import education.org.main.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UtilisateurService utilisateurService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    @ResponseBody
    public JwtTokenDto loginService(HttpServletRequest request, HttpServletResponse response) {

        String token = null;
        LOGGER.info("Authentication operation => begin");
        try {
            // Grab credentials and map them to login viewmodel
            LoginViewModel credentials = null;

            credentials = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);

            // Create login token
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    credentials.getUsername(),
                    credentials.getPassword(),
                    new ArrayList<>());

            // Authenticate user
            LOGGER.info("Processing the Authentication request");
            Authentication auth = authenticationManager.authenticate(authenticationToken);


            UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
            Utilisateur user =  utilisateurService.findByUsername(principal.getUsername());
            LOGGER.info("generate JWT Token ...");


//		         if(!isCaptchaValid("6Ld3zxwaAAAAANhs9UFfNtLuLIW4YVj7vQWUt9d4", credentials.getCaptchaToken() )){
//		 			throw new AuthenticationException("USER_INVALID_CREDENTIALS", new Throwable());
//		 		}

            // Create JWT Token;
            token = JWT.create()
                    .withSubject(principal.getUsername())
                    .withClaim("role", user.getRoles())
                    .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                    .sign(HMAC512(JwtProperties.SECRET.getBytes()));


            // Add token in response
            response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX +  token);
            LOGGER.info(" JWT Token genereated :: " + token );

        } catch (BadCredentialsException e) {
            LOGGER.error(e.getMessage(), e);
            return new JwtTokenDto(null, new DisplayErrorDto("BadCredentials","Mot de passe ou Username est Incorrect") , false);

        } catch (NullPointerException e) {
            LOGGER.error(e.getMessage(), e);
            return new JwtTokenDto(null, new DisplayErrorDto("WrongUsername","Username saisi est Incorrect") , false);
        } catch (DisabledException e) {
            LOGGER.error(e.getMessage(), e);
            return new JwtTokenDto(null, new DisplayErrorDto("DisabledUser","Votre compte est BLOQUÉ"), false);
        }  catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JwtTokenDto(null, new DisplayErrorDto("Anonymous","Une erreur thechnique est survenu"), false);
        }

        LOGGER.info("Authentication operation => end");
        LOGGER.info("Done");
        return new JwtTokenDto(token, new DisplayErrorDto("SUCCESS","Vous ête authentifié avec succée"), true);

    }

}
