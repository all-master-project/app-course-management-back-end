package education.org.main.contollers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import education.org.main.dto.DisplayErrorDto;
import education.org.main.dto.JwtTokenDto;
import education.org.main.security.config.JwtProperties;
import education.org.main.services.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import education.org.main.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

   @Autowired
   private LoginService loginService;

    @Autowired
    UtilisateurService utilisateurService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    @ResponseBody
    public JwtTokenDto loginService(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, String> tokens=new HashMap<String, String>();
    	LOGGER.info("Authentication operation => begin");
        try {
        	Authentication auth= loginService.authenticate(request, response);
        	tokens = loginService.generateToken(auth);
            // Add token in response
            response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX +  tokens);
            LOGGER.info(" JWT Token genereated :: " + tokens );

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
        return new JwtTokenDto(tokens, new DisplayErrorDto("SUCCESS","Vous ête authentifié avec succée"), true);

    }
    
    @PostMapping("/refresh/token")
    public void jwtRefresher(HttpServletRequest request, HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException
    {
    	loginService.jwtRefresher(request, response);
    }
}
